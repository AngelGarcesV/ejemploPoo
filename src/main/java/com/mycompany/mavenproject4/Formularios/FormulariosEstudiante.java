package com.mycompany.mavenproject4.Formularios;

import com.mycompany.mavenproject4.modelos.Estudiante;
import com.mycompany.mavenproject4.modelos.Programa;
import com.mycompany.mavenproject4.repositorios.EstudianteRepo;
import com.mycompany.mavenproject4.repositorios.ProgramaRepo;
import com.mycompany.mavenproject4.repositorios.personaRepo;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FormulariosEstudiante {
    static EstudianteRepo repositorioEstudiante = new EstudianteRepo();
    static ProgramaRepo repositorioPrograma = new ProgramaRepo();

    public static void mostrarFormularioCrearEstudiante() {
        JFrame formularioFrame = new JFrame("Formulario Crear Estudiante");
        formularioFrame.setSize(400, 350);
        formularioFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        formularioFrame.setLayout(new GridLayout(8, 2)); // Ajuste de filas

        JLabel nombresLabel = new JLabel("Nombres:");
        JTextField nombresField = new JTextField();
        JLabel apellidosLabel = new JLabel("Apellidos:");
        JTextField apellidosField = new JTextField();
        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField();
        JLabel codigoLabel = new JLabel("Código:");
        JTextField codigoField = new JTextField();
        JLabel programaLabel = new JLabel("id_Programa:");
        JTextField programaField = new JTextField();
        JLabel activoLabel = new JLabel("Activo:");
        JCheckBox activoCheckBox = new JCheckBox();
        JLabel promedioLabel = new JLabel("Promedio:");
        JTextField promedioField = new JTextField();

        JButton crearButton = new JButton("Crear");

        crearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombres = nombresField.getText();
                String apellidos = apellidosField.getText();
                String email = emailField.getText();
                String codigo = codigoField.getText();
                String id_programa = programaField.getText();
                boolean activo = activoCheckBox.isSelected();
                double promedio = Double.parseDouble(promedioField.getText());

                try{
                    double codigoDouble = Double.parseDouble(codigoField.getText());
                    Long long_id_programa = Long.parseLong(id_programa);
                    Programa infoPrograma = repositorioPrograma.obtenerProgramaByID(long_id_programa);
                    if(infoPrograma != null){
                        Estudiante nuevoEstudiante = new Estudiante(codigoDouble,infoPrograma,activo,promedio,null,nombres,apellidos,email);
                        repositorioEstudiante.crearEstudiante(nuevoEstudiante);
                        JOptionPane.showMessageDialog(null, "Estudiante creado con exito");
                    }else{
                        JOptionPane.showMessageDialog(null, "No se encontro el programa");
                    }
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(null, "Error al crear Estudiante");
                }



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
        formularioFrame.add(codigoLabel);
        formularioFrame.add(codigoField);
        formularioFrame.add(programaLabel);
        formularioFrame.add(programaField);
        formularioFrame.add(activoLabel);
        formularioFrame.add(activoCheckBox);
        formularioFrame.add(promedioLabel);
        formularioFrame.add(promedioField);
        formularioFrame.add(new JLabel()); // Espacio vacío
        formularioFrame.add(crearButton);

        // Mostrar el formulario
        formularioFrame.setVisible(true);
    }

    public static void mostrarFormularioEliminarEstudiante() {
        JFrame formularioFrame = new JFrame("Formulario Eliminar Estudiante");
        formularioFrame.setSize(400, 300);
        formularioFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        formularioFrame.setLayout(new GridLayout(2, 2));

        JLabel idPersonaLabel = new JLabel("ID Persona:");
        JTextField idPersonaField = new JTextField();
        JButton eliminarButton = new JButton("Eliminar");

        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    Long id_estudiante =Long.parseLong(idPersonaField.getText());
                    boolean estudianteEliminado = repositorioEstudiante.eliminarEstudiante(id_estudiante);
                    if(estudianteEliminado){
                        JOptionPane.showMessageDialog(null, "Estudiante eliminado con exito");
                    }else{
                        JOptionPane.showMessageDialog(null, "No se encontro el estudiante");
                    }
                }catch (Exception ex){
                    JOptionPane.showMessageDialog(null, "Error ocasionado por id");
                }

                // Cerrar el formulario
                formularioFrame.dispose();
            }
        });

        formularioFrame.add(idPersonaLabel);
        formularioFrame.add(idPersonaField);
        formularioFrame.add(new JLabel());  // Espacio vacío
        formularioFrame.add(eliminarButton);

        // Mostrar el formulario
        formularioFrame.setVisible(true);
    }

    public static void mostrarFormularioVerEstudiantePorId() {
        JFrame formularioFrame = new JFrame("Ver Estudiante por ID");
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
        String[] columnNames = {"ID", "APELLIDOS", "EMAIL", "NOMBRES", "ACTIVO", "CODIGO", "PROMEDIO", "PROGRAMA_ID"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        verButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Long idPersona = Long.parseLong(idField.getText());
                    Estudiante estudiante = repositorioEstudiante.obtenerEstudianteByID(idPersona);

                    if (estudiante != null) {
                        // Limpiar la tabla antes de agregar nuevos datos
                        tableModel.setRowCount(0);

                        Object[] rowData = {
                                estudiante.getID(),
                                estudiante.getApellidos(),
                                estudiante.getEmail(),
                                estudiante.getNombres(),
                                estudiante.getActivo(),
                                estudiante.getCodigo(),
                                estudiante.getPromedio(),
                                estudiante.getPrograma().getID()
                        };
                        tableModel.addRow(rowData);
                    } else {
                        JOptionPane.showMessageDialog(formularioFrame, "Estudiante no encontrado", "Error", JOptionPane.ERROR_MESSAGE);
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
    public static void mostrarTablaTodosEstudiantes() {
        List<Estudiante> estudiantes = repositorioEstudiante.obtenerTodosEstudiantes();
        JFrame frame = new JFrame("Lista de Estudiantes");
        frame.setSize(800, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Agregar todas las columnas faltantes
        String[] columnNames = {"ID", "Apellidos", "Nombres", "Email", "Activo", "Código", "Promedio", "Programa_ID"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        for (Estudiante estudiante : estudiantes) {
            Object[] rowData = {
                    estudiante.getID(),
                    estudiante.getApellidos(),
                    estudiante.getNombres(),
                    estudiante.getEmail(),
                    estudiante.getActivo(),  // Asegúrate de que haya un método get para esto
                    estudiante.getCodigo(),
                    estudiante.getPromedio(),
                    estudiante.getPrograma().getID()  // Si es un objeto Programa, usa estudiante.getPrograma().getID()
            };
            tableModel.addRow(rowData);
        }

        frame.add(scrollPane, BorderLayout.CENTER);
        frame.setVisible(true);
    }


    public static void mostrarFormularioActualizarEstudiante() {
        JFrame formularioFrame = new JFrame("Formulario Actualizar Estudiante");
        formularioFrame.setSize(400, 350);
        formularioFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        formularioFrame.setLayout(new GridLayout(10, 2));

        JLabel idPersonaLabel = new JLabel("ID Persona:");
        JTextField idPersonaField = new JTextField();
        JButton buscarButton = new JButton("Buscar");

        JLabel nombresLabel = new JLabel("Nombres:");
        JTextField nombresField = new JTextField();
        JLabel apellidosLabel = new JLabel("Apellidos:");
        JTextField apellidosField = new JTextField();
        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField();
        JLabel codigoLabel = new JLabel("Código:");
        JTextField codigoField = new JTextField();
        JLabel programaLabel = new JLabel("Programa:");
        JTextField programaField = new JTextField();
        JLabel activoLabel = new JLabel("Activo:");
        JCheckBox activoCheckBox = new JCheckBox();
        JLabel promedioLabel = new JLabel("Promedio:");
        JTextField promedioField = new JTextField();

        JButton actualizarButton = new JButton("Actualizar");

        // Deshabilitar campos hasta que se busque un estudiante
        nombresField.setEnabled(false);
        apellidosField.setEnabled(false);
        emailField.setEnabled(false);
        codigoField.setEnabled(false);
        programaField.setEnabled(false);
        activoCheckBox.setEnabled(false);
        promedioField.setEnabled(false);
        actualizarButton.setEnabled(false);

        // Acción del botón Buscar
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                buscarEstudiantePorId(idPersonaField, nombresField, apellidosField, emailField, codigoField,
                        programaField, activoCheckBox, promedioField, actualizarButton, formularioFrame);
            }
        });

        // Acción del botón Actualizar
        actualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    long idPersona = Long.parseLong(idPersonaField.getText().trim());
                    String nombres = nombresField.getText().trim();
                    String apellidos = apellidosField.getText().trim();
                    String email = emailField.getText().trim();
                    Double codigo = Double.parseDouble( codigoField.getText().trim());
                    Long programa =Long.parseLong(programaField.getText().trim());
                    boolean activo = activoCheckBox.isSelected();
                    double promedio = Double.parseDouble(promedioField.getText().trim());

                    Programa infoPrograma = repositorioPrograma.obtenerProgramaByID(programa);
                    if(infoPrograma != null){
                        Estudiante estudianteActualizado = new Estudiante(codigo,infoPrograma,activo,promedio,idPersona,nombres,apellidos,email);
                        repositorioEstudiante.actualizarEstudiantePorId(idPersona, estudianteActualizado);
                    }else{
                        JOptionPane.showMessageDialog(null, "Programa no encontrado");
                    }



                    JOptionPane.showMessageDialog(formularioFrame, "Estudiante actualizado correctamente", "Éxito", JOptionPane.INFORMATION_MESSAGE);
                    formularioFrame.dispose();
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(formularioFrame, "Ingrese valores numéricos válidos", "Error", JOptionPane.ERROR_MESSAGE);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(formularioFrame, "Ocurrió un error al actualizar el estudiante", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        formularioFrame.add(idPersonaLabel);
        formularioFrame.add(idPersonaField);
        formularioFrame.add(new JLabel()); // Espacio vacío
        formularioFrame.add(buscarButton);
        formularioFrame.add(nombresLabel);
        formularioFrame.add(nombresField);
        formularioFrame.add(apellidosLabel);
        formularioFrame.add(apellidosField);
        formularioFrame.add(emailLabel);
        formularioFrame.add(emailField);
        formularioFrame.add(codigoLabel);
        formularioFrame.add(codigoField);
        formularioFrame.add(programaLabel);
        formularioFrame.add(programaField);
        formularioFrame.add(activoLabel);
        formularioFrame.add(activoCheckBox);
        formularioFrame.add(promedioLabel);
        formularioFrame.add(promedioField);
        formularioFrame.add(new JLabel()); // Espacio vacío
        formularioFrame.add(actualizarButton);

        formularioFrame.setVisible(true);
    }

    // Función separada para buscar estudiante por ID
    public static void buscarEstudiantePorId(JTextField idPersonaField, JTextField nombresField, JTextField apellidosField,
                                             JTextField emailField, JTextField codigoField, JTextField programaField,
                                             JCheckBox activoCheckBox, JTextField promedioField,
                                             JButton actualizarButton, JFrame frame) {
        String idTexto = idPersonaField.getText().trim();
        if (idTexto.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Ingrese un ID válido.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            long idPersona = Long.parseLong(idTexto);
            Estudiante estudiante = repositorioEstudiante.obtenerEstudianteByID(idPersona);

            if (estudiante != null) {
                // Cargar datos en los campos
                nombresField.setText(estudiante.getNombres());
                apellidosField.setText(estudiante.getApellidos());
                emailField.setText(estudiante.getEmail());
                codigoField.setText(String.valueOf(estudiante.getCodigo()));
                programaField.setText(String.valueOf(estudiante.getPrograma().getID()));
                activoCheckBox.setSelected(estudiante.getActivo());
                promedioField.setText(String.valueOf(estudiante.getPromedio()));

                // Habilitar los campos de edición y el botón Actualizar
                nombresField.setEnabled(true);
                apellidosField.setEnabled(true);
                emailField.setEnabled(true);
                codigoField.setEnabled(true);
                programaField.setEnabled(true);
                activoCheckBox.setEnabled(true);
                promedioField.setEnabled(true);
                actualizarButton.setEnabled(true);
            } else {
                JOptionPane.showMessageDialog(frame, "Estudiante no encontrado.", "Error", JOptionPane.WARNING_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Error: El ID ingresado no es un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}
