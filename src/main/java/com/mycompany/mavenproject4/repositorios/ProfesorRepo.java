package com.mycompany.mavenproject4.repositorios;

import com.mycompany.mavenproject4.modelos.Profesor;
import com.mycompany.mavenproject4.entityManager.DatabaseManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProfesorRepo {

    public List<Profesor> obtenerTodosProfesor() {
        List<Profesor> profesores = new ArrayList<>();
        String sql = "SELECT * FROM profesor";

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                profesores.add(mapearProfesor(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return profesores;
    }

    public Profesor obtenerProfesorByID(Long id) {
        String sql = "SELECT * FROM profesor WHERE id = ?";
        Profesor profesor = null;

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    profesor = mapearProfesor(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return profesor;
    }

    public Profesor crearProfesor(Profesor profesor) {
        String sql = "INSERT INTO profesor (nombres, apellidos, email, tipoContrato) VALUES (?, ?, ?, ?)";

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, profesor.getNombres());
            statement.setString(2, profesor.getApellidos());
            statement.setString(3, profesor.getEmail());
            statement.setString(4, profesor.getTipoContrato());

            int affectedRows = statement.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        profesor.setID(generatedKeys.getLong(1));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

        return profesor;
    }

    public Profesor actualizarProfesorPorId(Long id, Profesor nuevoProfesor) {
        String sql = "UPDATE profesor SET nombres = ?, apellidos = ?, email = ?, tipoContrato = ? WHERE id = ?";

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, nuevoProfesor.getNombres());
            statement.setString(2, nuevoProfesor.getApellidos());
            statement.setString(3, nuevoProfesor.getEmail());
            statement.setString(4, nuevoProfesor.getTipoContrato());
            statement.setLong(5, id);

            int affectedRows = statement.executeUpdate();
            if (affectedRows > 0) {
                return obtenerProfesorByID(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean eliminarProfesor(Long id) {
        String sql = "DELETE FROM profesor WHERE id = ?";
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

    private Profesor mapearProfesor(ResultSet resultSet) throws SQLException {
        return new Profesor(
                resultSet.getString("tipoContrato"),
                resultSet.getLong("id"),
                resultSet.getString("nombres"),
                resultSet.getString("apellidos"),
                resultSet.getString("email")

        );
    }
}
