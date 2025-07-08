package br.com.projetofinal_grupo1.projetofinal_grupo1.service;

import br.com.projetofinal_grupo1.projetofinal_grupo1.model.EquipamentoColaborador;
import br.com.projetofinal_grupo1.projetofinal_grupo1.repository.EquipamentoColaboradorRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EquipamentoColaboradorService {

    @Autowired
    private EquipamentoColaboradorRepository equipamentoColaboradorRepository;

    public List<EquipamentoColaborador> listarTodos() {
        return equipamentoColaboradorRepository.findAll();
    }

    public Optional<EquipamentoColaborador> buscarPorId(Integer id) {
        return equipamentoColaboradorRepository.findById(id);
    }

    @Transactional
    public EquipamentoColaborador salvar(EquipamentoColaborador ec) {
        return equipamentoColaboradorRepository.save(ec);
    }

    @Transactional
    public ResponseEntity<EquipamentoColaborador> atualizar(Integer id, EquipamentoColaborador ec) {
        return equipamentoColaboradorRepository.findById(id)
                .map(e -> {
                    ec.setId(id);
                    return ResponseEntity.ok(equipamentoColaboradorRepository.save(ec));
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

