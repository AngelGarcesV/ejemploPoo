package com.mycompany.mavenproject4.Formularios.observadores;

import com.mycompany.mavenproject4.Controladores.ProfesorController;
import com.mycompany.mavenproject4.Observador.CursoSubject;
import com.mycompany.mavenproject4.modelos.Profesor;
import com.mycompany.mavenproject4.Observador.ProfesorObserver;
import com.mycompany.mavenproject4.Observador.ProfesorSubject;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PanelProfesores extends JPanel implements ProfesorObserver {
    private ProfesorController profesorController = new ProfesorController();

    public PanelProfesores(ProfesorSubject profesorSubject) {
        setLayout(new BorderLayout());


        JLabel label = new JLabel("Profesores", JLabel.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        add(label, BorderLayout.NORTH);


        String[] columnNames = { "ID", "Nombres", "Apellidos", "Email", "TipoContrato" };
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);


        profesorSubject.agregarObserver(this);
        profesorSubject.notificarObservers(); // Carga inicial
    }

    @Override
    public void updateProfesores() {
        List<Profesor> profesores = profesorController.getAllProfesor();
        DefaultTableModel tableModel = (DefaultTableModel) ((JTable) ((JScrollPane) getComponent(1)).getViewport().getView()).getModel();
        tableModel.setRowCount(0);  // Limpiar la tabla

        for (Profesor profesor : profesores) {
            Object[] rowData = {
                    profesor.getID(),
                    profesor.getNombres(),
                    profesor.getApellidos(),
                    profesor.getEmail(),
                    profesor.getTipoContrato()
            };
            tableModel.addRow(rowData);
        }
    }
}
