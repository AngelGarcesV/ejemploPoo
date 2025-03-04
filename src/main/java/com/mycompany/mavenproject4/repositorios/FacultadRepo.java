package com.mycompany.mavenproject4.repositorios;

import com.mycompany.mavenproject4.modelos.Facultad;
import com.mycompany.mavenproject4.modelos.Persona;
import com.mycompany.mavenproject4.entityManager.DatabaseManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FacultadRepo {

    public Facultad crearFacultad(Facultad facultad) {
        String sql = "INSERT INTO facultad (nombre, decano_id) VALUES (?, ?)";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, facultad.getNombre());
            stmt.setLong(2, facultad.getDecano().getID());

            int affectedRows = stmt.executeUpdate();
            if (affectedRows > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        facultad.setID(generatedKeys.getLong(1));
                        return facultad;
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public boolean eliminarFacultad(Long ID) {
        String sql = "DELETE FROM facultad WHERE id = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, ID);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static Facultad obtenerFacultadByID(Long ID) {
        String sql = "SELECT * FROM facultad WHERE id = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setLong(1, ID);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Long idDecano = rs.getLong("decano_id");
                    Persona decano = new personaRepo().obtenerPersonaByID(idDecano);
                    return new Facultad(ID, rs.getString("nombre"), decano);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Facultad> obtenerTodasFacultades() {
        List<Facultad> facultades = new ArrayList<>();
        String sql = "SELECT * FROM facultad";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Long idDecano = rs.getLong("decano_id");
                Persona decano = new personaRepo().obtenerPersonaByID(idDecano);
                facultades.add(new Facultad(rs.getLong("id"), rs.getString("nombre"), decano));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return facultades;
    }

    public static Facultad actualizarFacultadPorId(Long ID, Facultad facultad) {
        String sql = "UPDATE facultad SET nombre = ?, decano_id = ? WHERE id = ?";
        try (Connection conn = DatabaseManager.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, facultad.getNombre());
            stmt.setLong(2, facultad.getDecano().getID());
            stmt.setLong(3, ID);

            int affectedRows = stmt.executeUpdate();
            return affectedRows > 0 ? facultad : null;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
