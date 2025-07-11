package br.com.projetofinal_grupo1.projetofinal_grupo1;

import br.com.projetofinal_grupo1.projetofinal_grupo1.dto.ParametroEstoqueRequestDTO;
import br.com.projetofinal_grupo1.projetofinal_grupo1.dto.ParametroEstoqueResponseDTO;
import br.com.projetofinal_grupo1.projetofinal_grupo1.dto.PrevisaoFaltaResponseDTO;
import br.com.projetofinal_grupo1.projetofinal_grupo1.model.Equipamento;
import br.com.projetofinal_grupo1.projetofinal_grupo1.model.Estoque;
import br.com.projetofinal_grupo1.projetofinal_grupo1.model.ParametroEstoque;
import br.com.projetofinal_grupo1.projetofinal_grupo1.model.TipoEquipamento;
import br.com.projetofinal_grupo1.projetofinal_grupo1.repository.EstoqueRepository;
import br.com.projetofinal_grupo1.projetofinal_grupo1.repository.ParametroEstoqueRepository;
import br.com.projetofinal_grupo1.projetofinal_grupo1.service.ParametroEstoqueService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ParametroEstoqueServiceTest {

	@Mock
	private ParametroEstoqueRepository parametroEstoqueRepository;

	@Mock
	private EstoqueRepository estoqueRepository;

	@InjectMocks
	private ParametroEstoqueService parametroEstoqueService;

	private ParametroEstoque parametroEstoque;
	private ParametroEstoqueRequestDTO requestDTO;
	private ParametroEstoqueResponseDTO responseDTO;

	@BeforeEach
	void setUp() {
		parametroEstoque = new ParametroEstoque();
		parametroEstoque.setIdParametro(1);
		parametroEstoque.setTempoMedioReposicao(5);
		parametroEstoque.setTempoMedioConsumoEstoque(30);
		parametroEstoque.setTempoMedioEnvio(3);
		parametroEstoque.setTaxaMediaEquipamentosDefeituosos(0.05);
		parametroEstoque.setEstoqueMinimoSeguranca(100);

		requestDTO = new ParametroEstoqueRequestDTO();
		requestDTO.setTempoMedioReposicao(5);
		requestDTO.setTempoMedioConsumoEstoque(30);
		requestDTO.setTempoMedioEnvio(3);
		requestDTO.setTaxaMediaEquipamentosDefeituosos(0.05);
		requestDTO.setEstoqueMinimoSeguranca(100);
	}

	@Test
	void testCriarParametro_Success() {
		when(parametroEstoqueRepository.save(any(ParametroEstoque.class))).thenReturn(parametroEstoque);


		ParametroEstoqueResponseDTO result = parametroEstoqueService.criarParametro(requestDTO);


		assertNotNull(result);
		assertEquals(parametroEstoque.getIdParametro(), result.getIdParametro());
		assertEquals(parametroEstoque.getTempoMedioReposicao(), result.getTempoMedioReposicao());
		assertEquals(parametroEstoque.getTempoMedioConsumoEstoque(), result.getTempoMedioConsumoEstoque());
		assertEquals(parametroEstoque.getTempoMedioEnvio(), result.getTempoMedioEnvio());
		assertEquals(parametroEstoque.getTaxaMediaEquipamentosDefeituosos(), result.getTaxaMediaEquipamentosDefeituosos());
		assertEquals(parametroEstoque.getEstoqueMinimoSeguranca(), result.getEstoqueMinimoSeguranca());

		verify(parametroEstoqueRepository, times(1)).save(any(ParametroEstoque.class));
	}

	@Test
	void testBuscarParametroPorId_Found() {
		when(parametroEstoqueRepository.findById(1)).thenReturn(Optional.of(parametroEstoque));

		Optional<ParametroEstoqueResponseDTO> result = parametroEstoqueService.buscarParametroPorId(1);

		assertTrue(result.isPresent());
		assertEquals(parametroEstoque.getIdParametro(), result.get().getIdParametro());
		verify(parametroEstoqueRepository, times(1)).findById(1);
	}

	@Test
	void testBuscarParametroPorId_NotFound() {
		when(parametroEstoqueRepository.findById(99)).thenReturn(Optional.empty());

		Optional<ParametroEstoqueResponseDTO> result = parametroEstoqueService.buscarParametroPorId(99);

		assertFalse(result.isPresent());
		verify(parametroEstoqueRepository, times(1)).findById(99);
	}

	@Test
	void testListarTodosParametros() {
		ParametroEstoque parametro2 = new ParametroEstoque();
		parametro2.setIdParametro(2);
		parametro2.setTempoMedioReposicao(7);
		parametro2.setTempoMedioConsumoEstoque(25);
		parametro2.setTempoMedioEnvio(4);
		parametro2.setTaxaMediaEquipamentosDefeituosos(0.03);
		parametro2.setEstoqueMinimoSeguranca(150);

		List<ParametroEstoque> parametros = Arrays.asList(parametroEstoque, parametro2);
		when(parametroEstoqueRepository.findAll()).thenReturn(parametros);


		List<ParametroEstoqueResponseDTO> result = parametroEstoqueService.listarTodosParametros();


		assertNotNull(result);
		assertEquals(2, result.size());
		assertEquals(parametroEstoque.getIdParametro(), result.get(0).getIdParametro());
		assertEquals(parametro2.getIdParametro(), result.get(1).getIdParametro());
		verify(parametroEstoqueRepository, times(1)).findAll();
	}

	@Test
	void testAtualizarParametro_Success() {
		when(parametroEstoqueRepository.findById(1)).thenReturn(Optional.of(parametroEstoque));
		when(parametroEstoqueRepository.save(any(ParametroEstoque.class))).thenReturn(parametroEstoque);

		ParametroEstoqueResponseDTO result = parametroEstoqueService.atualizarParametro(1, requestDTO);

		assertNotNull(result);
		assertEquals(parametroEstoque.getIdParametro(), result.getIdParametro());
		verify(parametroEstoqueRepository, times(1)).findById(1);
		verify(parametroEstoqueRepository, times(1)).save(any(ParametroEstoque.class));
	}

	@Test
	void testAtualizarParametro_NotFound() {
		when(parametroEstoqueRepository.findById(99)).thenReturn(Optional.empty());

		RuntimeException exception = assertThrows(RuntimeException.class,
				() -> parametroEstoqueService.atualizarParametro(99, requestDTO));

		assertEquals("Parâmetro de estoque não encontrado com o ID: 99", exception.getMessage());
		verify(parametroEstoqueRepository, times(1)).findById(99);
		verify(parametroEstoqueRepository, never()).save(any(ParametroEstoque.class));
	}

	@Test
	void testDeletarParametro_Success() {
		when(parametroEstoqueRepository.existsById(1)).thenReturn(true);
		parametroEstoqueService.deletarParametro(1);

		verify(parametroEstoqueRepository, times(1)).existsById(1);
		verify(parametroEstoqueRepository, times(1)).deleteById(1);
	}

	@Test
	void testDeletarParametro_NotFound() {

		when(parametroEstoqueRepository.existsById(99)).thenReturn(false);

		RuntimeException exception = assertThrows(RuntimeException.class,
				() -> parametroEstoqueService.deletarParametro(99));

		assertEquals("Parâmetro de estoque não encontrado com o ID: 99", exception.getMessage());
		verify(parametroEstoqueRepository, times(1)).existsById(99);
		verify(parametroEstoqueRepository, never()).deleteById(anyInt());
	}

	@Test
	void testGetLatestParametroEstoque_Found() {
		ParametroEstoque parametro1 = new ParametroEstoque();
		parametro1.setIdParametro(1);

		ParametroEstoque parametro2 = new ParametroEstoque();
		parametro2.setIdParametro(2);

		ParametroEstoque parametro3 = new ParametroEstoque();
		parametro3.setIdParametro(3);

		List<ParametroEstoque> parametros = Arrays.asList(parametro1, parametro3, parametro2);
		when(parametroEstoqueRepository.findAll()).thenReturn(parametros);

		Optional<ParametroEstoque> result = parametroEstoqueService.getLatestParametroEstoque();

		assertTrue(result.isPresent());
		assertEquals(3, result.get().getIdParametro());
		verify(parametroEstoqueRepository, times(1)).findAll();
	}

	@Test
	void testGetLatestParametroEstoque_EmptyList() {
		when(parametroEstoqueRepository.findAll()).thenReturn(Collections.emptyList());

		Optional<ParametroEstoque> result = parametroEstoqueService.getLatestParametroEstoque();

		assertFalse(result.isPresent());
		verify(parametroEstoqueRepository, times(1)).findAll();
	}

	@Test
	void testGerarRelatorioPrevisaoFalta_Success() {
		when(parametroEstoqueRepository.getLatestParametroEstoque()).thenReturn(Optional.of(parametroEstoque));

		TipoEquipamento tipo = new TipoEquipamento();
		tipo.setNomeTipo("Notebook");

		Equipamento equipamento1 = new Equipamento();
		equipamento1.setId(1);
		equipamento1.setNumeroSerie("SN123");
		equipamento1.setTipoEquipamento(tipo);

		Equipamento equipamento2 = new Equipamento();
		equipamento2.setId(2);
		equipamento2.setNumeroSerie("SN456");
		equipamento2.setTipoEquipamento(tipo);

		Estoque estoque1 = new Estoque();
		estoque1.setEquipamento(equipamento1);
		estoque1.setQuantidadeDisponivel(50);
		estoque1.setQuantidadeEmUso(150);
		estoque1.setQuantidadeDefeituosa(5);

		Estoque estoque2 = new Estoque();
		estoque2.setEquipamento(equipamento2);
		estoque2.setQuantidadeDisponivel(200);
		estoque2.setQuantidadeEmUso(100);
		estoque2.setQuantidadeDefeituosa(10);

		List<Estoque> estoques = Arrays.asList(estoque1, estoque2);
		when(estoqueRepository.findAll()).thenReturn(estoques);

		List<PrevisaoFaltaResponseDTO> result = parametroEstoqueService.gerarRelatorioPrevisaoFalta();

		assertNotNull(result);
		assertEquals(2, result.size());

		PrevisaoFaltaResponseDTO previsao1 = result.get(0);
		assertEquals(equipamento1.getId(), previsao1.getIdEquipamento());
		assertEquals("SN123", previsao1.getNumeroSerieEquipamento());
		assertEquals("Notebook", previsao1.getTipoEquipamento());
		assertEquals(50, previsao1.getQuantidadeDisponivelAtual());
		assertEquals(150, previsao1.getQuantidadeEmUsoAtual());
		assertEquals(5, previsao1.getQuantidadeDefeituosaAtual());
		assertTrue(previsao1.isEmRiscoDeFalta());
		assertEquals("ATENÇÃO: Em risco de falta!", previsao1.getMensagemAlerta());

		PrevisaoFaltaResponseDTO previsao2 = result.get(1);
		assertEquals(equipamento2.getId(), previsao2.getIdEquipamento());
		assertFalse(previsao2.isEmRiscoDeFalta());
		assertEquals("Estoque suficiente.", previsao2.getMensagemAlerta());

		verify(parametroEstoqueRepository, times(1)).getLatestParametroEstoque();
		verify(estoqueRepository, times(1)).findAll();
	}

	@Test
	void testGerarRelatorioPrevisaoFalta_SemParametros() {
		when(parametroEstoqueRepository.getLatestParametroEstoque()).thenReturn(Optional.empty());

		RuntimeException exception = assertThrows(RuntimeException.class,
				() -> parametroEstoqueService.gerarRelatorioPrevisaoFalta());

		assertEquals("Parâmetros de estoque não configurados.", exception.getMessage());
		verify(parametroEstoqueRepository, times(1)).getLatestParametroEstoque();
		verify(estoqueRepository, never()).findAll();
	}

	@Test
	void testGerarRelatorioPrevisaoFalta_ComEquipamentoSemTipo() {
		when(parametroEstoqueRepository.getLatestParametroEstoque()).thenReturn(Optional.of(parametroEstoque));

		Equipamento equipamento = new Equipamento();
		equipamento.setId(1);
		equipamento.setNumeroSerie("SN789");
		equipamento.setTipoEquipamento(null); // Sem tipo

		Estoque estoque = new Estoque();
		estoque.setEquipamento(equipamento);
		estoque.setQuantidadeDisponivel(100);
		estoque.setQuantidadeEmUso(50);
		estoque.setQuantidadeDefeituosa(2);

		when(estoqueRepository.findAll()).thenReturn(Collections.singletonList(estoque));

		List<PrevisaoFaltaResponseDTO> result = parametroEstoqueService.gerarRelatorioPrevisaoFalta();

		assertNotNull(result);
		assertEquals(1, result.size());
		assertEquals("N/A", result.get(0).getTipoEquipamento());
	}

	@Test
	void testGerarRelatorioPrevisaoFalta_ComConsumoZero() {
		ParametroEstoque parametroComConsumoZero = new ParametroEstoque();
		parametroComConsumoZero.setIdParametro(1);
		parametroComConsumoZero.setTempoMedioReposicao(5);
		parametroComConsumoZero.setTempoMedioConsumoEstoque(0); // Consumo zero
		parametroComConsumoZero.setTempoMedioEnvio(3);
		parametroComConsumoZero.setTaxaMediaEquipamentosDefeituosos(0.05);
		parametroComConsumoZero.setEstoqueMinimoSeguranca(100);

		when(parametroEstoqueRepository.getLatestParametroEstoque()).thenReturn(Optional.of(parametroComConsumoZero));

		Equipamento equipamento = new Equipamento();
		equipamento.setId(1);
		equipamento.setNumeroSerie("SN999");

		Estoque estoque = new Estoque();
		estoque.setEquipamento(equipamento);
		estoque.setQuantidadeDisponivel(100);
		estoque.setQuantidadeEmUso(50);
		estoque.setQuantidadeDefeituosa(2);

		when(estoqueRepository.findAll()).thenReturn(Collections.singletonList(estoque));

		List<PrevisaoFaltaResponseDTO> result = parametroEstoqueService.gerarRelatorioPrevisaoFalta();

		assertNotNull(result);
		assertEquals(1, result.size());
		assertEquals(0.0, result.get(0).getConsumoDiarioEstimado());
	}
}