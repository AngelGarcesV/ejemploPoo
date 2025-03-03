package com.mycompany.mavenproject4;
import com.mycompany.mavenproject4.Formularios.*;
import javax.swing.*;
import java.awt.*;


public class Mavenproject4  {
        public static void main(String[] args) {
            String ArchivoInformacionInscritos = "informacionInscritos.dat";
            FormulariosPersona.inscripcionesPersonas.cargarDatos(ArchivoInformacionInscritos);
            String ArchivoInscripciones = "Inscripciones.dat";
            FormulariosInscripcion.cursosInscritos.cargarDatos(ArchivoInscripciones);
            String ArchivoCursosProfesor = "CursosProfesores.dat";
            FormulariosCursoProfesor.inscripcionCursosProfesores.cargarDatos(ArchivoCursosProfesor);



            JFrame frame = new JFrame("Interfaz con pestañas y botones");
            frame.setSize(1200, 720);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            // Crear un JTabbedPane para las pestañas
            JTabbedPane tabbedPane = new JTabbedPane();

            // Crear los paneles de cada pestaña
            JPanel panelPersona = crearPanelBotones("Menú Persona");
            JPanel panelEstudiante = crearPanelBotones("Menú Estudiante");
            JPanel panelProfesor = crearPanelBotones("Menú Profesor");
            JPanel panelFacultad = crearPanelBotones("Menú Facultad");
            JPanel panelPrograma = crearPanelBotones("Menú Programa");
            JPanel panelCurso = crearPanelBotones("Menú Curso");
            JPanel panelCursoProfesor = crearPanelBotones("Menú Curso Profesor");
            JPanel panelInscripcionCursos = crearPanelBotones("Menú Cursos Inscritos");

            // Añadir los paneles al JTabbedPane
            tabbedPane.addTab("PERSONA", panelPersona);
            tabbedPane.addTab("ESTUDIANTE", panelEstudiante);
            tabbedPane.addTab("PROFESOR", panelProfesor);
            tabbedPane.addTab("FACULTAD", panelFacultad);
            tabbedPane.addTab("PROGRAMA", panelPrograma);
            tabbedPane.addTab("CURSO", panelCurso);
            tabbedPane.addTab("CURSO PROFESOR", panelCursoProfesor);
            tabbedPane.addTab("CURSOS INSCRITOS", panelInscripcionCursos);

            // Añadir el JTabbedPane al frame
            frame.add(tabbedPane, BorderLayout.CENTER);

            // Mostrar la ventana
            frame.setVisible(true);
        }

    private static JPanel crearPanelBotones(String menu) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Título de la pestaña
        JLabel label = new JLabel(menu, JLabel.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        panel.add(label, gbc);
        gbc.gridwidth = 1;

        // Crear los botones
        String[] buttonLabels = { "CREAR", "VER TODOS", "VER POR ID", "ACTUALIZAR", "ELIMINAR" };
        for (String labelText : buttonLabels) {
            JButton button = new JButton(labelText);
            button.setPreferredSize(new Dimension(200, 100));
            gbc.gridx = 0;
            gbc.gridy = GridBagConstraints.RELATIVE;
            panel.add(button, gbc);

            // Asignar acción según el menú y el botón
            asignarAccionBoton(labelText, menu, button);
        }

        return panel;
    }

    private static void asignarAccionBoton(String labelText, String menu, JButton button) {
        switch (menu) {
            case "Menú Persona":
                asignarAccionesPersona(labelText, button);
                break;
            case "Menú Estudiante":
                asignarAccionesEstudiante(labelText, button);
                break;
            case "Menú Profesor":
                asignarAccionesProfesor(labelText, button);
                break;
            case "Menú Facultad":
                asignarAccionesFacultad(labelText, button);
                break;
            case "Menú Programa":
                asignarAccionesPrograma(labelText, button);
                break;
            case "Menú Curso Profesor":
                asignarAccionesCursoProfesor(labelText, button);
                break;
            case "Menú Curso":
                asignarAccionesCurso(labelText, button);
                break;
            case "Menú Cursos Inscritos":
                asignarAccionesInscripcion(labelText, button);
                break;
            default:
                break;
        }
    }

    private static void asignarAccionesPersona(String labelText, JButton button) {
        switch (labelText) {
            case "CREAR":
                button.addActionListener(e -> FormulariosPersona.mostrarFormularioCrearPersona());
                break;
            case "ACTUALIZAR":
                button.addActionListener(e -> FormulariosPersona.mostrarFormularioActualizarPersona());
                break;
            case "ELIMINAR":
                button.addActionListener(e -> FormulariosPersona.mostrarFormularioEliminarPersona());
                break;
            case "VER POR ID":
                button.addActionListener(e -> FormulariosPersona.mostrarFormularioVerPersonaPorId());
                break;
            case "VER TODOS":
                button.addActionListener(e -> FormulariosPersona.mostrarTablaTodasPersonas());

            default:
                break;
        }
    }

