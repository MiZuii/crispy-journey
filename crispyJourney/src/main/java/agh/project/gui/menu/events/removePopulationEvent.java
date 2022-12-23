package agh.project.gui.menu.events;

import agh.project.App.App;
import agh.project.gui.menu.MenuPopulationBox;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
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
        if (populationBox.getPopulation().isDefault) {
            Alert cantDelete = new Alert(Alert.AlertType.INFORMATION);
            cantDelete.setContentText("This is a default population therefor it cannot be deleted.");
            cantDelete.show();
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
