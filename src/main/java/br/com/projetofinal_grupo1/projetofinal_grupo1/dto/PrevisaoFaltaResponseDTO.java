package br.com.projetofinal_grupo1.projetofinal_grupo1.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder // Pro PrametroEstoqueService
public class PrevisaoFaltaResponseDTO {
    private int idEquipamento;
    private String numeroSerieEquipamento;
    private String tipoEquipamento;
    private int quantidadeDisponivelAtual;
    private int quantidadeEmUsoAtual;
    private int quantidadeDefeituosaAtual;
    private double consumoDiarioEstimado;
    private int leadTimeTotalDias;
    private int estoqueMinimoSeguranca;
    private double pontoDePedido;
    private boolean emRiscoDeFalta;
    private String mensagemAlerta;
}