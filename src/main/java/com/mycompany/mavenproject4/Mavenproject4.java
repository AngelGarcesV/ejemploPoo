package com.mycompany.mavenproject4;
import com.mycompany.mavenproject4.Controladores.*;
import com.mycompany.mavenproject4.Formularios.*;
import com.mycompany.mavenproject4.Formularios.observadores.PanelCursos;
import com.mycompany.mavenproject4.Formularios.observadores.PanelInscripcion;
import com.mycompany.mavenproject4.Formularios.observadores.PanelProfesores;
import com.mycompany.mavenproject4.Observador.CursoObserver;
import com.mycompany.mavenproject4.Observador.CursoSubject;
import com.mycompany.mavenproject4.Observador.InscripcionSubject;
import com.mycompany.mavenproject4.Observador.ProfesorSubject;
import com.mycompany.mavenproject4.modelos.Curso;
import com.mycompany.mavenproject4.modelos.Estudiante;
import com.mycompany.mavenproject4.modelos.Inscripcion;
import com.mycompany.mavenproject4.modelos.Programa;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import static com.mycompany.mavenproject4.Formularios.FormulariosCurso.cursoController;
import static com.mycompany.mavenproject4.Formularios.FormulariosEstudiante.estudianteController;


public class Mavenproject4 {
    static EstudianteController estudianteController = new EstudianteController();
    static InscripcionController inscripcionController = new InscripcionController();
    static ProgramaController programaController = new ProgramaController();

