package br.com.projetofinal_grupo1.projetofinal_grupo1.service;

import br.com.projetofinal_grupo1.projetofinal_grupo1.DTO.LoginRequestDTO;
import br.com.projetofinal_grupo1.projetofinal_grupo1.DTO.RegisterRequestDTO;
import br.com.projetofinal_grupo1.projetofinal_grupo1.DTO.TokenResponseDTO;
import br.com.projetofinal_grupo1.projetofinal_grupo1.model.Usuario;
import br.com.projetofinal_grupo1.projetofinal_grupo1.repository.UsuarioRepository;
import br.com.projetofinal_grupo1.projetofinal_grupo1.security.JwtUtil;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;


@Service
public class AuthService {


    private final EmailService emailService;
    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    public AuthService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil, EmailService emailService) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.emailService = emailService;
    }

    public void registrar(RegisterRequestDTO dto) {
        if (usuarioRepository.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email já cadastrado");
        }


        Usuario usuario = new Usuario();
        usuario.setEmail(dto.getEmail());
        usuario.setSenha(passwordEncoder.encode(dto.getSenha()));
        usuario.setEmailConfirmado(false); // ⚠️ Ainda não confirmado


        String token = UUID.randomUUID().toString();
        usuario.setTokenRecuperacao(token);
        usuario.setTokenRecuperacaoValidade(LocalDateTime.now().plusHours(2));


        usuarioRepository.save(usuario);


        String linkConfirmacao = "http://localhost:8080/usuarios/confirmar?token=" + token;


        emailService.enviarEmailSimples(
                usuario.getEmail(),
                "Confirme seu e-mail",
                "Clique no link para ativar sua conta: " + linkConfirmacao
        );

    }

    public TokenResponseDTO login(LoginRequestDTO dto) {
        Optional<Usuario> optional = usuarioRepository.findByEmail(dto.getEmail());

        if (optional.isEmpty()) {
            throw new RuntimeException("Email ou senha inválidos");
        }

        Usuario usuario = optional.get();

        if (!passwordEncoder.matches(dto.getSenha(), usuario.getSenha())) {
            throw new RuntimeException("Email ou senha inválidos");
        }

        String accessToken = jwtUtil.gerarToken(usuario);
        String refreshToken = jwtUtil.gerarRefreshToken(usuario);

        return new TokenResponseDTO(
                accessToken,
                refreshToken,
                jwtUtil.getExpiracao(accessToken).toString()
        );
    }

    public void solicitarRecuperacaoSenha(String email) {
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        String token = UUID.randomUUID().toString();
        LocalDateTime validade = LocalDateTime.now().plusHours(2);

        usuario.setTokenRecuperacao(token);
        usuario.setTokenRecuperacaoValidade(validade);
        usuarioRepository.save(usuario);

        String link = "http://localhost:8080/auth/resetar-senha?token=" + token;
        emailService.enviarEmailSimples(
                usuario.getEmail(),
                "Recuperação de senha",
                "Clique no link para redefinir sua senha: " + link
        );
    }
    public void redefinirSenha(String token, String novaSenha) {
        Usuario usuario = usuarioRepository.findByTokenRecuperacao(token)
                .orElseThrow(() -> new RuntimeException("Token inválido"));

        if (usuario.getTokenRecuperacaoValidade().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("Token expirado");
        }

        usuario.setSenha(passwordEncoder.encode(novaSenha));
        usuario.setTokenRecuperacao(null);
        usuario.setTokenRecuperacaoValidade(null);
        usuarioRepository.save(usuario);
    }
    public TokenResponseDTO refreshToken(String refreshHeader) {
        String refreshToken = refreshHeader.replace("Bearer ", "");

        if (!jwtUtil.validarToken(refreshToken)) {
            throw new RuntimeException("Refresh token inválido");
        }

        String email = jwtUtil.getEmailDoToken(refreshToken);
        Usuario usuario = usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

        String novoAccessToken = jwtUtil.gerarToken(usuario);

        return new TokenResponseDTO(
                novoAccessToken,
                refreshToken, // Retorna o mesmo refresh
                jwtUtil.getExpiracao(novoAccessToken).toString()
        );
    }
}
