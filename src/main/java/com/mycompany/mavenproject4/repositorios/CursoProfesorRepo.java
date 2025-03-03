package com.mycompany.mavenproject4.repositorios;

import com.mycompany.mavenproject4.modelos.CursoProfesor;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

import java.util.List;

public class CursoProfesorRepo {

    private EntityManagerFactory emf = Persistence.createEntityManagerFactory("db");

    public List<CursoProfesor> obtenerTodosCursoProfesor() {
        EntityManager em = emf.createEntityManager();
        List<CursoProfesor> cursoProfesores = em.createQuery("SELECT cp FROM CursoProfesor cp", CursoProfesor.class).getResultList();
        em.close();
        return cursoProfesores;
    }
    public boolean eliminarCursoProfesor(Long id) {
        boolean registroEliminado = false;
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        CursoProfesor cursoProfesor = em.find(CursoProfesor.class, id);
        if (cursoProfesor != null) {
            em.remove(cursoProfesor);
            registroEliminado =  true;
        }
        em.getTransaction().commit();
        em.close();
        return registroEliminado;
    }


    public CursoProfesor obtenerCursoProfesorByID(Long id) {
        EntityManager em = emf.createEntityManager();
        CursoProfesor cursoProfesor = em.find(CursoProfesor.class, id);
        em.close();
        return cursoProfesor;
    }

    public CursoProfesor crearCursoProfesor(CursoProfesor cursoProfesor) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(cursoProfesor);
        em.getTransaction().commit();
        em.close();
        return cursoProfesor;
    }

    public CursoProfesor actualizarCursoProfesorPorId(Long id, CursoProfesor nuevoCursoProfesor) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        CursoProfesor cursoProfesorExistente = em.find(CursoProfesor.class, id);
        if (cursoProfesorExistente != null) {
            cursoProfesorExistente.setProfesor(nuevoCursoProfesor.getProfesor());
            cursoProfesorExistente.setAño(nuevoCursoProfesor.getAño());
            cursoProfesorExistente.setSemestre(nuevoCursoProfesor.getSemestre());
            cursoProfesorExistente.setCurso(nuevoCursoProfesor.getCurso());
            em.merge(cursoProfesorExistente);
        }
        em.getTransaction().commit();
        em.close();
        return cursoProfesorExistente;
    }
}
