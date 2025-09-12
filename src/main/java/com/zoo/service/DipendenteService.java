package com.zoo.service;

import com.zoo.dao.DipendenteDAO;
import com.zoo.entity.Dipendente;

import java.util.List;

public class DipendenteService {

    private final DipendenteDAO dipendenteDAO = new DipendenteDAO();

    public void creaDipendente(Dipendente dipendente) {
        dipendenteDAO.save(dipendente);
    }

    public Dipendente trovaPerId(Long id) {
        return dipendenteDAO.findById(id);
    }

    public List<Dipendente> trovaTutti() {
        return dipendenteDAO.findAll();
    }

    public void aggiornaDipendente(Dipendente dipendente) {
        dipendenteDAO.update(dipendente);
    }

    public void eliminaDipendente(Long id) {
        dipendenteDAO.delete(id);
    }

	public Dipendente trovaPerEmail(String email) {
		return dipendenteDAO.findByEmail(email);
	}

	public Dipendente trovaPerUsername(String username) {
		return dipendenteDAO.findByUsername(username);
	}
}
