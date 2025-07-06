package br.com.projetofinal_grupo1.projetofinal_grupo1.feign;

import br.com.projetofinal_grupo1.projetofinal_grupo1.domain.model.Endereco;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "endereço_cliente", url = " viacep.com.br/ws")
public interface EnderecoClient {

    @GetMapping("{cep}/json")
    public Endereco retornarEnderecoPorCep(@PathVariable(name = "cep") String cep);

}
