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
@Table(name = "dipendente_animale")
public class DipendenteAnimale {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_dipendente_animale")
	private Long id_dipendente_animale;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_id_dipendente")
	private Dipendente dipendente;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_id_animale")
	private Animale animale;
	
	public DipendenteAnimale() {}

	public DipendenteAnimale(Long id_dipendente_animale, Dipendente dipendente, Animale animale) {
		super();
		this.id_dipendente_animale = id_dipendente_animale;
		this.dipendente = dipendente;
		this.animale = animale;
	}

	public Long getId_dipendente_animale() {
		return id_dipendente_animale;
	}

	public void setId_dipendente_animale(Long id_dipendente_animale) {
		this.id_dipendente_animale = id_dipendente_animale;
	}

	public Dipendente getDipendente() {
		return dipendente;
	}

	public void setDipendente(Dipendente dipendente) {
		this.dipendente = dipendente;
	}

	public Animale getAnimale() {
		return animale;
	}

	public void setAnimale(Animale animale) {
		this.animale = animale;
	}

	@Override
	public int hashCode() {
		return Objects.hash(animale, dipendente, id_dipendente_animale);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		DipendenteAnimale other = (DipendenteAnimale) obj;
		return Objects.equals(animale, other.animale) && Objects.equals(dipendente, other.dipendente)
				&& Objects.equals(id_dipendente_animale, other.id_dipendente_animale);
	}

	@Override
	public String toString() {
		return "DipendenteAnimale [id_dipendente_animale=" + id_dipendente_animale + ", dipendente=" + dipendente
				+ ", animale=" + animale + "]";
	}
}
