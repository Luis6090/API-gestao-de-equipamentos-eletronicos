package br.com.projetofinal_grupo1.projetofinal_grupo1.repository;

import br.com.projetofinal_grupo1.projetofinal_grupo1.model.ParametroEstoque;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ParametroEstoqueRepository extends JpaRepository<ParametroEstoque, Integer> {
    @Query("SELECT p FROM ParametroEstoque p ORDER BY p.id DESC LIMIT 1")
    Optional<ParametroEstoque> getLatestParametroEstoque();
}