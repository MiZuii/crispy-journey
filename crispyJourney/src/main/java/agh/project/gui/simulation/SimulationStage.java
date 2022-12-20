package agh.project.gui.simulation;

import agh.project.interfaces.SceneCreator;
import agh.project.interfaces.StageCreator;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SimulationStage implements StageCreator {

    private Stage simulationStage = new Stage();
    private Scene simulationScene;

    public SimulationStage() {
        SceneCreator sceneCreator = new SimulationScene();
        simulationScene = sceneCreator.createScene();
    }

    @Override
    public Stage createStage() {

        // attach scene
        simulationStage.setScene(simulationScene);

        // change stage properties
        configureStage();

        return simulationStage;
    }

    private void configureStage(){
        simulationStage.setTitle("Simulation");
        simulationStage.initModality(Modality.NONE);
    }
}
