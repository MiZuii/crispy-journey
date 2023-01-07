package agh.project.gui.simulation.populationDisplay;

import agh.project.interfaces.Updateable;
import agh.project.simulation.DataStorage;
import agh.project.simulation.Population;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.util.Objects;

public class PopulationDisplay extends VBox implements Updateable {

    private Label title;
    private HBox titleBox;
    private Label title2;
    private HBox title2Box;
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
    private Label simulationDay;
    private Label simulationDayData;
    private HBox simulationDayBox;
    private final Population population;
    private VBox populationInformationBox;
    private Label mapHeight;
    private Label mapWidth;
    private Label grassEnergyProfit;
    private Label minEnergyCopulation;
    private Label animalStartEnergy;
    private Label dailyEnergyLost;
    private Label animalStartingNumber;
    private Label grassPerDay;
    private Label refreshment;
    private Label energyPerCopulation;
    private Label maxMutationNumber;
    private Label genomLength;
    private Label moveFlag;
    private Label mutationFlag;
    private Label mapFlag;
    private Label grassFlag;

    public PopulationDisplay(Population population) {
        this.population = population;

        createElements();
        addStyle();
        addProperties();

    }

    private void createElements() {

        title = new Label("Population statistics");
        titleBox = new HBox(title);

        title2 = new Label("Population information");
        title2Box = new HBox(title2);

        mapHeight = new Label("Map Height: " + String.valueOf(population.mapHeight));
        mapWidth = new Label("Map Width: " + String.valueOf(population.mapWidth));
        grassEnergyProfit = new Label("Grass energy profit: " + String.valueOf(population.grassEnergyProfit));
        minEnergyCopulation = new Label("Min energy to copulate: " + String.valueOf(population.minEnergyCopulation));
        animalStartEnergy = new Label("Starting energy: " + String.valueOf(population.animalStartEnergy));
        grassPerDay = new Label("Grass per day: " + String.valueOf(population.grassPerDay));
        refreshment = new Label("Refresh rate (ms): " + String.valueOf(population.refreshment));
        energyPerCopulation = new Label("Energy loss on copulation: " + String.valueOf(population.energyPerCopulation));
        maxMutationNumber = new Label("Max mutation in children: " + String.valueOf(population.maxMutationNumber));
        genomLength = new Label("Genom length: " + String.valueOf(population.genomLength));
        mapFlag = new Label(population.mapFlag ? "Map variant: Hell" : "Map variant: Round");
        mutationFlag = new Label("Child inherits genes randomly");
        moveFlag = new Label(population.moveFlag ? "Random gene activation" : "Ordered gene activation");
        grassFlag = new Label("Grass mostly on equator");
        populationInformationBox = new VBox();
        populationInformationBox.getChildren().addAll(mapHeight,
                mapWidth,
                grassEnergyProfit,
                minEnergyCopulation,
                animalStartEnergy,
                grassPerDay,
                refreshment,
                energyPerCopulation,
                maxMutationNumber,
                genomLength,
                moveFlag,
                mutationFlag,
                mapFlag,
                grassFlag);

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

        simulationDay = new Label("Day of simulation: ");
        simulationDayData = new Label("");
        simulationDayBox = new HBox();
        simulationDayBox.getChildren().addAll(simulationDay, simulationDayData);

        this.getChildren().addAll(titleBox,
                simulationDayBox,
                numberOfAnimalsBox,
                numberOfGrassBox,
                numberOfEmptyFieldsBox,
                mostFrequentGenotypeBox,
                averageEnergyLevelBox,
                averageLifeSpanBox,
                title2Box,
                populationInformationBox);
    }

    private void addStyle() {
        try {
            this.getStylesheets().add(Objects.requireNonNull(this.getClass().getResource("/styles/PopulationDisplayStyle.css")).toExternalForm());
            this.getStyleClass().add("root");
            title.getStyleClass().add("title");
            titleBox.getStyleClass().add("root");
            title2.getStyleClass().add("title");
            title2Box.getStyleClass().add("root2");
            mapHeight.getStyleClass().add("info");
            mapWidth.getStyleClass().add("info");
            grassEnergyProfit.getStyleClass().add("info");
            minEnergyCopulation.getStyleClass().add("info");
            animalStartEnergy.getStyleClass().add("info");
            grassPerDay.getStyleClass().add("info");
            refreshment.getStyleClass().add("info");
            energyPerCopulation.getStyleClass().add("info");
            maxMutationNumber.getStyleClass().add("info");
            genomLength.getStyleClass().add("info");
            moveFlag.getStyleClass().add("info");
            mutationFlag.getStyleClass().add("info");
            mapFlag.getStyleClass().add("info");
            grassFlag.getStyleClass().add("info");
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
            simulationDay.getStyleClass().add("info");
            simulationDayData.getStyleClass().add("data");
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
        this.simulationDayData.setText(Integer.toString(data.getSimulationDay()));
        this.numberOfAnimalsData.setText(Integer.toString(data.getPopulationSize()));
        this.numberOfGrassData.setText(Integer.toString(data.getGrassPopulation()));
        this.numberOfEmptyFieldsData.setText(Integer.toString(data.getFreeSquares()));
        this.mostFrequentGenotypeData.setText(data.getPopularGenom().toString());
        this.averageEnergyLevelData.setText(Integer.toString((int) data.getAverageEnergy()));
        this.averageLifeSpanData.setText(Integer.toString((int) data.getAverageLifeLength()));
    }
}