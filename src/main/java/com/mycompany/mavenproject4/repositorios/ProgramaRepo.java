package com.mycompany.mavenproject4.repositorios;

import com.mycompany.mavenproject4.modelos.Programa;
import com.mycompany.mavenproject4.modelos.Programa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class ProgramaRepo {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("db");

    public List<Programa> obtenerTodosProgramas() {
        EntityManager em = emf.createEntityManager();
        List<Programa> programas = em.createQuery("SELECT p FROM Programa p", Programa.class).getResultList();
        em.close();
        return programas;
    }

    public Programa obtenerProgramaByID(Long id) {
        EntityManager em = emf.createEntityManager();
        Programa programa = em.find(Programa.class, id);
        em.close();
        return programa;
    }

    public Programa crearPrograma(Programa programa) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(programa);
        em.getTransaction().commit();
        em.close();
        return programa;
    }

    public Programa actualizarProgramaPorId(Long id, Programa nuevoPrograma) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Programa programaExistente = em.find(Programa.class, id);
        if (programaExistente != null) {
            programaExistente.setNombre(nuevoPrograma.getNombre());
            programaExistente.setDuracion(nuevoPrograma.getDuracion());
            programaExistente.setRegistro(nuevoPrograma.getRegistro());
            programaExistente.setFacultad(nuevoPrograma.getFacultad());
            em.merge(programaExistente);
        }
        em.getTransaction().commit();
        em.close();
        return programaExistente;
    }
    public boolean eliminarPrograma(Long id) {
        boolean registroEliminado = false;
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Programa programa = em.find(Programa.class, id);
        if (programa != null) {
            em.remove(programa);
            registroEliminado = true;
        }
        em.getTransaction().commit();
        em.close();
        return registroEliminado;
    }
}
