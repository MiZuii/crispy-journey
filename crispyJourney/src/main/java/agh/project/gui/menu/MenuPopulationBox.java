package agh.project.gui.menu;

import agh.project.simulation.Population;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class MenuPopulationBox extends HBox {

    public MenuPopulationBox(Population population) {
        this.getChildren().add(populationName(population));
    }

    private Label populationName(Population population){
        return new Label(population.name);
    }
}
