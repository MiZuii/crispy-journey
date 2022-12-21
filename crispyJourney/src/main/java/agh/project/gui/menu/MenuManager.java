package agh.project.gui.menu;

import agh.project.interfaces.StageCreator;
import agh.project.interfaces.WindowManager;
import javafx.stage.Stage;

public class MenuManager implements WindowManager {

    private Stage menuStage;
    private PopulationsHolder populationsHolder;

    public MenuManager(){
        StageCreator stageCreator = new MenuStage();
        menuStage = stageCreator.createStage();
        populationsHolder = new PopulationsHolder();
    }

    @Override
    public void start() {
        menuStage.show();
    }
}
