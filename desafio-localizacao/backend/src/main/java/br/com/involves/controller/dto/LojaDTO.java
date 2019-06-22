package br.com.involves.controller.dto;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LojaDTO {

	
    private Long id;
    @NotNull(message = "O nome da loja não pode ser nulo.")
	private String name;
	@NotNull(message = "A latitude da localização da loja não pode ser nulo.")
	private float latitude;
	@NotNull(message = "A longitude da localização da loja não pode ser nulo.")
	private float longitude;
	
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
	
}
