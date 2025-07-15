package br.com.projetofinal_grupo1.projetofinal_grupo1.dto;

import br.com.projetofinal_grupo1.projetofinal_grupo1.model.Colaborador;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class ColaboradorResponseDTO {
    private String nome;
    private String email;
    private String cep;
    private LocalDate data_aniversario;
    private LocalDate data_contratacao_inicio;
    private LocalDate data_contratacao_recisao;
    private String especificacao_equipamento;

    public static ColaboradorResponseDTO converterParaColaboradorResponseDTO(Colaborador colaborador) {
        return new ColaboradorResponseDTO(colaborador.getNome(), colaborador.getEmail(), colaborador.getCep(), colaborador.getData_nascimento(),
                                            colaborador.getData_contratacao_inicio(), colaborador.getData_contratacao_recisao(), colaborador.getEquipamento_especificacao());
    }
}
