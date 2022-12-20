package agh.project.gui.population;

import agh.project.interfaces.SceneCreator;
import agh.project.interfaces.StageCreator;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PopulationStage implements StageCreator {

    private Stage populationStage = new Stage();
    private Scene populationScene;

    public PopulationStage(){
        SceneCreator sceneCreator = new PopulationScene();
        populationScene = sceneCreator.createScene();
    }

    @Override
    public Stage createStage() {

        // attach scene
        populationStage.setScene(populationScene);

        // change stage properties
        configureStage();

        return populationStage;
    }

    private void configureStage(){
        populationStage.setTitle("Population Creator");
        populationStage.initModality(Modality.APPLICATION_MODAL);
    }
}
