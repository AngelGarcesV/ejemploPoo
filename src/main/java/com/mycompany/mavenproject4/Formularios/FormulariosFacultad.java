package com.mycompany.mavenproject4.Formularios;

import com.mycompany.mavenproject4.modelos.Facultad;
import com.mycompany.mavenproject4.modelos.Persona;
import com.mycompany.mavenproject4.repositorios.FacultadRepo;
import com.mycompany.mavenproject4.repositorios.personaRepo;

import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormulariosFacultad {
    static FacultadRepo repositorioFacultad = new FacultadRepo();
    static personaRepo repositorioPersona = new personaRepo();

    public static void mostrarFormularioCrearFacultad() {

        JFrame formularioFrame = new JFrame("Formulario Crear Facultad");
        formularioFrame.setSize(400, 300);
        formularioFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        formularioFrame.setLayout(new GridLayout(3, 2));

        JLabel nombreLabel = new JLabel("Nombre:");
        JTextField nombreField = new JTextField();
        JLabel idDecanoLabel = new JLabel("ID Decano:");
        JTextField idDecanoField = new JTextField();

        JButton crearButton = new JButton("Crear");

        crearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String nombre = nombreField.getText();

                try{
                    Long idDecano = Long.parseLong(idDecanoField.getText());
                    Persona decano = repositorioPersona.obtenerPersonaByID(idDecano);
                    Facultad infoFacultad = new Facultad(null, nombre, decano);
                    if( decano != null){
                        repositorioFacultad.crearFacultad(infoFacultad);
                        JOptionPane.showMessageDialog(formularioFrame, "Facultad creada con exito");
                    }else{
                        JOptionPane.showMessageDialog(formularioFrame, "Error al crear el facultad");
                    }
                }catch(Exception ex){
                    JOptionPane.showMessageDialog(formularioFrame, "Error al crear el facultad");
                }


                formularioFrame.dispose();
            }
        });

        formularioFrame.add(nombreLabel);
        formularioFrame.add(nombreField);
        formularioFrame.add(idDecanoLabel);
        formularioFrame.add(idDecanoField);
        formularioFrame.add(new JLabel());
        formularioFrame.add(crearButton);


        formularioFrame.setVisible(true);
    }

    public static void mostrarFormularioEliminarFacultad() {
        JFrame formularioFrame = new JFrame("Formulario Eliminar Facultad");
        formularioFrame.setSize(400, 300);
        formularioFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        formularioFrame.setLayout(new GridLayout(2, 2));

        JLabel idLabel = new JLabel("ID:");
        JTextField idField = new JTextField();
        JButton eliminarButton = new JButton("Eliminar");

        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                try{
                    Long idDecano = Long.parseLong(idField.getText());
                    if (repositorioFacultad.eliminarFacultad(idDecano)){
                        JOptionPane.showMessageDialog(formularioFrame, "Facultad eliminado con exito");
                    }
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(formularioFrame, "No se ha podido eliminar la facultad");
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

    public static void mostrarFormularioVerFacultadPorId() {
        JFrame formularioFrame = new JFrame("Formulario Ver Facultad por ID");
        formularioFrame.setSize(500, 300);
        formularioFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        formularioFrame.setLayout(new BorderLayout());


        JPanel inputPanel = new JPanel(new GridLayout(1, 2));
        JLabel idLabel = new JLabel("ID:");
        JTextField idField = new JTextField();
        JButton verButton = new JButton("Ver");

        inputPanel.add(idLabel);
        inputPanel.add(idField);
        inputPanel.add(verButton);


        String[] columnNames = {"ID", "Nombre", "ID Decano"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        verButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                try {
                    Long id_Facultad = Long.parseLong(id);
                    Facultad infoFacultad = repositorioFacultad.obtenerFacultadByID(id_Facultad);


                    tableModel.setRowCount(0);

                    if (infoFacultad != null) {
                        Object[] rowData = {
                                infoFacultad.getID(),
                                infoFacultad.getNombre(),
                                infoFacultad.getDecano().getID()
                        };
                        tableModel.addRow(rowData);
                    } else {
                        JOptionPane.showMessageDialog(formularioFrame, "Facultad no encontrada", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(formularioFrame, "Ingrese un ID válido", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(formularioFrame, "Ocurrió un error al buscar la facultad", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });


        formularioFrame.add(inputPanel, BorderLayout.NORTH);
        formularioFrame.add(scrollPane, BorderLayout.CENTER);


        formularioFrame.setVisible(true);
    }

    public static void mostrarTodosFacultad() {
        List<Facultad> facultades = repositorioFacultad.obtenerTodasFacultades();
        JFrame frame = new JFrame("Lista de Facultades");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        String[] columnNames = {"ID", "Nombre", "Decano"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        for (Facultad facultad : facultades) {
            Object[] rowData = {facultad.getID(), facultad.getNombre(), facultad.getDecano().getID()};
            tableModel.addRow(rowData);
        }

        frame.add(scrollPane, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    public static void mostrarFormularioActualizarFacultad() {
        JFrame formularioFrame = new JFrame("Actualizar Facultad");
        formularioFrame.setSize(400, 300);
        formularioFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        formularioFrame.setLayout(new GridLayout(5, 2));

        JLabel idLabel = new JLabel("ID Facultad:");
        JTextField idField = new JTextField();
        JButton buscarButton = new JButton("Buscar");

        JLabel nombreLabel = new JLabel("Nombre:");
        JTextField nombreField = new JTextField();
        JLabel idDecanoLabel = new JLabel("ID Decano:");
        JTextField idDecanoField = new JTextField();
        JButton actualizarButton = new JButton("Actualizar");


        nombreField.setEnabled(false);
        idDecanoField.setEnabled(false);
        actualizarButton.setEnabled(false);


        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                botonBuscarFacultad_FormularioActualizar(idField, nombreField, idDecanoField, actualizarButton, formularioFrame);
            }
        });


        actualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Long idFacultad = Long.parseLong(idField.getText().trim());
                    String nombre = nombreField.getText().trim();
                    Long idDecano = Long.parseLong(idDecanoField.getText().trim());

                    Persona infoPersona = repositorioPersona.obtenerPersonaByID(idDecano);
                    if (infoPersona != null) {
                        Facultad infoFacultad = new Facultad(idFacultad, nombre, infoPersona);
                        Facultad actualizado = FacultadRepo.actualizarFacultadPorId(idFacultad, infoFacultad);

                        if (actualizado != null) {
                            JOptionPane.showMessageDialog(formularioFrame, "Facultad actualizada con éxito", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                            formularioFrame.dispose();
                        } else {
                            JOptionPane.showMessageDialog(formularioFrame, "Error al actualizar la facultad", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                    } else {
                        JOptionPane.showMessageDialog(formularioFrame, "Decano no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(formularioFrame, "Ingrese valores numéricos válidos", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(formularioFrame, "Ocurrió un error al actualizar la facultad", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        formularioFrame.add(idLabel);
        formularioFrame.add(idField);
        formularioFrame.add(new JLabel());
        formularioFrame.add(buscarButton);
        formularioFrame.add(nombreLabel);
        formularioFrame.add(nombreField);
        formularioFrame.add(idDecanoLabel);
        formularioFrame.add(idDecanoField);
        formularioFrame.add(new JLabel());
        formularioFrame.add(actualizarButton);

        formularioFrame.setVisible(true);
    }

    public static void botonBuscarFacultad_FormularioActualizar(JTextField idField, JTextField nombreField, JTextField idDecanoField, JButton actualizarButton, JFrame frame) {
        String idTexto = idField.getText().trim();
        if (idTexto.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Ingrese un ID válido.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            Long idFacultad = Long.parseLong(idTexto);
            Facultad facultad = FacultadRepo.obtenerFacultadByID(idFacultad);

            if (facultad != null) {

                nombreField.setText(facultad.getNombre());
                idDecanoField.setText(String.valueOf(facultad.getDecano().getID()));


                nombreField.setEnabled(true);
                idDecanoField.setEnabled(true);
                actualizarButton.setEnabled(true);
            } else {
                JOptionPane.showMessageDialog(frame, "Facultad no encontrada.", "Error", JOptionPane.WARNING_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Error: El ID ingresado no es un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
