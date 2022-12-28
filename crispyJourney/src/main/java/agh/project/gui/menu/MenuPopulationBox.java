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
import javafx.scene.layout.VBox;

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
public class MenuPopulationBox extends VBox {

    private final Population population;
    private final Pane parentNode;
    private final App app;
    // Box elements
    private Button deletePopulationButton;
    private Label populationNameLabel;
    private Button saveButton;
    private Button startPopulationSimulationButton;
    private HBox buttonsContainer;
    private HBox populationNameLabelWrapper;

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
        buttonsContainer = new HBox();
        populationNameLabelWrapper = new HBox();
        populationNameLabel = new Label(population.name);

        // delete button
        deletePopulationButton = saveCreateButton("Delete", "delete", "X");
        deletePopulationButton.setOnAction(new removePopulationEvent(this, parentNode, app));

        // save button
        if (app.populationsHolder.getSavedPopulationsNames().contains(population.name)) {
            saveButton = saveCreateButton("Save", "save", "S");
        }
        else {
            saveButton = saveCreateButton("Save", "save-not", "S");
        }
        saveButton.setOnAction(new savePopulationEvent(this, app));

        // start button
        startPopulationSimulationButton = saveCreateButton("Play", "play", ">");

        addStyles();
        addProperties();

        // node placing
        populationNameLabelWrapper.getChildren().add(populationNameLabel);
        buttonsContainer.getChildren().addAll(saveButton, deletePopulationButton, startPopulationSimulationButton);
        this.getChildren().addAll(populationNameLabelWrapper, buttonsContainer);
    }

    private void addStyles(){
        try {
            this.getStylesheets().add(Objects.requireNonNull(this.getClass().getResource("/styles/MenuPopulationBoxStyle.css")).toExternalForm());
            this.getStyleClass().add("menu-population-box");
            populationNameLabel.getStyleClass().add("population-name-label");
            saveButton.getStyleClass().add("button");
            deletePopulationButton.getStyleClass().add("button");
            startPopulationSimulationButton.getStyleClass().add("button");
        }
        catch (NullPointerException e) {
            e.printStackTrace();
        }
    }

    private void addProperties() {
        // this box
        this.setAlignment(Pos.CENTER);
        this.setPadding(new Insets(8, 0, 8, 0));

        // buttons container
        buttonsContainer.setAlignment(Pos.CENTER);
        buttonsContainer.prefWidthProperty().bind(parentNode.widthProperty());
        buttonsContainer.setPadding(new Insets(0, 10, 0, 10));

        // buttons
        deletePopulationButton.setMaxWidth(Double.MAX_VALUE);
        startPopulationSimulationButton.setMaxWidth(Double.MAX_VALUE);
        saveButton.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(deletePopulationButton, Priority.ALWAYS);
        HBox.setHgrow(startPopulationSimulationButton, Priority.ALWAYS);
        HBox.setHgrow(saveButton, Priority.ALWAYS);

        // population Label with wrapper
        populationNameLabel.setPadding(new Insets(5, 30, 5, 30));
        populationNameLabel.setAlignment(Pos.CENTER_LEFT);
        populationNameLabelWrapper.setAlignment(Pos.CENTER_LEFT);
        HBox.setHgrow(populationNameLabelWrapper, Priority.ALWAYS);
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
        imgView.setFitHeight(18);
        imgView.setFitWidth(18);
        return imgView;
    }
}
