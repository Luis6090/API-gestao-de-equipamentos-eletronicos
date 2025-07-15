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
@Table(name = "colaborador")
public class Colaborador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cpf;
    private String nome;
    private String email;
    private String cep;
    private LocalDate dataNascimento;
    private LocalDate dataContratacaoInicio;
    private LocalDate dataContratacaoRecisao;
    private String equipamentoEspecificacao;

    @OneToOne(mappedBy = "colaborador", cascade = CascadeType.ALL, optional = false)
    @PrimaryKeyJoinColumn
    private Endereco idEndereco;

    @ManyToMany(mappedBy = "")

    public static Colaborador converterParaColaborador(ColaboradorRequestDTO colaboradorRequestDTO){
        Colaborador colaborador = new Colaborador();
        colaborador.setNome(colaboradorRequestDTO.getNome());
        colaborador.setCpf(colaboradorRequestDTO.getCpf());
        colaborador.setEmail(colaboradorRequestDTO.getEmail());
        colaborador.setCep(colaboradorRequestDTO.getCep());
        colaborador.setDataNascimento(colaboradorRequestDTO.getData_aniversario());
        colaborador.setDataContratacaoInicio(colaboradorRequestDTO.getData_contratacao_inicio());
        colaborador.setDataContratacaoRecisao(colaboradorRequestDTO.getData_contratacao_recisao());
        colaborador.setEquipamentoEspecificacao(colaboradorRequestDTO.getEspecificacao_equipamento());

        return colaborador;
    }

    public void atualizarColaborador(ColaboradorUpdateDTO colaboradorUpdateDTO){
        setNome(colaboradorUpdateDTO.getNome());
        setCep(colaboradorUpdateDTO.getCep());
        setEmail(colaboradorUpdateDTO.getEmail());
        setDataContratacaoInicio(colaboradorUpdateDTO.getDataContratacaoInicio());
        setEquipamentoEspecificacao(colaboradorUpdateDTO.getEquipamentoEspecificacao());
    }
}
