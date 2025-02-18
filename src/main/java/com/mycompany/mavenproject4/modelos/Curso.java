/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject4.modelos;

/**
 *
 * @author Estudiante_MCA
 */
public class Curso {
    private int ID;
    private Programa programa;
    private Boolean activo;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
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
    
    public Curso(int ID, Programa programa, Boolean activo){
        this.ID = ID;
        this.programa = programa;
        this.activo = activo;
    }
    @Override
    public String toString(){
        return "Id: "+ this.ID + "Programa: "+ this.programa + "Activo :" +this.activo;
    }
}
