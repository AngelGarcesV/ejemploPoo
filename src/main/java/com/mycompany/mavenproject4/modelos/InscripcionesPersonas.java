/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject4.modelos;

import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Estudiante_MCA
 */
public class InscripcionesPersonas {
    private List<Persona> listadoInscripcionPersonas;

    public InscripcionesPersonas(List<Persona> listadoInscripcionPersonas) {
        this.listadoInscripcionPersonas = listadoInscripcionPersonas;
    }
    
    
    public void inscribir(Persona persona){
        listadoInscripcionPersonas.add(persona);
    }
    
    public void eliminar(Persona persona){
        listadoInscripcionPersonas.remove(persona);
    }
    
    public void actualizar(Persona persona){
        for (int i = 0; i < listadoInscripcionPersonas.size(); i++){
            if (listadoInscripcionPersonas.get(i).getID() == persona.getID()){
                listadoInscripcionPersonas.set(i, persona);
                break;
            }
        }
    }
    
    public void guardarInformacion(String nombreArchivo) {
        try (FileOutputStream fos = new FileOutputStream(nombreArchivo);
             DataOutputStream dos = new DataOutputStream(fos)) {
             
            // Escribimos el número de personas en el archivo
            dos.writeInt(listadoInscripcionPersonas.size());

            // Escribimos los atributos de cada Persona (ID y nombre)
            for (Persona persona : listadoInscripcionPersonas) {
                dos.writeDouble(persona.getID());   // Escribimos el ID como entero
                dos.writeUTF(persona.getNombres());  // Escribimos el nombre como cadena UTF
            }
            
            System.out.println("Información guardada exitosamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar la información: " + e.getMessage());
        }
    }


    public void cargarDatos(String nombreArchivo) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(nombreArchivo))) {
            listadoInscripcionPersonas = (List<Persona>) ois.readObject();  // Leemos la lista de personas desde el archivo
            System.out.println("Datos de personas cargados exitosamente.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println( e.getMessage());
        }
    }

    public List<Persona> getListadoInscripcionPersonas() {
        return listadoInscripcionPersonas;
    }

    public void setListadoInscripcionPersonas(List<Persona> listadoInscripcionPersonas) {
        this.listadoInscripcionPersonas = listadoInscripcionPersonas;
    }
    
    
    
    
}
