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
public class playPopulationEvent implements EventHandler<ActionEvent> {

    private final Population population;
    private final App app;

    public playPopulationEvent(Population populationToSimulate, App app){
        this.population = populationToSimulate;
        this.app = app;
    }

    @Override
    public void handle(ActionEvent event) {
        SimulationManager simulationManager = new SimulationManager(app, population, false);
        app.appDisassembler.addSimulationToRunning(simulationManager); // simulation gets added to running simulation list
        simulationManager.start();
    }
}
