package com.example.demo3;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/vehicles_rental_managementuseSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";

    public static Connection connect() throws SQLException {
        try {
            // MySQL Driver is automatically loaded with newer JDBC drivers
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.err.println("Connection failed: " + e.getMessage());
            throw e; // Rethrow the SQLException after logging it
        }
    }
}
