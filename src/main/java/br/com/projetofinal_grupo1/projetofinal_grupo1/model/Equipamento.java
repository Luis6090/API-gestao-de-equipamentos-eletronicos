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
    @Column(name = "id_equipamento")
    private int id;

    public void setId(int id) {
        this.id = id;
    }

    @Column(name = "especificacoes")
    @JsonProperty("especificacoes")
    private String especificacoes;

    @Column(name = "numero_serie", unique = true, nullable = false)
    @JsonProperty("numeroSerie")
    private String numeroSerie;

    @Column(name = "marca")
    @JsonProperty("marca")
    private String marca;

    @Column(name = "modelo")
    @JsonProperty("modelo")
    private String modelo;

    @Column(name = "data_aquisicao")
    @JsonProperty("dataAquisicao")
    private LocalDate dataAquisicao;

    @Column(name = "tempo_uso")
    @JsonProperty("mesesDeUso")
    private int tempoUso;

    @ManyToOne
    @JoinColumn(name = "id_tipo_equipamento", nullable = false)
    private TipoEquipamento tipoEquipamento;

}
