package agh.project.gui.menu;

import agh.project.interfaces.SceneCreator;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;

public class MenuScene implements SceneCreator {

    Scene menuScene;

    public MenuScene(){
    }

    @Override
    public Scene createScene() {
        VBox root = new VBox();
        menuScene = new Scene(root, 1000, 600);
        return menuScene;
    }
}
