
package com.example.demo3;

import javafx.scene.chart.BarChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.BorderPane;

public class ChartViewer extends BorderPane {

    public ChartViewer() {
        createChart();
    }

    private void createChart() {
        // Define the axes
        CategoryAxis xAxis = new CategoryAxis();
        NumberAxis yAxis = new NumberAxis();

        // Set labels for the axes
        xAxis.setLabel("Categories");
        yAxis.setLabel("Value");

        // Create the BarChart
        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
        barChart.setTitle("Sample Bar Chart");

        // Create a series and add data
        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        series1.setName("Data Series 1");
        series1.getData().add(new XYChart.Data<>("A", 50));
        series1.getData().add(new XYChart.Data<>("B", 80));
        series1.getData().add(new XYChart.Data<>("C", 100));
        series1.getData().add(new XYChart.Data<>("D", 60));

        // Add the series to the chart
        barChart.getData().add(series1);

        // Add the chart to the BorderPane
        setCenter(barChart);
    }
}