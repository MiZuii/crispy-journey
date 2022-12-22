package agh.project.gui.population.inputRows;

import agh.project.gui.population.inputRows.abstractRow.InputRow;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

public class InputRowBoolean extends InputRow<Boolean> {

    public InputRowBoolean(String inputText) {
        createBoxElements(inputText);
    }

    private void  createBoxElements(String inputText) {
        Label inputName = new Label(inputText);
        CheckBox inputData = new CheckBox();

        this.getChildren().addAll(inputName, inputData);
    }

    @Override
    public Boolean getRowValue() {
        return false;
    }
}