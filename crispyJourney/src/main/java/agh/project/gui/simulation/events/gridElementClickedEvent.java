package agh.project.gui.simulation.events;

import agh.project.gui.simulation.SimulationManager;
import agh.project.gui.simulation.mapDisplay.MapDisplay;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class gridElementClickedEvent implements EventHandler<MouseEvent> {

    private final int row;
    private final int column;
    private final MapDisplay mapDisplay;

    public gridElementClickedEvent(int row, int column, MapDisplay mapDisplay) {
        this.row = row;
        this.column = column;
        this.mapDisplay = mapDisplay;
    }

    @Override
    public void handle(MouseEvent event) {
        mapDisplay.setSelectedAnimalID(row, column);
    }
}
