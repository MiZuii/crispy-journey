package agh.project.gui.simulation.mapDisplay;

import agh.project.interfaces.Updateable;
import agh.project.simulation.Population;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;

import java.util.Objects;
import java.util.concurrent.atomic.AtomicInteger;

public class MapDisplay extends VBox implements Updateable {

    private AtomicInteger selectedAnimalID = new AtomicInteger(-1);
    private final Population population;
    private GridPane grid;
    private double rowSize = 10;
    private double columnSize = 10;

    public MapDisplay(Population population) {
        this.population = population;
    }

    private void createElements() {
        grid = new GridPane();

        for (int row=0; row < population.mapHeight; row++) {
            for (int column=0; column < population.mapWidth; column ++) {
                grid.add(new gridElement(), column, row);
            }
        }

        for (int row=0; row < population.mapHeight; row++) {
            grid.getRowConstraints().add(new RowConstraints(rowSize));
        }

        for (int column=0; column < population.mapWidth; column ++) {
            grid.getColumnConstraints().add(new ColumnConstraints(columnSize));
        }
    }

    private void addStyles() {
        try {
            this.getStylesheets().add(Objects.requireNonNull(this.getClass().getResource("/styles/MapDisplayStyle.css")).toExternalForm());
            this.getStyleClass().add("root");
        }
        catch (NullPointerException e) {
            System.out.println("Map display style sheet couldn't have been loaded.");
            e.printStackTrace();
        }
    }

    private void addProperties() {

    }

    @Override
    public void update() {

    }

    public int getAnimalID() {
        return selectedAnimalID.get();
    }
}
