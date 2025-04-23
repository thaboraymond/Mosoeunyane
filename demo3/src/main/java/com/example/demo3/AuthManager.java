package com.example.demo3;



import java.sql.*;

public class AuthManager {

    // Authenticate user with database
    public boolean authenticate(String username, String password) {
        String query = "SELECT password FROM users WHERE username = ?";

        try (Connection conn = DBManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                String storedPassword = rs.getString("password");
                return storedPassword.equals(password); // Simple password match (consider using hashed passwords in real apps)
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    // Get user role from database
    public String getRole(String username) {
        String query = "SELECT role FROM users WHERE username = ?";
        String role = null;

        try (Connection conn = DBManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                role = rs.getString("role");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return role;
    }

    // Sign up new user into database
    public boolean signup(String username, String password, String role) {
        String query = "INSERT INTO users (username, password, role) VALUES (?, ?, ?)";

        try (Connection conn = DBManager.getConnection();
             PreparedStatement ps = conn.prepareStatement(query)) {

            ps.setString(1, username);
            ps.setString(2, password); // Store plain password (In a real app, hash the password)
            ps.setString(3, role);

            int rowsAffected = ps.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
}

