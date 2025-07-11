package br.com.projetofinal_grupo1.projetofinal_grupo1.model;

//Implementar registro de entregas
//Implementar registro de devoluções

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name ="emprestimo_devolucao")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EquipamentoColaborador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JsonProperty("idEquipamento")
    private int idEquipamento;

    @JsonProperty("idColaborador")
    private int idColaborador;

    @JsonProperty("dataEntrega")
    private LocalDate dataEntrega;

    @JsonProperty("dataDevolucao")
    private LocalDate dataDevolucao;

}
