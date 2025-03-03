package com.mycompany.mavenproject4.repositorios;

import com.mycompany.mavenproject4.modelos.Persona;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class personaRepo {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("db");

    public List<Persona> obtenerTodosPersona() {
        EntityManager em = emf.createEntityManager();
        List<Persona> personas = em.createQuery("SELECT p FROM Persona p", Persona.class).getResultList();
        em.close();
        return personas;
    }

    public Persona obtenerPersonaByID(Long id) {
        EntityManager em = emf.createEntityManager();
        Persona persona = em.find(Persona.class, id);
        em.close();
        return persona;
    }

    public Persona crearPersona(Persona persona) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(persona);
        em.getTransaction().commit();
        em.close();
        return persona;
    }
    public boolean eliminarPersona(Long id) {
        boolean registroEliminado = false;
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Persona persona = em.find(Persona.class, id);
        if (persona != null) {
            em.remove(persona);
            registroEliminado = true;
        }
        em.getTransaction().commit();
        em.close();
        return registroEliminado;
    }


    public Persona actualizarPersonaPorId(Long id, Persona nuevaPersona) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Persona personaExistente = em.find(Persona.class, id);
        if (personaExistente != null) {
            personaExistente.setNombres(nuevaPersona.getNombres());
            personaExistente.setApellidos(nuevaPersona.getApellidos());
            personaExistente.setEmail(nuevaPersona.getEmail());
            em.merge(personaExistente);
        }
        em.getTransaction().commit();
        em.close();
        return personaExistente;
    }
}