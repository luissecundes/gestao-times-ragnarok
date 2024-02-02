package br.com.duxusdesafio.dtos;

import java.time.LocalDate;
import java.util.List;

public class TimeDto {

	private LocalDate data;
	private List<String> integrantes;

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public List<String> getIntegrantes() {
		return integrantes;
	}

	public void setIntegrantes(List<String> integrantes) {
		this.integrantes = integrantes;
	}
}
