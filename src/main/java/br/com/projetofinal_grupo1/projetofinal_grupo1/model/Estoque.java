package br.com.projetofinal_grupo1.projetofinal_grupo1.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "estoque")
public class Estoque {

    @Id
    @Column(name = "id_estoque")
    private int idEstoque;

    @OneToOne
    @JoinColumn(name = "id_equipamento", nullable = false)
    @MapsId
    private Equipamento equipamento;

    @Column(name = "quantidade_disponivel", nullable = false)
    private int quantidadeDisponivel;

    @Column(name = "quantidade_em_uso", nullable = false)
    private int quantidadeEmUso;

    @Column(name = "quantidade_defeituosa", nullable = false)
    private int quantidadeDefeituosa;
}