package com.example.demo3;
class User {

    // Fields for the User class
    private int userId;
    private String username;
    private String password;
    private String role;  // Example roles: "Admin", "Employee"

    // Constructor to initialize the user object
    public User(int userId, String username, String password, String role) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // Getter and Setter methods for userId
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    // Getter and Setter methods for username
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // Getter and Setter methods for password
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    // Getter and Setter methods for role
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    // Method to display user details (for debugging or testing)
    @Override
    public String toString() {
        return "User ID: " + userId + ", Username: " + username + ", Role: " + role;
    }
}

