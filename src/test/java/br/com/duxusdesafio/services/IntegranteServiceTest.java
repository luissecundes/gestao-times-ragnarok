package br.com.duxusdesafio.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.duxusdesafio.models.Integrante;
import br.com.duxusdesafio.repositories.IntegranteRepository;

public class IntegranteServiceTest {

    @Mock
    private IntegranteRepository integranteRepository;

    @InjectMocks
    private IntegranteService integranteService;

    @SuppressWarnings("deprecation")
	@BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testFindAll() {
        List<Integrante> integrantes = new ArrayList<>();
        integrantes.add(new Integrante());
        integrantes.add(new Integrante());
        when(integranteRepository.findAll()).thenReturn(integrantes);
        List<Integrante> result = integranteService.findAll();
        assertEquals(2, result.size());
    }

    @Test
    public void testFindById() {
        Long id = 1L;
        Integrante integrante = new Integrante();
        integrante.setId(id);
        when(integranteRepository.findById(id)).thenReturn(Optional.of(integrante));
        Integrante result = integranteService.findById(id);
        assertNotNull(result);
        assertEquals(id, result.getId());
    }

    @Test
    public void testSave() {
        Integrante integrante = new Integrante();
        when(integranteRepository.save(integrante)).thenReturn(integrante);
        Integrante result = integranteService.save(integrante);
        assertNotNull(result);
    }

    @Test
    public void testUpdate() {
        Long id = 1L;
        Integrante integrante = new Integrante();
        integrante.setId(id);
        when(integranteRepository.existsById(id)).thenReturn(true);
        when(integranteRepository.save(integrante)).thenReturn(integrante);
        Integrante result = integranteService.update(id, integrante);
        assertNotNull(result);
        assertEquals(id, result.getId());
    }

    @Test
    public void testDelete() {
        Long id = 1L;
        integranteService.delete(id);
        verify(integranteRepository, times(1)).deleteById(id);
    }
}