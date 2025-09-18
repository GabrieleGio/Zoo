package com.zoo.dao;

import java.util.List;

import com.zoo.entity.Ruolo;
import com.zoo.utils.JpaUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

public class RuoloDAO {

    public void save(Ruolo ruolo) {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            em.persist(ruolo);
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public Ruolo findById(Long id) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            return em.find(Ruolo.class, id);
        } finally {
            em.close();
        }
    }

    public Ruolo findByNome(String nome) {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            TypedQuery<Ruolo> query = em.createQuery(
                "SELECT r FROM Ruolo r WHERE r.nome = :nome", Ruolo.class);
            query.setParameter("nome", nome);
            return query.getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            em.close();
        }
    }

    public List<Ruolo> findAll() {
        EntityManager em = JpaUtil.getEntityManager();
        try {
            return em.createQuery("SELECT r FROM Ruolo r", Ruolo.class).getResultList();
        } finally {
            em.close();
        }
    }

    public void deleteById(Long id) {
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();
        try {
            tx.begin();
            Ruolo ruolo = em.find(Ruolo.class, id);
            if (ruolo != null) {
                em.remove(ruolo);
            }
            tx.commit();
        } catch (Exception e) {
            if (tx.isActive()) tx.rollback();
            e.printStackTrace();
        } finally {
            em.close();
        }
    }
}
