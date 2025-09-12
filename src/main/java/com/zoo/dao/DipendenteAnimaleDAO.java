package com.zoo.dao;

import com.zoo.utils.JpaUtil;
import com.zoo.entity.DipendenteAnimale;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;

public class DipendenteAnimaleDAO {

    public void save(DipendenteAnimale assoc) {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.persist(assoc);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public DipendenteAnimale findById(Long id) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            return em.find(DipendenteAnimale.class, id);
        } finally {
            em.close();
        }
    }

    public List<DipendenteAnimale> findAll() {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            return em.createQuery("SELECT da FROM DipendenteAnimale da", DipendenteAnimale.class).getResultList();
        } finally {
            em.close();
        }
    }

    public void update(DipendenteAnimale assoc) {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.merge(assoc);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public void delete(Long id) {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            DipendenteAnimale assoc = em.find(DipendenteAnimale.class, id);
            if (assoc != null) {
                tx.begin();
                em.remove(assoc);
                tx.commit();
            }
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
