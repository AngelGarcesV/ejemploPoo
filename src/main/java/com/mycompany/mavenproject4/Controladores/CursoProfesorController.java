package com.mycompany.mavenproject4.Controladores;

import com.mycompany.mavenproject4.ControladoresArchivosBinarios.CursosProfesores;
import com.mycompany.mavenproject4.modelos.CursoProfesor;
import com.mycompany.mavenproject4.repositorios.CursoProfesorRepo;


import java.util.ArrayList;
import java.util.List;

public class CursoProfesorController {
    private final CursoProfesorRepo repositorioCursoProfesor = new CursoProfesorRepo();
    public static CursosProfesores inscripcionCursosProfesores = new CursosProfesores(new ArrayList<CursoProfesor>());
    static String ArchivoCursosProfesor = "cursosProfesores.dat";

    public List<CursoProfesor> getAllCursoProfesor() {
        return repositorioCursoProfesor.obtenerTodosCursoProfesor();
    }

    public CursoProfesor getCursoProfesorById(Long id) {
        return repositorioCursoProfesor.obtenerCursoProfesorByID(id);
    }

    public CursoProfesor createCursoProfesor(CursoProfesor cursoProfesor) {
        if(cursoProfesor.isValid()){
             CursoProfesor nuevoCursoProfesor = repositorioCursoProfesor.crearCursoProfesor(cursoProfesor);
             if(nuevoCursoProfesor != null){
                 inscripcionCursosProfesores.inscribir(nuevoCursoProfesor);
                 inscripcionCursosProfesores.guardarInformacion(ArchivoCursosProfesor);
             }
            return nuevoCursoProfesor;
        }
        return null;
    }

    public CursoProfesor updateCursoProfesor(CursoProfesor cursoProfesor) {
        return cursoProfesor.isValid() ? repositorioCursoProfesor.crearCursoProfesor(cursoProfesor) : null;

    }

    public boolean deleteCursoProfesor(Long id) {
        return repositorioCursoProfesor.eliminarCursoProfesor(id);
    }
}
