package com.mycompany.mavenproject4.Controladores;

import com.mycompany.mavenproject4.ControladoresArchivosBinarios.InscripcionesPersonas;
import com.mycompany.mavenproject4.modelos.Persona;
import com.mycompany.mavenproject4.repositorios.personaRepo;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class PersonaController {
    private final personaRepo repositorio = new personaRepo();
    public static InscripcionesPersonas inscripcionesPersonas = new InscripcionesPersonas(new ArrayList<Persona>());
    static String ArchivoInformacionInscritos = "informacionInscritos.dat";

    public List<Persona> getAllPersonas() {
        List<Persona> listaInscripciones = inscripcionesPersonas.getListadoInscripcionPersonas();
        for (Persona persona : listaInscripciones) {
            System.out.println(persona + "\n");
        }

        return repositorio.obtenerTodosPersona();
    }


    public Persona getPersonaById(Long id) {
        return repositorio.obtenerPersonaByID(id);
    }

    public Persona createPersona(Persona persona) {
        if (persona.isValid()) {
            Persona nuevaPersona = repositorio.crearPersona(persona);
            if(nuevaPersona != null) {
                inscripcionesPersonas.inscribir(nuevaPersona);
                inscripcionesPersonas.guardarInformacionArchivo(ArchivoInformacionInscritos);
                JOptionPane.showMessageDialog(null, "Persona Creada con exito");
                return nuevaPersona;
            }
                return null;
        }
        return null;
    }

    public Persona updatePersona(Long id, Persona infoPersona){
        if (infoPersona.isValid()) {
            Persona updatePersona = repositorio.actualizarPersonaPorId(id, infoPersona);
            if(updatePersona != null) {
                inscripcionesPersonas.actualizar(infoPersona);
                inscripcionesPersonas.guardarInformacionArchivo(ArchivoInformacionInscritos);
                return updatePersona;
            }else{
                return null;
            }
        }
        JOptionPane.showMessageDialog(null, "La información no es valida");
        return null;
    }

    public boolean deletePersona(Long id) {
        boolean personaEliminada = repositorio.eliminarPersona(id);
        if(personaEliminada) {
            inscripcionesPersonas.eliminar(id, ArchivoInformacionInscritos);
        }
        return personaEliminada;
    }
}
