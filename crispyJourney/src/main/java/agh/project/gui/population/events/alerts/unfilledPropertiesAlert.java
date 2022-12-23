package agh.project.gui.population.events.alerts;

import javafx.scene.control.Alert;

public class unfilledPropertiesAlert extends Alert {

    public unfilledPropertiesAlert() {
        super(AlertType.WARNING);
        this.setContentText("There are blank fields in the form. These must be completed before proceeding.");
    }
}
