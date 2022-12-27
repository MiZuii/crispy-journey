package agh.project.gui.menu.events;

import agh.project.App.App;
import agh.project.gui.menu.MenuPopulationBox;
import agh.project.gui.menu.events.alerts.duplicateSaveAlert;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 *
 * save population event is responsible for save
 * button functionality of populations in menu
 *
 * @author Piotr
 *
 */
public class savePopulationEvent implements EventHandler<ActionEvent> {

    private final MenuPopulationBox populationBox;
    private final App app;

    public savePopulationEvent(MenuPopulationBox populationBox, App app) {
        this.app = app;
        this.populationBox = populationBox;
    }

    @Override
    public void handle(ActionEvent event) {
        if (app.populationsHolder.getSavedPopulationsNames().contains(populationBox.getPopulation().name)) {
            new duplicateSaveAlert().show();
            return;
        }

        populationBox.changeSaveIcon();
        app.populationsHolder.addSavedPopulation(populationBox.getPopulation());
        app.populationsHolder.createPopulationFile(populationBox.getPopulation());
    }
}
