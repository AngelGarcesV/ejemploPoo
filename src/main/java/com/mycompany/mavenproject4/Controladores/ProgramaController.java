package com.mycompany.mavenproject4.Controladores;

import com.mycompany.mavenproject4.modelos.Facultad;
import com.mycompany.mavenproject4.modelos.Programa;
import com.mycompany.mavenproject4.repositorios.FacultadRepo;
import com.mycompany.mavenproject4.repositorios.ProgramaRepo;

import java.util.Date;
import java.util.List;

public class ProgramaController {
    private final ProgramaRepo repositorio = new ProgramaRepo();
    private final FacultadRepo repositorioFacultad = new FacultadRepo();

    public List<Programa> getAllProgramas() {
        return repositorio.obtenerTodosProgramas();
    }

    public Programa getProgramaById(Long id) {
        return repositorio.obtenerProgramaByID(id);
    }

    public Programa createPrograma(String nombre, Long duracion, Date registro, Facultad facultad) {
        Programa programa = new Programa(null, nombre, duracion, facultad, registro);
        return programa.isValid() ? repositorio.crearPrograma(programa) : null;
    }

    public Programa updatePrograma(Long id, String nombre, Long duracion, Date registro, Facultad facultad) {
        Programa programa = new Programa(id, nombre, duracion, facultad, registro);
        return programa.isValid() ? repositorio.actualizarProgramaPorId(id, programa) : null;
    }

    public boolean deletePrograma(Long id) {
        return repositorio.eliminarPrograma(id);
    }
}
