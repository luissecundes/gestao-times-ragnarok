package br.com.duxusdesafio.services;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.duxusdesafio.models.Time;
import br.com.duxusdesafio.repositories.TimeRepository;

public class TimeServiceTest {

	@Mock
	private TimeRepository timeRepository;

	@InjectMocks
	private TimeService timeService;

	@SuppressWarnings("deprecation")
	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void testFindAll() {
		List<Time> times = new ArrayList<>();
		times.add(new Time());
		times.add(new Time());
		when(timeRepository.findAll()).thenReturn(times);
		List<Time> result = timeService.findAll();
		assertNotNull(result);
		assertEquals(2, result.size());
	}

	@Test
	public void testSave() {
		Time time = new Time();
		when(timeRepository.save(time)).thenReturn(time);
		Time result = timeService.save(time);
		assertNotNull(result);
	}

	@Test
	public void testUpdate() {
		Long id = 1L;
		Time time = new Time();
		time.setId(id);
		when(timeRepository.existsById(id)).thenReturn(true);
		when(timeRepository.save(time)).thenReturn(time);
		Time result = timeService.update(id, time);
		assertNotNull(result);
		assertEquals(id, result.getId());
	}

	@Test
	public void testUpdate_ExistsById() {
		Long id = 1L;
		Time time = new Time();
		time.setId(id);
		when(timeRepository.existsById(id)).thenReturn(true);
		when(timeRepository.save(time)).thenReturn(time);
		Time result = timeService.update(id, time);
		assertNotNull(result);
		assertEquals(id, result.getId());
	}

	@Test
	public void testUpdate_NotExistsById() {
		Long id = 1L;
		Time time = new Time();
		when(timeRepository.existsById(id)).thenReturn(false);
		Time result = timeService.update(id, time);
		assertNull(result);
	}

	@Test
	public void testDelete() {
		Long id = 1L;
		timeService.delete(id);
		verify(timeRepository, times(1)).deleteById(id);
	}

	@Test
	public void testBuscaPorData() {
		LocalDate data = LocalDate.now();
		Time time = new Time();
		when(timeRepository.findByData(data)).thenReturn(time);
		Time result = timeService.findByDate(data);
		assertNotNull(result);
	}

	@Test
	public void testBuscaPorPeriodo() {
		LocalDate dataInicial = LocalDate.now().minusDays(1);
		LocalDate dataFim = LocalDate.now();
		List<Time> times = new ArrayList<>();
		when(timeRepository.findByDataBetween(dataInicial, dataFim)).thenReturn(times);
		List<Time> result = timeService.buscaPorPeriodo(dataInicial, dataFim);
		assertNotNull(result);
	}
}
