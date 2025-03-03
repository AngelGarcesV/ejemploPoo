package com.mycompany.mavenproject4.entityManager;

import jakarta.persistence.*;

public class entityManager {
    private static final EntityManagerFactory entityManagerFactory =
            Persistence.createEntityManagerFactory("db");

    public static EntityManager getEntityManager() {
        return entityManagerFactory.createEntityManager();
    }
}

