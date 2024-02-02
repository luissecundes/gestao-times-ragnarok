package br.com.duxusdesafio.services;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.duxusdesafio.models.Time;
import br.com.duxusdesafio.repositories.TimeRepository;

@Service
public class TimeService {

	@Autowired
	private TimeRepository timeRepository;

	public List<Time> findAll() {
		var result = timeRepository.findAll();
		return result;
	}

	public Time save(Time time) {
		return timeRepository.save(time);
	}

	public Time update(Long id, Time updatedTime) {
		if (timeRepository.existsById(id)) {
			updatedTime.setId(id);
			return timeRepository.save(updatedTime);
		} else {
			return null;
		}
	}

	public void delete(Long id) {
		timeRepository.deleteById(id);
	}

	public Time findByDate(LocalDate data) {
		return this.timeRepository.findByData(data);
	}

	public List<Time> buscaPorPeriodo(LocalDate dataInicial, LocalDate dataFim) {
		return this.timeRepository.findByDataBetween(dataInicial, dataFim);
	}


}
