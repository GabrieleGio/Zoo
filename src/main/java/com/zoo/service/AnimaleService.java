package com.zoo.service;

import com.zoo.dao.AnimaleDAO;
import com.zoo.entity.Animale;

import java.util.List;

public class AnimaleService {

    private final AnimaleDAO animaleDAO = new AnimaleDAO();

    public void creaAnimale(Animale animale) {
        animaleDAO.save(animale);
    }

    public Animale trovaPerId(Long id) {
        return animaleDAO.findById(id);
    }

    public List<Animale> trovaTutti() {
        return animaleDAO.findAll();
    }

    public void aggiornaAnimale(Animale animale) {
        animaleDAO.update(animale);
    }

    public void eliminaAnimale(Long id) {
        animaleDAO.delete(id);
    }
}
