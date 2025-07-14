package br.com.projetofinal_grupo1.projetofinal_grupo1.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class EquipamentoRequestDTO {

    @NotBlank(message = "Especificações são obrigatórias")
    @JsonProperty("especificacoes")
    private String especificacoes;

    @NotBlank(message = "Número de série é obrigatório")
    @JsonProperty("numeroSerie")
    private String numeroSerie;

    @NotBlank(message = "Marca é obrigatória")
    @JsonProperty("marca")
    private String marca;

    @NotBlank(message = "Modelo é obrigatório")
    @JsonProperty("modelo")
    private String modelo;

    @NotNull(message = "Data de aquisição é obrigatória")
    @PastOrPresent(message = "Data de aquisição não pode ser no futuro")
    @JsonProperty("dataAquisicao")
    private LocalDate dataAquisicao;

    @PositiveOrZero(message = "Meses de uso não pode ser negativo")
    @JsonProperty("mesesDeUso")
    private int mesesDeUso;

    @NotBlank(message = "Tipo de equipamento é obrigatório")
    @JsonProperty("tipoEquipamento")
    private String tipoEquipamento;
}