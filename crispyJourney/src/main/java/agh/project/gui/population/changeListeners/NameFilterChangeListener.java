package agh.project.gui.population.changeListeners;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;

public class NameFilterChangeListener implements ChangeListener<String> {

    private final TextField textField;

    public NameFilterChangeListener(TextField textField) {
        this.textField = textField;
    }

    @Override
    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
        if (!newValue.matches("[a-zA-Z0-9]")) {
            textField.setText(newValue.replaceAll("[^a-zA-Z0-9]", ""));
        }
    }
}
