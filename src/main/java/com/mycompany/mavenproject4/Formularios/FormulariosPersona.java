package com.mycompany.mavenproject4.Formularios;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import com.mycompany.mavenproject4.Controladores.InscripcionesPersonas;
import com.mycompany.mavenproject4.modelos.Persona;
import com.mycompany.mavenproject4.repositorios.personaRepo;


public class FormulariosPersona {

    static String ArchivoInformacionInscritos = "informacionInscritos.dat";
    static personaRepo repositorioPersona = new personaRepo();
    public static InscripcionesPersonas inscripcionesPersonas = new InscripcionesPersonas(new ArrayList<Persona>());


    public static void guardarPersonaDB_InscribirArchivo(String nombres, String apellidos, String email, String NombreArchivo) {
        Persona infoPersona = new Persona(null, nombres, apellidos, email);
        Persona NuevaPersona= repositorioPersona.crearPersona(infoPersona);
        if(NuevaPersona!=null){

            inscripcionesPersonas.inscribir(NuevaPersona);
            inscripcionesPersonas.guardarInformacionArchivo(NombreArchivo);
            JOptionPane.showMessageDialog(null, "Persona Creada con exito");
        }else{
            JOptionPane.showMessageDialog(null, "Fallo al crear la persona");
        }
    }
    public static void actualizarPersonaDB_ActualizarArchivo(Long id, String nombres, String apellidos, String email, String NombreArchivo) {
        Persona infoPersona = new Persona(id, nombres, apellidos, email);
        Persona personaEditada = repositorioPersona.actualizarPersonaPorId(id,infoPersona);
        if(personaEditada!=null){
            inscripcionesPersonas.actualizar(infoPersona);
            inscripcionesPersonas.guardarInformacionArchivo(NombreArchivo);
            JOptionPane.showMessageDialog(null, "Persona Actualizada con exito");
        }else{
            JOptionPane.showMessageDialog(null, "Persona no encontrada");
        }


    }


    public static void mostrarFormularioCrearPersona() {


        JFrame formularioFrame = new JFrame("Formulario Crear Persona");
        formularioFrame.setSize(400, 300);
        formularioFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        formularioFrame.setLayout(new GridLayout(4, 2));

        JLabel nombresLabel = new JLabel("Nombres:");
        JTextField nombresField = new JTextField();
        JLabel apellidosLabel = new JLabel("Apellidos:");
        JTextField apellidosField = new JTextField();
        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField();

        JButton crearButton = new JButton("Crear");

        crearButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nombres = nombresField.getText();
                String apellidos = apellidosField.getText();
                String email = emailField.getText();

                guardarPersonaDB_InscribirArchivo(nombres,apellidos,email,ArchivoInformacionInscritos);
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
        formularioFrame.add(new JLabel());
        formularioFrame.add(crearButton);

        // Mostrar el formulario
        formularioFrame.setVisible(true);
    }

