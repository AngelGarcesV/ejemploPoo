package com.mycompany.mavenproject4.Controladores;

import com.mycompany.mavenproject4.ControladoresArchivosBinarios.CursosInscritos;
import com.mycompany.mavenproject4.modelos.Inscripcion;
import com.mycompany.mavenproject4.repositorios.InscripcionRepo;

import java.util.ArrayList;
import java.util.List;

public class InscripcionController {
    private final InscripcionRepo repositorio = new InscripcionRepo();
    public static CursosInscritos cursosInscritos = new CursosInscritos(new ArrayList<Inscripcion>());
    static String ArchivoInscripciones = "Inscripciones.dat";

    public List<Inscripcion> getAllInscripciones() {
        return repositorio.obtenerTodasInscripciones();
    }

    public Inscripcion getInscripcionById(Long id) {
        return repositorio.obtenerInscripcionByID(id);
    }

    public Inscripcion createInscripcion(Inscripcion inscripcion) {
        if(inscripcion.isValid()){
            Inscripcion nuevaInscripcion = repositorio.crearInscripcion(inscripcion);
            if(nuevaInscripcion != null){
                cursosInscritos.inscribir(nuevaInscripcion);
                cursosInscritos.guardarInformacion(ArchivoInscripciones);
            }
            return nuevaInscripcion;
        }
        return null;
    }

    public Inscripcion updateInscripcion(Inscripcion inscripcion) {
        Inscripcion inscripcionActualizada = repositorio.actualizarInscripcionPorId(inscripcion.getId(), inscripcion);
        if(inscripcionActualizada.isValid()){
            if(inscripcionActualizada != null){
                cursosInscritos.actualizar(inscripcionActualizada);
                cursosInscritos.guardarInformacion(ArchivoInscripciones);
            }
            return inscripcionActualizada;
        }
        return null;
    }

    public boolean deleteInscripcion(Long id) {
        Inscripcion infoArchivo = InscripcionRepo.obtenerInscripcionByID(id);
        boolean inscripcionEliminada =  repositorio.eliminarInscripcion(id);
        if (inscripcionEliminada) {
            cursosInscritos.eliminar(infoArchivo);
            cursosInscritos.guardarInformacion(ArchivoInscripciones);
        }
        return inscripcionEliminada;
    }
}
