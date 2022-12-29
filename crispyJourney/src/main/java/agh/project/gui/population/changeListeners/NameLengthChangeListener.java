package agh.project.gui.population.changeListeners;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

public class NameLengthChangeListener implements ChangeListener<String> {

    private final TextField textField;
    private final short maxLength;

    public NameLengthChangeListener(TextField textField, short maxLength) {
        this.maxLength = maxLength;
        this.textField = textField;
    }

    @Override
    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
        if (newValue.length() > maxLength) {
            textField.setText(oldValue);
        }
    }
}
