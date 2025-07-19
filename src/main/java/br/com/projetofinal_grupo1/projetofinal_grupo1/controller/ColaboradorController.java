package br.com.projetofinal_grupo1.projetofinal_grupo1.controller;

import br.com.projetofinal_grupo1.projetofinal_grupo1.dto.ColaboradorRequestDTO;
import br.com.projetofinal_grupo1.projetofinal_grupo1.dto.ColaboradorResponseDTO;
import br.com.projetofinal_grupo1.projetofinal_grupo1.dto.ColaboradorUpdateDTO;
import br.com.projetofinal_grupo1.projetofinal_grupo1.service.ColaboradorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Colaboradores", description = "CRUD de colaboradores (criar, listar, editar, excluir).")
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/colaboradores")
public class ColaboradorController {

    private final ColaboradorService colaboradorService;

    public ColaboradorController(ColaboradorService colaboradorService) {
        this.colaboradorService = colaboradorService;
    }
    @Operation(summary = "Cadastrar colaborador")
    @PostMapping
    public ResponseEntity<Void> cadastrarColaborador(@RequestBody @Valid ColaboradorRequestDTO colaboradorRequestDTO){
        colaboradorService.registrarColaborador(colaboradorRequestDTO);
        return ResponseEntity.ok().build();
    }
    @Operation(summary = "Listar todos Colaboradores Cadastrados")
    @GetMapping
    public ResponseEntity<List<ColaboradorResponseDTO>> imprimir(){
        List<ColaboradorResponseDTO> listaDeColaborador = colaboradorService.devolverTodosOsColaborador();
        return ResponseEntity.status(HttpStatus.OK).body(listaDeColaborador);
    }
    @Operation(summary = "Atualizar dados")
    @PatchMapping("/{id}")
    public ResponseEntity<Void> editarColaborador (@PathVariable int id, @RequestBody @Valid ColaboradorUpdateDTO colaboradorUpdateDTO){
        colaboradorService.editarNomeEmailDataContratacaoDataRecisaoEspecificacaoEquipamentoDoColaborador(id, colaboradorUpdateDTO);
        return ResponseEntity.noContent().build();
    }
    @Operation(summary = "Deletar colaborador pelo ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarColaborador(@PathVariable int id){
        colaboradorService.deletarColaboradorPorId(id);
        return ResponseEntity.noContent().build();
    }
}
