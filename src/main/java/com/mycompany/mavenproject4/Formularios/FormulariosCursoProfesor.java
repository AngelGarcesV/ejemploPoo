package com.mycompany.mavenproject4.Formularios;

import com.mycompany.mavenproject4.Controladores.CursosProfesores;
import com.mycompany.mavenproject4.modelos.Curso;
import com.mycompany.mavenproject4.modelos.CursoProfesor;
import com.mycompany.mavenproject4.modelos.Profesor;
import com.mycompany.mavenproject4.repositorios.CursoProfesorRepo;
import com.mycompany.mavenproject4.repositorios.CursoRepo;
import com.mycompany.mavenproject4.repositorios.ProfesorRepo;

import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class FormulariosCursoProfesor {

    static ProfesorRepo repositorioProfesor = new ProfesorRepo();
    static CursoRepo repositorioCurso = new CursoRepo();
    static CursoProfesorRepo repositorioCursoProfesor = new CursoProfesorRepo();
    public static CursosProfesores inscripcionCursosProfesores = new CursosProfesores(new ArrayList<CursoProfesor>());
    static String ArchivoCursosProfesor = "cursosProfesores.dat";

    public static void guardarCursosProfesoresDB_InscribirArchivo(Profesor infoProfesor, int año, int semestre, Curso infoCurso, String nombreArchivo){
        CursoProfesor infoCursoProfesor = new CursoProfesor(null,infoProfesor,año,semestre,infoCurso);
        CursoProfesor nuevoCursoProfesor = repositorioCursoProfesor.crearCursoProfesor(infoCursoProfesor);
        inscripcionCursosProfesores.inscribir(nuevoCursoProfesor);
        inscripcionCursosProfesores.guardarInformacion(nombreArchivo);
    }

    public static void ActualizarCurssosProfesoresDB_ActualizarArchivo(Long id, Profesor profesor, int año, int semestre, Curso curso, String nombreArchivo){
        CursoProfesor cursoProfesorActualizado = new CursoProfesor(id,profesor,año,semestre,curso);
        repositorioCursoProfesor.actualizarCursoProfesorPorId(id, cursoProfesorActualizado);
        inscripcionCursosProfesores.guardarInformacion(nombreArchivo);
    }

    public static void mostrarFormularioCrearCursoProfesor() {
        JFrame formularioFrame = new JFrame("Formulario Crear CursoProfesor");
        formularioFrame.setSize(400, 300);
        formularioFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        formularioFrame.setLayout(new GridLayout(6, 2));
        JLabel idProfesorLabel = new JLabel("ID Profesor:");
        JTextField idProfesorField = new JTextField();
        JLabel añoLabel = new JLabel("Año:");
        JTextField añoField = new JTextField();
        JLabel semestreLabel = new JLabel("Semestre:");
        JTextField semestreField = new JTextField();
        JLabel cursoLabel = new JLabel("Id_Curso:");
        JTextField cursoField = new JTextField();

        JButton crearButton = new JButton("Crear");

        crearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int año = Integer.parseInt(añoField.getText());
                int semestre = Integer.parseInt(semestreField.getText());
                try{
                    Long idProfesor = Long.parseLong(idProfesorField.getText());
                    Long idcurso = Long.parseLong(cursoField.getText());
                    Profesor infoProfesor = repositorioProfesor.obtenerProfesorByID(idProfesor);
                    Curso infoCurso = repositorioCurso.obtenerCursoByID(idcurso);
                    if (infoProfesor != null && infoCurso != null) {

                        guardarCursosProfesoresDB_InscribirArchivo(infoProfesor,año,semestre, infoCurso,ArchivoCursosProfesor);

                        JOptionPane.showMessageDialog(formularioFrame, "Profesor Creado Exitosamente");
                    }else{
                        JOptionPane.showMessageDialog(formularioFrame, "Profesor o Curso no encontrado");
                    }
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(formularioFrame, ex.getMessage());
                }

                formularioFrame.dispose();
            }
        });

        formularioFrame.add(idProfesorLabel);
        formularioFrame.add(idProfesorField);
        formularioFrame.add(añoLabel);
        formularioFrame.add(añoField);
        formularioFrame.add(semestreLabel);
        formularioFrame.add(semestreField);
        formularioFrame.add(cursoLabel);
        formularioFrame.add(cursoField);
        formularioFrame.add(new JLabel());
        formularioFrame.add(crearButton);


        formularioFrame.setVisible(true);
    }

    public static void mostrarFormularioEliminarCursoProfesor() {
        JFrame formularioFrame = new JFrame("Formulario Eliminar CursoProfesor");
        formularioFrame.setSize(400, 300);
        formularioFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        formularioFrame.setLayout(new GridLayout(2, 2));

        JLabel idLabel = new JLabel("ID CursoProfesor:");
        JTextField idField = new JTextField();
        JButton eliminarButton = new JButton("Eliminar");

        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                try{
                    Long idCursoProfesor = Long.parseLong(id);
                    boolean eliminado = repositorioCursoProfesor.eliminarCursoProfesor(idCursoProfesor);
                    if (eliminado){
                        JOptionPane.showMessageDialog(formularioFrame, "Curso eliminado");
                    }else{
                        JOptionPane.showMessageDialog(formularioFrame, "Curso no eliminado");
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

    public static void mostrarFormularioVerCursoProfesorPorId() {
        JFrame formularioFrame = new JFrame("Ver CursoProfesor por ID");
        formularioFrame.setSize(600, 400);
        formularioFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        formularioFrame.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new FlowLayout());
        JLabel idLabel = new JLabel("ID CursoProfesor:");
        JTextField idField = new JTextField(10);
        JButton verButton = new JButton("Ver");

        inputPanel.add(idLabel);
        inputPanel.add(idField);
        inputPanel.add(verButton);


        String[] columnNames = {"ID", "AÑO", "SEMESTRE", "CURSO_ID", "PROFESOR_ID"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        verButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Long idCursoProfesor = Long.parseLong(idField.getText());
                    CursoProfesor cursoProfesor = repositorioCursoProfesor.obtenerCursoProfesorByID(idCursoProfesor);

                    if (cursoProfesor != null) {

                        tableModel.setRowCount(0);

                        Object[] rowData = {
                                cursoProfesor.getId(),
                                cursoProfesor.getAño(),
                                cursoProfesor.getSemestre(),
                                cursoProfesor.getCurso().getID(),
                                cursoProfesor.getProfesor().getID()
                        };
                        tableModel.addRow(rowData);
                    } else {
                        JOptionPane.showMessageDialog(formularioFrame, "CursoProfesor no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
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

    public static void mostrarTablaCursoProfesor() {
        List<CursoProfesor> cursoProfesores = repositorioCursoProfesor.obtenerTodosCursoProfesor();
        JFrame frame = new JFrame("Lista de Curso - Profesor");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        String[] columnNames = {"ID", "Año", "Semestre", "Curso_ID", "Profesor_ID"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        for (CursoProfesor cursoProfesor : cursoProfesores) {
            Object[] rowData = {
                    cursoProfesor.getId(),
                    cursoProfesor.getAño(),
                    cursoProfesor.getSemestre(),
                    cursoProfesor.getCurso().getID(),
                    cursoProfesor.getProfesor().getID()
            };
            tableModel.addRow(rowData);
        }

        frame.add(scrollPane, BorderLayout.CENTER);
        frame.setVisible(true);
    }


    public static void mostrarFormularioActualizarCursoProfesor() {
        JFrame formularioFrame = new JFrame("Formulario Actualizar CursoProfesor");
        formularioFrame.setSize(400, 300);
        formularioFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        formularioFrame.setLayout(new GridLayout(8, 2));

        JLabel idLabel = new JLabel("ID CursoProfesor:");
        JTextField idField = new JTextField();
        JButton buscarButton = new JButton("Buscar");

        JLabel idProfesorLabel = new JLabel("ID Profesor:");
        JTextField idProfesorField = new JTextField();
        JLabel añoLabel = new JLabel("Año:");
        JTextField añoField = new JTextField();
        JLabel semestreLabel = new JLabel("Semestre:");
        JTextField semestreField = new JTextField();
        JLabel cursoLabel = new JLabel("Id_Curso:");
        JTextField cursoField = new JTextField();

        JButton actualizarButton = new JButton("Actualizar");


        idProfesorField.setEnabled(false);
        añoField.setEnabled(false);
        semestreField.setEnabled(false);
        cursoField.setEnabled(false);
        actualizarButton.setEnabled(false);


        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarCursoProfesorPorId(idField, idProfesorField, añoField, semestreField, cursoField, actualizarButton, formularioFrame);
            }
        });


        actualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    long id = Long.parseLong(idField.getText().trim());
                    int año = Integer.parseInt(añoField.getText().trim());
                    int semestre = Integer.parseInt(semestreField.getText().trim());
                    long idCurso = Long.parseLong(cursoField.getText().trim());
                    long idProfesor = Long.parseLong(idProfesorField.getText().trim());
                    Curso curso = repositorioCurso.obtenerCursoByID(idCurso);
                    Profesor profesor = repositorioProfesor.obtenerProfesorByID(idProfesor);
                    if (curso == null || profesor == null) {
                        JOptionPane.showMessageDialog(formularioFrame, "Curso o Profesor no encontrados.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }else{

                        ActualizarCurssosProfesoresDB_ActualizarArchivo(id, profesor,año,semestre,curso,ArchivoCursosProfesor);
                    }

                    JOptionPane.showMessageDialog(formularioFrame, "CursoProfesor actualizado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    formularioFrame.dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(formularioFrame, ex.getMessage(), "Error por ID", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(formularioFrame, ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        formularioFrame.add(idLabel);
        formularioFrame.add(idField);
        formularioFrame.add(new JLabel());
        formularioFrame.add(buscarButton);
        formularioFrame.add(idProfesorLabel);
        formularioFrame.add(idProfesorField);
        formularioFrame.add(añoLabel);
        formularioFrame.add(añoField);
        formularioFrame.add(semestreLabel);
        formularioFrame.add(semestreField);
        formularioFrame.add(cursoLabel);
        formularioFrame.add(cursoField);
        formularioFrame.add(new JLabel());
        formularioFrame.add(actualizarButton);

        formularioFrame.setVisible(true);
    }


    public static void buscarCursoProfesorPorId(JTextField idField, JTextField idProfesorField, JTextField añoField,
                                                JTextField semestreField, JTextField cursoField,
                                                JButton actualizarButton, JFrame frame) {
        String idTexto = idField.getText().trim();
        if (idTexto.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Ingrese un ID válido.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            long id = Long.parseLong(idTexto);
            CursoProfesor cursoProfesor = repositorioCursoProfesor.obtenerCursoProfesorByID(id);

            if (cursoProfesor != null) {

                idProfesorField.setText(String.valueOf(cursoProfesor.getProfesor().getID()));
                añoField.setText(String.valueOf(cursoProfesor.getAño()));
                semestreField.setText(String.valueOf(cursoProfesor.getSemestre()));
                cursoField.setText(String.valueOf(cursoProfesor.getCurso().getID()));

                idProfesorField.setEnabled(true);
                añoField.setEnabled(true);
                semestreField.setEnabled(true);
                cursoField.setEnabled(true);
                actualizarButton.setEnabled(true);
            } else {
                JOptionPane.showMessageDialog(frame, "CursoProfesor no encontrado.", "Error", JOptionPane.WARNING_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Error: El ID ingresado no es un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
