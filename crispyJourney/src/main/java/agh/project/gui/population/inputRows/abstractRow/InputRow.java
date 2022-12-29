package agh.project.gui.population.inputRows.abstractRow;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public abstract class InputRow<T> extends HBox {

    protected final double parentPrefWidth;
    protected Label inputName;
    protected HBox filler;
    protected static final double FILLER_LENGTH = 10;

    public InputRow(String inputName, double prefWidth) {
        this.filler = new HBox();
        filler.setMinWidth(FILLER_LENGTH);
        filler.setMaxWidth(FILLER_LENGTH);
        filler.setPrefWidth(FILLER_LENGTH);

        this.inputName = new Label(inputName);
        this.parentPrefWidth = prefWidth;
        this.setMinHeight(30);
    }

    public abstract T getRowValue();

    protected void addProperties() {
        this.setAlignment(Pos.CENTER);
    }
}
