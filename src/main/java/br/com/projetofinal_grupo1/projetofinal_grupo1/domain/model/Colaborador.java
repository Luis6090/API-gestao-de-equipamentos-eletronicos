package br.com.projetofinal_grupo1.projetofinal_grupo1.domain.model;

import br.com.projetofinal_grupo1.projetofinal_grupo1.domain.DTO.ColaboradorRequestDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "COLABORADOR")
public class Colaborador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cpf;
    private String nome;
    private String email;
    private String cep;
    private LocalDate data_aniversario;
    private LocalDate data_contratacao_inicio;
    private LocalDate data_contratacao_recisao;
    private String equipamento_especificacao;

    public static Colaborador converterParaColaborador(ColaboradorRequestDTO colaboradorRequestDTO){
        return new Colaborador(colaboradorRequestDTO.getCpf(),colaboradorRequestDTO.getNome(), colaboradorRequestDTO.getEmail(), colaboradorRequestDTO.getCep()
                                , colaboradorRequestDTO.getData_aniversario(), colaboradorRequestDTO.getData_contratacao_inicio(), colaboradorRequestDTO.getData_contratacao_recisao()
                                , colaboradorRequestDTO.getEspecificacao_equipamento());
    }
}
