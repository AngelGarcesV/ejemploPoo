package com.mycompany.mavenproject4.Formularios.observadores;

import com.mycompany.mavenproject4.Controladores.CursoController;
import com.mycompany.mavenproject4.modelos.Curso;
import com.mycompany.mavenproject4.Observador.CursoObserver;
import com.mycompany.mavenproject4.Observador.CursoSubject;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class PanelCursos extends JPanel implements CursoObserver {
    private CursoController cursoController = new CursoController();

    public PanelCursos(CursoSubject cursoSubject) {
        setLayout(new BorderLayout());


        JLabel label = new JLabel("Cursos", JLabel.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 16));
        add(label, BorderLayout.NORTH);


        String[] columnNames = { "ID", "Activo", "Programa_ID" };
        DefaultTableModel tableModel = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);
        CursoSubject.agregarObserver(this);
        CursoSubject.notificarObservers();
    }

    @Override
    public void updateCursos() {
        List<Curso> cursos = cursoController.getAllCursos();
        DefaultTableModel tableModel = (DefaultTableModel) ((JTable) ((JScrollPane) getComponent(1)).getViewport().getView()).getModel();
        tableModel.setRowCount(0);
        for (Curso curso : cursos) {
            Object[] rowData = { curso.getID(), curso.getActivo(), curso.getPrograma().getID() };
            tableModel.addRow(rowData);
        }
    }
}
