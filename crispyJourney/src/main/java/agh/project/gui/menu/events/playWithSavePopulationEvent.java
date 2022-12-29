package agh.project.gui.menu.events;

import agh.project.App.App;
import agh.project.gui.simulation.SimulationManager;
import agh.project.simulation.Population;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;

/**
 *
 * playWithSavePopulationEvent creates new window
 * and runs new simulation on it -> with results to file
 *
 * @author Piotr
 *
 */
public class playWithSavePopulationEvent implements EventHandler<ActionEvent> {

    private final Population population;
    private final App app;

    public playWithSavePopulationEvent(Population populationToSimulate, App app){
        this.app = app;
        this.population = populationToSimulate;
    }

    @Override
    public void handle(ActionEvent event) {
        SimulationManager simulationManager = new SimulationManager(app, population, true);
        app.appDisassembler.addSimulationToRunning(simulationManager); // gets added to list of running simulations
        simulationManager.start();
    }
}
