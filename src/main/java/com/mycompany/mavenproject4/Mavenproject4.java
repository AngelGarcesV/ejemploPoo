/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.mavenproject4;

import com.mycompany.mavenproject4.modelos.Estudiante;
import com.mycompany.mavenproject4.modelos.Facultad;
import com.mycompany.mavenproject4.modelos.InscripcionesPersonas;
import com.mycompany.mavenproject4.modelos.Persona;
import com.mycompany.mavenproject4.modelos.Programa;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Estudiante_MCA
 */
public class Mavenproject4 {

    public static void main(String[] args) {
  
        Persona decanoIngenieria = new Persona(1.0,"richard", "staldman", "richard@nose.com");
        Facultad facultadIngenieria = new Facultad(1.0,"ingenieria",decanoIngenieria);
        Programa ingSistemas = new Programa(1.0, "sistemas", 40.0, facultadIngenieria, "presencial");
        Estudiante estudiante1 = new Estudiante(12345.0,ingSistemas,true,4.5,1001.0,"Juan","Pérez", "juanperez@email.com");
        
        List<Persona> listadoPersonas = new ArrayList<Persona>();
        InscripcionesPersonas listadoInscripcion = new InscripcionesPersonas(listadoPersonas);
        listadoInscripcion.inscribir(decanoIngenieria);
        listadoInscripcion.inscribir(decanoIngenieria);
        listadoInscripcion.guardarInformacion("informacionInscritos.dat");  
    }
}
