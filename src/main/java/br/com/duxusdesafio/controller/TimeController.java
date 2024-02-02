package br.com.duxusdesafio.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.duxusdesafio.exceptions.Exceptions;
import br.com.duxusdesafio.models.Time;
import br.com.duxusdesafio.services.TimeService;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/times")
public class TimeController {

	@Autowired
	private TimeService timeService;


	@PostMapping
	public ResponseEntity<String> save(@RequestBody Time time) {
		try {
			timeService.save(time);
			return new ResponseEntity<>("Data de formação do time cadastrada com sucesso!", HttpStatus.CREATED);
		} catch (Exceptions.NotFoundException ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<String> update(@PathVariable Long id, @RequestBody Time updatedTime) {
		try {
			Time updated = timeService.update(id, updatedTime);
			if (updated != null) {
				return new ResponseEntity<>("Data atualizada com sucesso!", HttpStatus.OK);
			} else {
				return new ResponseEntity<>("Data não encontrada.", HttpStatus.NOT_FOUND);
			}
		} catch (Exceptions.NotUpdatedException ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id) {
		try {
			timeService.delete(id);
			return new ResponseEntity<>("Data excluída com sucesso!", HttpStatus.OK);
		} catch (Exceptions.NotDeletedException ex) {
			return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
