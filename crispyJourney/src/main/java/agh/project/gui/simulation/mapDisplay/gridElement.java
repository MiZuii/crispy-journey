package agh.project.gui.simulation.mapDisplay;

import javafx.scene.control.Label;
import javafx.scene.layout.*;

import java.awt.*;
import java.util.Objects;

public class gridElement extends HBox {

    private Label content;
    private String ID;

    public gridElement(GridPane parent) {
        content = new Label("X");
        this.getChildren().add(content);

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
            content.setText(data);
        }
    }
}
