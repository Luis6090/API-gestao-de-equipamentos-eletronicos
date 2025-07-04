package br.com.projetofinal_grupo1.projetofinal_grupo1.controller;

import br.com.projetofinal_grupo1.projetofinal_grupo1.model.Equipamento;
import br.com.projetofinal_grupo1.projetofinal_grupo1.repository.EquipamentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/equipamentos")
public class EquipamentoController {

    @Autowired
    private EquipamentoRepository equipamentoRepository;

    @GetMapping
    public List<Equipamento> listar(){
        return equipamentoRepository.findAll();
    }

    @GetMapping("/{id}")
    public Equipamento buscar(@PathVariable int id){
        return equipamentoRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Equipamento salvar(@RequestBody Equipamento equipamento) {
        System.out.println("DEBUG >>> Recebido do Postman:");
        System.out.println(equipamento); // imprime todos os campos
        return equipamentoRepository.save(equipamento);
    }

    @PutMapping("/{id}")
    public Equipamento atualizar(@PathVariable int id, @RequestBody Equipamento equipamento) {
        Equipamento existente = equipamentoRepository.findById(id).orElse(null);
        if (existente != null) {
            equipamento.setId(id);
            return equipamentoRepository.save(equipamento);
        }
        return null;
    }

    @DeleteMapping("/{id}")
        public void deletar(@PathVariable int id) {
        equipamentoRepository.deleteById(id);
    }

}


