package com.mycompany.mavenproject4.Formularios;

import com.mycompany.mavenproject4.modelos.Facultad;
import com.mycompany.mavenproject4.modelos.Programa;
import com.mycompany.mavenproject4.repositorios.FacultadRepo;
import com.mycompany.mavenproject4.repositorios.ProgramaRepo;
import com.toedter.calendar.JDateChooser;

import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class FormulariosPrograma {
    static FacultadRepo repositorioFacultad = new FacultadRepo();
    static ProgramaRepo repositorioPrograma = new ProgramaRepo();
    static private JDateChooser dateChooser;

    public static void mostrarFormularioCrearPrograma() {
        JFrame formularioFrame = new JFrame("Formulario Crear Programa");
        formularioFrame.setSize(400, 300);
        formularioFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        formularioFrame.setLayout(new GridLayout(5, 2));

        JLabel nombreLabel = new JLabel("Nombre:");
        JTextField nombreField = new JTextField();
        JLabel duracionLabel = new JLabel("Duración:");
        JTextField duracionField = new JTextField();
        JLabel idFacultadLabel = new JLabel("ID Facultad:");
        JTextField idFacultadField = new JTextField();
        JLabel registroLabel = new JLabel("Registro:");


        JDateChooser registroChooser = new JDateChooser();
        registroChooser.setDateFormatString("dd/MM/yyyy");

        JButton crearButton = new JButton("Crear");

        crearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String nombre = nombreField.getText();
                    Long duracion = Long.parseLong(duracionField.getText());
                    Long idFacultad = Long.parseLong(idFacultadField.getText());
                    Date registro = registroChooser.getDate();

                    if (registro == null) {
                        JOptionPane.showMessageDialog(formularioFrame, "Seleccione una fecha válida", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    Facultad infoFacultad = repositorioFacultad.obtenerFacultadByID(idFacultad);

                    if (infoFacultad != null) {
                        Programa infoPrograma = new Programa(null, nombre, duracion, infoFacultad, registro);
                        repositorioPrograma.crearPrograma(infoPrograma);
                        JOptionPane.showMessageDialog(formularioFrame, "programa creado correctamente", "Info", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(formularioFrame, "Facultad no encontrada", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(formularioFrame, "Ingrese valores numéricos válidos para Duración y ID Facultad", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(formularioFrame, "Ocurrió un error al crear el programa", "Error", JOptionPane.ERROR_MESSAGE);
                }

                formularioFrame.dispose();
            }
        });

        formularioFrame.add(nombreLabel);
        formularioFrame.add(nombreField);
        formularioFrame.add(duracionLabel);
        formularioFrame.add(duracionField);
        formularioFrame.add(idFacultadLabel);
        formularioFrame.add(idFacultadField);
        formularioFrame.add(registroLabel);
        formularioFrame.add(registroChooser);
        formularioFrame.add(new JLabel());
        formularioFrame.add(crearButton);

        formularioFrame.setVisible(true);
    }

    public static void mostrarFormularioEliminarPrograma() {
        JFrame formularioFrame = new JFrame("Formulario Eliminar Programa");
        formularioFrame.setSize(400, 300);
        formularioFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        formularioFrame.setLayout(new GridLayout(2, 2));

        JLabel idLabel = new JLabel("ID Programa:");
        JTextField idField = new JTextField();
        JButton eliminarButton = new JButton("Eliminar");

        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                try{
                    Long id_Programa = Long.parseLong(id);
                    boolean eliminado = repositorioPrograma.eliminarPrograma(id_Programa);
                    if (eliminado) {
                        JOptionPane.showMessageDialog(formularioFrame, "El registro de programa fue eliminado");
                    }else{
                        JOptionPane.showMessageDialog(formularioFrame, "No se pudo eliminar el registro", "error", JOptionPane.ERROR_MESSAGE);
                    }

                }catch(Exception ex){
                    JOptionPane.showMessageDialog(formularioFrame, "No se pudo eliminar el registro", "error", JOptionPane.ERROR_MESSAGE);
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

    public static void mostrarFormularioVerProgramaPorId() {
        JFrame formularioFrame = new JFrame("Ver Programa por ID");
        formularioFrame.setSize(600, 400);
        formularioFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        formularioFrame.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new FlowLayout());
        JLabel idLabel = new JLabel("ID Programa:");
        JTextField idField = new JTextField(10);
        JButton verButton = new JButton("Ver");

        inputPanel.add(idLabel);
        inputPanel.add(idField);
        inputPanel.add(verButton);

        String[] columnNames = {"ID", "Duración", "Nombre", "Registro", "Facultad_ID"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        verButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Long idPrograma = Long.parseLong(idField.getText());
                    Programa programa = repositorioPrograma.obtenerProgramaByID(idPrograma);

                    if (programa != null) {
                        tableModel.setRowCount(0);

                        Object[] rowData = {
                                programa.getID(),
                                programa.getDuracion(),
                                programa.getNombre(),
                                programa.getRegistro(),
                                programa.getFacultad().getID()
                        };

                        tableModel.addRow(rowData);
                    } else {
                        JOptionPane.showMessageDialog(formularioFrame, "Programa no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
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

    public static void mostrarTodosPrograma() {
        List<Programa> programas = repositorioPrograma.obtenerTodosProgramas();
        JFrame frame = new JFrame("Lista de Programas");
        frame.setSize(700, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        String[] columnNames = {"ID", "Duración", "Nombre", "Registro", "Facultad_ID"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        for (Programa programa : programas) {
            Object[] rowData = {
                    programa.getID(),
                    programa.getDuracion(),
                    programa.getNombre(),
                    programa.getRegistro(),
                    programa.getFacultad().getID()
            };
            tableModel.addRow(rowData);
        }

        frame.add(scrollPane, BorderLayout.CENTER);
        frame.setVisible(true);
    }


    public static void mostrarFormularioActualizarPrograma() {
        JFrame formularioFrame = new JFrame("Actualizar Programa");
        formularioFrame.setSize(400, 350);
        formularioFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        formularioFrame.setLayout(new GridLayout(7, 2));

        JLabel idLabel = new JLabel("ID Programa:");
        JTextField idField = new JTextField();
        JButton buscarButton = new JButton("Buscar");

        JLabel nombreLabel = new JLabel("Nombre:");
        JTextField nombreField = new JTextField();
        JLabel duracionLabel = new JLabel("Duración:");
        JTextField duracionField = new JTextField();
        JLabel idFacultadLabel = new JLabel("ID Facultad:");
        JTextField idFacultadField = new JTextField();
        JLabel registroLabel = new JLabel("Registro:");

        JDateChooser registroChooser = new JDateChooser();
        registroChooser.setDateFormatString("dd/MM/yyyy");

        JButton actualizarButton = new JButton("Actualizar");

        nombreField.setEnabled(false);
        duracionField.setEnabled(false);
        idFacultadField.setEnabled(false);
        registroChooser.setEnabled(false);
        actualizarButton.setEnabled(false);

        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                botonBuscarPrograma_FormularioActualizar(idField, nombreField, duracionField, idFacultadField, registroChooser, actualizarButton, formularioFrame);
            }
        });

        actualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Long idPrograma = Long.parseLong(idField.getText().trim());
                    String nombre = nombreField.getText().trim();
                    Long duracion = Long.parseLong(duracionField.getText().trim());
                    Long idFacultad = Long.parseLong(idFacultadField.getText().trim());
                    Date registro = registroChooser.getDate();

                    if (registro == null) {
                        JOptionPane.showMessageDialog(formularioFrame, "Seleccione una fecha válida", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    Facultad infoFacultad = repositorioFacultad.obtenerFacultadByID(idFacultad);

                    if (infoFacultad != null) {
                        Programa programaActualizado = new Programa(idPrograma, nombre, duracion, infoFacultad, registro);
                        repositorioPrograma.actualizarProgramaPorId(idPrograma, programaActualizado);

                        JOptionPane.showMessageDialog(formularioFrame, "Programa actualizado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                        formularioFrame.dispose();
                    } else {
                        JOptionPane.showMessageDialog(formularioFrame, "Facultad no encontrada", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(formularioFrame, "Ingrese valores numéricos válidos", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(formularioFrame, "Ocurrió un error al actualizar el programa", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        formularioFrame.add(idLabel);
        formularioFrame.add(idField);
        formularioFrame.add(new JLabel());
        formularioFrame.add(buscarButton);
        formularioFrame.add(nombreLabel);
        formularioFrame.add(nombreField);
        formularioFrame.add(duracionLabel);
        formularioFrame.add(duracionField);
        formularioFrame.add(idFacultadLabel);
        formularioFrame.add(idFacultadField);
        formularioFrame.add(registroLabel);
        formularioFrame.add(registroChooser);
        formularioFrame.add(new JLabel());
        formularioFrame.add(actualizarButton);

        formularioFrame.setVisible(true);
    }

    public static void botonBuscarPrograma_FormularioActualizar(JTextField idField, JTextField nombreField, JTextField duracionField,
                                           JTextField idFacultadField, JDateChooser registroChooser,
                                           JButton actualizarButton, JFrame frame) {
        String idTexto = idField.getText().trim();
        if (idTexto.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Ingrese un ID válido.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            Long idPrograma = Long.parseLong(idTexto);
            Programa programa = repositorioPrograma.obtenerProgramaByID(idPrograma);

            if (programa != null) {
                nombreField.setText(programa.getNombre());
                duracionField.setText(String.valueOf(programa.getDuracion()));
                idFacultadField.setText(String.valueOf(programa.getFacultad().getID()));
                registroChooser.setDate(programa.getRegistro());

                nombreField.setEnabled(true);
                duracionField.setEnabled(true);
                idFacultadField.setEnabled(true);
                registroChooser.setEnabled(true);
                actualizarButton.setEnabled(true);
            } else {
                JOptionPane.showMessageDialog(frame, "Programa no encontrado.", "Error", JOptionPane.WARNING_MESSAGE);
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Error: El ID ingresado no es un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
