package com.mycompany.mavenproject4.Formularios.observadores;

import com.mycompany.mavenproject4.Controladores.InscripcionController;
import com.mycompany.mavenproject4.Observador.InscripcionObserver;
import com.mycompany.mavenproject4.Observador.InscripcionSubject;
import com.mycompany.mavenproject4.modelos.Inscripcion;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PanelInscripcion extends JPanel implements InscripcionObserver {
    private InscripcionController inscripcionController = new InscripcionController();

    public PanelInscripcion(InscripcionSubject inscripcionSubject) {
        setLayout(new BorderLayout());

        // Título
        JLabel label = new JLabel("Inscripciones", JLabel.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        add(label, BorderLayout.NORTH);

        // Tabla
        String[] columnNames = {"ID", "Año", "Semestre", "Curso_ID", "Estudiante_ID"};
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Registrar observer
        inscripcionSubject.agregarObserver(this);
        inscripcionSubject.notificarObservers(); // Carga inicial
    }

    @Override
    public void updateInscripciones() {
        List<Inscripcion> inscripciones = inscripcionController.getAllInscripciones();
        DefaultTableModel tableModel = (DefaultTableModel) ((JTable) ((JScrollPane) getComponent(1)).getViewport().getView()).getModel();
        tableModel.setRowCount(0); // Limpiar

        for (Inscripcion inscripcion : inscripciones) {
            Object[] rowData = {
                    inscripcion.getId(),
                    inscripcion.getAño(),
                    inscripcion.getSemestre(),
                    inscripcion.getCurso().getID(),
                    inscripcion.getEstudiante().getID()
            };
            tableModel.addRow(rowData);
        }
    }
}
