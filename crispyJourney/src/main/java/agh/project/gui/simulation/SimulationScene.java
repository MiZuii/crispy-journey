package agh.project.gui.simulation;

import agh.project.interfaces.SceneCreator;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.util.Objects;

public class SimulationScene implements SceneCreator {

    private static final double MENU_BOX_HEIGHT = 20;
    private static final double LEFT_BOX_WIDTH = 300;
    private static final double RIGHT_BOX_WIDTH = 300;
    private static final double GRAPH_BOX_MIN_HEIGHT = 200;

    private Scene simulationScene;
    private VBox root;
    private HBox menuBox;
    private HBox contentBox;
    private VBox leftBox;
    private VBox rightBox;
    private VBox middleBox;
    private VBox mapBox;
    private VBox graphBox;

    public SimulationScene() {
    }

    @Override
    public Scene createScene() {

        // -------------------- //
        //   Content elements   //
        // -------------------- //




        // -------------------- //
        //      Structure       //
        // -------------------- //

        // sub-boxes
        mapBox = new VBox();
        graphBox = new VBox();

        // main boxes setup
        leftBox = new VBox();
        middleBox = new VBox(mapBox, graphBox);
        rightBox = new VBox();

        // menu boxes
        menuBox = new HBox();
        contentBox = new HBox(leftBox, middleBox, rightBox);

        // root setup
        root = new VBox(menuBox, contentBox);

        simulationScene = new Scene(root, 1000, 600);

        addProperties();
        addStyles();

        return simulationScene;
    }

    private void addStyles() {
        try {
            root.getStylesheets().add(Objects.requireNonNull(this.getClass().getResource("/styles/SimulationSceneStyle.css")).toExternalForm());
            root.getStyleClass().add("root");
            menuBox.getStyleClass().add("menu-box");
            leftBox.getStyleClass().add("left-box");
            rightBox.getStyleClass().add("right-box");
            mapBox.getStyleClass().add("map-box");
            graphBox.getStyleClass().add("graph-box");
        }
        catch (NullPointerException e) {
            System.out.println("Menu scene style sheet couldn't have been loaded.");
            e.printStackTrace();
        }
    }

    private void addProperties() {

        // root
        root.setAlignment(Pos.CENTER);
        root.maxHeightProperty().bind(simulationScene.heightProperty());
        root.maxWidthProperty().bind(simulationScene.widthProperty());

        // menu box
        menuBox.setMinHeight(MENU_BOX_HEIGHT);
        menuBox.setMaxHeight(MENU_BOX_HEIGHT);
        menuBox.setPrefHeight(MENU_BOX_HEIGHT);
        HBox.setHgrow(menuBox, Priority.ALWAYS);

        // content box
        HBox.setHgrow(contentBox, Priority.ALWAYS);
        VBox.setVgrow(contentBox, Priority.ALWAYS);

        // middle box
        HBox.setHgrow(middleBox, Priority.ALWAYS);
        VBox.setVgrow(middleBox, Priority.ALWAYS);

        // left box
        leftBox.setMinWidth(LEFT_BOX_WIDTH);
        leftBox.setPrefWidth(LEFT_BOX_WIDTH);
        leftBox.setMaxWidth(LEFT_BOX_WIDTH);
        VBox.setVgrow(leftBox, Priority.ALWAYS);

        // right box
        rightBox.setMinWidth(RIGHT_BOX_WIDTH);
        rightBox.setPrefWidth(RIGHT_BOX_WIDTH);
        rightBox.setMaxWidth(RIGHT_BOX_WIDTH);
        VBox.setVgrow(rightBox, Priority.ALWAYS);

        // map box
        HBox.setHgrow(mapBox, Priority.SOMETIMES);
        mapBox.prefHeightProperty().bind(mapBox.widthProperty());

        // graph box
        graphBox.setMinHeight(GRAPH_BOX_MIN_HEIGHT);
        HBox.setHgrow(graphBox, Priority.ALWAYS);
        VBox.setVgrow(graphBox, Priority.ALWAYS);
    }
}
