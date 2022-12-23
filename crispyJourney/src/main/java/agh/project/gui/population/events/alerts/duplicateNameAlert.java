package agh.project.gui.population.events.alerts;

import javafx.scene.control.Alert;

public class duplicateNameAlert extends Alert {
    public duplicateNameAlert() {
        super(AlertType.WARNING);
        this.setContentText("Population name is already taken.");
    }
}
