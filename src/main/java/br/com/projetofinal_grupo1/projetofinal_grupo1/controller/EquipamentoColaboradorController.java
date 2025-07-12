package br.com.projetofinal_grupo1.projetofinal_grupo1.controller;

import br.com.projetofinal_grupo1.projetofinal_grupo1.model.Equipamento;
import br.com.projetofinal_grupo1.projetofinal_grupo1.model.EquipamentoColaborador;
import br.com.projetofinal_grupo1.projetofinal_grupo1.service.EquipamentoColaboradorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Tag(name = "Equipamento-Colaborador", description = "CRUD de envio de equipamentos para colaboradores (criar, listar, editar, excluir).")
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/api/emprestimos")
public class EquipamentoColaboradorController {

    @Autowired
    private EquipamentoColaboradorService equipamentoColaboradorService;

    @Operation(summary = "Listar todos os envios")
    @GetMapping
    public List<EquipamentoColaborador> listar(){
        return equipamentoColaboradorService.listarTodos();
    }

    @Operation(summary = "Buscar envios por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Envio encontrado"),
            @ApiResponse(responseCode = "404", description = "Envio não encontrado")
    })
    @GetMapping("/{id}")
    public Optional<EquipamentoColaborador> buscarPorId(@PathVariable int id) {
        return equipamentoColaboradorService.buscarPorId(id);
    }

    @Operation(summary = "Cadastrar novo envio")
    @PostMapping("/{id}")
    public ResponseEntity<EquipamentoColaborador> criar(@RequestBody EquipamentoColaborador ec){
        EquipamentoColaborador equipamentoCriado = equipamentoColaboradorService.salvar(ec);
        return ResponseEntity.status(201).body(equipamentoCriado);
    }

    @Operation(summary = "Atualizar envio existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Atualizado com sucesso"),
            @ApiResponse(responseCode = "404", description = "Envio não encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<EquipamentoColaborador> atualizar(
        @PathVariable Integer id,
        @RequestBody EquipamentoColaborador ec){
        return equipamentoColaboradorService.atualizar(id, ec);
        }

    @Operation(summary = "Deletar envio por ID")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Removido com sucesso"),
            @ApiResponse(responseCode = "404", description = "Envio não encontrado")
    })
    @DeleteMapping("/{id}")
public ResponseEntity<Void> deletar(@PathVariable Integer id){
    return equipamentoColaboradorService.deletar(id);

}
}
