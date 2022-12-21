package agh.project.gui.menu;

import agh.project.gui.population.PopulationManager;
import agh.project.interfaces.SceneCreator;
import agh.project.simulation.Population;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

public class MenuScene implements SceneCreator {

    Scene menuScene;

    public MenuScene(){
    }

    @Override
    public Scene createScene() {

        Button tmpbt = new Button("new window");
        VBox root = new VBox();

        root.getChildren().add(tmpbt);

        tmpbt.toFront();
        tmpbt.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                PopulationManager nm = new PopulationManager();
                nm.start();
            }
        });

        menuScene = new Scene(root, 600, 600);
        return menuScene;
    }
}
