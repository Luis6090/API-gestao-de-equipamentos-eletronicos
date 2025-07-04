package br.com.projetofinal_grupo1.projetofinal_grupo1.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "equipamento")
@ToString
public class Equipamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    private String tipoEquipamento;

    public void setId(int id) {
        this.id = id;
    }

}
