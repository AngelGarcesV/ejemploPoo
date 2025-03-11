package com.mycompany.mavenproject4.Controladores;

import com.mycompany.mavenproject4.modelos.Facultad;
import com.mycompany.mavenproject4.modelos.Persona;
import com.mycompany.mavenproject4.repositorios.FacultadRepo;
import com.mycompany.mavenproject4.repositorios.personaRepo;

import javax.swing.*;
import java.util.List;

public class FacultadController {
    private final FacultadRepo repositorioFacultad = new FacultadRepo();
    private final personaRepo repositorioPersona = new personaRepo();

    public List<Facultad> getAllFacultades() {
        return repositorioFacultad.obtenerTodasFacultades();
    }

    public Facultad getFacultadById(Long id) {
        return repositorioFacultad.obtenerFacultadByID(id);
    }

    public Facultad createFacultad(Facultad facultad) {
        if (facultad.isValid()){
            Facultad infoFacultad =  repositorioFacultad.crearFacultad(facultad);
            if(infoFacultad == null){
                JOptionPane.showMessageDialog(null, "Error al crear la facultad", "Error", JOptionPane.ERROR_MESSAGE);
            }
            return infoFacultad;
        }
        return null;
    }

    public Facultad updateFacultad(Facultad facultad) {
        if (facultad.isValid()){
            Facultad infoFacultad =  repositorioFacultad.actualizarFacultadPorId(facultad.getID(), facultad);
            if(infoFacultad == null){
                JOptionPane.showMessageDialog(null, "Error al actualizar la facultad", "Error", JOptionPane.ERROR_MESSAGE);
            }
            return infoFacultad;
        }
        return null;
    }

    public boolean deleteFacultad(Long id) {
        return repositorioFacultad.eliminarFacultad(id);
    }
}
