/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject4.modelos;

import java.io.Serializable;

/**
 *
 * @author Estudiante_MCA
 */
public class Programa implements Serializable {
    private Double ID;
    private String nombre;
    private Double duracion;
    private Facultad facultad;
    private String registro;
    
    public Programa(Double ID, String nombre, Double duracion, Facultad facultad, String registro){
        this.ID = ID;
        this.nombre = nombre;
        this.duracion = duracion;
        this.registro = registro;
        this.facultad = facultad;
    }

    public Double getID() {
        return ID;
    }

    public void setID(Double ID) {
        this.ID = ID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Double getDuracion() {
        return duracion;
    }

    public void setDuracion(Double duracion) {
        this.duracion = duracion;
    }

    public Facultad getFacultad() {
        return facultad;
    }

    public void setFacultad(Facultad facultad) {
        this.facultad = facultad;
    }

    public String getRegistro() {
        return registro;
    }

    public void setRegistro(String registro) {
        this.registro = registro;
    }
    @Override
    public String toString(){
    
        return "Id: "+ this.ID + " Nombre: "+ this.nombre+ " Duracion :"+ this.duracion+" Registro: " +this.registro + " Facultad: " + this.facultad;
    }
    
}
