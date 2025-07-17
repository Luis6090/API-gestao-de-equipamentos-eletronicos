package br.com.projetofinal_grupo1.projetofinal_grupo1.dto;

import br.com.projetofinal_grupo1.projetofinal_grupo1.model.EquipamentoToColaborador;
import br.com.projetofinal_grupo1.projetofinal_grupo1.model.IdEquipamentoToColaborador;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class EquipamentoToColaboradorResponseDTO {

    private LocalDate dataEntrega;
    private LocalDate dataDevolucao;
    @NotNull
    private IdEquipamentoToColaborador chaveCompostaEquipamentoColaborador;

    public EquipamentoToColaboradorResponseDTO(EquipamentoToColaborador ec) {
        this.chaveCompostaEquipamentoColaborador = ec.getId();
        this.dataEntrega = ec.getDataEntrega();
        this.dataDevolucao = ec.getDataDevolucao();
    }
}
