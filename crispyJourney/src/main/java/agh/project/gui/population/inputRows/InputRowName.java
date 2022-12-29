package agh.project.gui.population.inputRows;

import agh.project.gui.population.changeListeners.NameFilterChangeListener;
import agh.project.gui.population.changeListeners.NameLengthChangeListener;
import agh.project.gui.population.inputRows.abstractRow.InputRow;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;

import java.util.Objects;

public class InputRowName extends InputRow<String> {

    private TextField inputData;
    private static final short maxNameLength = 20;

    public InputRowName(String inputText, double parentPrefWidth) {
        super(inputText, parentPrefWidth);
        createBoxElements();
    }

    private void  createBoxElements() {
        inputData = new TextField();
        inputData.textProperty().addListener(new NameFilterChangeListener(inputData));
        inputData.textProperty().addListener(new NameLengthChangeListener(inputData, maxNameLength));

        this.getChildren().addAll(inputName, filler, inputData);

        addStyles();
        addProperties();
    }

    private void addStyles() {
        try {
            this.getStylesheets().add(Objects.requireNonNull(this.getClass().getResource("/styles/PopulationSceneStyle.css")).toExternalForm());
            this.getStyleClass().add("row");
            inputData.getStyleClass().add("row-name-data");
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

        inputName.setAlignment(Pos.CENTER_RIGHT);
    }

    @Override
    public String getRowValue() {
        if (inputData.getText().isBlank()) {
            return null;
        }
        return inputData.getText();
    }
}