    private static void asignarAccionesEstudiante(String labelText, JButton button) {
        switch (labelText) {
            case "CREAR":
                button.addActionListener(e -> FormulariosEstudiante.mostrarFormularioCrearEstudiante());
                break;
            case "ACTUALIZAR":
                button.addActionListener(e -> FormulariosEstudiante.mostrarFormularioActualizarEstudiante());
                break;
            case "ELIMINAR":
                button.addActionListener(e -> FormulariosEstudiante.mostrarFormularioEliminarEstudiante());
                break;
            case "VER POR ID":
                button.addActionListener(e -> FormulariosEstudiante.mostrarFormularioVerEstudiantePorId());
                break;
            case "VER TODOS":
                button.addActionListener(e -> FormulariosEstudiante.mostrarTablaTodosEstudiantes());
            default:
                break;
        }
    }

    private static void asignarAccionesProfesor(String labelText, JButton button) {
        switch (labelText) {
            case "CREAR":
                button.addActionListener(e -> FormulariosProfesor.mostrarFormularioCrearProfesor());
                break;
            case "ACTUALIZAR":
                button.addActionListener(e -> FormulariosProfesor.mostrarFormularioActualizarProfesor());
                break;
            case "ELIMINAR":
                button.addActionListener(e -> FormulariosProfesor.mostrarFormularioEliminarProfesor());
                break;
            case "VER POR ID":
                button.addActionListener(e -> FormulariosProfesor.mostrarFormularioVerProfesorPorId());
                break;
            case "VER TODOS":
                button.addActionListener(e -> FormulariosProfesor.mostrarTablaTodosProfesores());
            default:
                break;
        }
    }

    private static void asignarAccionesFacultad(String labelText, JButton button) {
        switch (labelText) {
            case "CREAR":
                button.addActionListener(e -> FormulariosFacultad.mostrarFormularioCrearFacultad());
                break;
            case "ACTUALIZAR":
                button.addActionListener(e -> FormulariosFacultad.mostrarFormularioActualizarFacultad());
                break;
            case "ELIMINAR":
                button.addActionListener(e -> FormulariosFacultad.mostrarFormularioEliminarFacultad());
                break;
            case "VER POR ID":
                button.addActionListener(e -> FormulariosFacultad.mostrarFormularioVerFacultadPorId());
                break;
            case "VER TODOS":
                button.addActionListener(e -> FormulariosFacultad.mostrarTodosFacultad());
            default:
                break;
        }
    }

    private static void asignarAccionesPrograma(String labelText, JButton button) {
        switch (labelText) {
            case "CREAR":
                button.addActionListener(e -> FormulariosPrograma.mostrarFormularioCrearPrograma());
                break;
            case "ACTUALIZAR":
                button.addActionListener(e -> FormulariosPrograma.mostrarFormularioActualizarPrograma());
                break;
            case "ELIMINAR":
                button.addActionListener(e -> FormulariosPrograma.mostrarFormularioEliminarPrograma());
                break;
            case "VER POR ID":
                button.addActionListener(e -> FormulariosPrograma.mostrarFormularioVerProgramaPorId());
                break;
            case "VER TODOS":
                button.addActionListener(e -> FormulariosPrograma.mostrarTodosPrograma());
            default:
                break;
        }
    }

    private static void asignarAccionesCursoProfesor(String labelText, JButton button) {
        switch (labelText) {
            case "CREAR":
                button.addActionListener(e -> FormulariosCursoProfesor.mostrarFormularioCrearCursoProfesor());
                break;
            case "ACTUALIZAR":
                button.addActionListener(e -> FormulariosCursoProfesor.mostrarFormularioActualizarCursoProfesor());
                break;
            case "ELIMINAR":
                button.addActionListener(e -> FormulariosCursoProfesor.mostrarFormularioEliminarCursoProfesor());
                break;
            case "VER POR ID":
                button.addActionListener(e -> FormulariosCursoProfesor.mostrarFormularioVerCursoProfesorPorId());
                break;
            case "VER TODOS":
                button.addActionListener(e -> FormulariosCursoProfesor.mostrarTablaCursoProfesor());
            default:
                break;
        }
    }

    private static void asignarAccionesCurso(String labelText, JButton button) {
        switch (labelText) {
            case "CREAR":
                button.addActionListener(e -> FormulariosCurso.mostrarFormularioCrearCurso());
                break;
            case "ACTUALIZAR":
                button.addActionListener(e -> FormulariosCurso.mostrarFormularioActualizarCurso());
                break;
            case "ELIMINAR":
                button.addActionListener(e -> FormulariosCurso.mostrarFormularioEliminarCurso());
                break;
            case "VER POR ID":
                button.addActionListener(e -> FormulariosCurso.mostrarFormularioVerCursoPorId());
                break;
            case "VER TODOS":
                button.addActionListener(e -> FormulariosCurso.mostrarTablaTodosCursos());
            default:
                break;
        }
    }

    private static void asignarAccionesInscripcion(String labelText, JButton button) {
        switch (labelText) {
            case "CREAR":
                button.addActionListener(e -> FormulariosInscripcion.mostrarFormularioCrearInscripcion());
                break;
            case "ACTUALIZAR":
                button.addActionListener(e -> FormulariosInscripcion.mostrarFormularioActualizarInscripcion());
                break;
            case "ELIMINAR":
                button.addActionListener(e -> FormulariosInscripcion.mostrarFormularioEliminarInscripcion());
                break;
            case "VER POR ID":
                button.addActionListener(e -> FormulariosInscripcion.mostrarFormularioVerInscripcionPorId());
                break;
            case "VER TODOS":
                button.addActionListener(e -> FormulariosInscripcion.mostrarTablaTodasInscripciones());
            default:
                break;
        }
    }
}
