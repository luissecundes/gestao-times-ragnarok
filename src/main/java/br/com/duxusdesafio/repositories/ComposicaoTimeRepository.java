package br.com.duxusdesafio.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.duxusdesafio.models.ComposicaoTime;
import br.com.duxusdesafio.models.Time;

@Repository
public interface ComposicaoTimeRepository extends JpaRepository<ComposicaoTime, Long> {

	List<ComposicaoTime> findByTime(Time time);
}
