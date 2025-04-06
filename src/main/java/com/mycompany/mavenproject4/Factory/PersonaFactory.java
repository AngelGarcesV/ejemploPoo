package com.mycompany.mavenproject4.Factory;

import com.mycompany.mavenproject4.modelos.Estudiante;
import com.mycompany.mavenproject4.modelos.Profesor;
import com.mycompany.mavenproject4.modelos.Programa;
import com.mycompany.mavenproject4.modelos.TipoPersona;
import com.mycompany.mavenproject4.modelos.interfaces.Ipersona;

public class PersonaFactory {
    public static Ipersona crearPersona(TipoPersona tipo) {
        switch (tipo) {
            case PROFESOR:
                return new Profesor();
            case ESTUDIANTE:
                return new Estudiante();
            default:
                throw new IllegalArgumentException("Tipo de persona no reconocido");
        }
    }

    public static Profesor crearProfesor(String tipoContrato, Long ID, String nombres, String apellidos, String email) {
        return new Profesor(tipoContrato, ID, nombres, apellidos, email);
    }

    public static Estudiante crearEstudiante(double codigo, Programa programa, Boolean activo, Double promedio,
                                           Long ID, String nombres, String apellidos, String email) {
        return new Estudiante(codigo, programa, activo, promedio, ID, nombres, apellidos, email);
    }
}
