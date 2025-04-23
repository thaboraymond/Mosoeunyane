package com.example.demo3;

import javafx.application.Application;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.stage.Stage;

import java.util.*;

public class  VehicleManagement extends Application {

    private TableView<Vehicle> table;
    private TextField brandField, modelField, categoryField, priceField;
    private List<Vehicle> vehicleList;  // In-memory list to store vehicles
    private Map<String, String> userCredentials;  // Map to store username and password pairs
    private Stage primaryStage;
    private TextField usernameField;
    private PasswordField passwordField;
    private String currentUserRole;  // Store the role of the current user

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) {
        primaryStage = stage;
        vehicleList = new ArrayList<>();  // Initialize the in-memory list
        userCredentials = new HashMap<>();  // Initialize the user credentials map

        // Add default users for testing with roles
        userCredentials.put("admin", "admin123");
        userCredentials.put("employee", "employee123");

        showLoginPage();  // Show login page when the app starts
    }

    // Method to display the login page
    private void showLoginPage() {
        VBox loginLayout = new VBox(10);
        loginLayout.setStyle("-fx-background-color: #f0f0f0;");  // Set background color

        usernameField = new TextField();
        passwordField = new PasswordField();
        Button loginButton = new Button("Login");
        Button registerButton = new Button("Register");

        loginButton.setOnAction(e -> login());
        registerButton.setOnAction(e -> showRegisterPage());

        loginLayout.getChildren().addAll(new Label("Username:"), usernameField,
                new Label("Password:"), passwordField, loginButton, registerButton);

        Scene loginScene = new Scene(loginLayout, 300, 200);
        primaryStage.setTitle("Login");
        primaryStage.setScene(loginScene);
        primaryStage.show();
    }

    // Method to handle login logic
    private void login() {
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Validation for empty fields
        if (username.isEmpty() || password.isEmpty()) {
            showError("Login Failed", "Please enter both username and password.");
            return;
        }

        // Check if the username and password match
        if (userCredentials.containsKey(username) && userCredentials.get(username).equals(password)) {
            currentUserRole = (username.equals("admin")) ? "admin" : "employee";  // Set role based on username
            showVehicleManagementPage();  // Login successful, show vehicle management page
        } else {
            showError("Invalid Credentials", "The username or password is incorrect.");
        }
    }

    // Method to show the registration page
    private void showRegisterPage() {
        VBox registerLayout = new VBox(10);
        registerLayout.setStyle("-fx-background-color: #f0f0f0;");  // Set background color

        TextField registerUsernameField = new TextField();
        PasswordField registerPasswordField = new PasswordField();
        PasswordField registerConfirmPasswordField = new PasswordField();
        Button registerButton = new Button("Register");
        Button cancelButton = new Button("Cancel");

        registerButton.setOnAction(e -> register(registerUsernameField, registerPasswordField, registerConfirmPasswordField));
        cancelButton.setOnAction(e -> showLoginPage());

        registerLayout.getChildren().addAll(new Label("Username:"), registerUsernameField,
                new Label("Password:"), registerPasswordField,
                new Label("Confirm Password:"), registerConfirmPasswordField, registerButton, cancelButton);

        Scene registerScene = new Scene(registerLayout, 300, 250);
        primaryStage.setTitle("Register");
        primaryStage.setScene(registerScene);
        primaryStage.show();
    }

    // Method to handle registration
    private void register(TextField registerUsernameField, PasswordField registerPasswordField, PasswordField registerConfirmPasswordField) {
        String username = registerUsernameField.getText();
        String password = registerPasswordField.getText();
        String confirmPassword = registerConfirmPasswordField.getText();

        // Validation
        if (username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            showError("All Fields Required", "Please fill in all fields.");
        } else if (!password.equals(confirmPassword)) {
            showError("Password Mismatch", "Passwords do not match.");
        } else if (userCredentials.containsKey(username)) {
            showError("Username Taken", "This username is already taken.");
        } else {
            // Add the new user to the credentials map
            userCredentials.put(username, password);
            showSuccess("Registration Successful", "You can now log in with your credentials.");
            showLoginPage();  // Return to login page after registration
        }
    }

    // Method to show vehicle management page after successful login
    private void showVehicleManagementPage() {
        table = new TableView<>();
        brandField = new TextField();
        modelField = new TextField();
        categoryField = new TextField();
        priceField = new TextField();

        Button addButton = new Button("Add Vehicle");
        addButton.setOnAction(e -> addVehicle());

        Button updateButton = new Button("Update Vehicle");
        updateButton.setOnAction(e -> updateVehicle());

        Button deleteButton = new Button("Delete Vehicle");
        deleteButton.setOnAction(e -> deleteVehicle());

        // Disable delete button for non-admin users
        if (!currentUserRole.equals("admin")) {
            deleteButton.setDisable(true);
        }

        // Table columns
        TableColumn<Vehicle, String> brandColumn = new TableColumn<>("Brand");
        brandColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getBrand()));

        TableColumn<Vehicle, String> modelColumn = new TableColumn<>("Model");
        modelColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getModel()));

        TableColumn<Vehicle, String> categoryColumn = new TableColumn<>("Category");
        categoryColumn.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getCategory()));

        TableColumn<Vehicle, Double> priceColumn = new TableColumn<>("Rental Price");
        priceColumn.setCellValueFactory(cellData -> new SimpleDoubleProperty(cellData.getValue().getRentalPrice()).asObject());

        TableColumn<Vehicle, Boolean> availabilityColumn = new TableColumn<>("Available");
        availabilityColumn.setCellValueFactory(cellData -> new SimpleBooleanProperty(cellData.getValue().isAvailabilityStatus()).asObject());

        // Add columns to table
        table.getColumns().addAll(brandColumn, modelColumn, categoryColumn, priceColumn, availabilityColumn);

        VBox vbox = new VBox();
        vbox.setStyle("-fx-background-color: #e6e6e6;");  // Set background color

        vbox.getChildren().addAll(new Label("Brand:"), brandField, new Label("Model:"), modelField,
                new Label("Category:"), categoryField, new Label("Price per Day:"), priceField, addButton, updateButton, deleteButton, table);

        Scene scene = new Scene(vbox, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Vehicle Management");
        primaryStage.show();

        loadVehicleData();
    }

    private void loadVehicleData() {
    }

    // Add a vehicle to the list
    private void addVehicle() {
        // Validate input fields
        if (brandField.getText().isEmpty() || modelField.getText().isEmpty() || categoryField.getText().isEmpty() || priceField.getText().isEmpty()) {
            showError("All Fields Required", "Please fill in all fields to add a vehicle.");
            return;
        }

        // Validate price field to ensure it's a valid number
        double rentalPrice = 0;
        try {
            rentalPrice = Double.parseDouble(priceField.getText());
        } catch (NumberFormatException e) {
            showError("Invalid Price", "Please enter a valid number for the rental price.");
            return;
        }

        // Check if vehicle already exists in the list
        if (vehicleList.stream().anyMatch(v -> v.getBrand().equals(brandField.getText()) && v.getModel().equals(modelField.getText()) && v.getCategory().equals(categoryField.getText()))) {
            showError("Duplicate Vehicle", "A vehicle with the same brand, model, and category already exists.");
        } else {
            Vehicle newVehicle = new Vehicle(
                    generateVehicleId(),  // Generate a new ID
                    brandField.getText(),
                    modelField.getText(),
                    categoryField.getText(),
                    rentalPrice,  // Valid price
                    true  // Assuming new vehicles are available
            );
            vehicleList.add(newVehicle);
            table.getItems().add(newVehicle);
            clearFields();
        }
    }

    // Update the details of an existing vehicle
    private void updateVehicle() {
        // Logic to update vehicle
    }

    // Delete a selected vehicle from the list
    private void deleteVehicle() {
        // Logic to delete selected vehicle
    }

    // Show an error alert
    private void showError(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Show a success alert
    private void showSuccess(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    // Clear the input fields
    private void clearFields() {
        brandField.clear();
        modelField.clear();
        categoryField.clear();
        priceField.clear();
    }

    // Generate a unique ID for each vehicle (for simplicity, this uses current size of the list)
    private int generateVehicleId() {
        return vehicleList.size() + 1;
    }

    // Vehicle class (for simplicity, you can expand this with more fields as necessary)
    public static class Vehicle {
        private final int id;
        private final String brand;
        private final String model;
        private final String category;
        private final double rentalPrice;
        private boolean availabilityStatus;

        public Vehicle(int id, String brand, String model, String category, double rentalPrice, boolean availabilityStatus) {
            this.id = id;
            this.brand = brand;
            this.model = model;
            this.category = category;
            this.rentalPrice = rentalPrice;
            this.availabilityStatus = availabilityStatus;
        }

        public int getId() {
            return id;
        }

        public String getBrand() {
            return brand;
        }

        public String getModel() {
            return model;
        }

        public String getCategory() {
            return category;
        }

        public double getRentalPrice() {
            return rentalPrice;
        }

        public boolean isAvailabilityStatus() {
            return availabilityStatus;
        }
    }
}
