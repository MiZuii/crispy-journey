package agh.project.gui.simulation.populationDisplay;

import agh.project.interfaces.Updateable;
import agh.project.simulation.DataStorage;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.util.Objects;

public class PopulationDisplay extends VBox implements Updateable {

    private Label title;
    private HBox titleBox;
    private Label numberOfAnimals;
    private Label numberOfAnimalsData;
    private HBox numberOfAnimalsBox;
    private Label numberOfGrass;
    private Label numberOfGrassData;
    private HBox numberOfGrassBox;
    private Label numberOfEmptyFields;
    private Label numberOfEmptyFieldsData;
    private HBox numberOfEmptyFieldsBox;
    private Label mostFrequentGenotype;
    private Label mostFrequentGenotypeData;
    private HBox mostFrequentGenotypeBox;
    private Label averageEnergyLevel;
    private Label averageEnergyLevelData;
    private HBox averageEnergyLevelBox;
    private Label averageLifeSpan;
    private Label averageLifeSpanData;
    private HBox averageLifeSpanBox;

    public PopulationDisplay() {

        createElements();
        addStyle();
        addProperties();

    }

    private void createElements() {

        title = new Label("Population information");
        titleBox = new HBox(title);

        numberOfAnimals = new Label("Number of animals: ");
        numberOfAnimalsData = new Label("");
        numberOfAnimalsBox = new HBox();
        numberOfAnimalsBox.getChildren().addAll(numberOfAnimals, numberOfAnimalsData);

        numberOfGrass = new Label("Number of grass: ");
        numberOfGrassData = new Label("");
        numberOfGrassBox = new HBox();
        numberOfGrassBox.getChildren().addAll(numberOfGrass, numberOfGrassData);

        numberOfEmptyFields = new Label("Number of empty fields: ");
        numberOfEmptyFieldsData = new Label("");
        numberOfEmptyFieldsBox = new HBox();
        numberOfEmptyFieldsBox.getChildren().addAll(numberOfEmptyFields, numberOfEmptyFieldsData);

        mostFrequentGenotype = new Label("Most frequent genotype: ");
        mostFrequentGenotypeData = new Label("");
        mostFrequentGenotypeBox = new HBox();
        mostFrequentGenotypeBox.getChildren().addAll(mostFrequentGenotype, mostFrequentGenotypeData);

        averageEnergyLevel = new Label("Average energy level: ");
        averageEnergyLevelData = new Label("");
        averageEnergyLevelBox = new HBox();
        averageEnergyLevelBox.getChildren().addAll(averageEnergyLevel, averageEnergyLevelData);

        averageLifeSpan = new Label("Average life span: ");
        averageLifeSpanData = new Label("");
        averageLifeSpanBox = new HBox();
        averageLifeSpanBox.getChildren().addAll(averageLifeSpan, averageLifeSpanData);

        this.getChildren().addAll(titleBox, numberOfAnimalsBox, numberOfGrassBox, numberOfEmptyFieldsBox, mostFrequentGenotypeBox, averageEnergyLevelBox, averageLifeSpanBox);
    }

    private void addStyle() {
        try {
            this.getStylesheets().add(Objects.requireNonNull(this.getClass().getResource("/styles/PopulationDisplayStyle.css")).toExternalForm());
            this.getStyleClass().add("root");
            title.getStyleClass().add("title");
            titleBox.getStyleClass().add("root");
            numberOfAnimals.getStyleClass().add("info");
            numberOfAnimalsData.getStyleClass().add("data");
            numberOfGrass.getStyleClass().add("info");
            numberOfGrassData.getStyleClass().add("data");
            numberOfEmptyFields.getStyleClass().add("info");
            numberOfEmptyFieldsData.getStyleClass().add("data");
            mostFrequentGenotype.getStyleClass().add("info");
            mostFrequentGenotypeData.getStyleClass().add("data");
            averageEnergyLevel.getStyleClass().add("info");
            averageEnergyLevelData.getStyleClass().add("data");
            averageLifeSpan.getStyleClass().add("info");
            averageLifeSpanData.getStyleClass().add("data");
        }
        catch (NullPointerException e) {
            System.out.println("Menu scene style sheet couldn't have been loaded.");
            e.printStackTrace();
        }
    }

    private void addProperties() {
        HBox.setHgrow(titleBox, Priority.ALWAYS);
    }

    @Override
    public void update(DataStorage data) {
        // Nie wiem czy tyle wystarczy
        this.numberOfAnimalsData.setText(Integer.toString(data.getPopulationSize()));
        this.numberOfGrassData.setText(Integer.toString(data.getGrassPopulation()));
        this.numberOfEmptyFieldsData.setText(Integer.toString(data.getFreeSquares()));
        this.mostFrequentGenotypeData.setText(data.getPopularGenom().toString());
        this.averageEnergyLevelData.setText(Double.toString(data.getAverageEnergy()));
        this.averageLifeSpanData.setText(Double.toString(data.getAverageLifeLength()));
    }
}