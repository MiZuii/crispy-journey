package agh.project.gui.simulation.mapDisplay;

import agh.project.gui.simulation.SimulationManager;
import agh.project.gui.simulation.events.gridElementClickedEvent;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.util.Objects;

public class gridElement extends HBox {

    private Label content;
    private String ID;

    public gridElement(int row, int column) {
        content = new Label("Error");
        this.getChildren().add(content);

        this.setOnMouseClicked(new gridElementClickedEvent(row, column));

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

    @Override
    public String toString() {
        return ID;
    }

    public void update(String data) {

        ID = data;

        if (data.equals("G")){
            this.setStyle("-fx-background-color: seagreen;");
            content.setText(data);
        }
        else if (data.isBlank()){
            this.setStyle("-fx-background-color: lightgreen;");
            content.setText("");
        }
        else {
            this.setStyle("-fx-background-color: red;");
            if (data.length() > 1)
                content.setText(data);
            else
                content.setText("M");
        }
    }
}
