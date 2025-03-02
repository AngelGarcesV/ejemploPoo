/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject4.modelos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.io.Serializable;

/**
 *
 * @author Estudiante_MCA
 */
//@Entity
//@Table(name = "Inscripcion")
public class Inscripcion implements Serializable {
    private Curso curso;
    @Column(name = "año")
    private int año;
    private int semestre;
    private Estudiante estudiante;
    
    public Inscripcion(Curso curso, int año, int semestre, Estudiante estudiante){
        this.curso = curso;
        this.año = año;
        this.semestre = semestre;
        this.estudiante = estudiante;
    }
    @Override
    public String toString(){
        return "Curso: "+ this.curso + " Año: " + this.año + " semestre :" + this.semestre + " Estudiante :" + this.estudiante+ " ";
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public int getSemestre() {
        return semestre;
    }

    public void setSemestre(int semestre) {
        this.semestre = semestre;
    }

    public Estudiante getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(Estudiante estudiante) {
        this.estudiante = estudiante;
    }
    
}
