package agh.project.gui.menu;

import agh.project.interfaces.SceneCreator;
import agh.project.interfaces.StageCreator;
import agh.project.interfaces.WindowManager;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MenuManager implements WindowManager {

    private Stage menuStage;

    public MenuManager(){
        StageCreator stageCreator = new MenuStage();
        menuStage = stageCreator.createStage();
    }

    @Override
    public void start() {
        menuStage.show();
    }
}
