package com.mycompany.mavenproject4.repositorios;

import com.mycompany.mavenproject4.modelos.Facultad;
import com.mycompany.mavenproject4.modelos.Programa;
import com.mycompany.mavenproject4.entityManager.DatabaseManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProgramaRepo {
    private FacultadRepo repositorioFacultad = new FacultadRepo();

    public List<Programa> obtenerTodosProgramas() {
        List<Programa> programas = new ArrayList<>();
        String sql = "SELECT * FROM Programa";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Long facultadId = rs.getLong("facultad_id");
                Facultad facultad = repositorioFacultad.obtenerFacultadByID(facultadId);

                programas.add(new Programa(
                        rs.getLong("id"),
                        rs.getString("nombre"),
                        rs.getLong("duracion"),
                        facultad,
                        rs.getDate("registro")
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return programas;
    }

    public Programa obtenerProgramaByID(Long id) {
        String sql = "SELECT * FROM Programa WHERE id = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Long facultadId = rs.getLong("facultad_id");
                    Facultad facultad = repositorioFacultad.obtenerFacultadByID(facultadId);

                    return new Programa(
                            rs.getLong("id"),
                            rs.getString("nombre"),
                            rs.getLong("duracion"),
                            facultad,
                            rs.getDate("registro")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Programa crearPrograma(Programa programa) {
        String sql = "INSERT INTO Programa (nombre, duracion, registro, facultad_id) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, programa.getNombre());
            stmt.setLong(2, programa.getDuracion());
            stmt.setDate(3, new java.sql.Date(programa.getRegistro().getTime()));
            stmt.setLong(4, programa.getFacultad().getID());
            stmt.executeUpdate();

            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    programa.setID(generatedKeys.getLong(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return programa;
    }

    public Programa actualizarProgramaPorId(Long id, Programa nuevoPrograma) {
        String sql = "UPDATE Programa SET nombre = ?, duracion = ?, registro = ?, facultad_id = ? WHERE id = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nuevoPrograma.getNombre());
            stmt.setLong(2, nuevoPrograma.getDuracion());
            stmt.setDate(3, new java.sql.Date(nuevoPrograma.getRegistro().getTime()));
            stmt.setLong(4, nuevoPrograma.getFacultad().getID());
            stmt.setLong(5, id);

            int filasActualizadas = stmt.executeUpdate();
            if (filasActualizadas > 0) {
                return nuevoPrograma;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean eliminarPrograma(Long id) {
        String sql = "DELETE FROM Programa WHERE id = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}
