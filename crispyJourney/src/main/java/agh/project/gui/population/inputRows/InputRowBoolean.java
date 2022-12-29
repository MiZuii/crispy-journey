package agh.project.gui.population.inputRows;

import agh.project.gui.population.inputRows.abstractRow.InputRow;
import javafx.geometry.Pos;
import javafx.scene.control.CheckBox;

import java.util.Objects;

public class InputRowBoolean extends InputRow<Boolean> {

    CheckBox inputData;

    public InputRowBoolean(String inputText, double parentPrefWidth) {
        super(inputText, parentPrefWidth);
        createBoxElements();
    }

    private void  createBoxElements() {
        inputData = new CheckBox();

        this.getChildren().addAll(inputName, filler, inputData);

        addStyles();
        addProperties();
    }

    private void addStyles() {
        try {
            this.getStylesheets().add(Objects.requireNonNull(this.getClass().getResource("/styles/PopulationSceneStyle.css")).toExternalForm());
            this.getStyleClass().add("row");
            inputData.getStyleClass().add("row-boolean-data");
            inputName.getStyleClass().add("row-label");
        }
        catch (NullPointerException e) {
            System.out.println("Menu scene style sheet couldn't have been loaded.");
            e.printStackTrace();
        }
    }

    protected void addProperties() {
        super.addProperties();
        inputData.setPrefWidth(parentPrefWidth/2);
        inputName.setPrefWidth(parentPrefWidth/2);

        inputName.setAlignment(Pos.CENTER_RIGHT);
    }

    @Override
    public Boolean getRowValue() {
        return inputData.isSelected();
    }
}
