package agh.project.gui.population.changeListeners;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

public class IntegerLimitChangeListener implements ChangeListener<String> {

    private final TextField textField;

    public IntegerLimitChangeListener(TextField textField) {
        this.textField = textField;
    }

    @Override
    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
        if (!newValue.isBlank() && Long.parseLong(newValue) > Integer.MAX_VALUE) {
            textField.setText(oldValue);
        }
    }
}
