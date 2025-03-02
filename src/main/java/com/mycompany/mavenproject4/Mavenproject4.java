/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.mavenproject4;

import com.mycompany.mavenproject4.modelos.*;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Estudiante_MCA
 */
public class Mavenproject4 {

    public static void main(String[] args) {

        String ArchivoInformacionInscritos = "informacionInscritos.dat";
        String ArchivoCurosProfesores = "curosProfesores.dat";
        String ArchivoInscripciones = "inscripciones.dat";

        Persona decanoIngenieria = new Persona(1.0,"richard", "staldman", "richard@nose.com");

        // Apartado Cursos Inscritos

        Facultad facultadIngenieria = new Facultad(1.0,"ingenieria", decanoIngenieria);

        Programa ingSistemas = new Programa(1.0, "sistemas", 40.0, facultadIngenieria, "presencial");
        Curso poo = new Curso(1L, ingSistemas, true);
        Estudiante estudiante1 = new Estudiante(12345.0,ingSistemas,true,4.5,1.0,"Juan","Pérez", "juanperez@email.com");
        Inscripcion Inscripcion1 = new Inscripcion(poo,2022, 3, estudiante1);

        Programa ingAmbiental = new Programa(2.0, "ambiental", 46.0, facultadIngenieria, "ambiental");
        Curso integral = new Curso(2L, ingSistemas, true);
        Estudiante estudiante2 = new Estudiante(161104812.0, ingAmbiental, true, 3.4, 2.0, "Santiago", "Cortes", "scortes@email.com");
        Inscripcion Inscripcion2 = new Inscripcion(integral, 2024, 2, estudiante2);



        List<Inscripcion> listadoInscripciones = new ArrayList<Inscripcion>();
        CursosInscritos listadoCursosInscritos = new CursosInscritos(listadoInscripciones);

        System.out.println("/// Carga de información cursos inscritos desde arhcivo///\n");
        listadoCursosInscritos.cargarDatos(ArchivoInscripciones);

        System.out.println("//Inscripcion de cursos\n");
        listadoCursosInscritos.inscribir(Inscripcion1);
        listadoCursosInscritos.inscribir(Inscripcion2);
        listadoCursosInscritos.guardarInformacion(ArchivoInscripciones);
        listadoCursosInscritos.cargarDatos(ArchivoInscripciones);

        //para actualizar una inscripción esta debe de tener el mismo id de estudiante y curso
        //que el registro que se desea actualizar

        System.out.println("\n/// Actualizar inscripción///\n");
        Inscripcion2.setAño(2014);
        listadoCursosInscritos.actualizar(Inscripcion2);
        listadoCursosInscritos.guardarInformacion(ArchivoInscripciones);
        listadoCursosInscritos.cargarDatos(ArchivoInscripciones);

        System.out.println("\n/// Eliminar inscripción///\n");Inscripcion2.setAño(2014);
        listadoCursosInscritos.eliminar(Inscripcion1);
        listadoCursosInscritos.guardarInformacion(ArchivoInscripciones);
        listadoCursosInscritos.cargarDatos(ArchivoInscripciones);

        System.out.println("\n/// Cantidad Actual Cursos inscritos///\n");

        System.out.println (listadoCursosInscritos.cantidadActual());

        System.out.println("\n/// Imprimir listado Cursos Inscritos\n");
        listadoCursosInscritos.imprimirListado();
        System.out.println("\n/// Imprimir posicion cursos inscritos\n");
        listadoCursosInscritos.imprimirPosicion(0);



        //// profesor

        Profesor profesor1 = new Profesor("OPS", 1.0,"nestor", "suat", "suat@gmail.com");
        Profesor profesor2 = new Profesor("Nomina", 1.0,"Frankie", "Ruiz", "fruiz@gmail.com");

        CursoProfesor cursoProfesor1 = new CursoProfesor(profesor1,2025,2,poo);
        CursoProfesor cursoProfesor2 = new CursoProfesor(profesor2, 2022,2,integral);

        List<CursoProfesor> cursoProfesorList = new ArrayList<CursoProfesor>();

        CursosProfesores listadoCursoProfesores = new CursosProfesores(cursoProfesorList);

        System.out.println("\n// Cargar información inicial//\n");
        listadoCursoProfesores.cargarDatos(ArchivoCurosProfesores);

        System.out.println("\n// Incribir curso profesor//\n");
        listadoCursoProfesores.inscribir(cursoProfesor1);
        listadoCursoProfesores.inscribir(cursoProfesor2);
        listadoCursoProfesores.guardarInformacion(ArchivoCurosProfesores);
        listadoCursoProfesores.cargarDatos(ArchivoCurosProfesores);

        System.out.println("\n/// Cantidad Actual Cursos Profesor///\n");
        System.out.println(listadoCursoProfesores.cantidadActual());

        System.out.println("\n/// Imprimir Listado Cursos Profesor///\n");
        listadoCursoProfesores.imprimirListado();

        System.out.println("\n/// Imprimir posicion cursos inscritos\n");
        listadoCursoProfesores.imprimirPosicion(0);


    }
}
