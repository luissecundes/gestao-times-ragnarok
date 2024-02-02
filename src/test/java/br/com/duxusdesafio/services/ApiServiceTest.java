package br.com.duxusdesafio.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.duxusdesafio.dtos.TimeDto;
import br.com.duxusdesafio.models.ComposicaoTime;
import br.com.duxusdesafio.models.Integrante;
import br.com.duxusdesafio.models.Time;

public class ApiServiceTest {

	@Mock
	private TimeService timeService;

	@InjectMocks
	private ApiService apiService;

	@SuppressWarnings("deprecation")
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testTimeDaDataComIntegrantes() {
		LocalDate data = LocalDate.now();
		Time time = new Time();
		time.setData(data);

		List<String> integrantes = new ArrayList<>();
		integrantes.add("Integrante 1");
		integrantes.add("Integrante 2");

		TimeDto expectedTimeDto = new TimeDto();
		expectedTimeDto.setData(data);
		expectedTimeDto.setIntegrantes(integrantes);
		when(timeService.findByDate(data)).thenReturn(time);
	}

	@Test
	public void testTimeDaDataSemIntegrantes() {
		LocalDate data = LocalDate.now();
		Time time = new Time();
		time.setData(data);

		when(timeService.findByDate(data)).thenReturn(time);

		TimeDto result = apiService.timeDaData(data);

		Assertions.assertEquals(data, result.getData());
		Assertions.assertTrue(result.getIntegrantes().isEmpty());
	}

	@Test
	public void testIntegranteMaisUsado() {
		LocalDate dataInicial = LocalDate.of(2023, 1, 1);
		LocalDate dataFinal = LocalDate.of(2023, 12, 31);
		List<Time> times = Arrays.asList(
				createTimeWithIntegrantes("Time1", "Integrante1", "Integrante2", "Integrante2"),
				createTimeWithIntegrantes("Time2", "Integrante1", "Integrante2", "Integrante3", "Integrante3",
						"Integrante3"));

		when(timeService.buscaPorPeriodo(dataInicial, dataFinal)).thenReturn(times);
		String integranteMaisUsado = apiService.integranteMaisUsado(dataInicial, dataFinal);
		Assertions.assertEquals("Integrante2", integranteMaisUsado);
	}

	private Time createTimeWithIntegrantes(String nomeTime, String... nomesIntegrantes) {
		Time time = new Time();

		List<ComposicaoTime> composicoes = new ArrayList<>();
		for (String nomeIntegrante : nomesIntegrantes) {
			ComposicaoTime composicao = new ComposicaoTime();
			Integrante integrante = new Integrante();
			integrante.setNome(nomeIntegrante);
			composicao.setIntegrante(integrante);
			composicao.setTime(time);
			composicoes.add(composicao);
		}
		time.setTimes(composicoes.stream().collect(Collectors.toSet()));
		return time;
	}

	@Test
	public void testTimeMaisComum() {
		LocalDate dataInicial = LocalDate.of(2023, 1, 1);
		LocalDate dataFinal = LocalDate.of(2023, 12, 31);
		List<Time> times = Arrays.asList(createTimeWithIntegrantes("Time1", "Integrante1", "Integrante2"),
				createTimeWithIntegrantes("Time2", "Integrante2", "Integrante3", "Integrante3"));
		when(timeService.buscaPorPeriodo(dataInicial, dataFinal)).thenReturn(times);
		List<String> resultado = apiService.timeMaisComum(dataInicial, dataFinal);
		assertEquals(2, resultado.size());
		assertTrue(resultado.contains("Integrante2"));
		assertTrue(resultado.contains("Integrante3"));
	}

	@Test
	public void testFuncaoMaisComum() {
		LocalDate dataInicial = LocalDate.of(2023, 1, 1);
		LocalDate dataFinal = LocalDate.of(2023, 12, 31);
		List<Time> times = Arrays.asList(createTimeWithIntegrantes("Time1", "Funcao1", "Funcao2"),
				createTimeWithIntegrantes("Time2", "Funcao2", "Funcao3", "Funcao3"));
		when(timeService.buscaPorPeriodo(dataInicial, dataFinal)).thenReturn(times);
		String resultado = apiService.funcaoMaisComum(dataInicial, dataFinal);
		assertEquals("Funcao2", resultado);
	}

	@Test
	public void testFranquiaMaisFamosa() {
		LocalDate dataInicial = LocalDate.of(2023, 1, 1);
		LocalDate dataFinal = LocalDate.of(2023, 12, 31);
		List<Time> times = Arrays.asList(createTimeWithIntegrantes("Time1", "Franquia1", "Franquia2"),
				createTimeWithIntegrantes("Time2", "Franquia2", "Franquia3", "Franquia3"));
		when(timeService.buscaPorPeriodo(dataInicial, dataFinal)).thenReturn(times);
		String resultado = apiService.franquiaMaisFamosa(dataInicial, dataFinal);
		assertEquals("Franquia2", resultado);
	}

	@Test
	public void testContagemPorFranquia() {
		LocalDate dataInicial = LocalDate.of(2023, 1, 1);
		LocalDate dataFinal = LocalDate.of(2023, 12, 31);
		List<Time> times = Arrays.asList(createTimeWithIntegrantes("Time1", "Franquia1", "Franquia2"),
				createTimeWithIntegrantes("Time2", "Franquia2", "Franquia3", "Franquia3"));

		when(timeService.buscaPorPeriodo(dataInicial, dataFinal)).thenReturn(times);

		Map<String, Long> resultado = apiService.contagemPorFranquia(dataInicial, dataFinal);

		assertEquals(3, resultado.size());
		assertEquals(1L, resultado.get("Franquia1").longValue());
		assertEquals(2L, resultado.get("Franquia2").longValue());
		assertEquals(2L, resultado.get("Franquia3").longValue());
	}

	@Test
	public void testContagemPorFuncao() {
		List<Time> times = Arrays.asList(createTimeWithIntegrantes("Time1", "Funcao1", "Funcao2", "Funcao1"),
				createTimeWithIntegrantes("Time2", "Funcao2", "Funcao3"));
		LocalDate dataInicial = LocalDate.now().minusDays(7);
		LocalDate dataFinal = LocalDate.now();
		when(timeService.buscaPorPeriodo(dataInicial, dataFinal)).thenReturn(times);
		Map<String, Long> resultado = apiService.contagemPorFuncao(dataInicial, dataFinal);
		assertEquals(3, resultado.size());
		assertEquals(2L, resultado.get("Funcao1").longValue());
		assertEquals(3L, resultado.get("Funcao2").longValue());
		assertEquals(1L, resultado.get("Funcao3").longValue());
	}

}
