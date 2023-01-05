package agh.project.gui.simulation.events;

import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

public class gridElementClickedEvent implements EventHandler<MouseEvent> {

    private final int row;
    private final int column;

    public gridElementClickedEvent(int row, int column) {
        this.row = row;
        this.column = column;
    }

    @Override
    public void handle(MouseEvent event) {
        System.out.println(String.valueOf(row) + " " + String.valueOf(column));
    }
}
