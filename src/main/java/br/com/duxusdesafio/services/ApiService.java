package br.com.duxusdesafio.services;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.duxusdesafio.dtos.TimeDto;
import br.com.duxusdesafio.models.Time;

@Service
public class ApiService {
	@Autowired
	private TimeService timeService;

	/*
	 * Vai retornar uma lista com os nomes dos integrantes daquela data
	 */
	public TimeDto timeDaData(LocalDate data) {
		Time time = this.timeService.findByDate(data);
		List<String> integrantes = time.getTimes().stream().filter(Objects::nonNull)
				.map(t -> t.getIntegrante().getNome()).collect(Collectors.toList());
		TimeDto timeDto = new TimeDto();
		timeDto.setData(time.getData());
		timeDto.setIntegrantes(integrantes);
		return timeDto;

	}

	/**
	 * Vai retornar o integrante que tiver presente na maior quantidade de times
	 * dentro do período
	 */
	public String integranteMaisUsado(LocalDate dataInicial, LocalDate dataFinal) {
		List<Time> todosOsIntegrantes = this.timeService.buscaPorPeriodo(dataInicial, dataFinal);
		ConcurrentMap<String, Long> sumario = todosOsIntegrantes.stream()
				.map(time -> time.getTimes().stream().map(composicao -> composicao.getIntegrante().getNome())
						.collect(Collectors.toList()))
				.flatMap(List::stream).collect(Collectors.groupingByConcurrent(String::valueOf, Collectors.counting()));

		Entry<String, Long> entry = sumario.entrySet().stream().max(Entry.comparingByValue()).get();

		return entry.getKey();
	}

	/**
	 * Vai retornar uma lista com os nomes dos integrantes do time mais comum dentro
	 * do período
	 */
	public List<String> timeMaisComum(LocalDate dataInicial, LocalDate dataFinal) {
		List<Time> todosOsTimes = this.timeService.buscaPorPeriodo(dataInicial, dataFinal);
		Map<Long, Long> contagemPorTime = todosOsTimes.stream().flatMap(time -> time.getTimes().stream())
				.collect(Collectors.groupingBy(composicao -> composicao.getTime().getId(), Collectors.counting()));
		Optional<Map.Entry<Long, Long>> timeMaisComum = contagemPorTime.entrySet().stream()
				.max(Map.Entry.comparingByValue());
		if (timeMaisComum.isEmpty()) {
			return Collections.emptyList();
		}
		Long idTimeMaisComum = timeMaisComum.get().getKey();
		List<String> integrantesTimeMaisComum = todosOsTimes.stream().flatMap(time -> time.getTimes().stream())
				.filter(composicao -> composicao.getTime().getId().equals(idTimeMaisComum))
				.map(composicao -> composicao.getIntegrante().getNome()).collect(Collectors.toList());

		return integrantesTimeMaisComum;
	}

	/**
	 * Vai retornar a função mais comum nos times dentro do período
	 */
	public String funcaoMaisComum(LocalDate dataInicial, LocalDate dataFinal) {
		List<Time> todasAsFuncoes = this.timeService.buscaPorPeriodo(dataInicial, dataFinal);
		ConcurrentMap<String, Long> sumario = todasAsFuncoes.stream()
				.map(time -> time.getTimes().stream().map(composicao -> composicao.getIntegrante().getFuncao())
						.collect(Collectors.toList()))
				.flatMap(List::stream).collect(Collectors.groupingByConcurrent(String::valueOf, Collectors.counting()));

		Entry<String, Long> entry = sumario.entrySet().stream().max(Entry.comparingByValue()).get();

		return entry.getKey();
	}

	/**
	 * Vai retornar o nome da Franquia mais comum nos times dentro do período
	 */
	public String franquiaMaisFamosa(LocalDate dataInicial, LocalDate dataFinal) {
		List<Time> todasAsFranquias = this.timeService.buscaPorPeriodo(dataInicial, dataFinal);
		ConcurrentMap<String, Long> sumario = todasAsFranquias.stream()
				.map(time -> time.getTimes().stream().map(composicao -> composicao.getIntegrante().getFranquia())
						.collect(Collectors.toList()))
				.flatMap(List::stream).collect(Collectors.groupingByConcurrent(String::valueOf, Collectors.counting()));

		Entry<String, Long> entry = sumario.entrySet().stream().max(Entry.comparingByValue()).get();

		return entry.getKey();
	}

	/**
	 * Vai retornar o nome da Franquia mais comum nos times dentro do período
	 */
	public Map<String, Long> contagemPorFranquia(LocalDate dataInicial, LocalDate dataFinal) {
		List<Time> franquiaTotal = this.timeService.buscaPorPeriodo(dataInicial, dataFinal);
		ConcurrentMap<String, Long> sumario = franquiaTotal.stream().flatMap(time -> time.getTimes().stream())
				.map(composicao -> composicao.getIntegrante().getFranquia())
				.collect(Collectors.groupingByConcurrent(String::valueOf, Collectors.counting()));

		return sumario;
	}

	/**
	 * Vai retornar o número (quantidade) de Funções dentro do período
	 */
	public Map<String, Long> contagemPorFuncao(LocalDate dataInicial, LocalDate dataFinal) {
		List<Time> funcoesTotal = this.timeService.buscaPorPeriodo(dataInicial, dataFinal);
		ConcurrentMap<String, Long> sumario = funcoesTotal.stream().flatMap(time -> time.getTimes().stream())
				.map(composicao -> composicao.getIntegrante().getFuncao())
				.collect(Collectors.groupingByConcurrent(String::valueOf, Collectors.counting()));

		return sumario;
	}

}
