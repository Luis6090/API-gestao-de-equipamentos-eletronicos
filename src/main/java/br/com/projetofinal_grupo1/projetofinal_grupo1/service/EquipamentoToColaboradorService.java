package br.com.projetofinal_grupo1.projetofinal_grupo1.service;

import br.com.projetofinal_grupo1.projetofinal_grupo1.dto.EquipamentoToColaboradorRequestDTO;
import br.com.projetofinal_grupo1.projetofinal_grupo1.dto.EquipamentoToColaboradorResponseDTO;
import br.com.projetofinal_grupo1.projetofinal_grupo1.model.Equipamento;
import br.com.projetofinal_grupo1.projetofinal_grupo1.model.EquipamentoToColaborador;
import br.com.projetofinal_grupo1.projetofinal_grupo1.repository.EquipamentoRepository;
import br.com.projetofinal_grupo1.projetofinal_grupo1.repository.EquipamentoToColaboradorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EquipamentoToColaboradorService {

    @Autowired
    private EquipamentoToColaboradorRepository equipamentoToColaboradorRepository;

    public List<EquipamentoToColaboradorResponseDTO> listarTodos() {
        return equipamentoToColaboradorRepository.findAll()
                .stream()
                .map(EquipamentoToColaboradorResponseDTO::new)
                .collect(Collectors.toList());
    }

    public ResponseEntity<EquipamentoToColaboradorResponseDTO> buscarPorId(Integer id) {
        Optional<EquipamentoToColaborador> encontrado = equipamentoToColaboradorRepository.findById(id);
        return encontrado
                .map(ec -> ResponseEntity.ok(new EquipamentoToColaboradorResponseDTO(ec)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Autowired
    private EquipamentoRepository equipamentoRepository;

    @Transactional
    public ResponseEntity<EquipamentoToColaboradorResponseDTO> salvar(EquipamentoToColaboradorRequestDTO dto) {
        Equipamento equipamento = equipamentoRepository.findById(dto.getIdEquipamento())
                .orElseThrow(() -> new RuntimeException("Equipamento não encontrado"));

        if (!Boolean.TRUE.equals(equipamento.getDisponivel())) {
            throw new IllegalStateException("Equipamento já está emprestado.");
        }

        equipamento.setDisponivel(false);
        equipamentoRepository.save(equipamento);

        EquipamentoToColaborador salvo = equipamentoToColaboradorRepository.save(dto.toEntity());
        return ResponseEntity.status(201).body(new EquipamentoToColaboradorResponseDTO(salvo));
    }

    @Transactional
    public ResponseEntity<EquipamentoToColaboradorResponseDTO> atualizar(Integer id, EquipamentoToColaboradorRequestDTO dto) {
        return equipamentoToColaboradorRepository.findById(id)
                .map(ec -> {
                    EquipamentoToColaborador atualizado = dto.toEntity();
                    atualizado.setId(id); // garantir atualização do objeto correto
                    equipamentoToColaboradorRepository.save(atualizado);
                    return ResponseEntity.ok(new EquipamentoToColaboradorResponseDTO(atualizado));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @Transactional
    public ResponseEntity<Void> deletar(Integer id) {
        if (equipamentoToColaboradorRepository.existsById(id)) {
            equipamentoToColaboradorRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @Transactional
    public ResponseEntity<Void> registrarDevolucao(Integer idEmprestimo) {
        EquipamentoToColaborador emprestimo = equipamentoToColaboradorRepository.findById(idEmprestimo)
                .orElseThrow(() -> new RuntimeException("Empréstimo não encontrado"));

        if (emprestimo.getDataDevolucao() != null) {
            return ResponseEntity.badRequest().build();
        }

        emprestimo.setDataDevolucao(LocalDate.now());
        equipamentoToColaboradorRepository.save(emprestimo);

        Equipamento equipamento = equipamentoRepository.findById(emprestimo.getIdEquipamento())
                .orElseThrow(() -> new RuntimeException("Equipamento não encontrado"));

        equipamento.setDisponivel(true);
        equipamentoRepository.save(equipamento);

        return ResponseEntity.ok().build();
    }
}
