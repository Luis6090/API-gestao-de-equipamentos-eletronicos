package br.com.projetofinal_grupo1.projetofinal_grupo1.repository;

import br.com.projetofinal_grupo1.projetofinal_grupo1.model.ParametroEstoque;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ParametroEstoqueRepository extends JpaRepository<ParametroEstoque, Integer> {
    Optional<Object> getLatestParametroEstoque();
}