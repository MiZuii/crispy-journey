package agh.project.gui.menu.events;

import agh.project.App.App;
import agh.project.gui.menu.MenuScene;
import agh.project.gui.population.PopulationManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 *
 * addPopulationEvent is and EventHandler responsible
 * for implementing button actions for opening population creation window
 *
 * @author Piotr
 *
 */
public class addPopulationEvent implements EventHandler<ActionEvent> {

    private App app;
    private MenuScene menuScene;

    public addPopulationEvent(App app, MenuScene menuScene){
        this.app = app;
        this.menuScene = menuScene;
    }

    @Override
    public void handle(ActionEvent event) {
        PopulationManager populationManager = new PopulationManager(menuScene);
        populationManager.start();
    }
}
