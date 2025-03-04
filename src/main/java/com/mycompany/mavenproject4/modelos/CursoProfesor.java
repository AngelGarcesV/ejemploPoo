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

public class CursoProfesor implements Serializable {
    private Long id;
    private Profesor profesor;
    private int año;
    private int semestre;
    private Curso curso;

    public CursoProfesor(Long id, Profesor profesor, int año, int semestre, Curso curso) {
        this.id = id;
        this.profesor = profesor;
        this.año = año;
        this.semestre = semestre;
        this.curso = curso;
    }

    public CursoProfesor() {

    }

    @Override
    public String toString() {
        return  "profesor: " + profesor + ", año: " + año + ", semestre=" + semestre + ", curso:" + curso;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
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

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }
    
    
}
