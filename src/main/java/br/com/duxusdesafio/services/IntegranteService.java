package br.com.duxusdesafio.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.duxusdesafio.models.Integrante;
import br.com.duxusdesafio.repositories.IntegranteRepository;

@Service
public class IntegranteService {

	@Autowired
	private IntegranteRepository integranteRepository;

	public List<Integrante> findAll() {
		return integranteRepository.findAll();
	}

	public Integrante findById(Long id) {
		Integrante result = integranteRepository.findById(id).get();
		return result;
	}

	public Integrante save(Integrante integrante) {
		return integranteRepository.save(integrante);
	}

	public Integrante update(Long id, Integrante updatedIntegrante) {
		if (integranteRepository.existsById(id)) {
			updatedIntegrante.setId(id);
			return integranteRepository.save(updatedIntegrante);
		} else {
			return null;
		}
	}

	public void delete(Long id) {
		integranteRepository.deleteById(id);
	}

	

}
