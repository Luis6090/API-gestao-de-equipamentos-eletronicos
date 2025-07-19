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
        Colaborador colaborador = Colaborador.converterParaColaborador(colaboradorRequestDTO);
        Endereco enderecoDoColaborador = enderecoClient.retornarEnderecoPorCep(colaborador.getCep());
        enderecoDoColaborador.setNumeroCasa(colaboradorRequestDTO.getNumeroCasa());

        colaborador.setEndereco(enderecoDoColaborador);
        enderecoDoColaborador.setColaborador(colaborador);

        colaboradorRepository.save(colaborador);
        enderecoRepository.save(enderecoDoColaborador);
    }

    public List<ColaboradorResponseDTO> devolverTodosOsColaborador(){
        List<Colaborador>listaDeColaboradores = colaboradorRepository.findAll();
        List<ColaboradorResponseDTO> listaDeColaboradoresResponseDTO = new ArrayList<>();

        for (Colaborador colaborador: listaDeColaboradores){
            listaDeColaboradoresResponseDTO.add(ColaboradorResponseDTO.converterParaColaboradorResponseDTO(colaborador));
        }

        return listaDeColaboradoresResponseDTO;
    }

    public ColaboradorResponseDTO devolverColaboradorPorId(Long id){
        return ColaboradorResponseDTO.converterParaColaboradorResponseDTO(colaboradorRepository.findById(id).get());
    }

    public void editarNomeEmailDataContratacaoDataRecisaoEspecificacaoEquipamentoDoColaborador(Long id, ColaboradorUpdateDTO colaboradorUpdateDTO){
        Colaborador colaborador = colaboradorRepository.findById(id).get();
        colaborador.atualizarColaborador(colaboradorUpdateDTO);
        colaboradorRepository.editarColaborador(id, colaborador.getNome(), colaborador.getEmail(), colaborador.getCep()
                                                , colaborador.getDataContratacaoInicio(), colaborador.getDataContratacaoRecisao()
                                                , colaborador.getEquipamentoEspecificacao());

       Endereco enderecoNovo = enderecoClient.retornarEnderecoPorCep(colaborador.getCep());
       Endereco enderecoDoColaborador = enderecoRepository.findById(id).get();

       if(!enderecoNovo.getCep().equals(enderecoDoColaborador.getCep())){
          enderecoRepository.editarEndereco(id, enderecoNovo.getCep(), enderecoNovo.getLogradouro(), enderecoNovo.getComplemento()
                                            , enderecoNovo.getUnidade(), enderecoNovo.getBairro(), enderecoNovo.getLocalidade(), enderecoNovo.getUf()
                                            , enderecoNovo.getEstado(), enderecoNovo.getRegiao(), enderecoNovo.getIbge(), enderecoNovo.getGia()
                                            , enderecoNovo.getDdd(), enderecoNovo.getSiafi());
       }
    }

    public void deletarColaboradorPorId(Long id){
        Optional<Colaborador> colaboradorDemitido = colaboradorRepository.findById(id);
        if(colaboradorDemitido.isPresent()){
            colaboradorRepository.delete(colaboradorDemitido.get());
        }
    }
}
