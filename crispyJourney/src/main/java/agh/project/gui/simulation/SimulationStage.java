package agh.project.gui.simulation;

import agh.project.App.App;
import agh.project.gui.simulation.events.simulationEndedEvent;
import agh.project.interfaces.StageCreator;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SimulationStage implements StageCreator {

    private static final double STAGE_MIN_WIDTH = 1000;
    private static final double STAGE_MIN_HEIGHT = 645;
    public Stage simulationStageFX;
    private final App app;
    private final SimulationManager simulationManager;
    public SimulationScene simulationScene;

    public SimulationStage(App app, SimulationManager simulationManager) {
        this.app = app;
        this.simulationManager = simulationManager;
        simulationStageFX = new Stage();
        simulationScene = new SimulationScene(simulationManager);
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

        simulationStageFX.setOnCloseRequest(new simulationEndedEvent(app, simulationManager));
    }
}
