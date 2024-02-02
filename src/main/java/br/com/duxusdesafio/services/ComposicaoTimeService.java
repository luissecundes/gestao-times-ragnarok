package br.com.duxusdesafio.services;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.duxusdesafio.models.ComposicaoTime;
import br.com.duxusdesafio.models.Integrante;
import br.com.duxusdesafio.models.Time;
import br.com.duxusdesafio.repositories.ComposicaoTimeRepository;
import br.com.duxusdesafio.repositories.IntegranteRepository;
import br.com.duxusdesafio.repositories.TimeRepository;

@Service
public class ComposicaoTimeService {

	@Autowired
	private ComposicaoTimeRepository composicaoTimeRepository;

	@Autowired
	private IntegranteRepository integranteRepository;

	@Autowired
	private TimeRepository timeRepository;

	public List<String> retornaIntegrantes(Time time) {
		return composicaoTimeRepository.findByTime(time).stream().filter(Objects::nonNull).map(c -> c.getIntegrante())
				.map(i -> i.getNome()).collect(Collectors.toList());
	}

	public void registraTime(Long integranteId, Long timeId) {
        Optional<Integrante> integranteOptional = integranteRepository.findById(integranteId);
        Optional<Time> timeOptional = timeRepository.findById(timeId);
        if (integranteOptional.isEmpty() || timeOptional.isEmpty()) {
            throw new IllegalArgumentException("Integrante ou time não encontrado.");
        }
        ComposicaoTime composicaoTime = new ComposicaoTime();
        composicaoTime.setIntegrante(integranteOptional.get());
        composicaoTime.setTime(timeOptional.get());
        composicaoTimeRepository.save(composicaoTime);
    }
	
	
	
	public void deletaComposicaoTime(Long composicaoTimeId) {
        composicaoTimeRepository.deleteById(composicaoTimeId);
    }

}