    public static void mostrarFormularioEliminarPersona() {
        JFrame formularioFrame = new JFrame("Formulario Eliminar Persona");
        formularioFrame.setSize(400, 300);
        formularioFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        formularioFrame.setLayout(new GridLayout(2, 2));  // Solo 2 filas

        JLabel idLabel = new JLabel("ID:");
        JTextField idField = new JTextField();
        JButton eliminarButton = new JButton("Eliminar");

        eliminarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String id = idField.getText();
                try {
                    long idLong = Long.parseLong(id);
                    if(repositorioPersona.eliminarPersona(idLong)){
                        JOptionPane.showMessageDialog(null, "Persona Eliminado con exito");
                    }else{
                        JOptionPane.showMessageDialog(null, "Persona no encontrada");
                    }
                }catch (Exception error){

                }
                formularioFrame.dispose();
            }
        });

        formularioFrame.add(idLabel);
        formularioFrame.add(idField);
        formularioFrame.add(new JLabel());
        formularioFrame.add(eliminarButton);

        // Mostrar el formulario
        formularioFrame.setVisible(true);
    }

    public static void mostrarFormularioVerPersonaPorId() {
        JFrame formularioFrame = new JFrame("Ver Persona por ID");
        formularioFrame.setSize(500, 300);
        formularioFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        formularioFrame.setLayout(new BorderLayout());

        // Panel de entrada con mejor distribución
        JPanel inputPanel = new JPanel(new FlowLayout());
        JLabel idLabel = new JLabel("ID:");
        JTextField idField = new JTextField(10);
        JButton verButton = new JButton("Ver");

        inputPanel.add(idLabel);
        inputPanel.add(idField);
        inputPanel.add(verButton);

        // Tabla para mostrar la información de la persona
        String[] columnNames = { "ID", "Apellidos", "Email", "Nombres" };
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(table);

        formularioFrame.add(inputPanel, BorderLayout.NORTH);
        formularioFrame.add(tableScrollPane, BorderLayout.CENTER);

        verButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String idTexto = idField.getText().trim();

                if (idTexto.isEmpty()) {
                    JOptionPane.showMessageDialog(formularioFrame, "Ingrese un ID válido.", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                try {
                    long idLong = Long.parseLong(idTexto);
                    Persona personaConsultada = repositorioPersona.obtenerPersonaByID(idLong);

                    // Limpiar la tabla antes de agregar nuevos datos
                    tableModel.setRowCount(0);

                    if (personaConsultada != null) {
                        Object[] row = {
                                personaConsultada.getID(),
                                personaConsultada.getApellidos(),
                                personaConsultada.getEmail(),
                                personaConsultada.getNombres()
                        };
                        tableModel.addRow(row); // Agregar la fila con los datos de la persona
                    } else {
                        JOptionPane.showMessageDialog(formularioFrame, "No se encontró la persona con el ID ingresado.", "Error", JOptionPane.WARNING_MESSAGE);
                    }

                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(formularioFrame, "Error: El ID ingresado no es un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Mostrar el formulario
        formularioFrame.setVisible(true);
    }


    public static void mostrarTablaTodasPersonas() {
        List<Persona> personas = repositorioPersona.obtenerTodosPersona();
        JFrame frame = new JFrame("Lista de Personas");
        frame.setSize(600, 400);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        String[] columnNames = {"ID", "Nombres", "Apellidos", "Email"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);

        for (Persona persona : personas) {
            Object[] rowData = {persona.getID(), persona.getNombres(), persona.getApellidos(), persona.getEmail()};
            tableModel.addRow(rowData);
        }

        frame.add(scrollPane, BorderLayout.CENTER);
        frame.setVisible(true);
    }

    public static void mostrarFormularioActualizarPersona() {
        JFrame formularioFrame = new JFrame("Actualizar Persona por ID");
        formularioFrame.setSize(500, 350);
        formularioFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        formularioFrame.setLayout(new BorderLayout());

        // Panel de entrada con ID y botón Buscar
        JPanel inputPanel = new JPanel(new FlowLayout());
        JLabel idLabel = new JLabel("ID:");
        JTextField idField = new JTextField(10);
        JButton buscarButton = new JButton("Buscar");

        inputPanel.add(idLabel);
        inputPanel.add(idField);
        inputPanel.add(buscarButton);

        // Panel de edición
        JPanel editPanel = new JPanel(new GridLayout(4, 2));
        JLabel nombreLabel = new JLabel("Nombres:");
        JTextField nombreField = new JTextField();
        JLabel apellidoLabel = new JLabel("Apellidos:");
        JTextField apellidoField = new JTextField();
        JLabel emailLabel = new JLabel("Email:");
        JTextField emailField = new JTextField();
        JButton actualizarButton = new JButton("Actualizar");

        editPanel.add(nombreLabel);
        editPanel.add(nombreField);
        editPanel.add(apellidoLabel);
        editPanel.add(apellidoField);
        editPanel.add(emailLabel);
        editPanel.add(emailField);
        editPanel.add(new JLabel()); // Espacio vacío
        editPanel.add(actualizarButton);

        // Deshabilitar campos de edición hasta que se busque un ID
        nombreField.setEnabled(false);
        apellidoField.setEnabled(false);
        emailField.setEnabled(false);
        actualizarButton.setEnabled(false);

        formularioFrame.add(inputPanel, BorderLayout.NORTH);
        formularioFrame.add(editPanel, BorderLayout.CENTER);

        // Acción del botón Buscar
        buscarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                botonBuscarPersona_FormularioActualizar(idField, nombreField, apellidoField, emailField, actualizarButton, formularioFrame);
            }
        });

        // Acción del botón Actualizar
        actualizarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    long idLong = Long.parseLong(idField.getText().trim());
                    String nombres = nombreField.getText().trim();
                    String apellidos = apellidoField.getText().trim();
                    String email = emailField.getText().trim();

                    if (nombres.isEmpty() || apellidos.isEmpty() || email.isEmpty()) {
                        JOptionPane.showMessageDialog(formularioFrame, "Todos los campos son obligatorios.", "Error", JOptionPane.ERROR_MESSAGE);
                        return;
                    }

                    // Llamar a la función de actualización
                    actualizarPersonaDB_ActualizarArchivo(idLong, nombres, apellidos, email, ArchivoInformacionInscritos);

                    // Cerrar el formulario después de actualizar
                    formularioFrame.dispose();

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(formularioFrame, "Ocurrió un error al actualizar la persona.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        formularioFrame.setVisible(true);
    }


    public static void botonBuscarPersona_FormularioActualizar(JTextField idField, JTextField nombreField, JTextField apellidoField,
                                          JTextField emailField, JButton actualizarButton, JFrame frame) {
        String idTexto = idField.getText().trim();
        if (idTexto.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Ingrese un ID válido.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        try {
            long idLong = Long.parseLong(idTexto);
            Persona personaConsultada = repositorioPersona.obtenerPersonaByID(idLong);

            if (personaConsultada != null) {
                // Cargar datos en los campos
                nombreField.setText(personaConsultada.getNombres());
                apellidoField.setText(personaConsultada.getApellidos());
                emailField.setText(personaConsultada.getEmail());

                // Habilitar los campos de edición y el botón Actualizar
                nombreField.setEnabled(true);
                apellidoField.setEnabled(true);
                emailField.setEnabled(true);
                actualizarButton.setEnabled(true);
            } else {
                JOptionPane.showMessageDialog(frame, "No se encontró la persona con el ID ingresado.", "Error", JOptionPane.WARNING_MESSAGE);
            }

        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Error: El ID ingresado no es un número válido.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
