package agh.project.gui.population;


import agh.project.interfaces.StageCreator;
import agh.project.interfaces.WindowManager;
import javafx.stage.Stage;

public class PopulationManager implements WindowManager {

    private Stage populationStage;

    public PopulationManager(){
        StageCreator stageCreator = new PopulationStage();
        populationStage = stageCreator.createStage();
    }

    @Override
    public void start() {
        populationStage.show();
    }
}
