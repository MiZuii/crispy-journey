package agh.project.gui.population.inputRows;

import agh.project.gui.population.changeListeners.NameFilterChangeListener;
import agh.project.gui.population.inputRows.abstractRow.InputRow;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class InputRowName extends InputRow<String> {

    private TextField inputData;

    public InputRowName(String inputText) {
        createBoxElements(inputText);
    }

    private void  createBoxElements(String inputText) {
        Label inputName = new Label(inputText);
        inputData = new TextField();
        inputData.textProperty().addListener(new NameFilterChangeListener(inputData));

        this.getChildren().addAll(inputName, inputData);
    }

    @Override
    public String getRowValue() {
        if (inputData.getText().isBlank()) {
            return null;
        }
        return inputData.getText();
    }
}
