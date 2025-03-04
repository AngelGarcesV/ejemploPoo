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
public class Estudiante extends Persona implements Serializable {
    private double codigo;
    private Programa programa;
    private Boolean activo;
    private Double promedio;


    public double getCodigo() {
        return codigo;
    }

    public void setCodigo(double codigo) {
        this.codigo = codigo;
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

    public Double getPromedio() {
        return promedio;
    }

    public void setPromedio(Double promedio) {
        this.promedio = promedio;
    }

    public Estudiante(double codigo, Programa programa, Boolean activo, Double promedio, Long ID, String nombres, String apellidos, String email) {
        super(ID, nombres, apellidos, email);
        this.codigo = codigo;
        this.programa = programa;
        this.activo = activo;
        this.promedio = promedio;
    }


    @Override
    public String toString() {
        
        return  "codigo: " + codigo + ", programa: " + programa + ", activo: " + activo + ", promedio: " + promedio +" ";
    }
   
    
    
}