    public static void main(String[] args) {
        CursoSubject cursoSubject = new CursoSubject();
        String ArchivoInformacionInscritos = "informacionInscritos.dat";
        PersonaController.inscripcionesPersonas.cargarDatos(ArchivoInformacionInscritos);
        String ArchivoInscripciones = "Inscripciones.dat";
        InscripcionController.cursosInscritos.cargarDatos(ArchivoInscripciones);
        String ArchivoCursosProfesor = "CursosProfesores.dat";
        CursoProfesorController.inscripcionCursosProfesores.cargarDatos(ArchivoCursosProfesor);

        // Crear la ventana principal
        JFrame frame = new JFrame("Interfaz con Menús y Botones");
        frame.setSize(1200, 720);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Crear un menú superior
        JMenuBar menuBar = new JMenuBar();

        // Crear los menús
        JMenu menuEstudiante = new JMenu("Estudiante");
        JMenu menuPersona = new JMenu("Persona");
        JMenu menuProfesor = new JMenu("Profesor");
        JMenu menuFacultad = new JMenu("Facultad");
        JMenu menuPrograma = new JMenu("Programa");
        JMenu menuCurso = new JMenu("Curso");
        JMenu menuCursoProfesor = new JMenu("Curso Profesor");
        JMenu menuInscripciones = new JMenu("Cursos Inscritos");

        // Agregar opciones a los menús
        JMenuItem estudianteItem = new JMenuItem("Estudiante");
        JMenuItem estudianteDetalleItem = new JMenuItem("Estudiante Detalle");
        JMenuItem personaItem = new JMenuItem("Persona");
        JMenuItem profesorItem = new JMenuItem("Profesor");
        JMenuItem facultadItem = new JMenuItem("Facultad");
        JMenuItem programaItem = new JMenuItem("Programa");
        JMenuItem cursoItem = new JMenuItem("Curso");
        JMenuItem cursoProfesorItem = new JMenuItem("Curso Profesor");
        JMenuItem inscripcionesItem = new JMenuItem("Cursos Inscritos");

        menuEstudiante.add(estudianteItem);
        menuEstudiante.add(estudianteDetalleItem);
        menuPersona.add(personaItem);
        menuProfesor.add(profesorItem);
        menuFacultad.add(facultadItem);
        menuPrograma.add(programaItem);
        menuCurso.add(cursoItem);
        menuCursoProfesor.add(cursoProfesorItem);
        menuInscripciones.add(inscripcionesItem);

        menuBar.add(menuEstudiante);
        menuBar.add(menuPersona);
        menuBar.add(menuProfesor);
        menuBar.add(menuFacultad);
        menuBar.add(menuPrograma);
        menuBar.add(menuCurso);
        menuBar.add(menuCursoProfesor);
        menuBar.add(menuInscripciones);

        frame.setJMenuBar(menuBar);

        // Crear el panel principal para los contenidos
        JPanel contentPanel = new JPanel();
        contentPanel.setLayout(new CardLayout());

        // Crear los paneles para cada sección
        JPanel panelEstudiante = crearPanelBotones("Menú Estudiante");
        JPanel panelEstudianteDetalle = crearPanelEstudianteDetalle();
        JPanel panelPersona = crearPanelBotones("Menú Persona");
        JPanel panelProfesor = crearPanelBotones("Menú Profesor");
        JPanel panelFacultad = crearPanelBotones("Menú Facultad");
        JPanel panelPrograma = crearPanelBotones("Menú Programa");
        JPanel panelCurso = crearPanelBotones("Menú Curso");
        JPanel panelCursoProfesor = crearPanelBotones("Menú Curso Profesor");
        JPanel panelInscripcionCursos = crearPanelBotones("Menú Cursos Inscritos");

        // Agregar los paneles al panel principal
        contentPanel.add(panelEstudiante, "Estudiante");
        contentPanel.add(panelEstudianteDetalle, "Estudiante Detalle");
        contentPanel.add(panelPersona, "Persona");
        contentPanel.add(panelProfesor, "Profesor");
        contentPanel.add(panelFacultad, "Facultad");
        contentPanel.add(panelPrograma, "Programa");
        contentPanel.add(panelCurso, "Curso");
        contentPanel.add(panelCursoProfesor, "Curso Profesor");
        contentPanel.add(panelInscripcionCursos, "Cursos Inscritos");

        frame.add(contentPanel, BorderLayout.CENTER);

        // Acción para los ítems del menú
        estudianteItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) (contentPanel.getLayout());
                cl.show(contentPanel, "Estudiante");
            }
        });

        estudianteDetalleItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) (contentPanel.getLayout());
                cl.show(contentPanel, "Estudiante Detalle");
            }
        });

        personaItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) (contentPanel.getLayout());
                cl.show(contentPanel, "Persona");
            }
        });

        profesorItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) (contentPanel.getLayout());
                cl.show(contentPanel, "Profesor");
            }
        });

        facultadItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) (contentPanel.getLayout());
                cl.show(contentPanel, "Facultad");
            }
        });

        programaItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) (contentPanel.getLayout());
                cl.show(contentPanel, "Programa");
            }
        });

        cursoItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) (contentPanel.getLayout());
                cl.show(contentPanel, "Curso");
            }
        });

        cursoProfesorItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) (contentPanel.getLayout());
                cl.show(contentPanel, "Curso Profesor");
            }
        });

        inscripcionesItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) (contentPanel.getLayout());
                cl.show(contentPanel, "Cursos Inscritos");
            }
        });

        frame.setVisible(true);
    }

    private static JPanel crearPanelEstudianteDetalle() {
        InscripcionSubject inscripcionSubject = new InscripcionSubject();
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel label = new JLabel("Detalle Estudiante", JLabel.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        panel.add(label, gbc);
        gbc.gridwidth = 1;

        JLabel labelIdEstudiante = new JLabel("ID Estudiante:");
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        panel.add(labelIdEstudiante, gbc);

        JTextField idEstudianteField = new JTextField(15);
        gbc.gridx = 1;
        panel.add(idEstudianteField, gbc);

        JButton btnVerEstudiante = new JButton("Ver Información");
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.gridwidth = 2;
        panel.add(btnVerEstudiante, gbc);

        String[] columnNames = { "ID", "Apellido", "Nombre", "Email", "Promedio" };
        Object[][] data = {};
        DefaultTableModel tableModel = new DefaultTableModel(data, columnNames);
        JTable table = new JTable(tableModel);


        table.setRowHeight(25);
        table.setPreferredScrollableViewportSize(new Dimension(900, 25));

        JScrollPane scrollPane = new JScrollPane(table);
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        panel.add(scrollPane, gbc);


        btnVerEstudiante.addActionListener(e -> {
            String idEstudiante = idEstudianteField.getText();

            try {
                Long idPersona = Long.parseLong(idEstudiante);
                Estudiante estudiante = estudianteController.getEstudianteById(idPersona);

                if (estudiante != null) {

                    tableModel.setRowCount(0);


                    Object[] rowData = {
                            estudiante.getID(),
                            estudiante.getApellidos(),
                            estudiante.getNombres(),
                            estudiante.getEmail(),
                            estudiante.getPromedio()
                    };
                    tableModel.addRow(rowData);
                } else {
                    JOptionPane.showMessageDialog(panel, "Estudiante no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(panel, "Ingrese un ID válido", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });


        JTabbedPane subTabbedPane = new JTabbedPane();
        subTabbedPane.addTab("Historial Cursos", new PanelInscripcion(new InscripcionSubject()));


        JPanel inscribirCursoPanel = new JPanel();
        JPanel panelCursos = new PanelCursos(new CursoSubject());
        JPanel panelProfesor = new PanelProfesores(new ProfesorSubject());
        inscribirCursoPanel.setLayout(new GridLayout(5, 2));


        JLabel idCursoLabel = new JLabel("ID Curso:");
        JTextField idCursoField = new JTextField();
        JLabel añoLabel = new JLabel("Año:");
        JTextField añoField = new JTextField();
        JLabel semestreLabel = new JLabel("Semestre:");
        JTextField semestreField = new JTextField();
        JLabel estudianteIdLabel = new JLabel("ID Estudiante:");
        JTextField estudianteIdField = new JTextField();

        JButton crearButton = new JButton("Crear");

        crearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Long idCurso = Long.parseLong(idCursoField.getText());
                    int año = Integer.parseInt(añoField.getText());
                    int semestre = Integer.parseInt(semestreField.getText());
                    Long estudianteId = Long.parseLong(estudianteIdField.getText());

                    Curso infoCurso = cursoController.getCursoById(idCurso);
                    Estudiante infoEstudiante = estudianteController.getEstudianteById(estudianteId);

                    if (infoCurso != null && infoEstudiante != null) {
                        Inscripcion infoInscripcion = new Inscripcion(null, infoCurso, año, semestre, infoEstudiante);
                        Inscripcion nuevaInscripcion = inscripcionController.createInscripcion(infoInscripcion);
                        if (nuevaInscripcion != null) {
                            inscripcionSubject.notificarObservers();
                            JOptionPane.showMessageDialog(inscribirCursoPanel, "Inscripción guardada correctamente");
                        } else {
                            JOptionPane.showMessageDialog(inscribirCursoPanel, "Error al guardar inscripción");
                        }
                    } else {
                        JOptionPane.showMessageDialog(inscribirCursoPanel, "El curso o el estudiante no existe");
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(inscribirCursoPanel, ex.getMessage());
                }
            }
        });

        inscribirCursoPanel.add(idCursoLabel);
        inscribirCursoPanel.add(idCursoField);
        inscribirCursoPanel.add(añoLabel);
        inscribirCursoPanel.add(añoField);
        inscribirCursoPanel.add(semestreLabel);
        inscribirCursoPanel.add(semestreField);
        inscribirCursoPanel.add(estudianteIdLabel);
        inscribirCursoPanel.add(estudianteIdField);
        inscribirCursoPanel.add(new JLabel());
        inscribirCursoPanel.add(crearButton);


        subTabbedPane.addTab("Inscribir Curso", inscribirCursoPanel);

        subTabbedPane.addTab("Cursos", panelCursos);
        subTabbedPane.addTab("Profesores", panelProfesor);


        subTabbedPane.setPreferredSize(new Dimension(900, 200));

        gbc.gridy = GridBagConstraints.RELATIVE;
        panel.add(subTabbedPane, gbc);

        return panel;
    }










    private static JPanel crearPanelBotones(String menu) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel label = new JLabel(menu, JLabel.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        panel.add(label, gbc);
        gbc.gridwidth = 1;

        String[] buttonLabels = { "CREAR", "VER TODOS", "VER POR ID", "ACTUALIZAR", "ELIMINAR" };
        for (String labelText : buttonLabels) {
            JButton button = new JButton(labelText);
            button.setPreferredSize(new Dimension(200, 100));
            gbc.gridx = 0;
            gbc.gridy = GridBagConstraints.RELATIVE;
            panel.add(button, gbc);

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
