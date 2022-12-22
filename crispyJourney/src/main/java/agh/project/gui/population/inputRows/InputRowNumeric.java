package agh.project.gui.population.inputRows;

import agh.project.gui.population.changeListeners.NumbersOnlyChangeListener;
import agh.project.gui.population.inputRows.abstractRow.InputRow;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class InputRowNumeric extends InputRow<Integer> {

    private TextField inputData;

    public InputRowNumeric(String inputText) {
        createBoxElements(inputText);
    }

    private void  createBoxElements(String inputText) {
        Label inputName = new Label(inputText);
        inputData = new TextField();
        inputData.textProperty().addListener(new NumbersOnlyChangeListener(inputData));

        this.getChildren().addAll(inputName, inputData);
    }

    @Override
    public Integer getRowValue() {
        if (inputData.getText().isBlank()) {
            return null;
        }
        return Integer.valueOf(inputData.getText());
    }
}
