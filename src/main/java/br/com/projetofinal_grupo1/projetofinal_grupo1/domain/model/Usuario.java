package br.com.projetofinal_grupo1.projetofinal_grupo1.domain.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "usuario")
@ToString
@Entity
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String email;
    private String senha;
    private boolean emailConfirmado;

    private String tokenRecuperacao;
    private LocalDateTime tokenRecuperacaoValidade;

}
