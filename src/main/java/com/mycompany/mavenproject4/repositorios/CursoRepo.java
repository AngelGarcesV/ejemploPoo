package com.mycompany.mavenproject4.repositorios;

import com.mycompany.mavenproject4.modelos.Curso;
import com.mycompany.mavenproject4.modelos.Persona;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class CursoRepo {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("db");

    public List<Curso> obtenerTodosCursos() {
        EntityManager em = emf.createEntityManager();
        List<Curso> cursos = em.createQuery("SELECT c FROM Curso c", Curso.class).getResultList();
        em.close();
        return cursos;
    }

    public Curso obtenerCursoByID(Long id) {
        EntityManager em = emf.createEntityManager();
        Curso curso = em.find(Curso.class, id);
        em.close();
        return curso;
    }

    public Curso crearCurso(Curso curso) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(curso);
        em.getTransaction().commit();
        em.close();
        return curso;
    }

    public static Curso actualizarCursoPorId(Long id, Curso nuevoCurso) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Curso cursoExistente = em.find(Curso.class, id);
        if (cursoExistente != null) {
            cursoExistente.setPrograma(nuevoCurso.getPrograma());
            cursoExistente.setActivo(nuevoCurso.getActivo());
            em.merge(cursoExistente);
        }
        em.getTransaction().commit();
        em.close();
        return cursoExistente;
    }

    public boolean eliminarCurso(Long id) {
        boolean registroEliminado = false;
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        Curso curso = em.find(Curso.class, id);
        if (curso != null) {
            em.remove(curso);
            registroEliminado = true;
        }
        em.getTransaction().commit();
        em.close();
        return registroEliminado;
    }
}
