package br.com.projetofinal_grupo1.projetofinal_grupo1.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.Parameter;
import br.com.projetofinal_grupo1.projetofinal_grupo1.dto.LoginRequestDTO;
import br.com.projetofinal_grupo1.projetofinal_grupo1.dto.RegisterRequestDTO;
import br.com.projetofinal_grupo1.projetofinal_grupo1.dto.TokenResponseDTO;
import br.com.projetofinal_grupo1.projetofinal_grupo1.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;

@SecurityRequirement(name = "BearerAuth")
@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @Operation(summary = "Registrar um novo usuário")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Usuário registrado com sucesso"),
            @ApiResponse(responseCode = "400", description = "Email já cadastrado")
    })
    @PostMapping("/register")
    public ResponseEntity<String> registrar(@RequestBody RegisterRequestDTO dto) {
        authService.registrar(dto);
        return ResponseEntity.ok("Usuário registrado com sucesso!");
    }

    @Operation(summary = "Fazer login do usuário e retornar token JWT")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Login realizado com sucesso"),
            @ApiResponse(responseCode = "401", description = "Credenciais inválidas")
    })
    @PostMapping("/login")
    public ResponseEntity<TokenResponseDTO> login(@RequestBody LoginRequestDTO dto) {
        TokenResponseDTO token = authService.login(dto);
        return ResponseEntity.ok(token);
    }

    @Operation(summary = "Solicitar recuperação de senha (envia link por e-mail)")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Link de recuperação enviado"),
            @ApiResponse(responseCode = "404", description = "E-mail não encontrado")
    })
    @PostMapping("/recuperar-senha")
    public ResponseEntity<String> recuperarSenha(
            @Parameter(description = "E-mail do usuário") @RequestParam String email) {
        authService.solicitarRecuperacaoSenha(email);
        return ResponseEntity.ok("Link de recuperação enviado para o e-mail.");
    }

    @Operation(summary = "Redefinir senha informando token recebido no e-mail")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Senha redefinida com sucesso"),
            @ApiResponse(responseCode = "400", description = "Token inválido ou expirado")
    })
    @PostMapping("/resetar-senha")
    public ResponseEntity<String> resetarSenha(
            @Parameter(description = "Token enviado por e-mail") @RequestParam String token,
            @Parameter(description = "Nova senha desejada") @RequestParam String novaSenha
    ) {
        authService.redefinirSenha(token, novaSenha);
        return ResponseEntity.ok("Senha redefinida com sucesso.");
    }
    @GetMapping("/test-auth")
    public ResponseEntity<?> testAuth(Authentication auth) {
        if (auth == null) {
            return ResponseEntity.status(401).body("Usuário não autenticado");
        }
        return ResponseEntity.ok("Usuário autenticado: " + auth.getName());
    }
}

