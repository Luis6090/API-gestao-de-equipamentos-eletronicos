package br.com.projetofinal_grupo1.projetofinal_grupo1.domain.DTO;

import br.com.projetofinal_grupo1.projetofinal_grupo1.domain.model.Colaborador;
import lombok.Getter;

import java.time.LocalDate;
import java.util.List;

@Getter
public class ColaboradorResponseDTO {
    private String nome;
    private String email;
    private String cep;
    private LocalDate data_aniversario;
    private LocalDate data_contratacao_inicio;
    private LocalDate data_contratacao_recisao;
    private String especificacao_equipamento;

    public static ColaboradorResponseDTO converterParaColaboradorResponseDTO(Colaborador colaborador) {
    }
}
