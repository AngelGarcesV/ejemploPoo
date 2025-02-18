/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.mavenproject4.modelos;

import java.util.List;

/**
 *
 * @author Estudiante_MCA
 */
public class CursosProfesores {
    private List<CursoProfesor> cursoProfesor;

    public List<CursoProfesor> getCursoProfesor() {
        return cursoProfesor;
    }

    public void setCursoProfesor(List<CursoProfesor> cursoProfesor) {
        this.cursoProfesor = cursoProfesor;
    }

    public CursosProfesores(List<CursoProfesor> cursoProfesor) {
        this.cursoProfesor = cursoProfesor;
    }
    
    public String imprimirPosicion(int posicion){
        System.out.println(this.cursoProfesor.get(posicion));
        return this.cursoProfesor.get(posicion).toString();
    }
    
    public int cantidadActual(){
        return this.cursoProfesor.size();
    }
    
    public void incribir(CursoProfesor NuevoCursoProfesor){
        this.cursoProfesor.add(NuevoCursoProfesor);
    }
    
}
