package agh.project.gui.menu;

import agh.project.App.App;
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
    private VBox root;
    private App app;

    public MenuScene(App app) {
        this.app = app;
    }

    @Override
    public Scene createScene() {

        // nodes creation
        root = new VBox();

        for (Population p : app.populationsHolder.getPopulationsList()) {
            root.getChildren().add(new MenuPopulationBox(p));
        }

        Button addPopulationButton = new Button("Add population");
        addPopulationButton.setOnAction(app.menuManager.addPopulationButtonAction);
        root.getChildren().add(addPopulationButton);
        addPopulationButton.toFront();


        menuScene = new Scene(root, 600, 600);
        return menuScene;
    }
}
