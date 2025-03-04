package com.mycompany.mavenproject4.repositorios;

import com.mycompany.mavenproject4.entityManager.DatabaseManager;
import com.mycompany.mavenproject4.modelos.Persona;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class personaRepo {

    public List<Persona> obtenerTodosPersona() {
        List<Persona> personas = new ArrayList<>();
        String sql = "SELECT * FROM persona";

        try (Connection connection = DatabaseManager.getConnection();
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {

            while (resultSet.next()) {
                Persona persona = new Persona(
                        resultSet.getLong("id"),
                        resultSet.getString("nombres"),
                        resultSet.getString("apellidos"),
                        resultSet.getString("email")
                );
                personas.add(persona);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return personas;
    }

    public Persona obtenerPersonaByID(Long id) {
        String sql = "SELECT * FROM persona WHERE id = ?";
        Persona persona = null;

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                persona = new Persona(
                        resultSet.getLong("id"),
                        resultSet.getString("nombres"),
                        resultSet.getString("apellidos"),
                        resultSet.getString("email")
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return persona;
    }

    public Persona crearPersona(Persona persona) {
        String sql = "INSERT INTO persona (nombres, apellidos, email) VALUES (?, ?, ?)";

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setString(1, persona.getNombres());
            statement.setString(2, persona.getApellidos());
            statement.setString(3, persona.getEmail());

            int affectedRows = statement.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        persona.setID(generatedKeys.getLong(1));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        return persona;
    }




    public boolean eliminarPersona(Long id) {
        String sql = "DELETE FROM persona WHERE id = ?";
        boolean registroEliminado = false;

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setLong(1, id);
            int affectedRows = statement.executeUpdate();
            registroEliminado = affectedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return registroEliminado;
    }

    public boolean actualizarPersonaPorId(Long id, Persona nuevaPersona) {
        String sql = "UPDATE persona SET nombres = ?, apellidos = ?, email = ? WHERE id = ?";
        boolean registroActualizado = false;

        try (Connection connection = DatabaseManager.getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, nuevaPersona.getNombres());
            statement.setString(2, nuevaPersona.getApellidos());
            statement.setString(3, nuevaPersona.getEmail());
            statement.setLong(4, id);

            int affectedRows = statement.executeUpdate();
            registroActualizado = affectedRows > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return registroActualizado;
    }
}
