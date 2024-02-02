package br.com.duxusdesafio.models;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "composicao_time")
public class ComposicaoTime {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@ManyToOne
	@JoinColumn(name = "time_id", nullable = false)
	private Time time;
	@ManyToOne
	@JoinColumn(name = "integrante_id", nullable = false)
	private Integrante integrante;

	public ComposicaoTime() {

	}

	public ComposicaoTime(Long id, Time time, Integrante integrante) {
		this.id = id;
		this.time = time;
		this.integrante = integrante;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public Integrante getIntegrante() {
		return integrante;
	}

	public void setIntegrante(Integrante integrante) {
		this.integrante = integrante;
	}

}
