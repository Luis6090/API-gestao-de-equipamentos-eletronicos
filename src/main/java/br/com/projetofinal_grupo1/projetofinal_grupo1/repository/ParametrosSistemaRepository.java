package br.com.projetofinal_grupo1.projetofinal_grupo1.repository;

import br.com.projetofinal_grupo1.projetofinal_grupo1.model.ParametrosSistema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ParametrosSistemaRepository extends JpaRepository<ParametrosSistema, Integer> {
    @Query("SELECT p FROM ParametrosSistema p ORDER BY p.id DESC LIMIT 1")
    Optional<ParametrosSistema> getLatestParametroEstoque();
}