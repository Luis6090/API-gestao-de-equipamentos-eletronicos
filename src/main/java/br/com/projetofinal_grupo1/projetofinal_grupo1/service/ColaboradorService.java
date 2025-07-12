package br.com.projetofinal_grupo1.projetofinal_grupo1.service;

import br.com.projetofinal_grupo1.projetofinal_grupo1.dto.ColaboradorRequestDTO;
import br.com.projetofinal_grupo1.projetofinal_grupo1.dto.ColaboradorResponseDTO;
import br.com.projetofinal_grupo1.projetofinal_grupo1.dto.ColaboradorUpdateDTO;
import br.com.projetofinal_grupo1.projetofinal_grupo1.model.Colaborador;
import br.com.projetofinal_grupo1.projetofinal_grupo1.model.Endereco;
import br.com.projetofinal_grupo1.projetofinal_grupo1.feign.EnderecoClient;
import br.com.projetofinal_grupo1.projetofinal_grupo1.repository.ColaboradorRepository;
import br.com.projetofinal_grupo1.projetofinal_grupo1.repository.EnderecoRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ColaboradorService {

    private final ColaboradorRepository colaboradorRepository;
    private final EnderecoRepository enderecoRepository;
    private final EnderecoClient enderecoClient;

    public ColaboradorService(ColaboradorRepository colaboradorRepository, EnderecoRepository enderecoRepository, EnderecoClient enderecoClient) {
        this.colaboradorRepository = colaboradorRepository;
        this.enderecoRepository = enderecoRepository;
        this.enderecoClient = enderecoClient;
    }

    public void registrarColaborador(ColaboradorRequestDTO colaboradorRequestDTO){
        colaboradorRepository.save(Colaborador.converterParaColaborador(colaboradorRequestDTO));
        registrarEndereco(colaboradorRequestDTO.getCep());
    }

    private void registrarEndereco(String cep){
        Endereco enderecoDoColaborador = enderecoClient.retornarEnderecoPorCep(cep);
        if (enderecoDoColaborador != null){
            enderecoRepository.save(enderecoDoColaborador);
        }
    }

    public List<ColaboradorResponseDTO> devolverTodosOsColaborador(){
        List<Colaborador>listaDeColaboradores = colaboradorRepository.findAll();
        List<ColaboradorResponseDTO> listaDeColaboradoresResponseDTO = new ArrayList<>();

        for (Colaborador colaborador: listaDeColaboradores){
            listaDeColaboradoresResponseDTO.add(ColaboradorResponseDTO.converterParaColaboradorResponseDTO(colaborador));
        }

        return listaDeColaboradoresResponseDTO;
    }

    public void editarNomeEmailDataContratacaoDataRecisaoEspecificacaoEquipamentoDoColaborador(Long id, ColaboradorUpdateDTO colaboradorUpdateDTO){
        Optional<Colaborador> colaborador = colaboradorRepository.findById(id);
        if(colaborador.isPresent()) {
            colaborador.get().atualizarColaborador(colaboradorUpdateDTO);
        }
    }

    public void deletarColaboradorPorId(Long id){
        Optional<Colaborador> colaboradorDemitido = colaboradorRepository.findById(id);
        if(colaboradorDemitido.isPresent()){
            colaboradorRepository.delete(colaboradorDemitido.get());
        }
    }
}
