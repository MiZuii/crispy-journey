package agh.project.gui.simulation.mapDisplay;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.util.Objects;

public class gridElement extends HBox {

    public gridElement(GridPane parent) {
        this.getChildren().add(new Label("X"));

        addProperties();
        addStyle();
    }

    private void addProperties() {
        HBox.setHgrow(this, Priority.ALWAYS);
        VBox.setVgrow(this, Priority.ALWAYS);
    }

    private void addStyle() {
        try {
            this.getStylesheets().add(Objects.requireNonNull(this.getClass().getResource("/styles/MapDisplayGridElementStyle.css")).toExternalForm());
            this.getStyleClass().add("root");
        }
        catch (NullPointerException e) {
            System.out.println("Menu scene style sheet couldn't have been loaded.");
            e.printStackTrace();
        }
    }
}
