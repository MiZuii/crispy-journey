package agh.project.gui.population;

import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;

public class InputRowBoolean extends HBox {

    public InputRowBoolean(String inputText) {
        createBoxElements(inputText);
    }

    private void  createBoxElements(String inputText) {
        Label inputName = new Label(inputText);
        CheckBox inputData = new CheckBox();

        this.getChildren().addAll(inputName, inputData);
    }

}
