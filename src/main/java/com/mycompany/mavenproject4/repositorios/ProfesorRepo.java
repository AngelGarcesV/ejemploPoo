package com.mycompany.mavenproject4.repositorios;

import com.mycompany.mavenproject4.modelos.Persona;
import com.mycompany.mavenproject4.modelos.Profesor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class ProfesorRepo {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("db");

    public List<Profesor> obtenerTodosProfesor() {
        EntityManager em = emf.createEntityManager();
        List<Profesor> profesores = em.createQuery("SELECT p FROM Profesor p", Profesor.class).getResultList();
        em.close();
        return profesores;
    }

    public Profesor obtenerProfesorByID(Long id) {
        EntityManager em = emf.createEntityManager();
        Profesor profesor = em.find(Profesor.class, id);
        em.close();
        return profesor;
    }

    public Profesor crearProfesor(Profesor profesor) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(profesor);
        em.getTransaction().commit();
        em.close();
        return profesor;
    }

    public Profesor actualizarProfesorPorId(Long id, Profesor nuevoProfesor) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Profesor profesorExistente = em.find(Profesor.class, id);
        if (profesorExistente != null) {
            profesorExistente.setNombres(nuevoProfesor.getNombres());
            profesorExistente.setApellidos(nuevoProfesor.getApellidos());
            profesorExistente.setEmail(nuevoProfesor.getEmail());
            profesorExistente.setTipoContrato(nuevoProfesor.getTipoContrato());
            em.merge(profesorExistente);
        }
        em.getTransaction().commit();
        em.close();
        return profesorExistente;
    }

    public boolean eliminarProfesor(Long id){
        boolean registroEliminado = false;
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Profesor profesor = em.find(Profesor.class, id);
        if (profesor != null) {
            em.remove(profesor);
            registroEliminado = true;
        }
        em.getTransaction().commit();
        em.close();
        return registroEliminado;
    }
}
