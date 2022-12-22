package agh.project.gui.population;

import agh.project.gui.menu.MenuScene;
import agh.project.interfaces.SceneCreator;
import agh.project.interfaces.StageCreator;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PopulationStage implements StageCreator {

    private final Stage populationStage = new Stage();
    private final PopulationScene populationScene;

    public PopulationStage(MenuScene menuScene){
        populationScene = new PopulationScene(menuScene);
    }

    @Override
    public Stage createStage() {

        // attach scene
        populationStage.setScene(populationScene.createScene());

        // change stage properties
        configureStage();

        return populationStage;
    }

    private void configureStage(){
        populationStage.setTitle("Population Creator");
        populationStage.initModality(Modality.APPLICATION_MODAL);
    }
}
