package br.com.projetofinal_grupo1.projetofinal_grupo1.repository;

import br.com.projetofinal_grupo1.projetofinal_grupo1.domain.model.Colaborador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ColaboradorRepository extends JpaRepository<Colaborador, String> {
}
