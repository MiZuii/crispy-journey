package agh.project.gui.menu.events;

import agh.project.App.App;
import agh.project.gui.menu.MenuPopulationBox;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;

public class removePopulationEvent implements EventHandler<ActionEvent> {

    private App app;
    private MenuPopulationBox populationBox;
    private Pane parentNode;

    public removePopulationEvent(MenuPopulationBox populationBoxToRemove, Pane parent, App app) {
        this.app = app;
        this.parentNode = parent;
        this.populationBox = populationBoxToRemove;
    }

    @Override
    public void handle(ActionEvent event) {
        app.populationsHolder.removePopulation(populationBox.getPopulation());
        parentNode.getChildren().remove(populationBox);
    }
}
