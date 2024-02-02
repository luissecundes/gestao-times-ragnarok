package br.com.duxusdesafio.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.duxusdesafio.controller.IntegranteController;
import br.com.duxusdesafio.exceptions.Exceptions;
import br.com.duxusdesafio.models.Integrante;
import br.com.duxusdesafio.services.IntegranteService;

public class IntegranteControllerTest {
	
	@Mock
    private IntegranteService integranteService;

    @InjectMocks
    private IntegranteController integranteController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testFindAll() {
        List<Integrante> integrantes = new ArrayList<>();
        integrantes.add(new Integrante());
        when(integranteService.findAll()).thenReturn(integrantes);

        ResponseEntity<?> responseEntity = integranteController.findAll();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testFindById() {
        Long id = 1L;
        Integrante integrante = new Integrante();
        integrante.setId(id);
        when(integranteService.findById(id)).thenReturn(integrante);

        ResponseEntity<?> responseEntity = integranteController.findById(id);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }
    @Test
    public void testSave() {
        Integrante integrante = new Integrante();
        doThrow(new Exceptions.NotSavedException()).when(integranteService).save(integrante);

        ResponseEntity<String> responseEntity = integranteController.save(integrante);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals("Erro ao salvar integrante", responseEntity.getBody());
    }

    @Test
    public void testUpdate() {
        Long id = 1L;
        Integrante integrante = new Integrante();
        when(integranteService.update(id, integrante)).thenThrow(new Exceptions.NotUpdatedException(id)); // Sem mensagem

        ResponseEntity<String> responseEntity = integranteController.update(id, integrante);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals("Erro ao atualizar integrante", responseEntity.getBody()); // Mensagem padrão
    }

    @Test
    public void testDelete() {
        Long id = 1L;
        doThrow(new Exceptions.NotDeletedException("Erro ao excluir integrante")).when(integranteService).delete(id);

        ResponseEntity<String> responseEntity = integranteController.delete(id);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, responseEntity.getStatusCode());
        assertEquals("Erro ao excluir integrante", responseEntity.getBody());
    }

}
