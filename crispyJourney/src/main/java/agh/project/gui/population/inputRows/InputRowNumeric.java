package agh.project.gui.population.inputRows;

import agh.project.gui.population.changeListeners.NumbersOnlyChangeListener;
import agh.project.gui.population.inputRows.abstractRow.InputRow;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.util.Objects;

public class InputRowNumeric extends InputRow<Integer> {

    private TextField inputData;
    private Label inputName;

    public InputRowNumeric(String inputText, double parentPrefWidth) {
        super(parentPrefWidth);
        createBoxElements(inputText);
    }

    private void  createBoxElements(String inputText) {
        inputName = new Label(inputText);
        inputData = new TextField();
        inputData.textProperty().addListener(new NumbersOnlyChangeListener(inputData));

        this.getChildren().addAll(inputName, inputData);

        addStyles();
        addProperties();
    }

    private void addStyles() {
        try {
            this.getStylesheets().add(Objects.requireNonNull(this.getClass().getResource("/styles/PopulationSceneStyle.css")).toExternalForm());
            this.getStyleClass().add("row");
            inputData.getStyleClass().add("row-numeric-data");
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
    public Integer getRowValue() {
        if (inputData.getText().isBlank()) {
            return null;
        }
        return Integer.valueOf(inputData.getText());
    }
}
