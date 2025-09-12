package com.zoo.dao;

import com.zoo.utils.JpaUtil;
import com.zoo.entity.Zona;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;

public class ZonaDAO {

    public void save(Zona zona) {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.persist(zona);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public Zona findById(Long id) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            return em.find(Zona.class, id);
        } finally {
            em.close();
        }
    }

    public List<Zona> findAll() {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            return em.createQuery("SELECT z FROM Zona z", Zona.class).getResultList();
        } finally {
            em.close();
        }
    }

    public void update(Zona zona) {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.merge(zona);
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
            Zona zona = em.find(Zona.class, id);
            if (zona != null) {
                tx.begin();
                em.remove(zona);
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
