package br.com.duxusdesafio.dtos;

public class ComposicaoTimeDto {

	private Long timeId;
	private Long integranteId;

	public Long getIntegranteId() {
		return integranteId;
	}

	public void setIntegranteId(Long integranteId) {
		this.integranteId = integranteId;
	}

	public Long getTimeId() {
		return timeId;
	}

	public void setTimeId(Long timeId) {
		this.timeId = timeId;
	}

}
