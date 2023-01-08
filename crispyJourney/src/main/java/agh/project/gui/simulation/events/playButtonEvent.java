package agh.project.gui.simulation.events;

import agh.project.gui.simulation.SimulationManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class playButtonEvent implements EventHandler<ActionEvent> {

    private final SimulationManager simulationManager;

    public playButtonEvent(SimulationManager simulationManager) {
        this.simulationManager = simulationManager;
    }

    @Override
    public void handle(ActionEvent event) {
        // this block is run when engine thread is running (or waiting between moves)
        // it pauses the engine thread
        if (simulationManager.getSimulationEngine().getState() == Thread.State.RUNNABLE || simulationManager.getSimulationEngine().getState() == Thread.State.TIMED_WAITING) {
            simulationManager.paused = true;
            simulationManager.getSimulationEngine().running.set(false);

        // this block is run when engine thread was stopped while running
        // it unpauses the engine thread
        } else if (simulationManager.getSimulationEngine().getState() == Thread.State.WAITING) {
            simulationManager.paused = false;
            synchronized (simulationManager.getSimulationEngine()) {
                simulationManager.getSimulationEngine().notify();
            }
        }
    }
}
