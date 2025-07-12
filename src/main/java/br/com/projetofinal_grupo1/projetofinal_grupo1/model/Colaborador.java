package br.com.projetofinal_grupo1.projetofinal_grupo1.model;

import br.com.projetofinal_grupo1.projetofinal_grupo1.dto.ColaboradorRequestDTO;
import br.com.projetofinal_grupo1.projetofinal_grupo1.dto.ColaboradorUpdateDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
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
        Colaborador colaborador = new Colaborador();
        colaborador.setNome(colaboradorRequestDTO.getNome());
        colaborador.setCpf(colaboradorRequestDTO.getCpf());
        colaborador.setEmail(colaboradorRequestDTO.getEmail());
        colaborador.setCep(colaboradorRequestDTO.getCep());
        colaborador.setData_aniversario(colaboradorRequestDTO.getData_aniversario());
        colaborador.setData_contratacao_inicio(colaboradorRequestDTO.getData_contratacao_inicio());
        colaborador.setData_contratacao_recisao(colaboradorRequestDTO.getData_contratacao_recisao());
        colaborador.setEquipamento_especificacao(colaboradorRequestDTO.getEspecificacao_equipamento());

        return colaborador;
    }

    public void atualizarColaborador(ColaboradorUpdateDTO colaboradorUpdateDTO){
        setNome(colaboradorUpdateDTO.getNome());
        setCep(colaboradorUpdateDTO.getCep());
        setEmail(colaboradorUpdateDTO.getEmail());
        setData_contratacao_inicio(colaboradorUpdateDTO.getDataContratacaoInicio());
        setEquipamento_especificacao(colaboradorUpdateDTO.getEquipamentoEspecificacao());
    }
}
