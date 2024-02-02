package br.com.duxusdesafio.controller;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.duxusdesafio.dtos.ComposicaoTimeDto;
import br.com.duxusdesafio.exceptions.Exceptions;
import br.com.duxusdesafio.models.Time;
import br.com.duxusdesafio.services.ComposicaoTimeService;
import br.com.duxusdesafio.services.TimeService;

@RestController
@RequestMapping("/api/composicao")
public class ComposicaoTimeController {

	@Autowired
	private ComposicaoTimeService composicaoTimeService;
	@Autowired
	private TimeService timeService;

	@GetMapping("/integrantes")
	public ResponseEntity<List<String>> getIntegrantesDoTime(
			@RequestParam("data") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate data) {
		Time time = timeService.findByDate(data);
		List<String> integrantes = composicaoTimeService.retornaIntegrantes(time);
		return ResponseEntity.ok(integrantes);
	}

	@PostMapping("/registrar")
	public ResponseEntity<String> registrarComposicaoTime(@RequestBody ComposicaoTimeDto composicaoTime) {
		try {
			composicaoTimeService.registraTime(composicaoTime.getIntegranteId(), composicaoTime.getTimeId());
			return ResponseEntity.ok("Composição de time registrada com sucesso.");
		} catch (IllegalArgumentException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
	}

	@DeleteMapping("/deletar/{id}")
	public ResponseEntity<String> deletarComposicaoTime(@PathVariable("id") Long composicaoTimeId) {
		try {
			composicaoTimeService.deletaComposicaoTime(composicaoTimeId);
			return ResponseEntity.ok("Time excluído com sucesso.");
		} catch (Exception e) {
			return ResponseEntity.badRequest().body("Erro ao deletar composição de time: " + e.getMessage());
		}
	}

}
