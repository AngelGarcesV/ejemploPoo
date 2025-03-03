package com.mycompany.mavenproject4.Formularios;

import com.mycompany.mavenproject4.modelos.Profesor;
import com.mycompany.mavenproject4.repositorios.ProfesorRepo;

import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormulariosProfesor {

    private static ProfesorRepo repositorioProfesor = new ProfesorRepo();

    public static void mostrarFormularioCrearProfesor() {
        JFrame formularioFrame = new JFrame("Formulario Crear Profesor");
        formularioFrame.setSize(400, 300);
        formularioFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        formularioFrame.setLayout(new GridLayout(5, 2)); // Ajustar para incluir nuevo campo

        JLabel nombresLabel = new JLabel("Nombres:");
        JTextField nombresField = new JTextField();
        JLabel apellidosLabel = new JLabel("Apellidos:");
        JTextField apellidosField = new JTextField();
        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField();
        JLabel tipoContratoLabel = new JLabel("Tipo de Contrato:");
        JTextField tipoContratoField = new JTextField();

        JButton crearButton = new JButton("Crear");

        crearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombres = nombresField.getText();
                String apellidos = apellidosField.getText();
                String email = emailField.getText();
                String tipoContrato = tipoContratoField.getText();

                Profesor nuevoProfesor = new Profesor(tipoContrato,null,nombres,apellidos,email);
                repositorioProfesor.crearProfesor(nuevoProfesor);
                JOptionPane.showMessageDialog(formularioFrame,"Profesor Creado");

                // Cerrar el formulario
                formularioFrame.dispose();
            }
        });

        formularioFrame.add(nombresLabel);
        formularioFrame.add(nombresField);
        formularioFrame.add(apellidosLabel);
        formularioFrame.add(apellidosField);
        formularioFrame.add(emailLabel);
        formularioFrame.add(emailField);
        formularioFrame.add(tipoContratoLabel);
        formularioFrame.add(tipoContratoField);
        formularioFrame.add(new JLabel()); // Espacio vacío
        formularioFrame.add(crearButton);

        // Mostrar el formulario
        formularioFrame.setVisible(true);
    }
    public static void mostrarTablaTodosProfesores() {
        List<Profesor> profesores = repositorioProfesor.obtenerTodosProfesor();
        JFrame frame = new JFrame("Lista de Profesores");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        String[] columnNames = {"ID", "Nombres", "Apellidos", "Email", "TipoContrato"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        for (Profesor profesor : profesores) {
            Object[] rowData = {profesor.getID(), profesor.getNombres(), profesor.getApellidos(), profesor.getEmail(), profesor.getTipoContrato()};
            tableModel.addRow(rowData);
        }

        frame.add(scrollPane, BorderLayout.CENTER);
        frame.setVisible(true);
    }


    public static void mostrarFormularioEliminarProfesor() {
        JFrame formularioFrame = new JFrame("Formulario Eliminar Profesor");
        formularioFrame.setSize(400, 300);
        formularioFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        formularioFrame.setLayout(new GridLayout(2, 2));

        JLabel idProfesorLabel = new JLabel("ID Persona:");
        JTextField idProfesorField = new JTextField();
        JButton eliminarButton = new JButton("Eliminar");

        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idProfesor = idProfesorField.getText();
                try{
                    Long id = Long.parseLong(idProfesor);
                    boolean profesorEliminado = repositorioProfesor.eliminarProfesor(id);
                    if(profesorEliminado){
                        JOptionPane.showMessageDialog(formularioFrame,"Profesor Eliminado");
                    }else{
                        JOptionPane.showMessageDialog(formularioFrame,"Profesor no eliminado");
                    }
                }catch(NumberFormatException ex){
                    JOptionPane.showMessageDialog(formularioFrame,"Id invalido");
                }

                // Cerrar el formulario
                formularioFrame.dispose();
            }
        });

        formularioFrame.add(idProfesorLabel);
        formularioFrame.add(idProfesorField);
        formularioFrame.add(new JLabel());  // Espacio vacío
        formularioFrame.add(eliminarButton);

        // Mostrar el formulario
        formularioFrame.setVisible(true);
    }

    public static void mostrarFormularioVerProfesorPorId() {
        JFrame formularioFrame = new JFrame("Ver Profesor por ID");
        formularioFrame.setSize(600, 400);
        formularioFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        formularioFrame.setLayout(new BorderLayout());

        JPanel inputPanel = new JPanel(new FlowLayout());
        JLabel idLabel = new JLabel("ID Persona:");
        JTextField idField = new JTextField(10);
        JButton verButton = new JButton("Ver");

        inputPanel.add(idLabel);
        inputPanel.add(idField);
        inputPanel.add(verButton);

        // Definir las columnas de la tabla
        String[] columnNames = {"ID", "APELLIDOS", "EMAIL", "NOMBRES", "TIPOCONTRATO"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        verButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Long idPersona = Long.parseLong(idField.getText());
                    Profesor profesor = repositorioProfesor.obtenerProfesorByID(idPersona);

                    if (profesor != null) {
                        // Limpiar la tabla antes de agregar nuevos datos
                        tableModel.setRowCount(0);

                        Object[] rowData = {
                                profesor.getID(),
                                profesor.getApellidos(),
                                profesor.getEmail(),
                                profesor.getNombres(),
                                profesor.getTipoContrato()
                        };
                        tableModel.addRow(rowData);
                    } else {
                        JOptionPane.showMessageDialog(formularioFrame, "Profesor no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
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

    public static void mostrarFormularioActualizarProfesor() {
        JFrame formularioFrame = new JFrame("Formulario Actualizar Profesor");
        formularioFrame.setSize(400, 300);
        formularioFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        formularioFrame.setLayout(new GridLayout(7, 2));

        JLabel idProfesorLabel = new JLabel("ID Persona:");
        JTextField idProfesorField = new JTextField();
        JButton buscarButton = new JButton("Buscar");

        JLabel nombresLabel = new JLabel("Nombres:");
        JTextField nombresField = new JTextField();
        JLabel apellidosLabel = new JLabel("Apellidos:");
        JTextField apellidosField = new JTextField();
        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField();
        JLabel tipoContratoLabel = new JLabel("Tipo de Contrato:");
        JTextField tipoContratoField = new JTextField();

        JButton actualizarButton = new JButton("Actualizar");

        // Deshabilitar campos hasta que se busque un profesor
        nombresField.setEnabled(false);
        apellidosField.setEnabled(false);
        emailField.setEnabled(false);
        tipoContratoField.setEnabled(false);
        actualizarButton.setEnabled(false);

        // Acción del botón Buscar
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                botonBuscarProfesor_Actualizar(idProfesorField, nombresField, apellidosField, emailField, tipoContratoField, actualizarButton, formularioFrame);
            }
        });

        // Acción del botón Actualizar
        actualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    long idPersona = Long.parseLong(idProfesorField.getText().trim());
                    String tipoContrato = tipoContratoField.getText().trim();
                    String nombres = nombresField.getText();
                    String apellidos = apellidosField.getText();
                    String email = emailField.getText();

                    Profesor profesorActualizado = new Profesor(tipoContrato,idPersona,nombres, apellidos,email);
                    repositorioProfesor.actualizarProfesorPorId(idPersona, profesorActualizado);

                    JOptionPane.showMessageDialog(formularioFrame, "Profesor actualizado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    formularioFrame.dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(formularioFrame, "Ingrese un ID válido", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(formularioFrame, "Ocurrió un error al actualizar el profesor", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        formularioFrame.add(idProfesorLabel);
        formularioFrame.add(idProfesorField);
        formularioFrame.add(new JLabel()); // Espacio vacío
        formularioFrame.add(buscarButton);
        formularioFrame.add(nombresLabel);
        formularioFrame.add(nombresField);
        formularioFrame.add(apellidosLabel);
        formularioFrame.add(apellidosField);
        formularioFrame.add(emailLabel);
        formularioFrame.add(emailField);
        formularioFrame.add(tipoContratoLabel);
        formularioFrame.add(tipoContratoField);
        formularioFrame.add(new JLabel()); // Espacio vacío
        formularioFrame.add(actualizarButton);

        formularioFrame.setVisible(true);
    }

    // Función para buscar profesor por ID
    public static void botonBuscarProfesor_Actualizar(JTextField idProfesorField, JTextField nombresField, JTextField apellidosField,
                                           JTextField emailField, JTextField tipoContratoField,
                                           JButton actualizarButton, JFrame frame) {
        String idTexto = idProfesorField.getText().trim();
        if (idTexto.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Ingrese un ID válido.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            long idPersona = Long.parseLong(idTexto);
            Profesor profesor = repositorioProfesor.obtenerProfesorByID(idPersona);

            if (profesor != null) {
                // Cargar datos en los campos
                nombresField.setText(profesor.getNombres());
                apellidosField.setText(profesor.getApellidos());
                emailField.setText(profesor.getEmail());
                tipoContratoField.setText(profesor.getTipoContrato());

                // Bloquear campos no editables
                nombresField.setEnabled(true);
                apellidosField.setEnabled(true);
                emailField.setEnabled(true);

                // Habilitar solo el campo modificable y el botón de actualizar
                tipoContratoField.setEnabled(true);
                actualizarButton.setEnabled(true);
            } else {
                JOptionPane.showMessageDialog(frame, "Profesor no encontrado.", "Error", JOptionPane.WARNING_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Error: El ID ingresado no es un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
