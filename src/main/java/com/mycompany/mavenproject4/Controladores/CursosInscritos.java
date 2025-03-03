package com.mycompany.mavenproject4.Controladores;

import com.mycompany.mavenproject4.modelos.Inscripcion;
import com.mycompany.mavenproject4.modelos.interfaces.Servicios;
import java.io.*;
import java.util.List;
import java.util.Objects;

public class CursosInscritos implements Serializable, Servicios {
    private List<Inscripcion> listadoCursosInscritos;

    public CursosInscritos(List<Inscripcion> listadoCursosInscritos) {
        this.listadoCursosInscritos = listadoCursosInscritos;
    }

    @Override
    public String toString() {
        return "listadoCursosInscritos" + listadoCursosInscritos;
    }

    @Override
    public String imprimirPosicion(int posicion) {
        try{
            System.out.println(this.listadoCursosInscritos.get(posicion));
            return this.listadoCursosInscritos.get(posicion).toString();
        } catch (RuntimeException e){
            System.err.println(e.getMessage());
            return e.getMessage();
        }
    }

    @Override
    public int cantidadActual() {
        return this.listadoCursosInscritos.size();
    }

    @Override
    public List<String> imprimirListado() {
        System.out.println(this.listadoCursosInscritos);
        return List.of();
    }

    public List<Inscripcion> getListadoCursosInscritos() {
        return listadoCursosInscritos;
    }

    public void setListadoCursosInscritos(List<Inscripcion> listadoCursosInscritos) {
        this.listadoCursosInscritos = listadoCursosInscritos;
    }

    public void eliminar(Inscripcion inscripcion) {
        boolean personaEncontrada = false;
        for (int i = 0; i < this.listadoCursosInscritos.size(); i++){
            Inscripcion inscripcionActual = this.listadoCursosInscritos.get(i);
            if (Objects.equals(inscripcionActual.getEstudiante().getID(), inscripcion.getEstudiante().getID()) && Objects.equals(inscripcionActual.getCurso().getID(), inscripcion.getCurso().getID())){
                this.listadoCursosInscritos.remove(i);
                personaEncontrada = true;
                System.out.println("Persona eliminada exitosamente.");
                break;
            }
        }
        if (!personaEncontrada){
            System.out.println("No se encontro a la persona inscrita");
        }
    }

    public void actualizar(Inscripcion inscripcion) {
        boolean cursoEncontrado = false;
        for (int i = 0; i < listadoCursosInscritos.size(); i++) {
            Inscripcion inscripcionActual = this.listadoCursosInscritos.get(i);
            if (Objects.equals(inscripcionActual.getEstudiante().getID(), inscripcion.getEstudiante().getID()) && Objects.equals(inscripcionActual.getCurso().getID(), inscripcion.getCurso().getID())){
                listadoCursosInscritos.set(i, inscripcion);
                cursoEncontrado = true;
                System.out.println("Curso actualizado exitosamente.");
                break;
            }
        }
        if (!cursoEncontrado) {
            System.out.println("No se encontró el curso inscrito.");
        }
    }

    public void guardarInformacion(String nombreArchivo) {
        try (FileOutputStream fos = new FileOutputStream(nombreArchivo);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(listadoCursosInscritos);
            System.out.println("Información guardada exitosamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar la información: " + e.getMessage());
        }
    }

    public void cargarDatos(String nombreArchivo) {
        try (FileInputStream fis = new FileInputStream(nombreArchivo);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            listadoCursosInscritos = (List<Inscripcion>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al cargar los datos: " + e.getMessage());
        }
    }

    public void inscribir(Inscripcion inscripcion) {
        for (Inscripcion c : listadoCursosInscritos) {
            if (Objects.equals(c, inscripcion)) {
                System.out.println("Error: El curso " + inscripcion + " ya existe.");
                return;
            }
        }
        listadoCursosInscritos.add(inscripcion);
        System.out.println("Curso inscrito exitosamente.");
    }
}
