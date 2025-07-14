package br.com.projetofinal_grupo1.projetofinal_grupo1.controller;


import br.com.projetofinal_grupo1.projetofinal_grupo1.model.Usuario;
import br.com.projetofinal_grupo1.projetofinal_grupo1.repository.UsuarioRepository;
import br.com.projetofinal_grupo1.projetofinal_grupo1.DTO.UsuarioCriacaoDTO;
import br.com.projetofinal_grupo1.projetofinal_grupo1.DTO.UsuarioAtualizacaoDTO;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;

@SecurityRequirement(name = "BearerAuth")
@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    public UsuarioController(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Operation(summary = "Listar todos os usuários")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de usuários retornada com sucesso")
    })
    @GetMapping
    public ResponseEntity<List<Usuario>> listarTodos() {
        return ResponseEntity.ok(usuarioRepository.findAll());
    }

    @Operation(summary = "Buscar um usuário pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário encontrado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Usuario> buscarPorId(
            @Parameter(description = "ID do usuário a ser buscado", required = true) @PathVariable int id) {
        return usuarioRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Criar um novo usuário")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Usuário criado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "409", description = "Email já cadastrado")
    })
    @PostMapping
    public ResponseEntity<String> criar(@Valid @RequestBody UsuarioCriacaoDTO usuarioCriacaoDTO) {
        if (usuarioRepository.existsByEmail(usuarioCriacaoDTO.getEmail())) {
            // Retorna 409 Conflict com uma mensagem no corpo da resposta
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Email já cadastrado.");
        }

        Usuario novoUsuario = new Usuario();
        novoUsuario.setEmail(usuarioCriacaoDTO.getEmail());
        novoUsuario.setSenha(passwordEncoder.encode(usuarioCriacaoDTO.getSenha()));
        novoUsuario.setEmailConfirmado(false);
        novoUsuario.setTokenRecuperacao(null);
        novoUsuario.setTokenRecuperacaoValidade(null);

        Usuario salvo = usuarioRepository.save(novoUsuario);
        // Retorna 201 Created com o usuário salvo (ou uma mensagem de sucesso)
        return ResponseEntity.status(HttpStatus.CREATED).body("Usuário criado com sucesso com ID: " + salvo.getId());
    }

    @Operation(summary = "Atualizar um usuário existente pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Usuário atualizado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Dados inválidos fornecidos"),
            @ApiResponse(responseCode = "404", description = "Usuário não encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Usuario> atualizar(
            @Parameter(description = "ID do usuário a ser atualizado", required = true) @PathVariable int id,
            @Valid @RequestBody UsuarioAtualizacaoDTO usuarioAtualizacaoDTO) {
        return usuarioRepository.findById(id)
                .map(usuarioExistente -> {
                    usuarioExistente.setEmail(usuarioAtualizacaoDTO.getEmail());

                    // Atualiza a senha apenas se uma nova for fornecida no DTO
                    if (usuarioAtualizacaoDTO.getSenha() != null && !usuarioAtualizacaoDTO.getSenha().isEmpty()) {
                        usuarioExistente.setSenha(passwordEncoder.encode(usuarioAtualizacaoDTO.getSenha()));
                    }

                    Usuario salvo = usuarioRepository.save(usuarioExistente);
                    return ResponseEntity.ok(salvo);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @Operation(summary = "Deletar um usuário pelo ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(
            @Parameter(description = "ID do usuário a ser deletado", required = true) @PathVariable int id) {
        return usuarioRepository.findById(id)
                .map(usuario -> {
                    usuarioRepository.delete(usuario);
                    return ResponseEntity.noContent().build();
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @Operation(summary = "Confirmar email")
    @GetMapping("/confirmar")
    public ResponseEntity<String> confirmarEmail(@RequestParam String token) {
        return usuarioRepository.findByTokenRecuperacao(token)
                .map(usuario -> {
                    if (usuario.getTokenRecuperacaoValidade().isBefore(LocalDateTime.now())) {
                        return ResponseEntity.badRequest().body("Token expirado.");
                    }

                    usuario.setEmailConfirmado(true);
                    usuario.setTokenRecuperacao(null);
                    usuario.setTokenRecuperacaoValidade(null);
                    usuarioRepository.save(usuario);

                    return ResponseEntity.ok("E-mail confirmado com sucesso!");
                })
                .orElse(ResponseEntity.badRequest().body("Token inválido."));
    }
}



