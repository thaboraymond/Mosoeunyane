package com.example.demo3;


import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class LoginForm {
    public void start(Stage primaryStage) {
        primaryStage.setTitle("User Login");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        // Username Label
        Label usernameLabel = new Label("Username:");
        GridPane.setConstraints(usernameLabel, 0, 0);

        // Username Input
        TextField usernameInput = new TextField();
        GridPane.setConstraints(usernameInput, 1, 0);

        // Password Label
        Label passwordLabel = new Label("Password:");
        GridPane.setConstraints(passwordLabel, 0, 1);

        // Password Input
        PasswordField passwordInput = new PasswordField();
        GridPane.setConstraints(passwordInput, 1, 1);

        // Login Button
        Button loginButton = new Button("Login");
        GridPane.setConstraints(loginButton, 1, 2);

        loginButton.setOnAction(e -> {
            String username = usernameInput.getText();
            String password = passwordInput.getText();
            if (validateLogin(username, password)) {
                // Proceed to main application
            } else {
                // Show error message
            }
        });

        grid.getChildren().addAll(usernameLabel, usernameInput, passwordLabel, passwordInput, loginButton);

        Scene scene = new Scene(grid, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private boolean validateLogin(String username, String password) {
        // Implement database validation logic here
        // Ensure password is hashed and compared securely
        return false;
    }
}
