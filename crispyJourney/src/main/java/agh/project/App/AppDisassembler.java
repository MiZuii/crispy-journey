package agh.project.App;

import agh.project.gui.simulation.SimulationManager;
import agh.project.gui.simulation.SimulationRefresher;
import javafx.event.EventHandler;
import javafx.stage.WindowEvent;

import java.util.ArrayList;

/**
 * this class stores currently running simulation managers
 * on app level so if the menu windows gets closed all the simulations
 * get closed and simulations threads are ended.
 *
 * @author Piotr
 */
public class AppDisassembler implements EventHandler<WindowEvent> {

    private ArrayList<SimulationManager> runningSimulations;
    private ArrayList<SimulationRefresher> simulationRefreshers;

    public AppDisassembler() {
        runningSimulations = new ArrayList<>();
        simulationRefreshers = new ArrayList<>();
    }

    public void addSimulationToRunning(SimulationManager newSimulation) {
        runningSimulations.add(newSimulation);
    }

    public void removeSimulationFromRunning(SimulationManager endedSimulation) {
        runningSimulations.remove(endedSimulation);
    }

    public void addSimulationRefresher(SimulationRefresher simulationRefresher) {
        simulationRefreshers.add(simulationRefresher);
    }

    public void removeSimulationRefresher(SimulationRefresher simulationRefresher) {
        simulationRefresher.interrupt();
        simulationRefreshers.remove(simulationRefresher);
    }

    @Override
    public void handle(WindowEvent event) {
        for (SimulationManager simulationManager : runningSimulations) {
            try {
                simulationManager.getSimulationEngine().interrupt();
            }
            catch (NullPointerException e) {
                e.printStackTrace();
            }

            try {
                simulationManager.simulationStage.simulationStageFX.close();
            }
            catch (NullPointerException e) {
                e.printStackTrace();
            }
        }

        for (SimulationRefresher simulationRefresher : simulationRefreshers) {
            simulationRefresher.interrupt();
        }
    }
}
