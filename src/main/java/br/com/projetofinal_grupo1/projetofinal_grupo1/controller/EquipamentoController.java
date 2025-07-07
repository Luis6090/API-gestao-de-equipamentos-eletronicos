package br.com.projetofinal_grupo1.projetofinal_grupo1.controller;

import br.com.projetofinal_grupo1.projetofinal_grupo1.model.Equipamento;
import br.com.projetofinal_grupo1.projetofinal_grupo1.repository.EquipamentoRepository;
import br.com.projetofinal_grupo1.projetofinal_grupo1.service.EquipamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/equipamentos")
public class EquipamentoController {

    private final EquipamentoService equipamentoService;

    public EquipamentoController(EquipamentoService equipamentoService) {
        this.equipamentoService = equipamentoService;
    }

    @Operation(summary = "Listar todos os equipamentos")
    @GetMapping
    public List<Equipamento> listar() {
        return equipamentoService.listarTodos();
    }

    @Operation(summary = "Buscar equipamento por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Equipamento encontrado"),
            @ApiResponse(responseCode = "404", description = "Equipamento não encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<Equipamento> buscar(@PathVariable int id) {
        return equipamentoService.buscarPorId(id);
    }

    @Operation(summary = "Cadastrar novo equipamento")
    @PostMapping
    public ResponseEntity<Equipamento> salvar(@RequestBody Equipamento equipamento) {
        Equipamento salvo = equipamentoService.salvar(equipamento);
        return ResponseEntity.status(201).body(salvo);
    }

    @Operation(summary = "Atualizar equipamento existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Equipamento não encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Equipamento> atualizar(@PathVariable int id, @RequestBody Equipamento equipamento) {
        return equipamentoService.atualizar(id, equipamento);
    }

    @Operation(summary = "Deletar equipamento por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Equipamento não encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable int id) {
        return equipamentoService.deletar(id);
    }
}


