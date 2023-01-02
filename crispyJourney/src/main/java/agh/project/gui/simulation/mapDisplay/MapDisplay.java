package agh.project.gui.simulation.mapDisplay;

import agh.project.interfaces.Updateable;
import javafx.scene.layout.VBox;

import java.util.concurrent.atomic.AtomicInteger;

public class MapDisplay extends VBox implements Updateable {

    private AtomicInteger selectedAnimalID = new AtomicInteger(-1);

    public MapDisplay() {

    }

    private void createElements() {

    }

    private void addStyles() {

    }

    private void addProperties() {

    }

    @Override
    public void update() {

    }

    public int getAnimalID() {
        return selectedAnimalID.get();
    }
}
