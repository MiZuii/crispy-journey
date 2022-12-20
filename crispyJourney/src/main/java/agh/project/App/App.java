package agh.project.App;

import agh.project.gui.menu.MenuManager;
import agh.project.gui.population.PopulationManager;
import agh.project.gui.simulation.SimulationManager;
import javafx.application.Application;
import javafx.stage.Stage;

import java.util.ArrayList;

public class App extends Application{

    private ArrayList<SimulationManager> simulationManagers;
    private MenuManager menuManager;
    private PopulationManager populationManager;

    @Override
    public void init() {

    }

    private void artificialInit() {
        simulationManagers = new ArrayList<>();
        menuManager = new MenuManager();
        populationManager = new PopulationManager();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // running artificial initiator
        artificialInit();

        // run menu window
        menuManager.start();
        simulationManagers.add(new SimulationManager());
        simulationManagers.add(new SimulationManager());
        for (SimulationManager s : simulationManagers) {
            s.start();
        }
    }
}
