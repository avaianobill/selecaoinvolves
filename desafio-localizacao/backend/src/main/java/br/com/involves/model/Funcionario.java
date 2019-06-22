package br.com.involves.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Funcionario implements Comparable<Funcionario>, Serializable{
	
	
	private static final long serialVersionUID = -558553967080513790L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(unique = true)
	private String name;
	private float latitude;
	private float longitude;
	
	@OneToMany
	@ElementCollection
	private List<Loja> lojas;
	
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
	
	public List<Loja> getLojas() {
		if(this.lojas == null) {
			this.lojas = new ArrayList<>();
		}
		return lojas;
	}
	
	public void setLojas(List<Loja> lojas) {
		this.lojas = lojas;
	}
	
	@Override
	public String toString() {
		return String.format("Funcionario - id: %s, nome: %s, latitude: %s e longetude: %s", this.id, this.name, this.latitude, this.longitude);
	}

	@Override
	public int compareTo(Funcionario o) {
		if(this.lojas.size() < o.getLojas().size()) {
			return -1;
		}else if(this.lojas.size() > o.getLojas().size()) {
			return 1;
		}
		return 0;
	}

}
