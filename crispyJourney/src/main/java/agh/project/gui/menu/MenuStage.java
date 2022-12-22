package agh.project.gui.menu;

import agh.project.App.App;
import agh.project.interfaces.SceneCreator;
import agh.project.interfaces.StageCreator;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MenuStage implements StageCreator {

    private App app;
    private Stage menuStageFX;
    private Scene menuSceneFX;
    public MenuScene menuScene;

    public MenuStage(App app){
        this.app = app;
        menuStageFX = new Stage();
        menuScene = new MenuScene(app);
    }

    @Override
    public Stage createStage() {

        // attach scene
        menuSceneFX = menuScene.createScene();
        menuStageFX.setScene(menuSceneFX);

        // change stage properties
        configureStage();

        return menuStageFX;
    }

    private void configureStage(){
        menuStageFX.setTitle("Menu");
        menuStageFX.initModality(Modality.NONE);
    }
}
