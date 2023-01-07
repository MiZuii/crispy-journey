package agh.project.gui.simulation.events;

import agh.project.gui.simulation.SimulationManager;
import agh.project.gui.simulation.SimulationScene;
import agh.project.gui.simulation.mapDisplay.MapDisplay;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class gridElementClickedEvent implements EventHandler<MouseEvent> {

    private final int row;
    private final int column;
    private final MapDisplay mapDisplay;
    private final SimulationManager simulationManager;

    public gridElementClickedEvent(int row, int column, MapDisplay mapDisplay, SimulationManager simulationManager) {
        this.row = row;
        this.column = column;
        this.mapDisplay = mapDisplay;
        this.simulationManager = simulationManager;
    }

    @Override
    public void handle(MouseEvent event) {
        // update selected animal ID
        mapDisplay.setSelectedAnimalID(row, column);

        // refresh scene
        simulationManager.getSimulationEngine().newDataToReceive.set(true);
    }
}
