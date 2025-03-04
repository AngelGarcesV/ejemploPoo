package com.mycompany.mavenproject4.Formularios;

import com.mycompany.mavenproject4.modelos.Curso;
import com.mycompany.mavenproject4.modelos.Programa;
import com.mycompany.mavenproject4.repositorios.CursoRepo;
import com.mycompany.mavenproject4.repositorios.ProgramaRepo;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class FormulariosCurso {

    private static ProgramaRepo repositorioPrograma = new ProgramaRepo();
    private static CursoRepo repositorioCurso = new CursoRepo();

    public static void mostrarFormularioCrearCurso() {
        JFrame formularioFrame = new JFrame("Formulario Crear Curso");
        formularioFrame.setSize(400, 300);
        formularioFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        formularioFrame.setLayout(new GridLayout(4, 2));

        JLabel nombreLabel = new JLabel("Nombre Curso:");
        JTextField nombreField = new JTextField();
        JLabel programaIdLabel = new JLabel("ID Programa:");
        JTextField programaIdField = new JTextField();
        JLabel activoLabel = new JLabel("Activo:");
        JCheckBox activoCheckBox = new JCheckBox();

        JButton crearButton = new JButton("Crear");

        crearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 String nombreCurso = nombreField.getText();
                boolean activo = activoCheckBox.isSelected();
                try{
                    Long programa_id = Long.parseLong(programaIdField.getText());
                    Programa infoPrograma = repositorioPrograma.obtenerProgramaByID(programa_id);
                    if(infoPrograma != null){
                        Curso nuevoCurso = new Curso(null,infoPrograma, activo);
                        repositorioCurso.crearCurso(nuevoCurso);
                        JOptionPane.showMessageDialog(formularioFrame, "Curso guardado correctamente");
                    }else{
                        JOptionPane.showMessageDialog(formularioFrame, "El programa no existe");
                    }
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(formularioFrame, "No fue posible crear el curso");
                }
                formularioFrame.dispose();
            }
        });

        formularioFrame.add(nombreLabel);
        formularioFrame.add(nombreField);
        formularioFrame.add(programaIdLabel);
        formularioFrame.add(programaIdField);
        formularioFrame.add(activoLabel);
        formularioFrame.add(activoCheckBox);
        formularioFrame.add(new JLabel());
        formularioFrame.add(crearButton);


        formularioFrame.setVisible(true);
    }

    public static void mostrarFormularioEliminarCurso() {
        JFrame formularioFrame = new JFrame("Formulario Eliminar Curso");
        formularioFrame.setSize(400, 300);
        formularioFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        formularioFrame.setLayout(new GridLayout(2, 2));

        JLabel idLabel = new JLabel("ID Curso:");
        JTextField idField = new JTextField();
        JButton eliminarButton = new JButton("Eliminar");

        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                try{
                    Long id_curso  = Long.parseLong(id);
                    boolean cursoEliminado = repositorioCurso.eliminarCurso(id_curso);
                    if(cursoEliminado){
                        JOptionPane.showMessageDialog(formularioFrame, "Curso eliminado correctamente");
                    }else{
                        JOptionPane.showMessageDialog(formularioFrame, "El curso no existe");
                    }
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(formularioFrame, "No fue posible eliminar el Curso");
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

    public static void mostrarFormularioVerCursoPorId() {
        JFrame formularioFrame = new JFrame("Ver Curso por ID");
        formularioFrame.setSize(500, 300);
        formularioFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        formularioFrame.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new FlowLayout());
        JLabel idLabel = new JLabel("ID Curso:");
        JTextField idField = new JTextField(10);
        JButton verButton = new JButton("Ver");

        inputPanel.add(idLabel);
        inputPanel.add(idField);
        inputPanel.add(verButton);


        String[] columnNames = {"ID", "ACTIVO", "PROGRAMA_ID"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        verButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Long idCurso = Long.parseLong(idField.getText());
                    Curso curso = repositorioCurso.obtenerCursoByID(idCurso);

                    if (curso != null) {

                        tableModel.setRowCount(0);

                        Object[] rowData = {
                                curso.getID(),
                                curso.getActivo(),
                                curso.getPrograma().getID()
                        };
                        tableModel.addRow(rowData);
                    } else {
                        JOptionPane.showMessageDialog(formularioFrame, "Curso no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
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

    public static void mostrarTablaTodosCursos() {
        List<Curso> cursos = repositorioCurso.obtenerTodosCursos();
        JFrame frame = new JFrame("Lista de Cursos");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        String[] columnNames = {"ID", "Activo", "Programa_ID"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        for (Curso curso : cursos) {
            Object[] rowData = {curso.getID(), curso.getActivo(), curso.getPrograma().getID()};
            tableModel.addRow(rowData);
        }

        frame.add(scrollPane, BorderLayout.CENTER);
        frame.setVisible(true);
    }


    public static void mostrarFormularioActualizarCurso() {
        JFrame formularioFrame = new JFrame("Actualizar Curso");
        formularioFrame.setSize(400, 300);
        formularioFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        formularioFrame.setLayout(new GridLayout(5, 2));

        JLabel idLabel = new JLabel("ID Curso:");
        JTextField idField = new JTextField();
        JButton buscarButton = new JButton("Buscar");

        JLabel programaIdLabel = new JLabel("ID Programa:");
        JTextField programaIdField = new JTextField();
        JLabel activoLabel = new JLabel("Activo:");
        JCheckBox activoCheckBox = new JCheckBox();
        JButton actualizarButton = new JButton("Actualizar");


        programaIdField.setEnabled(false);
        activoCheckBox.setEnabled(false);
        actualizarButton.setEnabled(false);


        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                botonBuscar_FormularioActualizar(idField, programaIdField, activoCheckBox, actualizarButton, formularioFrame);
            }
        });


        actualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Long idCurso = Long.parseLong(idField.getText().trim());
                    Long programaId = Long.parseLong(programaIdField.getText().trim());
                    boolean activo = activoCheckBox.isSelected();

                    Programa programa = repositorioPrograma.obtenerProgramaByID(programaId);
                    if (programa != null) {
                        Curso cursoActualizado = new Curso(idCurso, programa, activo);
                        Curso curosoActualizado = CursoRepo.actualizarCursoPorId(idCurso, cursoActualizado);

                        if (curosoActualizado != null) {
                            JOptionPane.showMessageDialog(formularioFrame, "Curso actualizado con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                            formularioFrame.dispose();
                        } else {
                            JOptionPane.showMessageDialog(formularioFrame, "Error al actualizar el curso", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(formularioFrame, "Programa no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(formularioFrame, "Ingrese valores numéricos válidos", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(formularioFrame, "Ocurrió un error al actualizar el curso", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        formularioFrame.add(idLabel);
        formularioFrame.add(idField);
        formularioFrame.add(new JLabel());
        formularioFrame.add(buscarButton);
        formularioFrame.add(programaIdLabel);
        formularioFrame.add(programaIdField);
        formularioFrame.add(activoLabel);
        formularioFrame.add(activoCheckBox);
        formularioFrame.add(new JLabel());
        formularioFrame.add(actualizarButton);

        formularioFrame.setVisible(true);
    }


    public static void botonBuscar_FormularioActualizar(JTextField idField, JTextField programaIdField, JCheckBox activoCheckBox, JButton actualizarButton, JFrame frame) {
        String idTexto = idField.getText().trim();
        if (idTexto.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Ingrese un ID válido.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            Long idCurso = Long.parseLong(idTexto);
            Curso curso = repositorioCurso.obtenerCursoByID(idCurso);

            if (curso != null) {

                programaIdField.setText(String.valueOf(curso.getPrograma().getID()));
                activoCheckBox.setSelected(curso.getActivo());


                programaIdField.setEnabled(true);
                activoCheckBox.setEnabled(true);
                actualizarButton.setEnabled(true);
            } else {
                JOptionPane.showMessageDialog(frame, "Curso no encontrado.", "Error", JOptionPane.WARNING_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Error: El ID ingresado no es un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
