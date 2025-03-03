package com.mycompany.mavenproject4.Formularios;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

public class OpcionesMenu {

    private static final Map<String, Runnable> actionMap = new HashMap<>();

    static {
        // Asociaciones para el menú Persona
        actionMap.put("CREAR|Menú Persona", () -> FormulariosPersona.mostrarFormularioCrearPersona());
        actionMap.put("ACTUALIZAR|Menú Persona", () -> FormulariosPersona.mostrarFormularioActualizarPersona());
        actionMap.put("ELIMINAR|Menú Persona", () -> FormulariosPersona.mostrarFormularioEliminarPersona());
        actionMap.put("VER POR ID|Menú Persona", () -> FormulariosPersona.mostrarFormularioVerPersonaPorId());

        actionMap.put("CREAR|Menú Estudiante", () -> FormulariosEstudiante.mostrarFormularioCrearEstudiante());
        actionMap.put("ACTUALIZAR|Menú Estudiante", () -> FormulariosEstudiante.mostrarFormularioActualizarEstudiante());
        actionMap.put("ELIMINAR|Menú Estudiante", () -> FormulariosEstudiante.mostrarFormularioEliminarEstudiante());
        actionMap.put("VER POR ID|Menú Estudiante", () -> FormulariosEstudiante.mostrarFormularioVerEstudiantePorId());

// Asignación de acciones para "Menú Profesor"
        actionMap.put("CREAR|Menú Profesor", () -> FormulariosProfesor.mostrarFormularioCrearProfesor());
        actionMap.put("ACTUALIZAR|Menú Profesor", () -> FormulariosProfesor.mostrarFormularioActualizarProfesor());
        actionMap.put("ELIMINAR|Menú Profesor", () -> FormulariosProfesor.mostrarFormularioEliminarProfesor());
        actionMap.put("VER POR ID|Menú Profesor", () -> FormulariosProfesor.mostrarFormularioVerProfesorPorId());

// Asignación de acciones para "Menú Facultad"
        actionMap.put("CREAR|Menú Facultad", () -> FormulariosFacultad.mostrarFormularioCrearFacultad());
        actionMap.put("ACTUALIZAR|Menú Facultad", () -> FormulariosFacultad.mostrarFormularioActualizarFacultad());
        actionMap.put("ELIMINAR|Menú Facultad", () -> FormulariosFacultad.mostrarFormularioEliminarFacultad());
        actionMap.put("VER POR ID|Menú Facultad", () -> FormulariosFacultad.mostrarFormularioVerFacultadPorId());

// Asignación de acciones para "Menú Programa"
        actionMap.put("CREAR|Menú Programa", () -> FormulariosPrograma.mostrarFormularioCrearPrograma());
        actionMap.put("ACTUALIZAR|Menú Programa", () -> FormulariosPrograma.mostrarFormularioActualizarPrograma());
        actionMap.put("ELIMINAR|Menú Programa", () -> FormulariosPrograma.mostrarFormularioEliminarPrograma());
        actionMap.put("VER POR ID|Menú Programa", () -> FormulariosPrograma.mostrarFormularioVerProgramaPorId());

// Asignación de acciones para "Menú Curso"
        actionMap.put("CREAR|Menú Curso", () -> FormulariosCurso.mostrarFormularioCrearCurso());
        actionMap.put("ACTUALIZAR|Menú Curso", () -> FormulariosCurso.mostrarFormularioActualizarCurso());
        actionMap.put("ELIMINAR|Menú Curso", () -> FormulariosCurso.mostrarFormularioEliminarCurso());
        actionMap.put("VER POR ID|Menú Curso", () -> FormulariosCurso.mostrarFormularioVerCursoPorId());

// Asignación de acciones para "Menú Curso Profesor"
        actionMap.put("CREAR|Menú Curso Profesor", () -> FormulariosCursoProfesor.mostrarFormularioCrearCursoProfesor());
        actionMap.put("ACTUALIZAR|Menú Curso Profesor", () -> FormulariosCursoProfesor.mostrarFormularioActualizarCursoProfesor());
        actionMap.put("ELIMINAR|Menú Curso Profesor", () -> FormulariosCursoProfesor.mostrarFormularioEliminarCursoProfesor());
        actionMap.put("VER POR ID|Menú Curso Profesor", () -> FormulariosCursoProfesor.mostrarFormularioVerCursoProfesorPorId());

// Asignación de acciones para "Menú Inscripción Cursos"
        actionMap.put("CREAR|Menú Cursos Inscritos", () -> FormulariosCurso.mostrarFormularioCrearCurso());
        actionMap.put("ACTUALIZAR|Menú Cursos Inscritos", () -> FormulariosCurso.mostrarFormularioActualizarCurso());
        actionMap.put("ELIMINAR|Menú Cursos Inscritos", () -> FormulariosCurso.mostrarFormularioEliminarCurso());
        actionMap.put("VER POR ID|Menú Cursos Inscritos", () -> FormulariosCurso.mostrarFormularioVerCursoPorId());
    }

    public static void addButtonActionListener(String labelText, String menu, javax.swing.JButton button) {
        String key = labelText + "|" + menu;
        if (actionMap.containsKey(key)) {
            button.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    actionMap.get(key).run();
                }
            });
        }
    }
}
