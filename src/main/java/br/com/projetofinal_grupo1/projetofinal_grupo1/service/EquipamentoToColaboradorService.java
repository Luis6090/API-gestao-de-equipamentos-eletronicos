package br.com.projetofinal_grupo1.projetofinal_grupo1.service;

import br.com.projetofinal_grupo1.projetofinal_grupo1.dto.EquipamentoToColaboradorRequestDTO;
import br.com.projetofinal_grupo1.projetofinal_grupo1.dto.EquipamentoToColaboradorResponseDTO;
import br.com.projetofinal_grupo1.projetofinal_grupo1.model.EquipamentoToColaborador;
import br.com.projetofinal_grupo1.projetofinal_grupo1.model.IdEquipamentoToColaborador;
import br.com.projetofinal_grupo1.projetofinal_grupo1.repository.EquipamentoToColaboradorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

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

    public ResponseEntity<EquipamentoToColaboradorResponseDTO> buscarPorId(IdEquipamentoToColaborador id) {
        Optional<EquipamentoToColaborador> encontrado = equipamentoToColaboradorRepository.findById(id);
        return encontrado
                .map(ec -> ResponseEntity.ok(new EquipamentoToColaboradorResponseDTO(ec)))
                .orElse(ResponseEntity.notFound().build());
    }

    @Transactional
    public ResponseEntity<EquipamentoToColaboradorResponseDTO> salvar(EquipamentoToColaboradorRequestDTO dto) {
        EquipamentoToColaborador salvo = equipamentoToColaboradorRepository.save(dto.toEntity());
        return ResponseEntity.status(201).body(new EquipamentoToColaboradorResponseDTO(salvo));
    }

    @Transactional
    public ResponseEntity<EquipamentoToColaboradorResponseDTO> atualizar(IdEquipamentoToColaborador id, EquipamentoToColaboradorRequestDTO dto) {
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
    public ResponseEntity<Void> deletar(IdEquipamentoToColaborador id) {
        if (equipamentoToColaboradorRepository.existsById(id)) {
            equipamentoToColaboradorRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
