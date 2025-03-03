/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject4.Controladores;

import com.mycompany.mavenproject4.modelos.Persona;
import com.mycompany.mavenproject4.repositorios.personaRepo;

import java.io.*;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Estudiante_MCA
 */
public class InscripcionesPersonas implements Serializable {
    private  personaRepo personaRepository = new personaRepo();
    private List<Persona> listadoInscripcionPersonas;

    public InscripcionesPersonas(List<Persona> listadoInscripcionPersonas) {
        this.listadoInscripcionPersonas = listadoInscripcionPersonas;
    }


    public Persona inscribir(Persona infoPersona) {
        try{
            listadoInscripcionPersonas.add(infoPersona);
            System.out.println("Persona inscrita exitosamente.");
            return infoPersona;
        }
        catch(Exception e){
            return null;
        }
    }


    
    public Boolean eliminar(Long id, String nombreArchivo) {
        try{
            boolean personaEncontrada  = false;
            if(personaRepository.eliminarPersona(id)){
                for (int i = 0; i < listadoInscripcionPersonas.size(); i++){
                    if (Objects.equals(listadoInscripcionPersonas.get(i).getID(), id)){
                        listadoInscripcionPersonas.remove(i);
                        personaEncontrada = true;
                        System.out.println("Persona eliminada exitosamente.");
                        break;
                    }
                }
                return personaEncontrada;
            }else{
                System.out.println("No se encontro a la persona inscrita");
                return personaEncontrada;
            }

        }catch (Exception e){
            return null;
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
    
    public void guardarInformacionArchivo(String nombreArchivo) {
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
