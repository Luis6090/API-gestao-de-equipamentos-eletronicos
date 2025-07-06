package br.com.projetofinal_grupo1.projetofinal_grupo1.domain.DTO;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ColaboradorRequestDTO {
    @CPF
    private String cpf;
    @NotBlank
    private String name;
    @NotBlank
    private String email;
    @NotBlank
    private String cep;
}
