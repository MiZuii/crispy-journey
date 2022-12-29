package agh.project.gui.simulation;

import agh.project.interfaces.StageCreator;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SimulationStage implements StageCreator {

    private static final double STAGE_MIN_WIDTH = 500;
    private static final double STAGE_MIN_HEIGHT = 500;
    public Stage simulationStageFX;
    private final SimulationScene simulationScene;

    public SimulationStage() {
        simulationStageFX = new Stage();
        simulationScene = new SimulationScene();
    }

    @Override
    public Stage createStage() {

        // attach scene
        Scene simulationSceneFX = simulationScene.createScene();
        simulationStageFX.setScene(simulationSceneFX);

        // change stage properties
        configureStage();

        return simulationStageFX;
    }

    private void configureStage(){
        simulationStageFX.setTitle("Simulation");
        simulationStageFX.initModality(Modality.NONE);
        simulationStageFX.setMinWidth(STAGE_MIN_WIDTH);
        simulationStageFX.setMinHeight(STAGE_MIN_HEIGHT);
    }
}
