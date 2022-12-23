package agh.project.gui.menu.events;

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

    private final MenuScene menuScene;

    public addPopulationEvent(MenuScene menuScene){
        this.menuScene = menuScene;
    }

    @Override
    public void handle(ActionEvent event) {
        PopulationManager populationManager = new PopulationManager(menuScene);
        populationManager.start();
    }
}
