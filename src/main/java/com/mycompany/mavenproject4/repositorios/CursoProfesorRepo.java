package com.mycompany.mavenproject4.repositorios;

import com.mycompany.mavenproject4.modelos.Curso;
import com.mycompany.mavenproject4.modelos.Profesor;
import com.mycompany.mavenproject4.modelos.CursoProfesor;
import com.mycompany.mavenproject4.entityManager.DatabaseManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CursoProfesorRepo {
    private static final CursoRepo cursoRepo = new CursoRepo();
    private static final ProfesorRepo profesorRepo = new ProfesorRepo();

    public List<CursoProfesor> obtenerTodosCursoProfesor() {
        List<CursoProfesor> cursoProfesores = new ArrayList<>();
        String sql = "SELECT id, curso_id, profesor_id, año, semestre FROM CursoProfesor";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Long cursoId = rs.getLong("curso_id");
                Long profesorId = rs.getLong("profesor_id");

                Curso curso = (cursoId != null) ? cursoRepo.obtenerCursoByID(cursoId) : null;
                Profesor profesor = (profesorId != null) ? profesorRepo.obtenerProfesorByID(profesorId) : null;

                CursoProfesor cursoProfesor = new CursoProfesor(
                        rs.getLong("id"),
                        profesor,
                        rs.getInt("año"),
                        rs.getInt("semestre"),
                        curso
                );
                cursoProfesores.add(cursoProfesor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cursoProfesores;
    }


    public CursoProfesor obtenerCursoProfesorByID(Long id) {
        String sql = "SELECT id, curso_id, profesor_id, año, semestre FROM CursoProfesor WHERE id = ?";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, id);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Curso curso = cursoRepo.obtenerCursoByID(rs.getLong("curso_id"));
                    Profesor profesor = profesorRepo.obtenerProfesorByID(rs.getLong("profesor_id"));

                    return new CursoProfesor(
                            rs.getLong("id"),
                            profesor,
                            rs.getInt("año"),
                            rs.getInt("semestre"),
                            curso
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public CursoProfesor crearCursoProfesor(CursoProfesor cursoProfesor) {
        String sql = "INSERT INTO CursoProfesor (curso_id, profesor_id, año, semestre) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setLong(1, cursoProfesor.getCurso().getID());
            stmt.setLong(2, cursoProfesor.getProfesor().getID());
            stmt.setInt(3, cursoProfesor.getAño());
            stmt.setInt(4, cursoProfesor.getSemestre());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        cursoProfesor.setId(generatedKeys.getLong(1));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cursoProfesor;
    }

    public CursoProfesor actualizarCursoProfesorPorId(Long id, CursoProfesor nuevoCursoProfesor) {
        String sql = "UPDATE CursoProfesor SET curso_id = ?, profesor_id = ?, año = ?, semestre = ? WHERE id = ?";

        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, nuevoCursoProfesor.getCurso().getID());
            stmt.setLong(2, nuevoCursoProfesor.getProfesor().getID());
            stmt.setInt(3, nuevoCursoProfesor.getAño());
            stmt.setInt(4, nuevoCursoProfesor.getSemestre());
            stmt.setLong(5, id);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                return obtenerCursoProfesorByID(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean eliminarCursoProfesor(Long id) {
        String sql = "DELETE FROM CursoProfesor WHERE id = ?";
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
