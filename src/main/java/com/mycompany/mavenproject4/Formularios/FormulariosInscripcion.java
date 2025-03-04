package com.mycompany.mavenproject4.Formularios;

import com.mycompany.mavenproject4.Controladores.CursosInscritos;
import com.mycompany.mavenproject4.modelos.Curso;
import com.mycompany.mavenproject4.modelos.CursoProfesor;
import com.mycompany.mavenproject4.modelos.Estudiante;
import com.mycompany.mavenproject4.modelos.Inscripcion;
import com.mycompany.mavenproject4.repositorios.CursoRepo;
import com.mycompany.mavenproject4.repositorios.EstudianteRepo;
import com.mycompany.mavenproject4.repositorios.InscripcionRepo;

import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class FormulariosInscripcion {
    static EstudianteRepo repositorioEstudiante = new EstudianteRepo();
    static CursoRepo repositorioCurso = new CursoRepo();
    static InscripcionRepo repositorioInscripcion = new InscripcionRepo();
    public static CursosInscritos cursosInscritos = new CursosInscritos(new ArrayList<Inscripcion>());
    static String ArchivoInscripciones = "Inscripciones.dat";

    private static void guardarInscripcionDB_Archivo(Curso infoCurso, int año, int semestre, Estudiante infoEstudiante, String archivoInscripciones) {
        Inscripcion nuevaInscripcion = repositorioInscripcion.crearInscripcion (new Inscripcion(null, infoCurso,año,semestre,infoEstudiante));
        cursosInscritos.inscribir(nuevaInscripcion);
        cursosInscritos.guardarInformacion(archivoInscripciones);
    }

    private static void actualizarInscripcionDB_Archivo(Long idInscripcion, Curso infoCurso, int año, int semestre, Estudiante infoEstudiante, String archivoInscripciones){
        Inscripcion inscripcionActualizada = new Inscripcion(idInscripcion, infoCurso, año, semestre, infoEstudiante);


        InscripcionRepo.actualizarInscripcionPorId(idInscripcion, inscripcionActualizada);
        cursosInscritos.actualizar(inscripcionActualizada);
        cursosInscritos.guardarInformacion(archivoInscripciones);
    }


    public static void mostrarFormularioCrearInscripcion() {
        JFrame formularioFrame = new JFrame("Formulario Crear Inscripción");
        formularioFrame.setSize(400, 300);
        formularioFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        formularioFrame.setLayout(new GridLayout(5, 2));

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
                try{
                    Long idCurso = Long.parseLong(idCursoField.getText());
                    int año = Integer.parseInt(añoField.getText());
                    int semestre = Integer.parseInt(semestreField.getText());
                    Long estudianteId = Long.parseLong(estudianteIdField.getText());

                    Curso infoCurso = repositorioCurso.obtenerCursoByID(idCurso);
                    Estudiante infoEstudiante = repositorioEstudiante.obtenerEstudianteByID(estudianteId);

                    if (infoCurso != null && infoEstudiante != null) {
                        guardarInscripcionDB_Archivo(infoCurso,año,semestre,infoEstudiante, ArchivoInscripciones);
                        JOptionPane.showMessageDialog(formularioFrame, "Curso guardado correctamente");
                    }else{
                        JOptionPane.showMessageDialog(formularioFrame, "El Curso no existe");
                    }
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(formularioFrame, ex.getMessage());
                }



                formularioFrame.dispose();
            }
        });

        formularioFrame.add(idCursoLabel);
        formularioFrame.add(idCursoField);
        formularioFrame.add(añoLabel);
        formularioFrame.add(añoField);
        formularioFrame.add(semestreLabel);
        formularioFrame.add(semestreField);
        formularioFrame.add(estudianteIdLabel);
        formularioFrame.add(estudianteIdField);
        formularioFrame.add(new JLabel());
        formularioFrame.add(crearButton);
        formularioFrame.setVisible(true);
    }

    public static void mostrarFormularioEliminarInscripcion() {
        JFrame formularioFrame = new JFrame("Formulario Eliminar Inscripción");
        formularioFrame.setSize(400, 300);
        formularioFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        formularioFrame.setLayout(new GridLayout(2, 2));

        JLabel idLabel = new JLabel("ID Inscripción:");
        JTextField idField = new JTextField();
        JButton eliminarButton = new JButton("Eliminar");

        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                try{
                    Long idInscripcion = Long.parseLong(id);
                    Inscripcion infoArchivo = InscripcionRepo.obtenerInscripcionByID(idInscripcion);
                    boolean inscripcionEliminada = repositorioInscripcion.eliminarInscripcion(idInscripcion);
                    if(inscripcionEliminada){
                        cursosInscritos.eliminar(infoArchivo);
                        cursosInscritos.guardarInformacion(ArchivoInscripciones);
                        JOptionPane.showMessageDialog(formularioFrame, "La inscripción se ha eliminado");
                    }else{
                        JOptionPane.showMessageDialog(formularioFrame, "El Inscripcion no existe");
                    }
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(formularioFrame, ex.getMessage());
                }


                formularioFrame.dispose();
            }
        });

        formularioFrame.add(idLabel);
        formularioFrame.add(idField);
        formularioFrame.add(new JLabel());
        formularioFrame.add(eliminarButton);


        formularioFrame.setVisible(true);
    }
    public static void mostrarTablaTodasInscripciones() {
        List<Inscripcion> inscripciones = repositorioInscripcion.obtenerTodasInscripciones();
        JFrame frame = new JFrame("Lista de Inscripciones");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        String[] columnNames = {"ID", "Año", "Semestre", "Curso_ID", "Estudiante_ID"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        for (Inscripcion inscripcion : inscripciones) {
            Object[] rowData = {inscripcion.getId(), inscripcion.getAño(), inscripcion.getSemestre(), inscripcion.getCurso().getID(), inscripcion.getEstudiante().getID()};
            tableModel.addRow(rowData);
        }

        frame.add(scrollPane, BorderLayout.CENTER);
        frame.setVisible(true);
    }


    public static void mostrarFormularioVerInscripcionPorId() {
        JFrame formularioFrame = new JFrame("Ver Inscripción por ID");
        formularioFrame.setSize(600, 400);
        formularioFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        formularioFrame.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new FlowLayout());
        JLabel idLabel = new JLabel("ID Inscripción:");
        JTextField idField = new JTextField(10);
        JButton verButton = new JButton("Ver");

        inputPanel.add(idLabel);
        inputPanel.add(idField);
        inputPanel.add(verButton);


        String[] columnNames = {"ID", "AÑO", "SEMESTRE", "CURSO_ID", "ESTUDIANTE_ID"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        verButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Long idInscripcion = Long.parseLong(idField.getText());
                    Inscripcion inscripcion = InscripcionRepo.obtenerInscripcionByID(idInscripcion);

                    if (inscripcion != null) {

                        tableModel.setRowCount(0);

                        Object[] rowData = {
                                inscripcion.getId(),
                                inscripcion.getAño(),
                                inscripcion.getSemestre(),
                                inscripcion.getCurso().getID(),
                                inscripcion.getEstudiante().getID()
                        };
                        tableModel.addRow(rowData);
                    } else {
                        JOptionPane.showMessageDialog(formularioFrame, "Inscripción no encontrada", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(formularioFrame, "Ingrese un ID válido", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        formularioFrame.add(inputPanel, BorderLayout.NORTH);
        formularioFrame.add(scrollPane, BorderLayout.CENTER);
        formularioFrame.setVisible(true);
    }


    public static void mostrarFormularioActualizarInscripcion() {
        JFrame formularioFrame = new JFrame("Actualizar Inscripción");
        formularioFrame.setSize(400, 300);
        formularioFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        formularioFrame.setLayout(new GridLayout(7, 2));

        JLabel idLabel = new JLabel("ID Inscripción:");
        JTextField idField = new JTextField();
        JButton buscarButton = new JButton("Buscar");

        JLabel idCursoLabel = new JLabel("ID Curso:");
        JTextField idCursoField = new JTextField();
        JLabel añoLabel = new JLabel("Año:");
        JTextField añoField = new JTextField();
        JLabel semestreLabel = new JLabel("Semestre:");
        JTextField semestreField = new JTextField();
        JLabel estudianteIdLabel = new JLabel("ID Estudiante:");
        JTextField estudianteIdField = new JTextField();

        JButton actualizarButton = new JButton("Actualizar");


        idCursoField.setEnabled(false);
        añoField.setEnabled(false);
        semestreField.setEnabled(false);
        estudianteIdField.setEnabled(false);
        actualizarButton.setEnabled(false);

        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                botonBuscarInscripcion(idField, idCursoField, añoField, semestreField, estudianteIdField, actualizarButton, formularioFrame);
            }
        });

        actualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Long idInscripcion = Long.parseLong(idField.getText().trim());
                    Long idCurso = Long.parseLong(idCursoField.getText().trim());
                    int año = Integer.parseInt(añoField.getText().trim());
                    int semestre = Integer.parseInt(semestreField.getText().trim());
                    Long idEstudiante = Long.parseLong(estudianteIdField.getText().trim());

                    Estudiante infoEstudiante = repositorioEstudiante.obtenerEstudianteByID(idEstudiante);
                    Curso infoCurso = repositorioCurso.obtenerCursoByID(idCurso);
                    if(infoCurso != null && infoEstudiante != null){
                        actualizarInscripcionDB_Archivo(idInscripcion,infoCurso,año,semestre,infoEstudiante,ArchivoInscripciones);
                    }else{
                        JOptionPane.showMessageDialog(formularioFrame, "No se encontro en estudiante o curso");
                    }

                    JOptionPane.showMessageDialog(formularioFrame, "Inscripción actualizada con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    formularioFrame.dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(formularioFrame, "Ingrese valores válidos", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(formularioFrame, "Ocurrió un error al actualizar la inscripción", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        formularioFrame.add(idLabel);
        formularioFrame.add(idField);
        formularioFrame.add(new JLabel());
        formularioFrame.add(buscarButton);
        formularioFrame.add(idCursoLabel);
        formularioFrame.add(idCursoField);
        formularioFrame.add(añoLabel);
        formularioFrame.add(añoField);
        formularioFrame.add(semestreLabel);
        formularioFrame.add(semestreField);
        formularioFrame.add(estudianteIdLabel);
        formularioFrame.add(estudianteIdField);
        formularioFrame.add(new JLabel());
        formularioFrame.add(actualizarButton);

        formularioFrame.setVisible(true);
    }

    public static void botonBuscarInscripcion(JTextField idField, JTextField idCursoField, JTextField añoField, JTextField semestreField, JTextField estudianteIdField, JButton actualizarButton, JFrame frame) {
        String idTexto = idField.getText().trim();
        if (idTexto.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Ingrese un ID válido.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            Long idInscripcion = Long.parseLong(idTexto);
            Inscripcion inscripcion = InscripcionRepo.obtenerInscripcionByID(idInscripcion);

            if (inscripcion != null) {

                idCursoField.setText(String.valueOf(inscripcion.getCurso().getID()));
                añoField.setText(String.valueOf(inscripcion.getAño()));
                semestreField.setText(String.valueOf(inscripcion.getSemestre()));
                estudianteIdField.setText(String.valueOf(inscripcion.getEstudiante().getID()));


                idCursoField.setEnabled(true);
                añoField.setEnabled(true);
                semestreField.setEnabled(true);
                estudianteIdField.setEnabled(true);
                actualizarButton.setEnabled(true);
            } else {
                JOptionPane.showMessageDialog(frame, "Inscripción no encontrada.", "Error", JOptionPane.WARNING_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Error: El ID ingresado no es un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
