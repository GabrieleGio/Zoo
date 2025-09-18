package com.zoo.service;

import java.util.List;

import com.zoo.dao.RuoloDAO;
import com.zoo.entity.Ruolo;

public class RuoloService {

    private RuoloDAO ruoloDAO = new RuoloDAO();

    public Ruolo getRuoloByNome(String nome) {
        return ruoloDAO.findByNome(nome);
    }

    public Ruolo getRuoloById(Long id) {
        return ruoloDAO.findById(id);
    }

    public void saveRuolo(Ruolo ruolo) {
        ruoloDAO.save(ruolo);
    }

    public void deleteRuoloById(Long id) {
        ruoloDAO.deleteById(id);
    }

    public List<Ruolo> getTuttiIRuoli() {
        return ruoloDAO.findAll();
    }

    public void inizializzaRuoliBase() {
        String[] ruoliBase = { "ADMIN", "DIRETTORE", "USER", "IN_ATTESA" };

        for (String nomeRuolo : ruoliBase) {
            Ruolo ruolo = ruoloDAO.findByNome(nomeRuolo);
            if (ruolo == null) {
                ruoloDAO.save(new Ruolo(null, nomeRuolo));
            }
        }
    }
}
