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
	
	@Column(name = "anni")
	private Integer anni;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_id_zona")
	private Zona zona;
	
	public Animale() {
		// TODO Auto-generated constructor stub
	}

	public Animale(Long id_animale, String nome, String specie, Integer anni, Zona zona) {
		super();
		this.id_animale = id_animale;
		this.nome = nome;
		this.specie = specie;
		this.anni = anni;
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

	public Integer getAnni() {
		return anni;
	}

	public void setAnni(Integer anni) {
		this.anni = anni;
	}

	public Zona getZona() {
		return zona;
	}

	public void setZona(Zona zona) {
		this.zona = zona;
	}

	@Override
	public int hashCode() {
		return Objects.hash(anni, id_animale, nome, specie, zona);
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
		return Objects.equals(anni, other.anni) && Objects.equals(id_animale, other.id_animale)
				&& Objects.equals(nome, other.nome) && Objects.equals(specie, other.specie)
				&& Objects.equals(zona, other.zona);
	}

	@Override
	public String toString() {
		return "Animale [id_animale=" + id_animale + ", nome=" + nome + ", specie=" + specie + ", anni=" + anni
				+ ", zona=" + zona + "]";
	}
	
	
}
