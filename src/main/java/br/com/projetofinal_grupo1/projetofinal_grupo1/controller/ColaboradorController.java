package br.com.projetofinal_grupo1.projetofinal_grupo1.controller;

import br.com.projetofinal_grupo1.projetofinal_grupo1.domain.DTO.ColaboradorRequestDTO;
import br.com.projetofinal_grupo1.projetofinal_grupo1.service.ColaboradorService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/colaborador")
public class ColaboradorController {

    private final ColaboradorService colaboradorService;

    public ColaboradorController(ColaboradorService colaboradorService) {
        this.colaboradorService = colaboradorService;
    }

    @PostMapping(path = "cadastrar")
    public ResponseEntity<Void> cadastrarColaborador(ColaboradorRequestDTO colaboradorRequestDTO){
        colaboradorService.registrarColaborador(colaboradorRequestDTO);
        return ResponseEntity.ok().build();
    }
}
