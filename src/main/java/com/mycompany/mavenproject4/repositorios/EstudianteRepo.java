package com.mycompany.mavenproject4.repositorios;

import com.mycompany.mavenproject4.modelos.Estudiante;
import com.mycompany.mavenproject4.modelos.Programa;
import com.mycompany.mavenproject4.entityManager.DatabaseManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EstudianteRepo {
    private static final ProgramaRepo programaRepo = new ProgramaRepo();

    public List<Estudiante> obtenerTodosEstudiantes() {
        List<Estudiante> estudiantes = new ArrayList<>();
        String sql = "SELECT id, nombres, apellidos, email, codigo, programa_id, activo, promedio FROM Estudiante";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Programa programa = programaRepo.obtenerProgramaByID(rs.getLong("programa_id"));

                Estudiante estudiante = new Estudiante(
                        rs.getDouble("codigo"),
                        programa,
                        rs.getBoolean("activo"),
                        rs.getDouble("promedio"),
                        rs.getLong("id"),
                        rs.getString("nombres"),
                        rs.getString("apellidos"),
                        rs.getString("email")
                );
                estudiantes.add(estudiante);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return estudiantes;
    }

    public Estudiante obtenerEstudianteByID(Long id) {
        String sql = "SELECT id, nombres, apellidos, email, codigo, programa_id, activo, promedio FROM Estudiante WHERE id = ?";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Programa programa = programaRepo.obtenerProgramaByID(rs.getLong("programa_id"));

                    return new Estudiante(
                            rs.getDouble("codigo"),
                            programa,
                            rs.getBoolean("activo"),
                            rs.getDouble("promedio"),
                            rs.getLong("id"),
                            rs.getString("nombres"),
                            rs.getString("apellidos"),
                            rs.getString("email")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Estudiante crearEstudiante(Estudiante estudiante) {
        String sql = "INSERT INTO Estudiante (nombres, apellidos, email, codigo, programa_id, activo, promedio) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, estudiante.getNombres());
            stmt.setString(2, estudiante.getApellidos());
            stmt.setString(3, estudiante.getEmail());
            stmt.setDouble(4, estudiante.getCodigo());
            stmt.setLong(5, estudiante.getPrograma().getID());
            stmt.setBoolean(6, estudiante.getActivo());
            stmt.setDouble(7, estudiante.getPromedio());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        estudiante.setID(generatedKeys.getLong(1));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return estudiante;
    }

    public Estudiante actualizarEstudiantePorId(Long id, Estudiante nuevoEstudiante) {
        String sql = "UPDATE Estudiante SET nombres = ?, apellidos = ?, email = ?, codigo = ?, programa_id = ?, activo = ?, promedio = ? WHERE id = ?";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, nuevoEstudiante.getNombres());
            stmt.setString(2, nuevoEstudiante.getApellidos());
            stmt.setString(3, nuevoEstudiante.getEmail());
            stmt.setDouble(4, nuevoEstudiante.getCodigo());
            stmt.setLong(5, nuevoEstudiante.getPrograma().getID());
            stmt.setBoolean(6, nuevoEstudiante.getActivo());
            stmt.setDouble(7, nuevoEstudiante.getPromedio());
            stmt.setLong(8, id);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                return obtenerEstudianteByID(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean eliminarEstudiante(Long id) {
        String sql = "DELETE FROM Estudiante WHERE id = ?";
        boolean eliminado = false;

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);
            eliminado = stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return eliminado;
    }
}
