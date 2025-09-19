package com.zoo.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(name = "zona")
public class Zona {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_zona")
	private Long id_zona;
	
	@Column(name = "nome")
	private String nome;
	
	@Column(name = "capienza")
	private Integer capienza = 0;
	
	@ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_id_habitat", referencedColumnName = "id_habitat")
    private Habitat habitat;
	
	@OneToMany(mappedBy = "zona", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<Animale> animaliPresenti = new ArrayList<>();
	
	public Zona() {}

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

	@Transient
	public Integer getCapienzaAttuale() {
	    return capienza - animaliPresenti.size();
	}

	public List<Animale> getAnimaliPresenti() {
		return animaliPresenti;
	}

	public void setAnimaliPresenti(List<Animale> animaliPresenti) {
		this.animaliPresenti = animaliPresenti;
	}
	
	public void addAnimaleToAnimaliPresenti(Animale animale) {
	    if (!animaliPresenti.contains(animale)) {
	        animaliPresenti.add(animale);
	        animale.setZona(this);
	    }
	}

	public void removeAnimaleFromAnimaliPresenti(Animale animale) {
	    if (animaliPresenti.contains(animale)) {
	        animaliPresenti.remove(animale);
	        animale.setZona(null);
	    }
	}

	@Override
	public int hashCode() {
	    return Objects.hash(animaliPresenti, capienza, habitat, id_zona, nome);
	}

	@Override
	public boolean equals(Object obj) {
	    if (this == obj)
	        return true;
	    if (obj == null || getClass() != obj.getClass())
	        return false;
	    Zona other = (Zona) obj;
	    return Objects.equals(animaliPresenti, other.animaliPresenti)
	            && Objects.equals(capienza, other.capienza)
	            && Objects.equals(habitat, other.habitat)
	            && Objects.equals(id_zona, other.id_zona)
	            && Objects.equals(nome, other.nome);
	}

	@Override
	public String toString() {
	    StringBuilder result = new StringBuilder("Zona [");
	    result.append("ID: ").append(id_zona)
	          .append(", Nome: ").append(nome)
	          .append(", Habitat: ").append(habitat != null ? habitat.getNome() : "N/A")
	          .append(", Capienza Massima: ").append(capienza)
	          .append(", Capienza Attuale: ").append(getCapienzaAttuale());

	    if (!animaliPresenti.isEmpty()) {
	        result.append(", Animali Presenti: ").append(animaliPresenti.size()).append(" (");
	        for (int i = 0; i < animaliPresenti.size(); i++) {
	            result.append(animaliPresenti.get(i).getNome());
	            if (i < animaliPresenti.size() - 1) {
	                result.append(", ");
	            }
	        }
	        result.append(")");
	    } else {
	        result.append(", Animali Presenti: 0");
	    }

	    result.append("]");
	    return result.toString();
	}
}
