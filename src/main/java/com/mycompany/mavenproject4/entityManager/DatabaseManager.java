package com.mycompany.mavenproject4.entityManager;

import com.mycompany.mavenproject4.PropertiesLoader.PropertiesLoader;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseManager {
    private static final String URL = PropertiesLoader.get("database.url");
    private static final String USER = PropertiesLoader.get("database.user");
    private static final String PASSWORD = PropertiesLoader.get("database.password");

    static {
        try {
            Class.forName("org.h2.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Error cargando el driver de H2", e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}
