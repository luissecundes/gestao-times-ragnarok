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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.duxusdesafio.exceptions.Exceptions;
import br.com.duxusdesafio.models.Integrante;
import br.com.duxusdesafio.services.IntegranteService;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "/integrantes")
public class IntegranteController {

    @Autowired
    private IntegranteService IntegranteService;

    @GetMapping
    public ResponseEntity<?> findAll() {
        try {
            List<Integrante> result = IntegranteService.findAll();
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exceptions.NotFoundException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        try {
            Integrante result = IntegranteService.findById(id);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exceptions.NotFoundIdException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<String> save(@RequestBody Integrante integranteId) {
        try {
            IntegranteService.save(integranteId);
            return new ResponseEntity<>("Integrante Cadastrado com sucesso!!!", HttpStatus.CREATED);
        } catch (Exceptions.NotSavedException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<String> update(@PathVariable Long id, @RequestBody Integrante updatedIntegrante) {
        try {
            Integrante updated = IntegranteService.update(id, updatedIntegrante);
            if (updated != null) {
                return new ResponseEntity<>("Integrante atualizado com sucesso!", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Integrante não encontrado.", HttpStatus.NOT_FOUND);
            }
        } catch (Exceptions.NotUpdatedException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id) {
        try {
            IntegranteService.delete(id);
            return new ResponseEntity<>("Integrante excluído com sucesso!", HttpStatus.OK);
        } catch (Exceptions.NotDeletedException ex) {
            return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

