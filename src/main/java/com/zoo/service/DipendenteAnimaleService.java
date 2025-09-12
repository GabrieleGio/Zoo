package com.zoo.service;

import com.zoo.dao.DipendenteAnimaleDAO;
import com.zoo.entity.DipendenteAnimale;

import java.util.List;

public class DipendenteAnimaleService {

    private final DipendenteAnimaleDAO dao = new DipendenteAnimaleDAO();

    public void assegnaDipendenteAAnimale(DipendenteAnimale relazione) {
        dao.save(relazione);
    }

    public DipendenteAnimale trovaPerId(Long id) {
        return dao.findById(id);
    }

    public List<DipendenteAnimale> trovaTutti() {
        return dao.findAll();
    }

    public void aggiornaRelazione(DipendenteAnimale relazione) {
        dao.update(relazione);
    }

    public void eliminaRelazione(Long id) {
        dao.delete(id);
    }
}
