package com.mycompany.mavenproject4.modelos;

import com.mycompany.mavenproject4.modelos.interfaces.Servicios;
import java.io.*;
import java.util.List;
import java.util.Objects;

public class CursosProfesores implements Servicios, Serializable {
    private List<CursoProfesor> cursoProfesor;

    @Override
    public String imprimirPosicion(int posicion) {
        System.out.println(this.cursoProfesor.get(posicion));
        return this.cursoProfesor.get(posicion).toString();
    }

    @Override
    public String toString() {
        return "cursoProfesor=" + cursoProfesor;
    }

    @Override
    public int cantidadActual() {
        return this.cursoProfesor.size();
    }

    @Override
    public List<String> imprimirListado() {
        System.out.println(this.cursoProfesor);
        return List.of();
    }


    public void guardarInformacion(String nombreArchivo) {
        try (FileOutputStream fos = new FileOutputStream(nombreArchivo);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(cursoProfesor);
            System.out.println("Información guardada exitosamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar la información: " + e.getMessage());
        }
    }

    public void cargarDatos(String nombreArchivo) {
        try (FileInputStream fis = new FileInputStream(nombreArchivo);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            cursoProfesor = (List<CursoProfesor>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al cargar los datos: " + e.getMessage());
        }
    }

    public void inscribir(CursoProfesor curso) {
        for (CursoProfesor c : cursoProfesor) {
            if (Objects.equals(c, curso)) {
                System.out.println("Error: El curso  " + curso +" ya existe.");
                return;
            }
        }
        cursoProfesor.add(curso);
        System.out.println("Curso inscrito exitosamente.");
    }

    public List<CursoProfesor> getCursoProfesor() {
        return cursoProfesor;
    }

    public void setCursoProfesor(List<CursoProfesor> cursoProfesor) {
        this.cursoProfesor = cursoProfesor;
    }

    public CursosProfesores(List<CursoProfesor> cursoProfesor) {
        this.cursoProfesor = cursoProfesor;
    }


}
