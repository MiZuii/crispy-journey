package agh.project.gui.menu;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;

public class removePopulationEvent implements EventHandler<ActionEvent> {

    private MenuPopulationBox populationBox;
    private Pane parentNode;

    public removePopulationEvent(MenuPopulationBox populationBoxToRemove, Pane parent) {
        this.parentNode = parent;
        this.populationBox = populationBoxToRemove;
    }

    @Override
    public void handle(ActionEvent event) {
        parentNode.getChildren().remove(populationBox);
    }
}
