package agh.project.gui.population.inputRows.abstractRow;

import javafx.scene.layout.HBox;

public abstract class InputRow<T> extends HBox {

    public abstract T getRowValue();
}
