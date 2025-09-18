package com.zoo.service;

import java.util.List;

import com.zoo.dao.HabitatDAO;
import com.zoo.entity.Habitat;

public class HabitatService {

    private HabitatDAO habitatDAO = new HabitatDAO();

    public void creaHabitat(String nome) {
        Habitat habitat = new Habitat(null, nome);
        habitatDAO.save(habitat);
    }

    public Habitat cercaPerId(Long id) {
        return habitatDAO.findById(id);
    }

    public Habitat cercaPerNome(String nome) {
        return habitatDAO.findByNome(nome);
    }

    public List<Habitat> listaTutti() {
        return habitatDAO.findAll();
    }

    public void aggiornaHabitat(Habitat habitat) {
        habitatDAO.update(habitat);
    }

    public void eliminaPerId(Long id) {
        habitatDAO.deleteById(id);
    }

}
