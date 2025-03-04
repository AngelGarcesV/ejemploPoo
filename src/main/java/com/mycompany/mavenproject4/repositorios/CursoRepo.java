package com.mycompany.mavenproject4.repositorios;

import com.mycompany.mavenproject4.modelos.Curso;
import com.mycompany.mavenproject4.modelos.Programa;
import com.mycompany.mavenproject4.entityManager.DatabaseManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CursoRepo {
    private static ProgramaRepo programaRepo = new ProgramaRepo();

    public List<Curso> obtenerTodosCursos() {
        List<Curso> cursos = new ArrayList<>();
        String sql = "SELECT * FROM curso";

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                cursos.add(mapearCurso(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cursos;
    }

    public static Curso obtenerCursoByID(Long id) {
        String sql = "SELECT * FROM curso WHERE id = ?";
        Curso curso = null;

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    curso = mapearCurso(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return curso;
    }

    public Curso crearCurso(Curso curso) {
        String sql = "INSERT INTO curso (programa_id, activo) VALUES (?, ?)";

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setLong(1, curso.getPrograma().getID());
            statement.setBoolean(2, curso.getActivo());

            int affectedRows = statement.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        curso.setID(generatedKeys.getLong(1));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return curso;
    }

    public static Curso actualizarCursoPorId(Long id, Curso nuevoCurso) {
        String sql = "UPDATE curso SET programa_id = ?, activo = ? WHERE id = ?";

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, nuevoCurso.getPrograma().getID());
            statement.setBoolean(2, nuevoCurso.getActivo());
            statement.setLong(3, id);

            int affectedRows = statement.executeUpdate();
            if (affectedRows > 0) {
                return obtenerCursoByID(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean eliminarCurso(Long id) {
        String sql = "DELETE FROM curso WHERE id = ?";
        boolean registroEliminado = false;

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);
            registroEliminado = statement.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return registroEliminado;
    }

    private static Curso mapearCurso(ResultSet resultSet) throws SQLException {
        Long programaId = resultSet.getLong("programa_id");
        Programa programa = programaRepo.obtenerProgramaByID(programaId);

        return new Curso(
                resultSet.getLong("id"),
                programa,
                resultSet.getBoolean("activo")
        );
    }
}
