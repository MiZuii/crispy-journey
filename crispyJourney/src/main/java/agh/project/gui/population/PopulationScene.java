package agh.project.gui.population;

import agh.project.interfaces.SceneCreator;
import javafx.scene.Scene;
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

        createNumericInputs();
        root.getChildren().addAll(numericInputs);

        createBolleanInputs();
        root.getChildren().addAll(booleanInputs);

        populationScene = new Scene(root, 400, 700);
        return populationScene;
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
