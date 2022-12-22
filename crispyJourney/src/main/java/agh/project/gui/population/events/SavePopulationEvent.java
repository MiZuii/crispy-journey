package agh.project.gui.population.events;

import agh.project.gui.menu.MenuScene;
import agh.project.gui.population.PopulationScene;
import agh.project.simulation.Population;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;

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
            Alert emptyFields = new Alert(Alert.AlertType.WARNING);
            emptyFields.setContentText("There are blank fields in the form. These must be completed before proceeding.");
            emptyFields.show();
            return;
        }

        if (menuScene.populationNameTaken(populationScene.getPopulationName())) {
            Alert nameTaken = new Alert(Alert.AlertType.WARNING);
            nameTaken.setContentText("Population name is alredy taken.");
            nameTaken.show();
            return;
        }

        menuScene.addNewPopulation(populationScene.getNewPopulationFromInputs());
        populationScene.closePopulationWindow();
    }
}
