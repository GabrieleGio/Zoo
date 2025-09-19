package com.zoo.service;

import com.zoo.dao.AnimaleDAO;
import com.zoo.dao.ZonaDAO;
import com.zoo.entity.Animale;
import com.zoo.entity.Zona;

import java.util.List;

public class AnimaleService {

    private final AnimaleDAO animaleDAO = new AnimaleDAO();
    private final ZonaDAO zonaDAO = new ZonaDAO();

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
        Animale animale = animaleDAO.findById(id);

        if (animale == null) {
            return;
        }

        Zona zona = animale.getZona();
        if (zona != null) {
            zona.getAnimaliPresenti().remove(animale);
            animale.setZona(null);
            zonaDAO.update(zona);
        }

        animaleDAO.delete(animale.getId_animale());
    }
    
    public void spostaAnimaleInZona(Long animaleId, Long nuovaZonaId) {
        Animale animale = animaleDAO.findById(animaleId);
        if (animale == null) {
            throw new RuntimeException("Animale non trovato.");
        }

        Zona nuovaZona = zonaDAO.findById(nuovaZonaId);
        if (nuovaZona == null) {
            throw new RuntimeException("Zona non trovata.");
        }

        Zona vecchiaZona = animale.getZona();

        if (nuovaZona.getCapienzaAttuale() <= 0) {
            throw new RuntimeException("La zona di destinazione ha raggiunto la capienza massima.");
        }

        if (vecchiaZona != null && vecchiaZona.getId_zona().equals(nuovaZona.getId_zona())) {
            throw new RuntimeException("L'animale è già in questa zona.");
        }

        if (vecchiaZona != null) {
            vecchiaZona.removeAnimaleFromAnimaliPresenti(animale);
            zonaDAO.update(vecchiaZona);
        }

        nuovaZona.addAnimaleToAnimaliPresenti(animale);
        animale.setZona(nuovaZona);

        animaleDAO.update(animale);
        zonaDAO.update(nuovaZona);
    }


    public void rimuoviAnimaleDaZona(Long animaleId) {
        Animale animale = trovaPerId(animaleId);
        if (animale == null) {
            throw new RuntimeException("Animale non trovato.");
        }

        Zona zona = animale.getZona();
        if (zona != null) {
            zona.getAnimaliPresenti().remove(animale);
            animale.setZona(null);
            animaleDAO.update(animale);
            zonaDAO.update(zona);
        } else {
            animaleDAO.update(animale);
        }
    }




}

