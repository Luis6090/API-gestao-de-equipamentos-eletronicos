package br.com.projetofinal_grupo1.projetofinal_grupo1.repository;

import br.com.projetofinal_grupo1.projetofinal_grupo1.model.Colaborador;
import jakarta.transaction.Transactional;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface ColaboradorRepository extends JpaRepository<Colaborador, Integer> {
    @Modifying
    @Transactional
    @Query("UPDATE Colaborador col SET col.nome = :nome, col.email = :email, col.cep = :cep, col.dataContratacaoInicio = :dataContratacaoInicio" +
            ", col.dataContratacaoRecisao = :dataContratacaoRecisao, col.equipamentoEspecificacao = :equipamentoEspecificacao WHERE col.id = :id")
    void editarColaborador(@Param("id") int id, @Param("nome") String nome, @Param("email") String email, @Param("cep") String cep
            , @Param("dataContratacaoInicio")LocalDate dataInicio, @Param("dataContratacaoRecisao") LocalDate dataRecisao
            , @Param("equipamentoEspecificacao") String equipamentoEspecificacao);
}
