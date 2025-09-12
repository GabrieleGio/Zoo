package com.zoo.utils;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

public class JpaUtil {
    private static EntityManagerFactory entityManagerFactory = null;

    static {
        try {
            entityManagerFactory = Persistence.createEntityManagerFactory("zoo-pu");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static EntityManager getEntityManager() {
        if (entityManagerFactory == null) {
            throw new IllegalStateException("EntityManagerFactory non è stato inizializzato.");
        }
        return entityManagerFactory.createEntityManager();
    }
}
