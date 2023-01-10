package agh.project.gui.population;

import agh.project.gui.menu.MenuScene;
import agh.project.gui.population.events.SavePopulationEvent;
import agh.project.gui.population.events.SavePopulationToFileEvent;
import agh.project.gui.population.inputRows.InputRowBoolean;
import agh.project.gui.population.inputRows.InputRowName;
import agh.project.gui.population.inputRows.InputRowNumeric;
import agh.project.interfaces.SceneCreator;
import agh.project.simulation.Population;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.Objects;

public class PopulationScene implements SceneCreator {

    private static final double SCENE_WIDTH = 500;
    private static final double SCENE_HEIGHT = 540;

    private Scene populationScene;
    private VBox root;
    private HBox saveButtonsBox;
    private Button save;
    private Button saveToFile;
    private InputRowName populationNameInput;
    private final ArrayList<InputRowNumeric> numericInputs;
    private final ArrayList<InputRowBoolean> booleanInputs;
    private final MenuScene menuScene;

    public PopulationScene(MenuScene menuScene){
        this.menuScene = menuScene;
        numericInputs = new ArrayList<>();
        booleanInputs = new ArrayList<>();
    }

    @Override
    public Scene createScene() {
        // create root
        root = new VBox();

        // create input rows
        populationNameInput = new InputRowName("Population name", SCENE_WIDTH);
        createNumericInputs();
        createBooleanInputs();

        // create buttons
        saveButtonsBox = new HBox();
        saveToFile = new Button("Save to file");
        save = new Button("      Save     ");

        // add functionalities to buttons
        saveToFile.setOnAction(new SavePopulationToFileEvent(menuScene, this));
        save.setOnAction(new SavePopulationEvent(menuScene, this));

        // create hierarchy (the order of lines need to be kept!)
        saveButtonsBox.getChildren().addAll(save, saveToFile);
        root.getChildren().add(populationNameInput);
        root.getChildren().addAll(numericInputs);
        root.getChildren().addAll(booleanInputs);
        root.getChildren().add(saveButtonsBox);

        // create scene
        populationScene = new Scene(root, SCENE_WIDTH, SCENE_HEIGHT);

        addStyles();
        addProperties();

        return populationScene;
    }

    private void addStyles() {
        try {
            root.getStylesheets().add(Objects.requireNonNull(this.getClass().getResource("/styles/PopulationSceneStyle.css")).toExternalForm());
            root.getStyleClass().add("root");
            saveButtonsBox.getStyleClass().add("save-buttons-box");
            saveToFile.getStyleClass().add("button");
            save.getStyleClass().add("button");
        }
        catch (NullPointerException e) {
            System.out.println("Menu scene style sheet couldn't have been loaded.");
            e.printStackTrace();
        }
    }

    private void addProperties() {
        // root properties
        root.prefWidthProperty().bind(populationScene.widthProperty());
        root.prefHeightProperty().bind(populationScene.heightProperty());
        root.setAlignment(Pos.TOP_CENTER);

        // button box
        saveButtonsBox.setAlignment(Pos.CENTER);
        HBox.setHgrow(saveButtonsBox, Priority.ALWAYS);

        // buttons
        save.setMaxWidth(Double.MAX_VALUE);
        saveToFile.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(save, Priority.ALWAYS);
        HBox.setHgrow(saveToFile, Priority.ALWAYS);
    }

    public String getPopulationName() {
        return populationNameInput.getRowValue();
    }

    public boolean anyEmptyInputs() {
        boolean flag = false;
        for (InputRowNumeric row : numericInputs) {
            if (row.getRowValue() == null) {
                flag = true;
            }
        }
        if (populationNameInput.getRowValue().isBlank()) {
            flag = true;
        }
        return flag;
    }

    public Population getNewPopulationFromInputs() {

        ArrayList<Integer> intArgs = new ArrayList<>();
        for (InputRowNumeric row : numericInputs) {
            intArgs.add(row.getRowValue());
        }
        int[] iargs = new int[intArgs.size()];
        for (int i=0; i<iargs.length; i++) {
            iargs[i] = intArgs.get(i);
        }

        ArrayList<Boolean> booleanArgs = new ArrayList<>();
        for (InputRowBoolean row : booleanInputs) {
            booleanArgs.add(row.getRowValue());
        }
        boolean[] bargs = new boolean[booleanArgs.size()];
        for (int i=0; i<bargs.length; i++) {
            bargs[i] = booleanArgs.get(i);
        }

        return new Population(populationNameInput.getRowValue(), false, iargs, bargs);
    }

    private void createBooleanInputs() {
        InputRowBoolean row1 = new InputRowBoolean("Partially random moves", SCENE_WIDTH);
        booleanInputs.add(row1);
        InputRowBoolean row2 = new InputRowBoolean("Random mutation", SCENE_WIDTH);
        booleanInputs.add(row2);
        InputRowBoolean row3 = new InputRowBoolean("Round/Hell (uncheck/check)", SCENE_WIDTH);
        booleanInputs.add(row3);
        InputRowBoolean row4 = new InputRowBoolean("Grass on equator (unchangeable)", SCENE_WIDTH);
        booleanInputs.add(row4);
    }

    private void createNumericInputs() {
        InputRowNumeric row1 = new InputRowNumeric("Map Height", SCENE_WIDTH);
        numericInputs.add(row1);
        InputRowNumeric row2 = new InputRowNumeric("Map Width", SCENE_WIDTH);
        numericInputs.add(row2);
        InputRowNumeric row3 = new InputRowNumeric("Energy profit after eating grass", SCENE_WIDTH);
        numericInputs.add(row3);
        InputRowNumeric row4 = new InputRowNumeric("Minimal energy to copulate", SCENE_WIDTH);
        numericInputs.add(row4);
        InputRowNumeric row5 = new InputRowNumeric("Starting energy of animals", SCENE_WIDTH);
        numericInputs.add(row5);
        InputRowNumeric row6 = new InputRowNumeric("Daily energy loss", SCENE_WIDTH);
        numericInputs.add(row6);
        InputRowNumeric row7 = new InputRowNumeric("Number of starting animals", SCENE_WIDTH);
        numericInputs.add(row7);
        InputRowNumeric row8 = new InputRowNumeric("Spawning grass per day", SCENE_WIDTH);
        numericInputs.add(row8);
        InputRowNumeric row9 = new InputRowNumeric("Refreshment rate (ms)", SCENE_WIDTH);
        numericInputs.add(row9);
        InputRowNumeric row10 = new InputRowNumeric("Energy needed to copulate", SCENE_WIDTH);
        numericInputs.add(row10);
        InputRowNumeric row11 = new InputRowNumeric("Maximum mutation number", SCENE_WIDTH);
        numericInputs.add(row11);
        InputRowNumeric row12 = new InputRowNumeric("Genom Length", SCENE_WIDTH);
        numericInputs.add(row12);
    }

    public void closePopulationWindow() {
        ((Stage) populationScene.getWindow()).close();
    }
}
