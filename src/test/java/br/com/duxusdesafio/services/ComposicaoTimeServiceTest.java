package br.com.duxusdesafio.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.duxusdesafio.models.ComposicaoTime;
import br.com.duxusdesafio.models.Integrante;
import br.com.duxusdesafio.models.Time;
import br.com.duxusdesafio.repositories.ComposicaoTimeRepository;

public class ComposicaoTimeServiceTest {

    @Mock
    private ComposicaoTimeRepository composicaoTimeRepository;

    @InjectMocks
    private ComposicaoTimeService composicaoTimeService;

    @SuppressWarnings("deprecation")
	@BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testRetornaIntegrantes() {
        Time time = new Time();
        time.setId(1L);

        Integrante integrante1 = new Integrante();
        integrante1.setNome("Integrante 1");

        Integrante integrante2 = new Integrante();
        integrante2.setNome("Integrante 2");

        ComposicaoTime composicaoTime1 = new ComposicaoTime();
        composicaoTime1.setIntegrante(integrante1);

        ComposicaoTime composicaoTime2 = new ComposicaoTime();
        composicaoTime2.setIntegrante(integrante2);

        List<ComposicaoTime> composicaoTimes = new ArrayList<>();
        composicaoTimes.add(composicaoTime1);
        composicaoTimes.add(composicaoTime2);

        when(composicaoTimeRepository.findByTime(time)).thenReturn(composicaoTimes);
        List<String> result = composicaoTimeService.retornaIntegrantes(time);
        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.contains("Integrante 1"));
        assertTrue(result.contains("Integrante 2"));
    }
}