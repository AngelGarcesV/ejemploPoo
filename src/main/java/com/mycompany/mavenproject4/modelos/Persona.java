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
public class Persona implements Serializable {
    private Double ID;
    private String nombres;
    private String apellidos;
    private String email;

    public Persona(Double ID, String nombres, String apellidos, String email) {
        this.ID = ID;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.email = email;
    }

    public Persona() {
    }

    public Double getID() {
        return ID;
    }

    public void setID(Double ID) {
        this.ID = ID;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    
    @Override
    public String toString(){
    
        return "Id: "+ this.ID + ", Nombres: "+ this.nombres+ ", Apellidos: "+ this.apellidos + ", email: "+ this.email+"\n";
    }
}
