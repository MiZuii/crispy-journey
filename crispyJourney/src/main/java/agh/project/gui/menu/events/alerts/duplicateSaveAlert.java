package agh.project.gui.menu.events.alerts;

import javafx.scene.control.Alert;

public class duplicateSaveAlert extends Alert {
    public duplicateSaveAlert() {
        super(AlertType.INFORMATION);
        this.setContentText("This population is already saved.");
    }
}
