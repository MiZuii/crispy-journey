package agh.project.gui.menu;

import agh.project.simulation.Population;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class MenuPopulationBox extends HBox {

    private Population population;

    public MenuPopulationBox(Population population) {
        this.population = population;
        createBox();
    }

    private void createBox(){

        // nodes creation
        Label populationNameLabel = new Label(population.name);
        Button deletePopulationButton = new Button("X");
        Button startPopulationSimulationButton = new Button(">");

        // node placeing
        this.getChildren().addAll(populationNameLabel, deletePopulationButton, startPopulationSimulationButton);
    }
}
