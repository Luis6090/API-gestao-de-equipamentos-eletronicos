package br.com.projetofinal_grupo1.projetofinal_grupo1.controller;

import br.com.projetofinal_grupo1.projetofinal_grupo1.dto.ColaboradorRequestDTO;
import br.com.projetofinal_grupo1.projetofinal_grupo1.dto.ColaboradorResponseDTO;
import br.com.projetofinal_grupo1.projetofinal_grupo1.dto.ColaboradorUpdateDTO;
import br.com.projetofinal_grupo1.projetofinal_grupo1.service.ColaboradorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController("/colaborador")
public class ColaboradorController {

    private final ColaboradorService colaboradorService;

    public ColaboradorController(ColaboradorService colaboradorService) {
        this.colaboradorService = colaboradorService;
    }

    @PostMapping(path = "cadastrar-colaborador")
    public ResponseEntity<Void> cadastrarColaborador(@RequestBody @Valid ColaboradorRequestDTO colaboradorRequestDTO){
        colaboradorService.registrarColaborador(colaboradorRequestDTO);
        return ResponseEntity.ok().build();
    }

    @GetMapping(path = "imprimir-colaboradores")
    public ResponseEntity<List<ColaboradorResponseDTO>> imprimir(){
        List<ColaboradorResponseDTO> listaDeColaborador = colaboradorService.devolverTodosOsColaborador();
        return ResponseEntity.status(HttpStatus.OK).body(listaDeColaborador);
    }

    @PatchMapping(path = "editar-colaborador/{id}")
    public ResponseEntity<Void> editarColaborador (@PathVariable Long id, @RequestBody @Valid ColaboradorUpdateDTO colaboradorUpdateDTO){
        colaboradorService.editarNomeEmailDataContratacaoDataRecisaoEspecificacaoEquipamentoDoColaborador(id, colaboradorUpdateDTO);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping(path = "deleta-colaborador/{id}")
    public ResponseEntity<Void> deletarColaborador(@PathVariable Long id){
        colaboradorService.deletarColaboradorPorId(id);
        return ResponseEntity.noContent().build();
    }
}
