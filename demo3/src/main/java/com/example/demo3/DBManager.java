package com.example.demo3;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBManager {

    private static final String URL = "jdbc:mysql://localhost:3306/vehicles_rental_system"; // Replace with your DB URL
    private static final String USER = "root"; // Replace with your DB username
    private static final String PASSWORD = "123456"; // Replace with your DB password

    public static Connection getConnection() throws SQLException {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            System.out.println("Database connection failed: " + e.getMessage());
            throw e;
        }
    }
}


