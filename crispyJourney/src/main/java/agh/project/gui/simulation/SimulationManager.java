package agh.project.gui.simulation;

import agh.project.gui.menu.MenuStage;
import agh.project.interfaces.StageCreator;
import agh.project.interfaces.WindowManager;
import javafx.stage.Stage;

public class SimulationManager implements WindowManager {

    Stage simulationStage;

    public SimulationManager(){
        StageCreator stageCreator = new SimulationStage();
        simulationStage = stageCreator.createStage();
    }

    @Override
    public void start() {
        simulationStage.show();
    }
}
