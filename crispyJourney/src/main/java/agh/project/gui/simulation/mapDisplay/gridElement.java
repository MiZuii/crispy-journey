package agh.project.gui.simulation.mapDisplay;

import agh.project.gui.simulation.SimulationManager;
import agh.project.gui.simulation.events.gridElementClickedEvent;
import javafx.geometry.Insets;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.util.Objects;

public class gridElement extends HBox {

    private String ID;
    private final int maxEnergyLevel;
    private final MapDisplay mapDisplay;
    private final SimulationManager simulationManager;

    public gridElement(int row, int column, int animalMaxEnergyLevel, MapDisplay mapDisplay, SimulationManager simulationManager) {
        this.simulationManager = simulationManager;
        this.maxEnergyLevel = animalMaxEnergyLevel;
        this.mapDisplay = mapDisplay;

        this.setOnMouseClicked(new gridElementClickedEvent(row, column, mapDisplay, simulationManager));

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

    private void setDynamicStyle(int currentEnergy, int lightUp) {
        double delta = 255/(double)maxEnergyLevel;
        int blueCoef = (int) Math.min(255.0, delta*currentEnergy);
        if (lightUp==1){
//            this.setStyle("-fx-background-color: linear-gradient(from 50% 70% to 50% 100%, rgb(" + String.valueOf(255 - blueCoef) + ", 50 ," + String.valueOf(blueCoef) + ", 1), rgb(255, 255, 255, 0.6));" +
//                    "-fx-border-color: white;");
            this.setStyle("-fx-background-color: radial-gradient(center 50% 50%, radius 130% , rgb(" + String.valueOf(255 - blueCoef) + ", 50 ," + String.valueOf(blueCoef) + ", 1), rgb(255, 255, 255, 0.4));" +
                    "-fx-border-color: white;");
        }
        else {
            this.setStyle("-fx-background-color: rgb(" + String.valueOf(255 - blueCoef) + ", 30 ," + String.valueOf(blueCoef) + ", 1);" +
                    "-fx-border-color: black;");
        }
    }

    public int getFirstAnimalID() {
        String animalID = ID.split(" ")[0].split(":")[0];
        if (animalID.isBlank() || animalID.equals("G")) {
            return -1;
        }
        return Integer.parseInt(animalID);
    }

    @Override
    public String toString() {
        return ID;
    }

    public void update(String data) {

        ID = data;

        if (data.equals("G")){
            this.setStyle("-fx-background-color: seagreen;" +
                    "-fx-border-color: black;");
        }
        else if (data.isBlank()){
            this.setStyle("-fx-background-color: lightgreen;" +
                    "-fx-border-color: black;");
        }
        else {
            String representation = data.split(" ")[0];
            String[] reprParts = representation.split(":");
            setDynamicStyle(Integer.parseInt(reprParts[1]), (simulationManager.paused ? Integer.parseInt(reprParts[2]) : 0));
        }
    }
}
