package agh.project.gui.menu;

import agh.project.simulation.Population;
import javafx.scene.control.Button;
import javafx.scene.control.Control;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 *
 * MenuPopulationBox is a class extenging Hbox. It is
 * a class that contains population and creates Hbox
 * which displays information of the populations and
 * creates buttons responsible for deleteing and starting
 * simulation.
 *
 * @author Piotr
 *
 */
public class MenuPopulationBox extends HBox {

    private Population population;
    private Pane parentNode;

    public MenuPopulationBox(Population population, Pane parent) {
        this.parentNode = parent;
        this.population = population;
        createBox();
    }

    /**
     * Creates javafx nodes and adds them to itself.
     */
    private void createBox(){

        // nodes creation
        Label populationNameLabel = new Label(population.name);
        Button deletePopulationButton = new Button("X");
        deletePopulationButton.setOnAction(new removePopulationEvent(this, parentNode));
        Button saveButton = new Button("S");
        Button startPopulationSimulationButton = new Button(">");

        // node placeing
        this.getChildren().addAll(populationNameLabel, saveButton, deletePopulationButton, startPopulationSimulationButton);
    }
}
