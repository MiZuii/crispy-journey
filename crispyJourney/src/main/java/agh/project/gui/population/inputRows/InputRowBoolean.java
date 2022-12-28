package agh.project.gui.population.inputRows;

import agh.project.gui.population.inputRows.abstractRow.InputRow;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

import java.util.Objects;

public class InputRowBoolean extends InputRow<Boolean> {

    CheckBox inputData;
    Label inputName;

    public InputRowBoolean(String inputText, double parentPrefWidth) {
        super(parentPrefWidth);
        createBoxElements(inputText);
    }

    private void  createBoxElements(String inputText) {
        inputName = new Label(inputText);
        inputData = new CheckBox();

        this.getChildren().addAll(inputName, inputData);

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
    }

    @Override
    public Boolean getRowValue() {
        return inputData.isSelected();
    }
}
