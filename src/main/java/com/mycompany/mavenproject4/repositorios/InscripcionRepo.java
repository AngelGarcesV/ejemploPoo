package com.mycompany.mavenproject4.repositorios;

import com.mycompany.mavenproject4.modelos.Inscripcion;
import com.mycompany.mavenproject4.modelos.Persona;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class InscripcionRepo {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("db");

    public List<Inscripcion> obtenerTodasInscripciones() {
        EntityManager em = emf.createEntityManager();
        List<Inscripcion> inscripciones = em.createQuery("SELECT i FROM Inscripcion i", Inscripcion.class).getResultList();
        em.close();
        return inscripciones;
    }

    public static Inscripcion obtenerInscripcionByID(Long id) {
        EntityManager em = emf.createEntityManager();
        Inscripcion inscripcion = em.find(Inscripcion.class, id);
        em.close();
        return inscripcion;
    }

    public Inscripcion crearInscripcion(Inscripcion inscripcion) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(inscripcion);
        em.getTransaction().commit();
        em.close();
        return inscripcion;
    }

    public static Inscripcion actualizarInscripcionPorId(Long id, Inscripcion nuevaInscripcion) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Inscripcion inscripcionExistente = em.find(Inscripcion.class, id);
        if (inscripcionExistente != null) {
            inscripcionExistente.setCurso(nuevaInscripcion.getCurso());
            inscripcionExistente.setAño(nuevaInscripcion.getAño());
            inscripcionExistente.setSemestre(nuevaInscripcion.getSemestre());
            inscripcionExistente.setEstudiante(nuevaInscripcion.getEstudiante());
            em.merge(inscripcionExistente);
        }
        em.getTransaction().commit();
        em.close();
        return inscripcionExistente;
    }

    public boolean eliminarInscripcion(Long id) {
        boolean registroEliminado = false;
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Inscripcion inscripcion = em.find(Inscripcion.class, id);
        if (inscripcion != null) {
            em.remove(inscripcion);
            registroEliminado = true;
        }
        em.getTransaction().commit();
        em.close();
        return registroEliminado;
    }
}
