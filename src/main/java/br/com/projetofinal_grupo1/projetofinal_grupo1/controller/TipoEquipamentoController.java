package br.com.projetofinal_grupo1.projetofinal_grupo1.controller;

import br.com.projetofinal_grupo1.projetofinal_grupo1.dto.TipoEquipamentoRequestDTO;
import br.com.projetofinal_grupo1.projetofinal_grupo1.model.TipoEquipamento;
import br.com.projetofinal_grupo1.projetofinal_grupo1.service.TipoEquipamentoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Tipos de equipamentos", description = "CRUD de tipos de equipamentos (divide em categorias).")
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/tipos-equipamento" )
public class TipoEquipamentoController {

    private final TipoEquipamentoService tipoEquipamentoService;

    public TipoEquipamentoController(TipoEquipamentoService tipoEquipamentoService) {
        this.tipoEquipamentoService = tipoEquipamentoService;
    }
    @Operation(summary = "Cria um tipo de equipamento genérico (ex: notebook, celular")
    @PostMapping
    public ResponseEntity<?> criarTipoEquipamento(@RequestBody TipoEquipamentoRequestDTO dto) {
        try {
            TipoEquipamento novo = tipoEquipamentoService.criarTipoEquipamento(dto.getNomeTipo());
            return ResponseEntity.status(HttpStatus.CREATED).body(novo);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }
    @Operation(summary = "Lista todos os tipos de equipamento")
    @GetMapping
    public ResponseEntity<List<TipoEquipamento>> listarTodosTiposEquipamento() {
        List<TipoEquipamento> tipos = tipoEquipamentoService.listarTodosTiposEquipamento();
        return new ResponseEntity<>(tipos, HttpStatus.OK);
    }

    @Operation(summary = "Busca um tipo de equipamento por ID")
    @GetMapping("/{id}")
    public ResponseEntity<TipoEquipamento> buscarTipoEquipamentoPorId(@PathVariable int id) {
        return tipoEquipamentoService.buscarTipoEquipamentoPorId(id)
                .map(tipo -> new ResponseEntity<>(tipo, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @Operation(summary = "Atualiza um tipo de equipamento existente Por ID")
    @PutMapping("/{id}")
    public ResponseEntity<TipoEquipamento> atualizarTipoEquipamento(@PathVariable int id, @RequestBody TipoEquipamento tipoEquipamento) {
        try {
            TipoEquipamento atualizado = tipoEquipamentoService.atualizarTipoEquipamento(id, tipoEquipamento);
            return new ResponseEntity<>(atualizado, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @Operation(summary = "Deleta um tipo de equipamento por ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarTipoEquipamento(@PathVariable int id) {
        try {
            tipoEquipamentoService.deletarTipoEquipamento(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}