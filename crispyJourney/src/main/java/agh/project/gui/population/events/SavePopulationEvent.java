package agh.project.gui.population.events;

import agh.project.gui.menu.MenuScene;
import agh.project.gui.population.PopulationScene;
import agh.project.gui.population.events.alerts.duplicateNameAlert;
import agh.project.gui.population.events.alerts.unfilledPropertiesAlert;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class SavePopulationEvent implements EventHandler<ActionEvent> {

    private final MenuScene menuScene;
    private final PopulationScene populationScene;

    public SavePopulationEvent(MenuScene menuScene, PopulationScene populationScene) {
        this.menuScene = menuScene;
        this.populationScene = populationScene;
    }

    @Override
    public void handle(ActionEvent event) {
        if(populationScene.anyEmptyInputs()) {
            new unfilledPropertiesAlert().show();
            return;
        }

        if (menuScene.populationNameTaken(populationScene.getPopulationName())) {
            new duplicateNameAlert().show();
            return;
        }

        menuScene.addNewPopulation(populationScene.getNewPopulationFromInputs());
        populationScene.closePopulationWindow();
    }
}
