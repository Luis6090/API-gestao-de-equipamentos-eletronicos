package br.com.projetofinal_grupo1.projetofinal_grupo1.domain.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterRequestDTO {

    private String email;
    private String senha;
}
