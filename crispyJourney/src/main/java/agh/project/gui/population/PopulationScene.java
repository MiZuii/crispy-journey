package agh.project.gui.population;

import agh.project.interfaces.SceneCreator;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

public class PopulationScene implements SceneCreator {

    Scene populationScene;

    public PopulationScene(){
    }

    @Override
    public Scene createScene() {
        VBox root = new VBox();
        populationScene = new Scene(root, 400, 700);
        return populationScene;
    }
}
