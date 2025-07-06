package br.com.projetofinal_grupo1.projetofinal_grupo1.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Colaborador {
    private String cpf;
    private String nome;
    private String email;
    private LocalDate data_contratacao_inicio;
    private LocalDate data_contratacao_recisao;
    private String equipamento_especificacao;
}
