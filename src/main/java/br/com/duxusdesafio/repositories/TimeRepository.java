package br.com.duxusdesafio.repositories;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.duxusdesafio.models.Time;

@Repository
public interface TimeRepository extends JpaRepository<Time, Long> {

	Time findByData(LocalDate dataDisponivel);

	List<Time> findByDataBetween(LocalDate dataInicial, LocalDate dataFim);
	
}