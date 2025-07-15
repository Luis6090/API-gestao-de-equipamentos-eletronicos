package br.com.projetofinal_grupo1.projetofinal_grupo1.service;

import br.com.projetofinal_grupo1.projetofinal_grupo1.dto.ParametroEstoqueRequestDTO;
import br.com.projetofinal_grupo1.projetofinal_grupo1.dto.ParametroEstoqueResponseDTO;
import br.com.projetofinal_grupo1.projetofinal_grupo1.dto.PrevisaoFaltaResponseDTO;
import br.com.projetofinal_grupo1.projetofinal_grupo1.model.Estoque;
import br.com.projetofinal_grupo1.projetofinal_grupo1.model.ParametroEstoque;
import br.com.projetofinal_grupo1.projetofinal_grupo1.repository.EstoqueRepository;
import br.com.projetofinal_grupo1.projetofinal_grupo1.repository.ParametroEstoqueRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ParametroEstoqueService {

    private final ParametroEstoqueRepository parametroEstoqueRepository;
    private final EstoqueRepository estoqueRepository;

    public ParametroEstoqueService(ParametroEstoqueRepository parametroEstoqueRepository, EstoqueRepository estoqueRepository) {
        this.parametroEstoqueRepository = parametroEstoqueRepository;
        this.estoqueRepository = estoqueRepository;
    }

    private ParametroEstoqueResponseDTO toResponseDTO(ParametroEstoque parametro) {
        ParametroEstoqueResponseDTO dto = new ParametroEstoqueResponseDTO();
        dto.setIdParametro(parametro.getIdParametro());
        dto.setTempoMedioReposicao(parametro.getTempoMedioReposicao());
        dto.setTempoMedioConsumoEstoque(parametro.getTempoMedioConsumoEstoque());
        dto.setTempoMedioEnvio(parametro.getTempoMedioEnvio());
        dto.setTaxaMediaEquipamentosDefeituosos(parametro.getTaxaMediaEquipamentosDefeituosos());
        dto.setEstoqueMinimoSeguranca(parametro.getEstoqueMinimoSeguranca());
        return dto;
    }

    private ParametroEstoque toEntity(ParametroEstoqueRequestDTO dto) {
        ParametroEstoque parametro = new ParametroEstoque();
        parametro.setTempoMedioReposicao(dto.getTempoMedioReposicao());
        parametro.setTempoMedioConsumoEstoque(dto.getTempoMedioConsumoEstoque());
        parametro.setTempoMedioEnvio(dto.getTempoMedioEnvio());
        parametro.setTaxaMediaEquipamentosDefeituosos(dto.getTaxaMediaEquipamentosDefeituosos());
        parametro.setEstoqueMinimoSeguranca(dto.getEstoqueMinimoSeguranca());
        return parametro;
    }


    @Transactional
    public ParametroEstoqueResponseDTO criarParametro(ParametroEstoqueRequestDTO dto) {
        ParametroEstoque parametro = toEntity(dto);
        ParametroEstoque salvo = parametroEstoqueRepository.save(parametro);
        return toResponseDTO(salvo);
    }

    @Transactional(readOnly = true)
    public ParametroEstoqueResponseDTO buscarParametroPorId(int id) {
        return parametroEstoqueRepository.findById(id)
                .map(this::toResponseDTO)
                .get();
    }

    //Ver com a Nanda se isso pode ser feito
    @Transactional(readOnly = true)
    public List<ParametroEstoqueResponseDTO> listarTodosParametros() {
        return parametroEstoqueRepository.findAll().stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public ParametroEstoqueResponseDTO atualizarParametro(int id, ParametroEstoqueRequestDTO dto) {
        ParametroEstoque existente = parametroEstoqueRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Parâmetro de estoque não encontrado com o ID: " + id));

        existente.setTempoMedioReposicao(dto.getTempoMedioReposicao());
        existente.setTempoMedioConsumoEstoque(dto.getTempoMedioConsumoEstoque());
        existente.setTempoMedioEnvio(dto.getTempoMedioEnvio());
        existente.setTaxaMediaEquipamentosDefeituosos(dto.getTaxaMediaEquipamentosDefeituosos());
        existente.setEstoqueMinimoSeguranca(dto.getEstoqueMinimoSeguranca());

        ParametroEstoque atualizado = parametroEstoqueRepository.save(existente);
        return toResponseDTO(atualizado);
    }

    @Transactional
    public void deletarParametro(int id) {
        if (!parametroEstoqueRepository.existsById(id)) {
            throw new RuntimeException("Parâmetro de estoque não encontrado com o ID: " + id);
        }
        parametroEstoqueRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public Optional<ParametroEstoque> getLatestParametroEstoque() {
        return parametroEstoqueRepository.findAll().stream()
                .max((p1, p2) -> Integer.compare(p1.getIdParametro(), p2.getIdParametro()));
    }

    @Transactional(readOnly = true)
    public List<PrevisaoFaltaResponseDTO> gerarRelatorioPrevisaoFalta() {
        ParametroEstoque parametros = parametroEstoqueRepository.getLatestParametroEstoque()
                .orElseThrow(() -> new RuntimeException("Parâmetros de estoque não configurados."));

        List<Estoque> todosEstoques = estoqueRepository.findAll();
        List<PrevisaoFaltaResponseDTO> relatorio = new ArrayList<>();

        double consumoDiarioBase = (double) parametros.getTempoMedioConsumoEstoque();

        for (Estoque estoque : todosEstoques) {
            if (estoque.getTipoEquipamento() == null) {
                System.out.println("Estoque com ID " + estoque.getId() + " não possui TipoEquipamento associado. Ignorando no relatório.");
                continue;
            }

            double consumoDiarioEstimado = 0;
            if (consumoDiarioBase > 0) {
                consumoDiarioEstimado = (double) estoque.getQuantidadeEmUso() / consumoDiarioBase;
            }

            consumoDiarioEstimado *= (1 + parametros.getTaxaMediaEquipamentosDefeituosos());

            int leadTimeTotalDias = parametros.getTempoMedioReposicao() + parametros.getTempoMedioEnvio();

            double pontoDePedido = (consumoDiarioEstimado * leadTimeTotalDias) + parametros.getEstoqueMinimoSeguranca();

            boolean emRiscoDeFalta = estoque.getQuantidadeDisponivel() < pontoDePedido;
            String mensagemAlerta = emRiscoDeFalta ? "ATENÇÃO: Em risco de falta!" : "Estoque suficiente.";

            relatorio.add(PrevisaoFaltaResponseDTO.builder()

                    .idTipoEquipamento(estoque.getTipoEquipamento().getId())
                    .nomeTipoEquipamento(estoque.getTipoEquipamento().getNomeTipo())
                    .quantidadeDisponivelAtual(estoque.getQuantidadeDisponivel())
                    .quantidadeEmUsoAtual(estoque.getQuantidadeEmUso())
                    .quantidadeDefeituosaAtual(estoque.getQuantidadeDefeituosa())
                    .consumoDiarioEstimado(consumoDiarioEstimado)
                    .leadTimeTotalDias(leadTimeTotalDias)
                    .estoqueMinimoSeguranca(parametros.getEstoqueMinimoSeguranca())
                    .pontoDePedido(pontoDePedido)
                    .emRiscoDeFalta(emRiscoDeFalta)
                    .mensagemAlerta(mensagemAlerta)
                    .build());
        }
        return relatorio;
    }
}
