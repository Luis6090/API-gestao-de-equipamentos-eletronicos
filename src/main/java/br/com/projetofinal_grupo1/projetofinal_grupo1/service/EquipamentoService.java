package br.com.projetofinal_grupo1.projetofinal_grupo1.service;

import br.com.projetofinal_grupo1.projetofinal_grupo1.dto.EquipamentoRequestDTO;
import br.com.projetofinal_grupo1.projetofinal_grupo1.dto.EquipamentoResponseDTO;
import br.com.projetofinal_grupo1.projetofinal_grupo1.model.Equipamento;
import br.com.projetofinal_grupo1.projetofinal_grupo1.model.TipoEquipamento;
import br.com.projetofinal_grupo1.projetofinal_grupo1.repository.EquipamentoRepository;
import br.com.projetofinal_grupo1.projetofinal_grupo1.repository.TipoEquipamentoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EquipamentoService {

    private final EquipamentoRepository equipamentoRepository;
    private final TipoEquipamentoRepository tipoEquipamentoRepository;

    public EquipamentoService(EquipamentoRepository equipamentoRepository, TipoEquipamentoRepository tipoEquipamentoRepository) {
        this.equipamentoRepository = equipamentoRepository;
        this.tipoEquipamentoRepository = tipoEquipamentoRepository;
    }

    private EquipamentoResponseDTO toResponseDTO(Equipamento equipamento) {
        EquipamentoResponseDTO dto = new EquipamentoResponseDTO();
        dto.setId(equipamento.getId());
        dto.setEspecificacoes(equipamento.getEspecificacoes());
        dto.setNumeroSerie(equipamento.getNumeroSerie());
        dto.setMarca(equipamento.getMarca());
        dto.setModelo(equipamento.getModelo());
        dto.setDataAquisicao(equipamento.getDataAquisicao());
        dto.setMesesDeUso(equipamento.getTempoUso());
        dto.setTipoEquipamento(equipamento.getTipoEquipamento() != null ? equipamento.getTipoEquipamento().getNomeTipo() : null);
        return dto;
    }

    private Equipamento toEntity(EquipamentoRequestDTO dto) {
        Equipamento equipamento = new Equipamento();
        equipamento.setEspecificacoes(dto.getEspecificacoes());
        equipamento.setNumeroSerie(dto.getNumeroSerie());
        equipamento.setMarca(dto.getMarca());
        equipamento.setModelo(dto.getModelo());
        equipamento.setDataAquisicao(dto.getDataAquisicao());
        equipamento.setTempoUso(dto.getMesesDeUso());
        return equipamento;
    }

    // Operações Create, Read, Update, Delete
    @Transactional
    public EquipamentoResponseDTO criarEquipamento(EquipamentoRequestDTO dto) {

        // Validação: se o tipo de equipamento existe
        TipoEquipamento tipoEquipamento = tipoEquipamentoRepository.findByNomeTipo(dto.getTipoEquipamento())
                .orElseThrow(() -> new RuntimeException("Tipo de equipamento não encontrado: " + dto.getTipoEquipamento()));

        // Validação: se o número de série já existe
        if (equipamentoRepository.findByNumeroSerie(dto.getNumeroSerie()).isPresent()) {
            throw new RuntimeException("Já existe um equipamento com o número de série: " + dto.getNumeroSerie());
        }

        Equipamento equipamento = toEntity(dto);
        equipamento.setTipoEquipamento(tipoEquipamento); // Associa o tipo
        Equipamento salvo = equipamentoRepository.save(equipamento);
        return toResponseDTO(salvo);
    }

    @Transactional(readOnly = true)
    public Optional<EquipamentoResponseDTO> buscarEquipamentoPorId(int id) {
        return equipamentoRepository.findById(id)
                .map(this::toResponseDTO);
    }

    @Transactional(readOnly = true)
    public List<EquipamentoResponseDTO> listarTodosEquipamentos() {
        return equipamentoRepository.findAll().stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    @Transactional
    public EquipamentoResponseDTO atualizarEquipamento(int id, EquipamentoRequestDTO dto) {
        Equipamento existente = equipamentoRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Equipamento não encontrado com o ID: " + id));

        // Validação: tipo de equipamento para atualização
        TipoEquipamento tipoEquipamento = tipoEquipamentoRepository.findByNomeTipo(dto.getTipoEquipamento())
                .orElseThrow(() -> new RuntimeException("Tipo de equipamento não encontrado: " + dto.getTipoEquipamento()));

        // Validação: verificar duplicidade
        if (!dto.getNumeroSerie().equals(existente.getNumeroSerie())) {
            if (equipamentoRepository.findByNumeroSerie(dto.getNumeroSerie()).isPresent()) {
                throw new RuntimeException("Já existe um equipamento com o número de série: " + dto.getNumeroSerie());
            }
        }

        existente.setEspecificacoes(dto.getEspecificacoes());
        existente.setNumeroSerie(dto.getNumeroSerie());
        existente.setMarca(dto.getMarca());
        existente.setModelo(dto.getModelo());
        existente.setDataAquisicao(dto.getDataAquisicao());
        existente.setTempoUso(dto.getMesesDeUso());
        existente.setTipoEquipamento(tipoEquipamento); // Atualiza o tipo

        Equipamento atualizado = equipamentoRepository.save(existente);
        return toResponseDTO(atualizado);
    }

    @Transactional
    public void deletarEquipamento(int id) {
        if (!equipamentoRepository.existsById(id)) {
            throw new RuntimeException("Equipamento não encontrado com o ID: " + id);
        }
        equipamentoRepository.deleteById(id);
    }
}
