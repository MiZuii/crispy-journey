package agh.project.gui.simulation;

import agh.project.interfaces.SceneCreator;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

public class SimulationScene implements SceneCreator {

    Scene simulationScene;

    public SimulationScene() {
    }

    @Override
    public Scene createScene() {
        VBox root = new VBox();
        simulationScene = new Scene(root, 1000, 600);
        return simulationScene;
    }
}
