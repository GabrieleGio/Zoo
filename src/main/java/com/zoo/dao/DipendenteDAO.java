package com.zoo.dao;

import com.zoo.utils.JpaUtil;
import com.zoo.entity.Dipendente;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class DipendenteDAO {

	public void save(Dipendente dipendente) {
		EntityManager em = JpaUtil.getEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			em.persist(dipendente);
			tx.commit();
		} catch (Exception e) {
			if (tx.isActive())
				tx.rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}
	}

	public Dipendente findById(Long id) {
		EntityManager em = JpaUtil.getEntityManager();
		try {
			TypedQuery<Dipendente> query = em.createQuery(
					"SELECT d FROM Dipendente d JOIN FETCH d.ruolo WHERE d.id_dipendente = :id", Dipendente.class);
			query.setParameter("id", id);
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} finally {
			em.close();
		}
	}

	public List<Dipendente> findAll() {
		EntityManager em = JpaUtil.getEntityManager();
		try {
			return em.createQuery("SELECT d FROM Dipendente d JOIN FETCH d.ruolo", Dipendente.class).getResultList();
		} finally {
			em.close();
		}
	}

	public void update(Dipendente dipendente) {
		EntityManager em = JpaUtil.getEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			tx.begin();
			em.merge(dipendente);
			tx.commit();
		} catch (Exception e) {
			if (tx.isActive())
				tx.rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}
	}

	public void delete(Long id) {
		EntityManager em = JpaUtil.getEntityManager();
		EntityTransaction tx = em.getTransaction();

		try {
			Dipendente dipendente = em.find(Dipendente.class, id);
			if (dipendente != null) {
				tx.begin();
				em.remove(dipendente);
				tx.commit();
			}
		} catch (Exception e) {
			if (tx.isActive())
				tx.rollback();
			e.printStackTrace();
		} finally {
			em.close();
		}
	}

	public Dipendente findByEmail(String email) {
		EntityManager em = JpaUtil.getEntityManager();
		try {
			TypedQuery<Dipendente> query = em.createQuery(
					"SELECT d FROM Dipendente d JOIN FETCH d.ruolo WHERE d.email = :email", Dipendente.class);
			query.setParameter("email", email);
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} finally {
			em.close();
		}
	}

	public Dipendente findByUsername(String username) {
		EntityManager em = JpaUtil.getEntityManager();
		try {
			TypedQuery<Dipendente> query = em.createQuery(
					"SELECT d FROM Dipendente d JOIN FETCH d.ruolo WHERE d.username = :username", Dipendente.class);
			query.setParameter("username", username);
			return query.getSingleResult();
		} catch (NoResultException e) {
			return null;
		} finally {
			em.close();
		}
	}
}
