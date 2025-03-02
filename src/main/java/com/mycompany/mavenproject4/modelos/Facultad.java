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
@Entity
@Table(name="Facultad")
public class Facultad implements Serializable {
    @Id
    private Double ID;
    @Column(name="nombre")
    private String nombre;
    @OneToOne
    @JoinColumn(name = "decano")
    private Persona decano;
    
    public Facultad(Double ID, String nombre, Persona decano){
        this.ID = ID;
        this.nombre = nombre;
        this.decano = decano;
    }

    public Facultad() {

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

    public Persona getDecano() {
        return decano;
    }

    public void setDecano(Persona decano) {
        this.decano = decano;
    }
    
    @Override
    public String toString(){
    
        return "Id: "+ this.ID + " Nombre: "+ this.nombre+ "D ecano: "+ this.decano + " ";
    }
}
