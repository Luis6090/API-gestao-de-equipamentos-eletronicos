package br.com.projetofinal_grupo1.projetofinal_grupo1.dto;

import br.com.projetofinal_grupo1.projetofinal_grupo1.model.EquipamentoColaborador;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class EquipamentoColaboradorRequestDTO {

    @NotNull
    private Integer idEquipamento;

    @NotNull
    private Integer idColaborador;

    private LocalDate dataEntrega;
    private LocalDate dataDevolucao;

    public EquipamentoColaborador toEntity() {
        EquipamentoColaborador ec = new EquipamentoColaborador();
        ec.setIdEquipamento(this.idEquipamento);
        ec.setIdColaborador(this.idColaborador);
        ec.setDataEntrega(this.dataEntrega != null ? this.dataEntrega : LocalDate.now());
        ec.setDataDevolucao(this.dataDevolucao);
        return ec;
    }

    // Getters e setters (ou use Lombok @Getter/@Setter se preferir)
}
