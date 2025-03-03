/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject4.modelos;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.io.Serializable;

/**
 *
 * @author Estudiante_MCA
 */
@Entity
public class Profesor extends Persona implements Serializable {
    @Column(name = "tipoContrato")
    private String TipoContrato;
    
    public Profesor(String TipoContrato){
        this.TipoContrato = TipoContrato;
       
    }

    public Profesor(String TipoContrato, Long ID, String nombres, String apellidos, String email) {
        super(ID, nombres, apellidos, email);
        this.TipoContrato = TipoContrato;
    }

    public Profesor() {

    }

    public String getTipoContrato() {
        return TipoContrato;
    }

    public void setTipoContrato(String TipoContrato) {
        this.TipoContrato = TipoContrato;
    }
    
    
    @Override
    public String toString(){
        return "TipoContrato: " + this.TipoContrato;
    }
}
