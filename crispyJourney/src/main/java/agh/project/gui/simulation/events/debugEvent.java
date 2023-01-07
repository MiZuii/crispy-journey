package agh.project.gui.simulation.events;

import agh.project.gui.simulation.SimulationManager;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

public class debugEvent implements EventHandler<ActionEvent> {

    private final SimulationManager simulationManager;

    public debugEvent(SimulationManager simulationManager) {
        this.simulationManager = simulationManager;
    }

    @Override
    public void handle(ActionEvent event) {
        for (Thread t : Thread.getAllStackTraces().keySet()) {
            if (t.getName().equals("SimulationEngine") || t.getName().equals("refresher")) {
                System.out.println("Thread: " + t.getName());
            }
        }
    }
}
