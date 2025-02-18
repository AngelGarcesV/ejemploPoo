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
public class CursosInscritos {
    private List<Inscripcion> listadoCursosInscritos;

    public CursosInscritos(List<Inscripcion> listadoCursosInscritos) {
        this.listadoCursosInscritos = listadoCursosInscritos;
    }

    public List<Inscripcion> getListadoCursosInscritos() {
        return listadoCursosInscritos;
    }

    public void setListadoCursosInscritos(List<Inscripcion> listadoCursosInscritos) {
        this.listadoCursosInscritos = listadoCursosInscritos;
    }
    
    public void inscribirCurso(Inscripcion inscripcion){
        listadoCursosInscritos.add(inscripcion);
    }
    public void eliminar(Inscripcion inscripcion){
        listadoCursosInscritos.remove(inscripcion);
    }
    
    public void actualizar(Inscripcion inscripcion){
        for (int i = 0; i < listadoCursosInscritos.size(); i++){
            if (listadoCursosInscritos.get(i).getEstudiante().getCodigo() == inscripcion.getEstudiante().getCodigo() && 
                listadoCursosInscritos.get(i).getCurso().getID() == inscripcion.getCurso().getID()){
                listadoCursosInscritos.set(i, inscripcion);
                break;
            }
        }
    }
    
    public void guardarInformacion(Inscripcion inscripcion){
        System.out.println("Informacion guardada: " + inscripcion);
    }

    @Override
    public String toString() {
        return "listadoCursosInscritos: " + listadoCursosInscritos;
    }
    
    
    
    
    
}
