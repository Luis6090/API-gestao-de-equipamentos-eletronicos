package br.com.projetofinal_grupo1.projetofinal_grupo1.domain.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    private LocalDate data_aniversario;
    @NotNull
    private LocalDate data_contratacao_inicio;
    @NotNull
    private LocalDate data_contratacao_recisao;
    @NotNull
    private String especificacao_equipamento;
}
