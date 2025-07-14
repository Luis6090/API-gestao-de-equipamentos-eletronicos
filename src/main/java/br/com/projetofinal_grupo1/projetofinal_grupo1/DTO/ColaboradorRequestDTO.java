package br.com.projetofinal_grupo1.projetofinal_grupo1.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.PastOrPresent;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ColaboradorRequestDTO {
    @CPF
    @NotNull
    private String cpf;
    @NotBlank
    private String nome;
    @NotBlank
    private String email;
    @NotBlank
    private String cep;
    @NotNull
    @Past
    private LocalDate data_aniversario;
    @NotNull
    @PastOrPresent
    private LocalDate data_contratacao_inicio;
    @NotNull
    @PastOrPresent
    private LocalDate data_contratacao_recisao;
    @NotBlank
    private String especificacao_equipamento;
}
