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
public class Inscripcion implements Serializable {
    private Long id;
    private Curso curso;
    private int año;
    private int semestre;
    private Estudiante estudiante;
    
    public Inscripcion(Long id, Curso curso, int año, int semestre, Estudiante estudiante){
        this.id = id;
        this.curso = curso;
        this.año = año;
        this.semestre = semestre;
        this.estudiante = estudiante;
    }

    public Inscripcion() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
