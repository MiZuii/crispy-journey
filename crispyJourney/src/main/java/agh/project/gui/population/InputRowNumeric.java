package agh.project.gui.population;

import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.control.TextField;

public class InputRowNumeric extends HBox {

    public InputRowNumeric(String inputText) {
        createBoxElements(inputText);
    }

    private void  createBoxElements(String inputText) {
        Label inputName = new Label(inputText);
        TextField inputData = new TextField();
        inputData.textProperty().addListener(new NumbersOnlyChangeListener(inputData));

        this.getChildren().addAll(inputName, inputData);
    }

}
