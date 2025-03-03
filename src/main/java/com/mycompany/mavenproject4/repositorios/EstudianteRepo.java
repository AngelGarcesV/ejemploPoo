package com.mycompany.mavenproject4.repositorios;

import com.mycompany.mavenproject4.modelos.Estudiante;
import com.mycompany.mavenproject4.modelos.Estudiante;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class EstudianteRepo {
    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("db");

    public List<Estudiante> obtenerTodosEstudiantes() {
        EntityManager em = emf.createEntityManager();
        List<Estudiante> estudiantes = em.createQuery("SELECT e FROM Estudiante e", Estudiante.class).getResultList();
        em.close();
        return estudiantes;
    }

    public Estudiante obtenerEstudianteByID(Long id) {
        EntityManager em = emf.createEntityManager();
        Estudiante estudiante = em.find(Estudiante.class, id);
        em.close();
        return estudiante;
    }

    public Estudiante crearEstudiante(Estudiante estudiante) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(estudiante);
        em.getTransaction().commit();
        em.close();
        return estudiante;
    }

    public Estudiante actualizarEstudiantePorId(Long id, Estudiante nuevoEstudiante) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Estudiante estudianteExistente = em.find(Estudiante.class, id);
        if (estudianteExistente != null) {
            estudianteExistente.setNombres(nuevoEstudiante.getNombres());
            estudianteExistente.setApellidos(nuevoEstudiante.getApellidos());
            estudianteExistente.setEmail(nuevoEstudiante.getEmail());
            estudianteExistente.setCodigo(nuevoEstudiante.getCodigo());
            estudianteExistente.setPrograma(nuevoEstudiante.getPrograma());
            estudianteExistente.setActivo(nuevoEstudiante.getActivo());
            estudianteExistente.setPromedio(nuevoEstudiante.getPromedio());
            em.merge(estudianteExistente);
        }
        em.getTransaction().commit();
        em.close();
        return estudianteExistente;
    }

    public boolean eliminarEstudiante(Long id) {
        boolean registroEliminado = false;
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Estudiante estudiante = em.find(Estudiante.class, id);
        if (estudiante != null) {
            em.remove(estudiante);
            registroEliminado = true;
        }
        em.getTransaction().commit();
        em.close();
        return registroEliminado;
    }
}
