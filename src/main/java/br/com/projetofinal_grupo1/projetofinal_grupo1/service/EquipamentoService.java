package br.com.projetofinal_grupo1.projetofinal_grupo1.service;

import br.com.projetofinal_grupo1.projetofinal_grupo1.model.Equipamento;
import br.com.projetofinal_grupo1.projetofinal_grupo1.repository.EquipamentoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EquipamentoService {

    private final EquipamentoRepository equipamentoRepository;

    public EquipamentoService(EquipamentoRepository equipamentoRepository) {
        this.equipamentoRepository = equipamentoRepository;
    }

    public List<Equipamento> listarTodos() {
        return equipamentoRepository.findAll();
    }

    public ResponseEntity<Equipamento> buscarPorId(int id) {
        return equipamentoRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    public Equipamento salvar(Equipamento equipamento) {
        return equipamentoRepository.save(equipamento);
    }

    public ResponseEntity<Equipamento> atualizar(int id, Equipamento equipamento) {
        return equipamentoRepository.findById(id)
                .map(e -> {
                    equipamento.setId(id);
                    return ResponseEntity.ok(equipamentoRepository.save(equipamento));
                })
                .orElse(ResponseEntity.notFound().build());
    }

    public ResponseEntity<Void> deletar(int id) {
        if (equipamentoRepository.existsById(id)) {
            equipamentoRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
