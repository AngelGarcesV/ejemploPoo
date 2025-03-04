package com.mycompany.mavenproject4.repositorios;

import com.mycompany.mavenproject4.modelos.Curso;
import com.mycompany.mavenproject4.modelos.Estudiante;
import com.mycompany.mavenproject4.modelos.Inscripcion;
import com.mycompany.mavenproject4.entityManager.DatabaseManager;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InscripcionRepo {
    static CursoRepo repositorioCurso = new CursoRepo();
    static EstudianteRepo repositorioEstudiante = new EstudianteRepo();

    public List<Inscripcion> obtenerTodasInscripciones() {
        List<Inscripcion> inscripciones = new ArrayList<>();
        String sql = "SELECT id, curso_id, año, semestre, estudiante_id FROM Inscripcion";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Curso curso = repositorioCurso.obtenerCursoByID(rs.getLong("curso_id"));
                Estudiante estudiante = repositorioEstudiante.obtenerEstudianteByID(rs.getLong("estudiante_id"));

                Inscripcion inscripcion = new Inscripcion(
                        rs.getLong("id"),
                        curso,
                        rs.getInt("año"),
                        rs.getInt("semestre"),
                        estudiante
                );
                inscripciones.add(inscripcion);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inscripciones;
    }


    public static Inscripcion obtenerInscripcionByID(Long id) {
        String sql = "SELECT id, curso_id, año, semestre, estudiante_id FROM Inscripcion WHERE id = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Estudiante estudiante = repositorioEstudiante.obtenerEstudianteByID(rs.getLong("estudiante_id"));
                    Curso curso = CursoRepo.obtenerCursoByID(rs.getLong("curso_id"));

                    return new Inscripcion(
                            rs.getLong("id"),
                            curso,
                            rs.getInt("año"),
                            rs.getInt("semestre"),
                            estudiante
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public Inscripcion crearInscripcion(Inscripcion inscripcion) {
        String sql = "INSERT INTO Inscripcion (curso_id, año, semestre, estudiante_id) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setLong(1, inscripcion.getCurso().getID());
            stmt.setInt(2, inscripcion.getAño());
            stmt.setInt(3, inscripcion.getSemestre());
            stmt.setLong(4, inscripcion.getEstudiante().getID());
            stmt.executeUpdate();
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    inscripcion.setId(generatedKeys.getLong(1));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return inscripcion;
    }

    public static Inscripcion actualizarInscripcionPorId(Long id, Inscripcion nuevaInscripcion) {
        String sql = "UPDATE Inscripcion SET curso_id = ?, año = ?, semestre = ?, estudiante_id = ? WHERE id = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, nuevaInscripcion.getCurso().getID());
            stmt.setInt(2, nuevaInscripcion.getAño());
            stmt.setInt(3, nuevaInscripcion.getSemestre());
            stmt.setLong(4, nuevaInscripcion.getEstudiante().getID());
            stmt.setLong(5, id);
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                return nuevaInscripcion;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean eliminarInscripcion(Long id) {
        String sql = "DELETE FROM Inscripcion WHERE id = ?";
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
