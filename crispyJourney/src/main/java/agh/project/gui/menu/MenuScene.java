package agh.project.gui.menu;

import agh.project.App.App;
import agh.project.gui.menu.events.addPopulationEvent;
import agh.project.gui.population.PopulationManager;
import agh.project.interfaces.SceneCreator;
import agh.project.simulation.Population;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;

/**
 * MenuScene is responsible for creating menu javafx scene of the menu
 *
 * @author Piotr
 *
 */
public class MenuScene implements SceneCreator {

    Scene menuScene;
    private VBox root;
    private Button addPopulationButton;
    private App app;

    public MenuScene(App app) {
        this.app = app;
    }

    @Override
    public Scene createScene() {

        // nodes creation
        root = new VBox();

        for (Population p : app.populationsHolder.getPopulationsList()) {
            root.getChildren().add(new MenuPopulationBox(p, root, app));
        }

        addPopulationButton = new Button("Add population");
        addPopulationButton.setOnAction(new addPopulationEvent(app, this));
        root.getChildren().add(addPopulationButton);
        addPopulationButton.toFront();

        menuScene = new Scene(root, 600, 600);
        return menuScene;
    }

    public void addNewPopulation(Population population) {
        root.getChildren().add(new MenuPopulationBox(population, root, app));
        addPopulationButton.toFront();
        app.populationsHolder.addPopulation(population);
    }

    public boolean populationNameTaken(String name) {
        return app.populationsHolder.getPopulationNames().contains(name);
    }

    public void savePopulationToFile(Population population) {
        app.populationsHolder.createPopulationFile(population);
    }
}
