package com.example;


import com.example.demo3.VehicleManagementApp;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.List;

public class Dashboard {

    private final Stage primaryStage;
    private final List<VehicleManagementApp.Vehicle> vehicles;
    private final List<VehicleManagementApp.Booking> bookings;
    private final List<VehicleManagementApp.Customer> customers;

    public Dashboard(Stage primaryStage,
                     List<VehicleManagementApp.Vehicle> vehicles,
                     List<VehicleManagementApp.Booking> bookings,
                     List<VehicleManagementApp.Customer> customers) {
        this.primaryStage = primaryStage;
        this.vehicles = vehicles;
        this.bookings = bookings;
        this.customers = customers;
    }

    public void showDashboard() {
        VBox root = new VBox(20);
        root.setPadding(new Insets(20));
        root.setBackground(new Background(new BackgroundFill(Color.LIGHTBLUE, CornerRadii.EMPTY, Insets.EMPTY)));

        Label title = new Label("Dashboard Overview");
        title.setStyle("-fx-font-size: 20px; -fx-font-weight: bold;");

        Label vehicleCount = new Label("Total Vehicles: " + vehicles.size());
        Label bookingCount = new Label("Total Bookings: " + bookings.size());
        Label customerCount = new Label("Total Customers: " + customers.size());

        vehicleCount.setStyle("-fx-font-size: 16px;");
        bookingCount.setStyle("-fx-font-size: 16px;");
        customerCount.setStyle("-fx-font-size: 16px;");

        // Pie Chart for visual summary
        PieChart pieChart = new PieChart();
        pieChart.setTitle("Data Summary");

        PieChart.Data vehicleData = new PieChart.Data("Vehicles", vehicles.size());
        PieChart.Data bookingData = new PieChart.Data("Bookings", bookings.size());
        PieChart.Data customerData = new PieChart.Data("Customers", customers.size());

        pieChart.getData().addAll(vehicleData, bookingData, customerData);

        root.getChildren().addAll(title, vehicleCount, bookingCount, customerCount, pieChart);

        Scene scene = new Scene(root, 500, 400);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Dashboard");
        primaryStage.show();
    }
}
