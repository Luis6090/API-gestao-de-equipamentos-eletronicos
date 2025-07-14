package br.com.projetofinal_grupo1.projetofinal_grupo1.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class ColaboradorUpdateDTO {
    @NotBlank
    private String nome;
    @NotBlank
    private String email;
    @NotBlank
    private String cep;
    @NotNull
    @PastOrPresent
    private LocalDate dataContratacaoInicio;
    @NotBlank
    private String equipamentoEspecificacao;
}
