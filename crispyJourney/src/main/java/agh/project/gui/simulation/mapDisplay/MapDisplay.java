package agh.project.gui.simulation.mapDisplay;

import agh.project.gui.simulation.SimulationManager;
import agh.project.gui.simulation.SimulationScene;
import agh.project.interfaces.Updateable;
import agh.project.simulation.DataStorage;
import agh.project.simulation.Population;
import javafx.beans.binding.Bindings;
import javafx.geometry.Pos;
import javafx.scene.layout.*;

import java.util.ArrayList;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class MapDisplay extends VBox implements Updateable {

    private AtomicInteger selectedAnimalID = new AtomicInteger(-1);
    private final VBox parentBox;
    private final Population population;
    private GridPane grid;
    private gridElement[][] elementsMap;
    private ArrayList<RowConstraints> rowConstraints = new ArrayList<>();
    private ArrayList<ColumnConstraints> columnConstraints = new ArrayList<>();
    private final SimulationManager simulationManager;

    public MapDisplay(Population population, VBox parentBox, SimulationManager simulationManager) {
        this.simulationManager = simulationManager;
        this.parentBox = parentBox;
        this.population = population;
        elementsMap = new gridElement[population.mapHeight][population.mapWidth];

        createElements();
        addProperties();
        addStyles();
    }

    private void createElements() {
        grid = new GridPane();

        for (int row=0; row < population.mapHeight; row++) {
            for (int column=0; column < population.mapWidth; column ++) {
                gridElement tmp = new gridElement(row, column, population.animalStartEnergy, this, simulationManager);
                grid.add(tmp, column, row);
                GridPane.setFillHeight(tmp, true);
                GridPane.setFillWidth(tmp, true);
                elementsMap[row][column] = tmp;
            }
        }

        for (int row=0; row < population.mapHeight; row++) {
            RowConstraints tmp = new RowConstraints();
            tmp.setVgrow(Priority.ALWAYS);
            grid.getRowConstraints().add(tmp);
            rowConstraints.add(tmp);
        }

        for (int column=0; column < population.mapWidth; column ++) {
            ColumnConstraints tmp = new ColumnConstraints();
            tmp.setHgrow(Priority.ALWAYS);
            grid.getColumnConstraints().add(tmp);
            columnConstraints.add(tmp);
        }

        this.getChildren().add(grid);
    }

    private void addStyles() {
        try {
            this.getStylesheets().add(Objects.requireNonNull(this.getClass().getResource("/styles/MapDisplayStyle.css")).toExternalForm());
            this.getStyleClass().add("root");
            grid.getStyleClass().add("grid");
        }
        catch (NullPointerException e) {
            System.out.println("Map display style sheet couldn't have been loaded.");
            e.printStackTrace();
        }
    }
    private void addProperties() {
        // root
        HBox.setHgrow(this, Priority.ALWAYS);
        VBox.setVgrow(this, Priority.ALWAYS);
        this.setAlignment(Pos.CENTER);

        // column and row constraints
        for (RowConstraints rc : rowConstraints) {
            rc.setMaxHeight(Double.MAX_VALUE);
            rc.setFillHeight(true);
        }

        for (ColumnConstraints cc : columnConstraints) {
            cc.setMaxWidth(Double.MAX_VALUE);
            cc.setFillWidth(true);
        }

        // grid
        grid.setAlignment(Pos.CENTER);
        grid.setMaxHeight(Double.MAX_VALUE);
        grid.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(grid, Priority.ALWAYS);
        VBox.setVgrow(grid, Priority.ALWAYS);
    }

    @Override
    public void update(DataStorage data) {

        ArrayList<ArrayList<String>> map = data.getMap();

        for (int row=0; row < population.mapHeight; row++) {
            for (int column=0; column < population.mapWidth; column ++) {
                String tmp = map.get(row).get(column);

                if (!tmp.equals(elementsMap[row][column].toString()) || simulationManager.paused) {
                    elementsMap[row][column].update(tmp);
                }
            }
        }
    }

    public void setSelectedAnimalID(int row, int column) {
        selectedAnimalID.set(elementsMap[row][column].getFirstAnimalID());
    }

    public int getAnimalID() {
        return selectedAnimalID.get();
    }
}
