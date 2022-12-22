package agh.project.gui.population;

import agh.project.gui.population.inputRows.InputRowBoolean;
import agh.project.gui.population.inputRows.InputRowName;
import agh.project.gui.population.inputRows.InputRowNumeric;
import agh.project.interfaces.SceneCreator;
import agh.project.simulation.Population;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.util.ArrayList;

public class PopulationScene implements SceneCreator {

    Scene populationScene;
    private VBox root;
    private ArrayList<InputRowNumeric> numericInputs;
    private ArrayList<InputRowBoolean> booleanInputs;

    public PopulationScene(){
        numericInputs = new ArrayList<>();
        booleanInputs = new ArrayList<>();
    }

    @Override
    public Scene createScene() {
        root = new VBox();

        InputRowName populationNameInput = new InputRowName("Population name");
        root.getChildren().add(populationNameInput);

        createNumericInputs();
        root.getChildren().addAll(numericInputs);

        createBolleanInputs();
        root.getChildren().addAll(booleanInputs);

        HBox saveButtonsBox = new HBox();
        Button saveToFile = new Button("Save to file");
        Button save = new Button("Save");
        saveButtonsBox.getChildren().addAll(save, saveToFile);
        root.getChildren().add(saveButtonsBox);

        populationScene = new Scene(root, 400, 700);
        return populationScene;
    }

    public Population getNewPopulationFromInputs() {
        return null;
    }

    private void createBolleanInputs() {
        InputRowBoolean row1 = new InputRowBoolean("example data 1");
        booleanInputs.add(row1);
        InputRowBoolean row2 = new InputRowBoolean("example data 2");
        booleanInputs.add(row2);
        InputRowBoolean row3 = new InputRowBoolean("example data 3");
        booleanInputs.add(row3);
        InputRowBoolean row4 = new InputRowBoolean("example data 4");
        booleanInputs.add(row4);
    }

    private void createNumericInputs() {
        InputRowNumeric row1 = new InputRowNumeric("example data 1");
        numericInputs.add(row1);
        InputRowNumeric row2 = new InputRowNumeric("example data 2");
        numericInputs.add(row2);
        InputRowNumeric row3 = new InputRowNumeric("example data 3");
        numericInputs.add(row3);
        InputRowNumeric row4 = new InputRowNumeric("example data 4");
        numericInputs.add(row4);
        InputRowNumeric row5 = new InputRowNumeric("example data 5");
        numericInputs.add(row5);
        InputRowNumeric row6 = new InputRowNumeric("example data 6");
        numericInputs.add(row6);
        InputRowNumeric row7 = new InputRowNumeric("example data 7");
        numericInputs.add(row7);
        InputRowNumeric row8 = new InputRowNumeric("example data 8");
        numericInputs.add(row8);
        InputRowNumeric row9 = new InputRowNumeric("example data 9");
        numericInputs.add(row9);
        InputRowNumeric row10 = new InputRowNumeric("example data 10");
        numericInputs.add(row10);
        InputRowNumeric row11 = new InputRowNumeric("example data 11");
        numericInputs.add(row11);
        InputRowNumeric row12 = new InputRowNumeric("example data 12");
        numericInputs.add(row12);
        InputRowNumeric row13 = new InputRowNumeric("example data 13");
        numericInputs.add(row13);
    }
}
