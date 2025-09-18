package com.zoo.entity;


import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table (name = "animale")
public class Animale {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_animale")
	private Long id_animale;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "specie")
	private String specie;
	
	@Column(name = "anno_nascita")
	private Integer annoNascita;
	
	@Column(name = "habitat_preferito")
	private String habitatPreferito;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_id_zona")
	private Zona zona;
	
	public Animale() {}

	public Animale(Long id_animale, String nome, String specie, Integer annoNascita, String habitatPreferito, Zona zona) {
		super();
		this.id_animale = id_animale;
		this.nome = nome;
		this.specie = specie;
		this.annoNascita = annoNascita;
		this.habitatPreferito = habitatPreferito;
		this.zona = zona;
	}


	public Long getId_animale() {
		return id_animale;
	}

	public void setId_animale(Long id_animale) {
		this.id_animale = id_animale;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSpecie() {
		return specie;
	}

	public void setSpecie(String specie) {
		this.specie = specie;
	}

	public Integer getAnnoNascita() {
		return annoNascita;
	}

	public void setAnnoNascita(Integer annoNascita) {
		this.annoNascita = annoNascita;
	}
	
	
	public String getHabitatPreferito() {
		return habitatPreferito;
	}

	public void setHabitatPreferito(String habitatPreferito) {
		this.habitatPreferito = habitatPreferito;
	}

	public Zona getZona() {
		return zona;
	}

	public void setZona(Zona zona) {
		this.zona = zona;
	}
	
	
	@Override
	public int hashCode() {
		return Objects.hash(annoNascita, habitatPreferito, id_animale, nome, specie, zona);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Animale other = (Animale) obj;
		return Objects.equals(annoNascita, other.annoNascita)
				&& Objects.equals(habitatPreferito, other.habitatPreferito)
				&& Objects.equals(id_animale, other.id_animale) && Objects.equals(nome, other.nome)
				&& Objects.equals(specie, other.specie) && Objects.equals(zona, other.zona);
	}

	@Override
	public String toString() {
		return "ID: " + id_animale + 
				"\nNome: " + nome + 
				"\nSpecie: " + specie + 
				"\nAnno di nascita: " + annoNascita +
				"\nZona: " + zona;
	}
	
}
