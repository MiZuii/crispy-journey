package agh.project.gui.menu.events;

import agh.project.App.App;
import agh.project.gui.menu.MenuPopulationBox;
import agh.project.gui.menu.events.alerts.defaultDeletionAlert;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.layout.Pane;

public class removePopulationEvent implements EventHandler<ActionEvent> {

    private final App app;
    private final MenuPopulationBox populationBox;
    private final Pane parentNode;

    public removePopulationEvent(MenuPopulationBox populationBoxToRemove, Pane parent, App app) {
        this.app = app;
        this.parentNode = parent;
        this.populationBox = populationBoxToRemove;
    }

    @Override
    public void handle(ActionEvent event) {
        if (populationBox.getPopulation().isDefault) {
            new defaultDeletionAlert().show();
            return;
        }

        // if population is saved additionally delete its file and remove it from saved populations list
        if (app.populationsHolder.getSavedPopulationsNames().contains(populationBox.getPopulation().name)) {
            app.populationsHolder.removeSavedPopulation(populationBox.getPopulation());
        }

        app.populationsHolder.removePopulation(populationBox.getPopulation());
        parentNode.getChildren().remove(populationBox);
    }
}
