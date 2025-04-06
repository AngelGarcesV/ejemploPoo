package com.mycompany.mavenproject4.Controladores;

import com.mycompany.mavenproject4.Factory.PersonaFactory;
import com.mycompany.mavenproject4.modelos.Estudiante;
import com.mycompany.mavenproject4.modelos.Programa;
import com.mycompany.mavenproject4.modelos.interfaces.Ipersona;
import com.mycompany.mavenproject4.repositorios.EstudianteRepo;
import com.mycompany.mavenproject4.repositorios.ProgramaRepo;

import java.util.List;

public class EstudianteController {
    private final EstudianteRepo repositorio = new EstudianteRepo();
    private final ProgramaRepo programaRepo = new ProgramaRepo();

    public List<Estudiante> getAllEstudiantes() {
        return repositorio.obtenerTodosEstudiantes();
    }

    public Estudiante getEstudianteById(Long id) {
        return repositorio.obtenerEstudianteByID(id);
    }

    public Estudiante createEstudiante(String nombres, String apellidos, String email, double codigo, Programa programa, boolean activo, double promedio) {
        Estudiante estudiante = PersonaFactory.crearEstudiante(codigo, programa, activo, promedio, null, nombres, apellidos, email);
        return estudiante.isValid() ? repositorio.crearEstudiante(estudiante): null;
    }

    public Estudiante updateEstudiante(Long id, String nombres, String apellidos, String email, double codigo, Programa programa, boolean activo, double promedio) {
        Estudiante estudiante =  PersonaFactory.crearEstudiante(codigo, programa, activo, promedio, id, nombres, apellidos, email);
        return estudiante.isValid() ? repositorio.actualizarEstudiantePorId(id, estudiante): null;
    }

    public boolean deleteEstudiante(Long id) {
        return repositorio.eliminarEstudiante(id);
    }

}
