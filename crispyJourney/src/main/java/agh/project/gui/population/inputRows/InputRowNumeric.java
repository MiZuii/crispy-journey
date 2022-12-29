package agh.project.gui.population.inputRows;

import agh.project.gui.population.changeListeners.IntegerLimitChangeListener;
import agh.project.gui.population.changeListeners.NumbersOnlyChangeListener;
import agh.project.gui.population.inputRows.abstractRow.InputRow;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;

import java.util.Objects;

public class InputRowNumeric extends InputRow<Integer> {

    private TextField inputData;

    public InputRowNumeric(String inputText, double parentPrefWidth) {
        super(inputText, parentPrefWidth);
        createBoxElements();
    }

    private void  createBoxElements() {
        inputData = new TextField();
        inputData.textProperty().addListener(new NumbersOnlyChangeListener(inputData));
        inputData.textProperty().addListener(new IntegerLimitChangeListener(inputData));

        this.getChildren().addAll(inputName, filler, inputData);

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
        inputData.setPrefWidth(parentPrefWidth/2-FILLER_LENGTH/2);
        inputName.setPrefWidth(parentPrefWidth/2-FILLER_LENGTH/2);

        inputName.setAlignment(Pos.CENTER_RIGHT);
    }

    @Override
    public Integer getRowValue() {
        if (inputData.getText().isBlank()) {
            return null;
        }
        return Integer.valueOf(inputData.getText());
    }
}
