package br.com.projetofinal_grupo1.projetofinal_grupo1.model;

import br.com.projetofinal_grupo1.projetofinal_grupo1.dto.ColaboradorRequestDTO;
import br.com.projetofinal_grupo1.projetofinal_grupo1.dto.ColaboradorUpdateDTO;
import jakarta.persistence.*;
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
    @Column(name = "id_colaborador")
    private Long id;
    @Column(name = "cpf")
    private String cpf;
    @Column(name = "nome")
    private String nome;
    @Column(name = "email_colaborador")
    private String email;
    @Column(name = "cep")
    private String cep;
    @Column(name = "data_nascimento")
    private LocalDate data_nascimento;
    @Column(name = "data_inicio_contrato")
    private LocalDate data_contratacao_inicio;
    @Column(name = "data_rescisao_contrato")
    private LocalDate data_contratacao_recisao;
    @Column(name = "especificacoes_equipamentos_necessarios")
    private String equipamento_especificacao;

    @OneToOne(mappedBy = "colaborador", cascade = CascadeType.ALL, optional = false)
    @PrimaryKeyJoinColumn
    private Endereco enderco;


    public static Colaborador converterParaColaborador(ColaboradorRequestDTO colaboradorRequestDTO){
        Colaborador colaborador = new Colaborador();
        colaborador.setNome(colaboradorRequestDTO.getNome());
        colaborador.setCpf(colaboradorRequestDTO.getCpf());
        colaborador.setEmail(colaboradorRequestDTO.getEmail());
        colaborador.setCep(colaboradorRequestDTO.getCep());
        colaborador.setData_nascimento(colaboradorRequestDTO.getData_aniversario());
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
