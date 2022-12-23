package agh.project.gui.menu;

import agh.project.App.App;
import agh.project.gui.menu.events.addPopulationEvent;
import agh.project.interfaces.SceneCreator;
import agh.project.simulation.Population;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

import java.util.Objects;

/**
 * MenuScene is responsible for creating menu javafx scene of the menu
 *
 * @author Piotr
 *
 */
public class MenuScene implements SceneCreator {

    private Scene menuScene;
    private VBox root;
    private VBox populationsContainer;
    private Label header;
    private HBox headerBox;
    private Button addPopulationButton;
    private HBox addPopulationButtonBox;
    private final App app;

    public MenuScene(App app) {
        this.app = app;
    }

    /* -------------------------------------------------------------------------- */
    /*                               Scene creation                               */
    /* -------------------------------------------------------------------------- */

    @Override
    public Scene createScene() {

        // nodes creation
        populationsContainer = new VBox();
        root = new VBox();
        header = new Label("Populations Simulator");
        headerBox = new HBox(header);
        addPopulationButton = saveCreateButton("", "plus", "+");
        addPopulationButtonBox = new HBox(addPopulationButton);

        // adding pre-saved populations
        for (Population p : app.populationsHolder.getPopulationsList()) {
            populationsContainer.getChildren().add(new MenuPopulationBox(p, populationsContainer, app));
        }

        // adding buttons
        populationsContainer.getChildren().add(addPopulationButtonBox);
        root.getChildren().addAll(headerBox, populationsContainer);

        // create scene
        menuScene = new Scene(root, 600, 600);

        addProperties();
        addStyles();

        return menuScene;
    }

    private void addStyles() {
        try {
            root.getStylesheets().add(Objects.requireNonNull(this.getClass().getResource("/styles/MenuSceneStyle.css")).toExternalForm());
            root.getStyleClass().add("root");
            header.getStyleClass().add("header-label");
            headerBox.getStyleClass().add("header-box");
            populationsContainer.getStyleClass().add("populations-container");
            addPopulationButton.getStyleClass().add("add-population-button");
        }
        catch (NullPointerException e) {
            System.out.println("Menu scene style sheet couldn't have been loaded.");
            e.printStackTrace();
        }
    }

    private void addProperties() {
        // root properties
        root.prefWidthProperty().bind(menuScene.widthProperty());
        root.prefHeightProperty().bind(menuScene.heightProperty());
        root.setAlignment(Pos.TOP_CENTER);

        // population container
        populationsContainer.setMaxWidth(600);
        VBox.setVgrow(populationsContainer, Priority.ALWAYS);

        // add population button
        addPopulationButton.setPadding(new Insets(10, 10, 10, 10));

        // buttons
        addPopulationButton.setOnAction(new addPopulationEvent(this));
        addPopulationButtonBox.setAlignment(Pos.CENTER_RIGHT);
        addPopulationButtonBox.setPadding(new Insets(10, 10, 10, 10));

        // header
        HBox.setHgrow(headerBox, Priority.ALWAYS);
        headerBox.setPadding(new Insets(10, 0, 10, 20));
    }

    private Button saveCreateButton(String content, String imgName, String safetyContent) {
        Button tmpButton;
        try {
            tmpButton = new Button(content, getImageViewFromString(imgName));
        }
        catch (NullPointerException e) {
            tmpButton = new Button(safetyContent);
            System.out.println("Couldn't load image: " + imgName);
        }
        return tmpButton;
    }

    private ImageView getImageViewFromString(String imageName) throws NullPointerException{
        Image img = new Image(Objects.requireNonNull(this.getClass().getResourceAsStream("/images/" + imageName + ".png")));
        ImageView imgView = new ImageView(img);
        imgView.setFitHeight(30);
        imgView.setFitWidth(30);
        return imgView;
    }

    /* -------------------------------------------------------------------------- */
    /*                            Public functionalities                          */
    /* -------------------------------------------------------------------------- */

    public void addNewPopulation(Population population) {
        populationsContainer.getChildren().add(new MenuPopulationBox(population, populationsContainer, app));
        addPopulationButtonBox.toFront();
        app.populationsHolder.addPopulation(population);
    }

    public void addSavedPopulationToHolder(Population population) {
        app.populationsHolder.addSavedPopulation(population);
    }

    public boolean populationNameTaken(String name) {
        return app.populationsHolder.getPopulationsNames().contains(name);
    }

    public void savePopulationToFile(Population population) {
        app.populationsHolder.createPopulationFile(population);
    }
}
