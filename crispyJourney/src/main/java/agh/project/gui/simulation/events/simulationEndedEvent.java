package agh.project.gui.simulation.events;

import agh.project.App.App;
import agh.project.gui.simulation.SimulationManager;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;

public class simulationEndedEvent implements EventHandler<WindowEvent> {

    private final App app;
    private final SimulationManager simulationManager;

    public simulationEndedEvent(App app, SimulationManager simulationManager) {
        this.app = app;
        this.simulationManager = simulationManager;
    }

    @Override
    public void handle(WindowEvent event) {
        app.appDisassembler.removeSimulationFromRunning(simulationManager);
    }
}
