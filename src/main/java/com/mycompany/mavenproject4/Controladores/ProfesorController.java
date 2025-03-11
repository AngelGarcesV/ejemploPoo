package com.mycompany.mavenproject4.Controladores;

import com.mycompany.mavenproject4.modelos.Profesor;
import com.mycompany.mavenproject4.repositorios.ProfesorRepo;

import java.util.List;


public class ProfesorController {
    private final ProfesorRepo repositorioProfesor = new ProfesorRepo();


    public List<Profesor> getAllProfesor() {
        return repositorioProfesor.obtenerTodosProfesor();
    }


    public Profesor getProfesorById(Long id) {
        return repositorioProfesor.obtenerProfesorByID(id);
    }


    public Profesor createProfesor(Profesor profesor) {
        return profesor.isValid() ? repositorioProfesor.crearProfesor(profesor) : null;
    }


    public Profesor updateProfesor(Profesor profesor) {
        return profesor.isValid() ? repositorioProfesor.actualizarProfesorPorId(profesor.getID(), profesor) : null;

    }


    public boolean deleteProfesor(Long id) {
        return repositorioProfesor.eliminarProfesor(id);
    }
}
