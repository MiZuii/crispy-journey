package agh.project.gui.simulation.mapDisplay;

import agh.project.gui.simulation.events.gridElementClickedEvent;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.util.Objects;

public class gridElement extends HBox {

    private String ID;
    private final int maxEnergyLevel;

    public gridElement(int row, int column, int animalMaxEnergyLevel) {
        this.maxEnergyLevel = animalMaxEnergyLevel;

        this.setOnMouseClicked(new gridElementClickedEvent(row, column));

        addProperties();
        addStyle();
    }

    private void addProperties() {
        HBox.setHgrow(this, Priority.ALWAYS);
        VBox.setVgrow(this, Priority.ALWAYS);
    }

    private void addStyle() {
        try {
            this.getStylesheets().add(Objects.requireNonNull(this.getClass().getResource("/styles/MapDisplayGridElementStyle.css")).toExternalForm());
            this.getStyleClass().add("root");
        }
        catch (NullPointerException e) {
            System.out.println("Menu scene style sheet couldn't have been loaded.");
            e.printStackTrace();
        }
    }

    private void setDynamicStyle(int currentEnergy) {
        double delta = 255/(double)maxEnergyLevel;
        int blueCoef = (int) Math.min(255.0, delta*currentEnergy);
        this.setStyle("-fx-background-color: rgb(" + String.valueOf(255 - blueCoef) + ", 30, " + String.valueOf(blueCoef) + ", 1)");
    }

    @Override
    public String toString() {
        return ID;
    }

    public void update(String data) {

        ID = data;

        if (data.equals("G")){
            this.setStyle("-fx-background-color: seagreen;");
        }
        else if (data.isBlank()){
            this.setStyle("-fx-background-color: lightgreen;");
        }
        else {
            String representation = data.split(" ")[0];
            String[] reprParts = representation.split(":");
            setDynamicStyle(Integer.parseInt(reprParts[1]));
        }
    }
}
