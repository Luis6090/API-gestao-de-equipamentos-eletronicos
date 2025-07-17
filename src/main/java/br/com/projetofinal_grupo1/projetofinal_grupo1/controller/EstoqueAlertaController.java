package br.com.projetofinal_grupo1.projetofinal_grupo1.controller;

import br.com.projetofinal_grupo1.projetofinal_grupo1.service.EstoqueAlertaService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Alertas")
@SecurityRequirement(name = "bearerAuth")
@RestController
@RequestMapping("/estoque-alerta")
public class EstoqueAlertaController {

    private final EstoqueAlertaService estoqueAlertaService;

    public EstoqueAlertaController(EstoqueAlertaService estoqueAlertaService) {
        this.estoqueAlertaService = estoqueAlertaService;
    }

    @PostMapping("/verificar")
    public ResponseEntity<Void> verificarEstoqueCritico() {
        estoqueAlertaService.verificarEstoqueCritico();
        return ResponseEntity.ok().build();
    }
}
