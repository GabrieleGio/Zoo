package com.zoo.dao;

import com.zoo.utils.JpaUtil;
import com.zoo.entity.Animale;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;

import java.util.List;

public class AnimaleDAO {

	public void save(Animale animale) {
	    EntityManager em = JpaUtil.getEntityManager();
	    EntityTransaction tx = em.getTransaction();

	    try {
	        tx.begin();
	        em.merge(animale);
	        tx.commit();
	    } catch (Exception e) {
	        if (tx.isActive()) tx.rollback();
	        e.printStackTrace();
	    } finally {
	        em.close();
	    }
	}


	public Animale findById(Long id) {
	    EntityManager em = JpaUtil.getEntityManager();
	    try {
	        return em.createQuery(
	            "SELECT a FROM Animale a LEFT JOIN FETCH a.zona WHERE a.id_animale = :id", Animale.class)
	            .setParameter("id", id)
	            .getSingleResult();
	    } finally {
	        em.close();
	    }
	}

    public List<Animale> findAll() {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            return em.createQuery("SELECT a FROM Animale a LEFT JOIN FETCH a.zona", Animale.class).getResultList();
        } finally {
            em.close();
        }
    }


    public void update(Animale animale) {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.merge(animale);
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
            Animale animale = em.find(Animale.class, id);
            if (animale != null) {
                tx.begin();
                em.remove(animale);
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
