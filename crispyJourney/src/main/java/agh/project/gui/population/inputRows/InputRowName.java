package agh.project.gui.population.inputRows;

import agh.project.gui.population.changeListeners.NameFilterChangeListener;
import agh.project.gui.population.inputRows.abstractRow.InputRow;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.util.Objects;

public class InputRowName extends InputRow<String> {

    private TextField inputData;
    private Label inputName;

    public InputRowName(String inputText, double parentPrefWidth) {
        super(parentPrefWidth);
        createBoxElements(inputText);
    }

    private void  createBoxElements(String inputText) {
        inputName = new Label(inputText);
        inputData = new TextField();
        inputData.textProperty().addListener(new NameFilterChangeListener(inputData));

        this.getChildren().addAll(inputName, inputData);

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
    }

    @Override
    public String getRowValue() {
        if (inputData.getText().isBlank()) {
            return null;
        }
        return inputData.getText();
    }
}
