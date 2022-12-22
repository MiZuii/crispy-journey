package agh.project.gui.population;


import agh.project.gui.menu.MenuScene;
import agh.project.interfaces.StageCreator;
import agh.project.interfaces.WindowManager;
import javafx.stage.Stage;

public class PopulationManager implements WindowManager {

    private PopulationStage populationStage;

    public PopulationManager(MenuScene menuScene){
        populationStage = new PopulationStage(menuScene);
    }

    @Override
    public void start() {
        Stage populationStageFX = populationStage.createStage();
        populationStageFX.show();
    }
}
