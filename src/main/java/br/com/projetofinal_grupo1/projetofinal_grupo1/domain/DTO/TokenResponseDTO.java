package br.com.projetofinal_grupo1.projetofinal_grupo1.domain.DTO;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class TokenResponseDTO {
    private String accessToken;
    private String refreshToken;
    private String expiracao;
}