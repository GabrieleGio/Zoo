package com.zoo.service;

import com.zoo.dao.ZonaDAO;
import com.zoo.entity.Zona;

import java.util.List;

public class ZonaService {

    private final ZonaDAO zonaDAO = new ZonaDAO();

    public void creaZona(Zona zona) {
        zonaDAO.save(zona);
    }

    public Zona trovaPerId(Long id) {
        return zonaDAO.findById(id);
    }

    public List<Zona> trovaTutte() {
        return zonaDAO.findAll();
    }

    public void aggiornaZona(Zona zona) {
        zonaDAO.update(zona);
    }

    public void eliminaZona(Long id) {
        zonaDAO.delete(id);
    }
}
