package br.com.duxusdesafio.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.duxusdesafio.models.Integrante;

public interface IntegranteRepository extends JpaRepository <Integrante, Long> {
	

}
