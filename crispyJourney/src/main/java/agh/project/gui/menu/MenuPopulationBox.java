package agh.project.gui.menu;

import agh.project.App.App;
import agh.project.gui.menu.events.removePopulationEvent;
import agh.project.gui.menu.events.savePopulationEvent;
import agh.project.simulation.Population;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.text.TextAlignment;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Path;
import java.util.Enumeration;
import java.util.Objects;

/**
 *
 * MenuPopulationBox is a class extending HBox. It is
 * a class that contains population and creates HBox
 * which displays information of the populations and
 * creates buttons responsible for deleting and starting
 * simulation.
 *
 * @author Piotr
 *
 */
public class MenuPopulationBox extends HBox {

    private final Population population;
    private final Pane parentNode;
    private final App app;
    // Box elements
    private HBox filler;
    private Button deletePopulationButton;
    private Label populationNameLabel;
    private Button saveButton;
    private Button startPopulationSimulationButton;

    public MenuPopulationBox(Population population, Pane parent, App app) {
        this.app = app;
        this.parentNode = parent;
        this.population = population;
        createBox();
    }

    /**
     * Population getter
     */
    public Population getPopulation() {
        return population;
    }

    /**
     * Change populationBox save icon to saved
     */
    public void changeSaveIcon() {
        try {
            saveButton.setGraphic(getImageViewFromString("save"));
        }
        catch (NullPointerException e) {
            saveButton = new Button("S");
            System.out.println("Couldn't load image: save");
        }
    }

    /**
     * Creates javafx nodes and adds them to itself.
     */
    private void createBox(){

        // nodes creation
        filler = new HBox();
        populationNameLabel = new Label(population.name);

        // delete button
        deletePopulationButton = saveCreateButton("", "delete", "X");
        deletePopulationButton.setOnAction(new removePopulationEvent(this, parentNode, app));

        // save button
        if (app.populationsHolder.getSavedPopulationsNames().contains(population.name)) {
            saveButton = saveCreateButton("", "save", "S");
        }
        else {
            saveButton = saveCreateButton("", "save-not", "S");
        }
        saveButton.setOnAction(new savePopulationEvent(this, app));

        // start button
        startPopulationSimulationButton = saveCreateButton("", "play", ">");

        addStyles();
        addProperties();

        // node placing
        this.getChildren().addAll(populationNameLabel, filler, saveButton, deletePopulationButton, startPopulationSimulationButton);
    }

    private void addStyles(){
        try {
            this.getStylesheets().add(Objects.requireNonNull(this.getClass().getResource("/styles/MenuPopulationBoxStyle.css")).toExternalForm());
            this.getStyleClass().add("menu-population-box");
            populationNameLabel.getStyleClass().add("population-name-label");
        }
        catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private void addProperties() {
        this.setAlignment(Pos.CENTER_RIGHT);
        this.setPadding(new Insets(15, 0, 5, 0));
        populationNameLabel.setPadding(new Insets(5, 30, 5, 30));
        populationNameLabel.setAlignment(Pos.CENTER_LEFT);
        HBox.setHgrow(filler, Priority.ALWAYS);
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
}
