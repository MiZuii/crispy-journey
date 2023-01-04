package agh.project.gui.simulation;

import agh.project.App.App;
import agh.project.interfaces.WindowManager;
import agh.project.simulation.Population;
import agh.project.simulation.SimulationEngine;
import javafx.stage.Stage;

/**
 *
 * Class SimulationManager is a class responsible for creating and managing
 * simulation window.
 *
 * @author Piotr
 *
 */
public class SimulationManager implements WindowManager {

    public SimulationStage simulationStage;
    private SimulationEngine simulationEngine;
    private final App app;
    private final Population population;
    private final boolean saveToCSV;
    private CSVCreator csvCreator;

    public SimulationManager(App app, Population populationToSimulate, boolean saveToCSV){
        this.app = app;
        this.population = populationToSimulate;
        System.out.println(population.refreshment);
        this.saveToCSV = saveToCSV;

        simulationStage = new SimulationStage(app, this);
    }

    /**
     * Methode responsible for creating window stage and showing it
     */
    @Override
    public void start() {
        Stage simulationStageFX = simulationStage.createStage();
        if (saveToCSV) {
            csvCreator = new CSVCreator();
        }
        simulationEngine = new SimulationEngine(population, this, csvCreator);
        simulationEngine.start();
        simulationStageFX.show();
        simulationStage.simulationScene.startRefereshing();
    }

    public Population getPopulation() {
        return population;
    }

    public SimulationEngine getSimulationEngine() {
        return simulationEngine;
    }

    public synchronized int getSelectedAnimalID() {
        return simulationStage.simulationScene.getAnimalID();
    }
}
