package com.zoo.dao;

import com.zoo.utils.JpaUtil;
import com.zoo.entity.Zona;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.TypedQuery;

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
    
    public int calcolaCapienzaAttuale(Long idZona) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            Long count = em.createQuery(
                "SELECT COUNT(a) FROM Animale a WHERE a.zona.id_zona = :idZona", Long.class)
                .setParameter("idZona", idZona)
                .getSingleResult();
            Zona zona = em.find(Zona.class, idZona);
            return zona.getCapienza() - count.intValue();
        } finally {
            em.close();
        }
    }
    
    public long countAnimaliInZona(Long idZona) {
    	EntityManager em = JpaUtil.getEntityManager();
        String jpql = "SELECT COUNT(a) FROM Animale a WHERE a.zona.id = :idZona";
        TypedQuery<Long> query = em.createQuery(jpql, Long.class);
        query.setParameter("idZona", idZona);
        return query.getSingleResult();
    }
}
