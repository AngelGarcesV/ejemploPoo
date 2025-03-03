package com.mycompany.mavenproject4.repositorios;

import com.mycompany.mavenproject4.modelos.Facultad;
import com.mycompany.mavenproject4.modelos.Persona;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class FacultadRepo {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("db");

    public List<Facultad> obtenerTodasFacultades() {
        EntityManager em = emf.createEntityManager();
        List<Facultad> facultades = em.createQuery("SELECT f FROM Facultad f", Facultad.class).getResultList();
        em.close();
        return facultades;
    }

    public static Facultad obtenerFacultadByID(Long id) {
        EntityManager em = emf.createEntityManager();
        Facultad facultad = em.find(Facultad.class, id);
        em.close();
        return facultad;
    }

    public Facultad crearFacultad(Facultad facultad) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(facultad);
        em.getTransaction().commit();
        em.close();
        return facultad;
    }

    public static Facultad actualizarFacultadPorId(Long id, Facultad nuevaFacultad) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Facultad facultadExistente = em.find(Facultad.class, id);
        if (facultadExistente != null) {
            facultadExistente.setNombre(nuevaFacultad.getNombre());
            facultadExistente.setDecano(nuevaFacultad.getDecano());
            em.merge(facultadExistente);
        }
        em.getTransaction().commit();
        em.close();
        return facultadExistente;
    }

    public boolean eliminarFacultad(Long id) {
        boolean registroEliminado = false;
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Facultad facultad = em.find(Facultad.class, id);
        if (facultad != null) {
            em.remove(facultad);
            registroEliminado = true;
        }
        em.getTransaction().commit();
        em.close();
        return registroEliminado;
    }

}
