package com.example.demo3;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class RegisterForm {
    public void start(Stage primaryStage) {
        primaryStage.setTitle("User Registration");

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        // Username Label - constrains use (child, column, row)
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

        // Role Label
        Label roleLabel = new Label("Role:");
        GridPane.setConstraints(roleLabel, 0, 2);

        // Role Input
        ComboBox<String> roleInput = new ComboBox<>();
        roleInput.getItems().addAll("admin", "customer");
        GridPane.setConstraints(roleInput, 1, 2);

        // Register Button
        Button registerButton = new Button("Register");
        GridPane.setConstraints(registerButton, 1, 3);

        registerButton.setOnAction(e -> {
            String username = usernameInput.getText();
            String password = passwordInput.getText();
            String role = roleInput.getValue();
            registerUser(username, password, role);
        });

        grid.getChildren().addAll(usernameLabel, usernameInput, passwordLabel, passwordInput, roleLabel, roleInput, registerButton);

        Scene scene = new Scene(grid, 300, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void registerUser(String username, String password, String role) {
        // Implement database insertion logic here
        // Ensure password is hashed before storing
    }
}

