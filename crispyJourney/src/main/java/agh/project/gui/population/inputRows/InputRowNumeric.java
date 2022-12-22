package agh.project.gui.population.inputRows;

import agh.project.gui.population.changeListeners.NumbersOnlyChangeListener;
import agh.project.gui.population.inputRows.abstractRow.InputRow;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class InputRowNumeric extends InputRow<Integer> {

    public InputRowNumeric(String inputText) {
        createBoxElements(inputText);
    }

    private void  createBoxElements(String inputText) {
        Label inputName = new Label(inputText);
        TextField inputData = new TextField();
        inputData.textProperty().addListener(new NumbersOnlyChangeListener(inputData));

        this.getChildren().addAll(inputName, inputData);
    }

    @Override
    public Integer getRowValue() {
        return null;
    }
}
