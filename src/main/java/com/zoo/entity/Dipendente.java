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
@Table (name = "dipendente")
public class Dipendente {
	
	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	@Column (name = "id_dipendente")
	private Long id_dipendente;
	
	@Column (name = "nome")
	private String nome;
	
	@Column (name = "cognome")
	private String cognome;
	
	@Column (name = "username")
	private String username;
	
	@Column (name = "email")
	private String email;
	
	@Column (name = "password")
	private String password;
	
	@Enumerated(EnumType.STRING)
	@Column (name = "ruolo")
	private Ruolo ruolo;
	
	public Dipendente() {}

	public Dipendente(Long id_dipendente, String nome, String cognome, String username, String email, String password,
			Ruolo ruolo) {
		super();
		this.id_dipendente = id_dipendente;
		this.nome = nome;
		this.cognome = cognome;
		this.username = username;
		this.email = email;
		this.password = password;
		this.ruolo = ruolo;
	}

	public Long getId_dipendente() {
		return id_dipendente;
	}

	public void setId_dipendente(Long id_dipendente) {
		this.id_dipendente = id_dipendente;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Ruolo getRuolo() {
		return ruolo;
	}

	public void setRuolo(Ruolo ruolo) {
		this.ruolo = ruolo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cognome, email, id_dipendente, nome, password, ruolo, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Dipendente other = (Dipendente) obj;
		return Objects.equals(cognome, other.cognome) && Objects.equals(email, other.email)
				&& Objects.equals(id_dipendente, other.id_dipendente) && Objects.equals(nome, other.nome)
				&& Objects.equals(password, other.password) && ruolo == other.ruolo
				&& Objects.equals(username, other.username);
	}

	@Override
	public String toString() {
		return "Dipendente [id_dipendente=" + id_dipendente + ", nome=" + nome + ", cognome=" + cognome + ", username="
				+ username + ", email=" + email + ", password=" + password + ", ruolo=" + ruolo + "]";
	}
	
	
}
