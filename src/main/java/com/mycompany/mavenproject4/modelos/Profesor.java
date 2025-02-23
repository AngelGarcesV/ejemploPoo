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
public class Profesor extends Persona implements Serializable {
    private String TipoContrato;
    
    public Profesor(String TipoContrato){
        this.TipoContrato = TipoContrato;
       
    }

    public Profesor(String TipoContrato, Double ID, String nombres, String apellidos, String email) {
        super(ID, nombres, apellidos, email);
        this.TipoContrato = TipoContrato;
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
