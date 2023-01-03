package agh.project.gui.simulation.graphDisplay;

import agh.project.interfaces.Updateable;
import agh.project.simulation.DataStorage;
import javafx.geometry.Pos;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;

import java.util.ArrayList;
import java.util.Objects;

public class GraphDisplay extends HBox implements Updateable {

    private ArrayList<Integer> graphData;
    private int days = 0;
    private LineChart<Number, Number> lineChart;
    private XYChart.Series<Number, Number> series;
    private NumberAxis xAxis;
    private NumberAxis yAxis;

    public GraphDisplay() {

        graphData = new ArrayList<>();

        createElements();
        addProperties();
        addStyles();
    }

    private void createElements() {
        // Define the x-axis and y-axis
        xAxis = new NumberAxis();
        yAxis = new NumberAxis();

        // Create the line chart
        lineChart = new LineChart<>(xAxis, yAxis);

        // Add data to the chart
        series = new XYChart.Series<>();
        ArrayList<XYChart.Data<Number, Number>> data = fillGraph();
        series.getData().addAll(data);
        lineChart.getData().add(series);
        this.getChildren().add(lineChart);
    }

    private void addStyles() {
        try {
            this.getStylesheets().add(Objects.requireNonNull(this.getClass().getResource("/styles/GraphDisplayStyle.css")).toExternalForm());
            this.getStyleClass().add("root");
        }
        catch (NullPointerException e) {
            System.out.println("Menu scene style sheet couldn't have been loaded.");
            e.printStackTrace();
        }
    }

    private void addProperties(){
        // root
        this.alignmentProperty().set(Pos.CENTER);

        // line chart
        lineChart.setCreateSymbols(false);
        lineChart.setLegendVisible(false);
        lineChart.setTitle("Population size chart");
        HBox.setHgrow(lineChart, Priority.ALWAYS);
    }

    private ArrayList<XYChart.Data<Number, Number>> fillGraph() {
        ArrayList<XYChart.Data<Number, Number>> ans = new ArrayList<>();

        for (int i=0; i<graphData.size(); i++) {
            ans.add(new XYChart.Data<>(i, graphData.get(i)));
        }
        return ans;
    }

    @Override
    public void update(DataStorage dataStorage) {
        days += 1;

        // refresh graph
        series.getData().add(new XYChart.Data<>(days, dataStorage.getPopulationSize()));
    }

    public void clean() {
        graphData.clear();
    }
}
