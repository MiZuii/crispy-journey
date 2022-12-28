package agh.project.gui.population.inputRows.abstractRow;

import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

public abstract class InputRow<T> extends HBox {

    protected final double parentPrefWidth;

    public InputRow(double prefWidth) {
        this.parentPrefWidth = prefWidth;
    }

    public abstract T getRowValue();

    protected void addProperties() {
        this.setAlignment(Pos.CENTER);
    }
}
