package br.com.projetofinal_grupo1.projetofinal_grupo1.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlterarSenhaDTO {
    @NotBlank
    private String email;

    @NotBlank
    private String senhaAtual;

    @NotBlank
    @Size(min = 6, message = "A nova senha deve ter no mínimo 6 caracteres.")
    private String novaSenha;

}
