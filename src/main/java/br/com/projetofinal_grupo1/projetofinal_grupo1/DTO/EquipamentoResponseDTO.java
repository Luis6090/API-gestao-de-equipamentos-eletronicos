package br.com.projetofinal_grupo1.projetofinal_grupo1.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class EquipamentoResponseDTO {

    private int id;

    @JsonProperty("especificacoes")
    private String especificacoes;

    @JsonProperty("numeroSerie")
    private String numeroSerie;

    @JsonProperty("marca")
    private String marca;

    @JsonProperty("modelo")
    private String modelo;

    @JsonProperty("dataAquisicao")
    private LocalDate dataAquisicao;

    @JsonProperty("mesesDeUso")
    private int mesesDeUso;

    @JsonProperty("tipoEquipamento")
    private String tipoEquipamento; // Nome do tipo de equipamento
}