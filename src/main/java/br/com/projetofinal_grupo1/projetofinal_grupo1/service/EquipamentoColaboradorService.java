package br.com.projetofinal_grupo1.projetofinal_grupo1.service;

import br.com.projetofinal_grupo1.projetofinal_grupo1.dto.EquipamentoColaboradorRequestDTO;
import br.com.projetofinal_grupo1.projetofinal_grupo1.dto.EquipamentoColaboradorResponseDTO;
import br.com.projetofinal_grupo1.projetofinal_grupo1.model.EquipamentoColaborador;
import br.com.projetofinal_grupo1.projetofinal_grupo1.repository.EquipamentoColaboradorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EquipamentoColaboradorService {

    @Autowired
    private EquipamentoColaboradorRepository equipamentoColaboradorRepository;

    public List<EquipamentoColaboradorResponseDTO> listarTodos() {
        return equipamentoColaboradorRepository.findAll()
                .stream()
                .map(EquipamentoColaboradorResponseDTO::new)
                .collect(Collectors.toList());
    }

    public ResponseEntity<EquipamentoColaboradorResponseDTO> buscarPorId(Integer id) {
        Optional<EquipamentoColaborador> encontrado = equipamentoColaboradorRepository.findById(id);
        return encontrado
                .map(ec -> ResponseEntity.ok(new EquipamentoColaboradorResponseDTO(ec)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Transactional
    public ResponseEntity<EquipamentoColaboradorResponseDTO> salvar(EquipamentoColaboradorRequestDTO dto) {
        EquipamentoColaborador salvo = equipamentoColaboradorRepository.save(dto.toEntity());
        return ResponseEntity.status(201).body(new EquipamentoColaboradorResponseDTO(salvo));
    }

    @Transactional
    public ResponseEntity<EquipamentoColaboradorResponseDTO> atualizar(Integer id, EquipamentoColaboradorRequestDTO dto) {
        return equipamentoColaboradorRepository.findById(id)
                .map(ec -> {
                    EquipamentoColaborador atualizado = dto.toEntity();
                    atualizado.setId(id); // garantir atualização do objeto correto
                    equipamentoColaboradorRepository.save(atualizado);
                    return ResponseEntity.ok(new EquipamentoColaboradorResponseDTO(atualizado));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @Transactional
    public ResponseEntity<Void> deletar(Integer id) {
        if (equipamentoColaboradorRepository.existsById(id)) {
            equipamentoColaboradorRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
