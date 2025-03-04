/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject4.modelos;

import jakarta.persistence.*;

import java.io.Serializable;

/**
 *
 * @author Estudiante_MCA
 */

public class Curso implements Serializable {
    private  Long id;
    private Programa programa;
    private Boolean activo;

    public Curso() {

    }

    public Programa getPrograma() {
        return programa;
    }

    public void setPrograma(Programa programa) {
        this.programa = programa;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }
    
    public Curso( Long ID, Programa programa, Boolean activo){
        this.id = ID;
        this.programa = programa;
        this.activo = activo;
    }
    @Override
    public String toString(){
        return "Id: "+ this.id + " Programa: "+ this.programa + " Activo :" +this.activo + " ";
    }

    public void setID(Long id) {
        this.id = id;
    }

    public Long getID() {
        return id;
    }
}
