package agh.project.gui.menu;

import agh.project.interfaces.SceneCreator;
import agh.project.interfaces.StageCreator;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MenuStage implements StageCreator {

    private Stage menuStage = new Stage();
    private Scene menuScene;

    public MenuStage(){
        SceneCreator sceneCreator = new MenuScene();
        menuScene = sceneCreator.createScene();
    }

    @Override
    public Stage createStage() {

        // attach scene
        menuStage.setScene(menuScene);

        // change stage properties
        configureStage();

        return menuStage;
    }

    private void configureStage(){
        menuStage.setTitle("Menu");
        menuStage.initModality(Modality.NONE);
    }
}
