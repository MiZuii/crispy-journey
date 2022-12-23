package agh.project.gui.population;

import agh.project.gui.menu.MenuScene;
import agh.project.gui.population.events.SavePopulationEvent;
import agh.project.gui.population.events.SavePopulationToFileEvent;
import agh.project.gui.population.inputRows.InputRowBoolean;
import agh.project.gui.population.inputRows.InputRowName;
import agh.project.gui.population.inputRows.InputRowNumeric;
import agh.project.interfaces.SceneCreator;
import agh.project.simulation.Population;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;

public class PopulationScene implements SceneCreator {

    Scene populationScene;
    private InputRowName populationNameInput;
    private final ArrayList<InputRowNumeric> numericInputs;
    private final ArrayList<InputRowBoolean> booleanInputs;
    private MenuScene menuScene;

    public PopulationScene(MenuScene menuScene){
        this.menuScene = menuScene;
        numericInputs = new ArrayList<>();
        booleanInputs = new ArrayList<>();
    }

    @Override
    public Scene createScene() {
        VBox root = new VBox();

        populationNameInput = new InputRowName("Population name");
        root.getChildren().add(populationNameInput);

        createNumericInputs();
        root.getChildren().addAll(numericInputs);

        createBolleanInputs();
        root.getChildren().addAll(booleanInputs);

        HBox saveButtonsBox = new HBox();
        Button saveToFile = new Button("Save to file");
        saveToFile.setOnAction(new SavePopulationToFileEvent(menuScene, this));
        Button save = new Button("Save");
        save.setOnAction(new SavePopulationEvent(menuScene, this));
        saveButtonsBox.getChildren().addAll(save, saveToFile);
        root.getChildren().add(saveButtonsBox);

        populationScene = new Scene(root, 400, 700);
        return populationScene;
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

    public void closePopulationWindow() {
        ((Stage) populationScene.getWindow()).close();
    }
}
