package br.com.involves.controller.dto;

import java.util.List;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FuncionarioDTO {

	private Long id;
	
	@NotNull(message = "O nome do funcionario não pode ser nulo.")
	private String name;
	@NotNull(message = "A latitude da localização do funcionario não pode ser nulo.")
	private float latitude;
	@NotNull(message = "A longitude da localização do funcionario não pode ser nulo.")
	private float longitude;
	
	private List<LojaDTO> lojas;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getLatitude() {
		return latitude;
	}

	public void setLatitude(float latitude) {
		this.latitude = latitude;
	}

	public float getLongitude() {
		return longitude;
	}

	public void setLongitude(float longitude) {
		this.longitude = longitude;
	}

	public List<LojaDTO> getLojas() {
		return lojas;
	}

	public void setLojas(List<LojaDTO> lojas) {
		this.lojas = lojas;
	}
	
}
