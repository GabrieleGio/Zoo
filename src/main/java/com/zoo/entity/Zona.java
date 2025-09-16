package com.zoo.entity;

import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "zona")
public class Zona {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_zona")
	private Long id_zona;
	
	@Column(name = "nome")
	private String nome;
	
	@Enumerated(EnumType.STRING)
	private Habitat habitat;
	
	@Column(name = "capienza")
	private Integer capienza;
	
	public Zona() {
		// TODO Auto-generated constructor stub
	}

	public Zona(Long id_zona, String nome, Habitat habitat, Integer capienza) {
		super();
		this.id_zona = id_zona;
		this.nome = nome;
		this.habitat = habitat;
		this.capienza = capienza;
	}

	public Long getId_zona() {
		return id_zona;
	}

	public void setId_zona(Long id_zona) {
		this.id_zona = id_zona;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Habitat getHabitat() {
		return habitat;
	}

	public void setHabitat(Habitat habitat) {
		this.habitat = habitat;
	}

	public Integer getCapienza() {
		return capienza;
	}

	public void setCapienza(Integer capienza) {
		this.capienza = capienza;
	}

	@Override
	public int hashCode() {
		return Objects.hash(capienza, habitat, id_zona, nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Zona other = (Zona) obj;
		return Objects.equals(capienza, other.capienza) && habitat == other.habitat
				&& Objects.equals(id_zona, other.id_zona) && Objects.equals(nome, other.nome);
	}

	@Override
	public String toString() {
		return "ID: " + id_zona + 
				"\nNome: " + nome + 
				"\nHabitat: " + habitat + 
				"\nCapienza: " + capienza;
	}
	
	
}
