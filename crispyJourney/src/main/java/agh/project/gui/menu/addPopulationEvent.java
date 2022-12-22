package agh.project.gui.menu;

import agh.project.App.App;
import agh.project.gui.population.PopulationManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class addPopulationEvent implements EventHandler<ActionEvent> {

    private App app;

    public addPopulationEvent(App app){
        this.app = app;
    }

    @Override
    public void handle(ActionEvent event) {
        PopulationManager populationManager = new PopulationManager();
        populationManager.start();
    }
}
