package agh.project.gui.simulation;

import agh.project.gui.simulation.SimulationScene;
import javafx.application.Platform;

public class SimulationRefresher extends Thread {

    private final SimulationScene simulationScene;
    private final int FPS;

    public SimulationRefresher(int FPS, SimulationScene simulationScene) {
        this.FPS = FPS;
        this.simulationScene = simulationScene;
    }

    @Override
    public void run() {
        while (true) {
            try {
                sleep(1000/FPS);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    simulationScene.refresh();
                }
            });
        }
    }
}
