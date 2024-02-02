package br.com.duxusdesafio.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.duxusdesafio.dtos.TimeDto;
import br.com.duxusdesafio.services.ApiService;

@RestController
@RequestMapping("/api")
public class ApiController {

	@Autowired
	private ApiService apiService;

	@GetMapping("/buscartime")
	public ResponseEntity<TimeDto> buscarTimePorData(
			@RequestParam("data") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data) {
		TimeDto timeDto = apiService.timeDaData(data);
		return ResponseEntity.ok(timeDto);
	}

	@GetMapping("/integranteMaisUsado")
	public ResponseEntity<String> integranteMaisUsado(
			@RequestParam("dataInicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
			@RequestParam("dataFim") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim) {
		return ResponseEntity.ok(apiService.integranteMaisUsado(dataInicio, dataFim));
	}

	@GetMapping("/timeMaisComum")
	public ResponseEntity<List<String>> integrantesTimeMaisComum(
			@RequestParam("dataInicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
			@RequestParam("dataFim") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim) {
		List<String> integrantesTimeMaisComum = apiService.timeMaisComum(dataInicio, dataFim);
		return ResponseEntity.ok(integrantesTimeMaisComum);
	}

	@GetMapping("/funcaoMaisComum")
	public ResponseEntity<String> funcaoMaisComum(
			@RequestParam("dataInicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
			@RequestParam("dataFim") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim) {
		return ResponseEntity.ok(apiService.funcaoMaisComum(dataInicio, dataFim));
	}

	@GetMapping("/franquiaMaisFamosa")
	public ResponseEntity<String> franquiaMaisFamosa(
			@RequestParam("dataInicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
			@RequestParam("dataFim") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim) {
		return ResponseEntity.ok(apiService.franquiaMaisFamosa(dataInicio, dataFim));
	}

	@GetMapping("/contagemPorFranquia")
	public ResponseEntity<Map<String, Long>> contagemPorFranquia(
			@RequestParam("dataInicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
			@RequestParam("dataFim") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim) {
		return ResponseEntity.ok(apiService.contagemPorFranquia(dataInicio, dataFim));
	}

	@GetMapping("/contagemPorFuncao")
	public ResponseEntity<Map<String, Long>> contagemPorFuncao(
			@RequestParam("dataInicio") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataInicio,
			@RequestParam("dataFim") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFim) {
		return ResponseEntity.ok(apiService.contagemPorFuncao(dataInicio, dataFim));
	}

}
