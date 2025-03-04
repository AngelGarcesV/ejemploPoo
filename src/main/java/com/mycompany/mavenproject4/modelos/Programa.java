/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject4.modelos;

import jakarta.persistence.*;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Estudiante_MCA
 */
public class Programa implements Serializable {
        private Long ID;
        private String nombre;
        private Long duracion;
        private Facultad facultad;
        private Date registro;

    public Programa(Long ID, String nombre, Long duracion, Facultad facultad, Date registro){
        this.ID = ID;
        this.nombre = nombre;
        this.duracion = duracion;
        this.registro = registro;
        this.facultad = facultad;
    }

    public Programa() {

    }

    public Long getID() {
        return ID;
    }

    public void setID(Long ID) {
        this.ID = ID;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Long getDuracion() {
        return duracion;
    }

    public void setDuracion(Long duracion) {
        this.duracion = duracion;
    }

    public Facultad getFacultad() {
        return facultad;
    }

    public void setFacultad(Facultad facultad) {
        this.facultad = facultad;
    }

    public Date getRegistro() {
        return registro;
    }

    public void setRegistro(Date registro) {
        this.registro = registro;
    }
    @Override
    public String toString(){

        return "Id: "+ this.ID + " Nombre: "+ this.nombre+ " Duracion :"+ this.duracion+" Registro: " +this.registro + " Facultad: " + this.facultad;
    }

}
