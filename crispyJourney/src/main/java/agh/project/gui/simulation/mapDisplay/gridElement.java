package agh.project.gui.simulation.mapDisplay;

import javafx.scene.control.Label;
import javafx.scene.layout.*;

import java.awt.*;
import java.util.Objects;

public class gridElement extends HBox {

    private Label content;

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

    public void update(String data) {
        if (data.equals("G")){
            this.setStyle("-fx-background-color: seagreen;");
        }
        else if (data.length() > 0){
            this.setStyle("-fx-background-color: darkgoldenrod");
        }
        if (data.length() > 0)
            content.setText(data.substring(0, 1));
    }

    public void clear(){
        addStyle();
        content.setText("X");
    }
}
