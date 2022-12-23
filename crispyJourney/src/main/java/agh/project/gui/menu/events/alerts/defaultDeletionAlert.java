package agh.project.gui.menu.events.alerts;

import javafx.scene.control.Alert;

public class defaultDeletionAlert extends Alert {

    public defaultDeletionAlert() {
        super(AlertType.INFORMATION);
        this.setContentText("This is a default population therefor it cannot be deleted.");
    }
}
