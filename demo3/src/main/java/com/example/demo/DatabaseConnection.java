package com.example.demo;

import java.sql.*;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/payroll_system";
    private static final String USER = "root";
    private static final String PASSWORD = "123456";  // Change to your database password

    public static Connection connect() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }


}

