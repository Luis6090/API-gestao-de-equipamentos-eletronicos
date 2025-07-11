package br.com.projetofinal_grupo1.projetofinal_grupo1.dto;

import br.com.projetofinal_grupo1.projetofinal_grupo1.model.EquipamentoColaborador;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class EquipamentoColaboradorResponseDTO {

    private int id;
    private int idEquipamento;
    private int idColaborador;
    private LocalDate dataEntrega;
    private LocalDate dataDevolucao;

    public EquipamentoColaboradorResponseDTO(EquipamentoColaborador ec) {
        this.id = ec.getId();
        this.idEquipamento = ec.getIdEquipamento();
        this.idColaborador = ec.getIdColaborador();
        this.dataEntrega = ec.getDataEntrega();
        this.dataDevolucao = ec.getDataDevolucao();
    }
}
