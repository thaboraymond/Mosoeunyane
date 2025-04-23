package com.example.demo3;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class VehicleRentalApp extends Application {

    @Override
    public void start(Stage stage) {
        // Create ListView to display vehicles
        ListView<String> vehicleList = new ListView<>();

        // Load vehicle data from database
        try {
            loadVehicleData(vehicleList);
        } catch (SQLException e) {
            System.err.println("Error loading vehicle data: " + e.getMessage());
        }

        // Set up the layout
        VBox layout = new VBox(vehicleList);
        Scene scene = new Scene(layout, 600, 400);

        // Set up the stage
        stage.setTitle("Vehicle Rental");
        stage.setScene(scene);
        stage.show();
    }

    private void loadVehicleData(ListView<String> vehicleList) throws SQLException {
        String query = "SELECT * FROM vehicles";

        try (Connection connection = DatabaseConnection.connect();
             PreparedStatement stmt = connection.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String category = rs.getString("category");
                String make = rs.getString("make");
                String model = rs.getString("model");
                double price = rs.getDouble("price");
                boolean availability = rs.getBoolean("availability");

                int priceInCoins = (int) (price * 100);

                VehicleManagement.Vehicle vehicle = new VehicleManagement.Vehicle(id, category, make, model, price, availability);

                vehicleList.getItems().add(
                        category + " - " + make + " " + model +
                                " | Price: " + priceInCoins + " coins | Available: " + (availability ? "Yes" : "No")
                );
            }
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
