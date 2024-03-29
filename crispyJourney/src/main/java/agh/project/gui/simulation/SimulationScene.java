package agh.project.gui.simulation;

import agh.project.gui.simulation.animalDisplay.AnimalDisplay;
import agh.project.gui.simulation.graphDisplay.GraphDisplay;
import agh.project.gui.simulation.mapDisplay.MapDisplay;
import agh.project.gui.simulation.populationDisplay.PopulationDisplay;
import agh.project.interfaces.SceneCreator;
import agh.project.interfaces.Updateable;
import agh.project.simulation.DataStorage;
import agh.project.gui.simulation.events.playButtonEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.util.ArrayList;
import java.util.Objects;

import static java.lang.Thread.sleep;

public class SimulationScene implements SceneCreator {

    private static final double MENU_BOX_HEIGHT = 25;
    private static final double LEFT_BOX_WIDTH = 250;
    private static final double RIGHT_BOX_WIDTH = 250;
    private static final double GRAPH_BOX_HEIGHT = 300;
    private static final double TITLE_CENTERING_WIDTH = 38;
    private static final double INITIAL_SCENE_WIDTH = 1000;
    private static final double INITIAL_SCENE_HEIGHT = 600;
    private static final int FPS = 60;

    private Scene simulationScene;
    private VBox root;
    private HBox menuBox;
    private HBox contentBox;
    private VBox leftBox;
    private VBox rightBox;
    private VBox middleBox;
    private VBox mapBox;
    private VBox graphBox;
    private HBox titleBox;
    private Button play;
    private Label title;
    private AnimalDisplay animalDisplay;
    private GraphDisplay graphDisplay;
    private MapDisplay mapDisplay;
    private PopulationDisplay populationDisplay;
    private ArrayList<Updateable> updateableDisplays = new ArrayList<>();
    private DataStorage data;
    private final SimulationManager simulationManager;

    public SimulationScene(SimulationManager simulationManager) {
        this.simulationManager = simulationManager;
    }

    @Override
    public Scene createScene() {

        // pre creation -> for boxes that need to be passed into displays
        mapBox = new VBox();

        // -------------------- //
        //   Content elements   //
        // -------------------- //

        animalDisplay = new AnimalDisplay(simulationManager);
        graphDisplay = new GraphDisplay();
        mapDisplay = new MapDisplay(simulationManager.getPopulation(), mapBox, simulationManager);
        populationDisplay = new PopulationDisplay(simulationManager.getPopulation());
        updateableDisplays.add(animalDisplay);
        updateableDisplays.add(graphDisplay);
        updateableDisplays.add(mapDisplay);
        updateableDisplays.add(populationDisplay);
        play = new Button("play");
        play.setOnAction(new playButtonEvent(simulationManager));
        title = new Label("Population: " + simulationManager.getPopulation().name);


        // -------------------- //
        //      Structure       //
        // -------------------- //

        // sub-boxes
        mapBox.getChildren().add(mapDisplay);
        graphBox = new VBox(graphDisplay);

        // main boxes setup
        leftBox = new VBox(populationDisplay);
        middleBox = new VBox(mapBox, graphBox);
        rightBox = new VBox(animalDisplay);

        // menu boxes
        titleBox = new HBox(title);
        menuBox = new HBox();
        menuBox.getChildren().addAll(play, titleBox);
        contentBox = new HBox(leftBox, middleBox, rightBox);

        // root setup
        root = new VBox(menuBox, contentBox);

        simulationScene = new Scene(root, INITIAL_SCENE_WIDTH, INITIAL_SCENE_HEIGHT);

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
            play.getStyleClass().add("menu-button");
            title.getStyleClass().add("menu-label");
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
        menuBox.setAlignment(Pos.CENTER_LEFT);

        // menu title box
        HBox.setHgrow(titleBox, Priority.ALWAYS);
        titleBox.setAlignment(Pos.CENTER);
        titleBox.setPadding(new Insets(0, TITLE_CENTERING_WIDTH, 0, 0));

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
        HBox.setHgrow(mapBox, Priority.ALWAYS);
        VBox.setVgrow(mapBox, Priority.ALWAYS);

        // graph box
        graphBox.setMinHeight(GRAPH_BOX_HEIGHT);
        graphBox.setMaxHeight(GRAPH_BOX_HEIGHT);
        graphBox.setPrefHeight(GRAPH_BOX_HEIGHT);
        HBox.setHgrow(graphBox, Priority.ALWAYS);
    }

    private void updateScene() {
        // update displays
        for (Updateable display : updateableDisplays) {
            display.update(this.data);
        }
    }

    public void startRefereshing() {
        SimulationRefresher refresher = new SimulationRefresher(FPS, this);
        refresher.setName("refresher");
        simulationManager.addSimulationRefresher(refresher);
        refresher.start();
    }

    public void refresh() {
        if (simulationManager.getSimulationEngine().newDataToReceive.get()) {
            simulationManager.getSimulationEngine().newDataToReceive.set(false);
            data = simulationManager.getSimulationEngine().getData();
            updateScene();
        }
    }

    public int getAnimalID() {
        return mapDisplay.getAnimalID();
    }
}
