/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject4.modelos;

import java.io.*;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

/**
 *
 * @author Estudiante_MCA
 */
public class InscripcionesPersonas implements Serializable {

    private List<Persona> listadoInscripcionPersonas;

    public InscripcionesPersonas(List<Persona> listadoInscripcionPersonas) {
        this.listadoInscripcionPersonas = listadoInscripcionPersonas;
    }

    public void inscribir(Persona persona) {
        for (Persona p : listadoInscripcionPersonas) {
            if (p.getID().equals(persona.getID())) {
                System.out.println("Error: La persona con ID " + persona.getID() + " ya está inscrita.");
                return;
            }
        }
        listadoInscripcionPersonas.add(persona);
        System.out.println("Persona inscrita exitosamente.");
    }


    
    public void eliminar(Persona persona) {
        boolean personaEncontrada  = false;

        for (int i = 0; i < listadoInscripcionPersonas.size(); i++){
            if (Objects.equals(listadoInscripcionPersonas.get(i), persona)){
                listadoInscripcionPersonas.remove(i);
                personaEncontrada = true;
                System.out.println("Persona eliminada exitosamente.");
                break;
            }
        }
        if (!personaEncontrada){
            System.out.println("No se encontro a la persona inscrita");
        }
    }
    
    public void actualizar(Persona persona){
        boolean personaEncontrada  = false;

        for (int i = 0; i < listadoInscripcionPersonas.size(); i++){
            if (Objects.equals(listadoInscripcionPersonas.get(i).getID(), persona.getID())){
                listadoInscripcionPersonas.set(i, persona);
                personaEncontrada = true;
                System.out.println("Persona actualizada exitosamente.");
                break;

            }
        }
        if (!personaEncontrada){
            System.out.println("No se encontro a la persona inscrita");
        }
    }
    
    public void guardarInformacion(String nombreArchivo) {
        try (FileOutputStream fos = new FileOutputStream(nombreArchivo);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(listadoInscripcionPersonas);
            oos.close();
            fos.close();
            System.out.println("Información guardada exitosamente.");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }


    public void cargarDatos(String nombreArchivo) {
        try (FileInputStream fis = new FileInputStream(nombreArchivo);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            listadoInscripcionPersonas = (List<Persona>) ois.readObject();  // Leemos la lista completa
        } catch (IOException | ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public List<Persona> getListadoInscripcionPersonas() {
        return listadoInscripcionPersonas;
    }

    public void setListadoInscripcionPersonas(List<Persona> listadoInscripcionPersonas) {
        this.listadoInscripcionPersonas = listadoInscripcionPersonas;
    }
}