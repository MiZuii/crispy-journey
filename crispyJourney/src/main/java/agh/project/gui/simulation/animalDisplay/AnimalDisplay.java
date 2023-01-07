package agh.project.gui.simulation.animalDisplay;

        import agh.project.gui.simulation.SimulationManager;
        import agh.project.interfaces.Updateable;
        import agh.project.simulation.DataStorage;
        import javafx.scene.control.Label;
        import javafx.scene.layout.HBox;
        import javafx.scene.layout.Priority;
        import javafx.scene.layout.VBox;

        import java.util.Objects;

public class AnimalDisplay extends VBox implements Updateable {


    private Label title;
    private HBox titleBox;
    private Label genome;
    private Label genomeData;
    private HBox genomeBox;
    private Label genomeActived;
    private Label genomeActivedData;
    private HBox genomeActivedBox;
    private Label energy;
    private Label energyData;
    private HBox energyBox;
    private Label grassEaten;
    private Label grassEatenData;
    private HBox grassEatenBox;
    private Label numberOfChildren;
    private Label numberOfChildrenData;
    private HBox numberOfChildrenBox;
    private Label lifeLength;
    private Label lifeLengthData;
    private HBox lifeLengthBox;
    private Label animalID;
    private Label animalIDData;
    private HBox animalIDBox;
    private final SimulationManager simulationManager;

    public AnimalDisplay(SimulationManager simulationManager) {
        this.simulationManager = simulationManager;

        createElements();
        addStyle();
        addProperties();
    }

    private void createElements() {

        title = new Label("Animal information");
        titleBox = new HBox(title);

        genome = new Label("Genes: ");
        genomeData = new Label("");
        genomeBox = new HBox();
        genomeBox.getChildren().addAll(genome, genomeData);

        genomeActived = new Label("Actived genome: ");
        genomeActivedData = new Label("");
        genomeActivedBox = new HBox();
        genomeActivedBox.getChildren().addAll(genomeActived, genomeActivedData);

        energy = new Label("Energy: ");
        energyData = new Label("");
        energyBox = new HBox();
        energyBox.getChildren().addAll(energy, energyData);

        grassEaten = new Label("Number of grass eaten: ");
        grassEatenData = new Label("");
        grassEatenBox = new HBox();
        grassEatenBox.getChildren().addAll(grassEaten, grassEatenData);

        numberOfChildren = new Label("Number of children: ");
        numberOfChildrenData = new Label("");
        numberOfChildrenBox = new HBox();
        numberOfChildrenBox.getChildren().addAll(numberOfChildren, numberOfChildrenData);

        lifeLength = new Label("Life length: ");
        lifeLengthData = new Label("");
        lifeLengthBox = new HBox();
        lifeLengthBox.getChildren().addAll(lifeLength, lifeLengthData);

        animalID = new Label("Animal ID: ");
        animalIDData = new Label("Not Selected");
        animalIDBox = new HBox();
        animalIDBox.getChildren().addAll(animalID, animalIDData);

        this.getChildren().addAll(titleBox, animalIDBox, genomeBox, genomeActivedBox, energyBox, grassEatenBox, numberOfChildrenBox, lifeLengthBox);
    }

    private void addStyle() {
        try {
            this.getStylesheets().add(Objects.requireNonNull(this.getClass().getResource("/styles/PopulationDisplayStyle.css")).toExternalForm());
            this.getStyleClass().add("root");
            title.getStyleClass().add("title");
            titleBox.getStyleClass().add("root");
            genome.getStyleClass().add("info");
            genomeData.getStyleClass().add("data");
            genomeActived.getStyleClass().add("info");
            genomeActivedData.getStyleClass().add("data");
            energy.getStyleClass().add("info");
            energyData.getStyleClass().add("data");
            grassEaten.getStyleClass().add("info");
            grassEatenData.getStyleClass().add("data");
            numberOfChildren.getStyleClass().add("info");
            numberOfChildrenData.getStyleClass().add("data");
            lifeLength.getStyleClass().add("info");
            lifeLengthData.getStyleClass().add("data");
            animalID.getStyleClass().add("info");
            animalIDData.getStyleClass().add("data");
        }
        catch (NullPointerException e) {
            System.out.println("Menu scene style sheet couldn't have been loaded.");
            e.printStackTrace();
        }
    }

    private void addProperties() {
        HBox.setHgrow(titleBox, Priority.ALWAYS);
    }

    @Override
    public void update(DataStorage data) {
        this.animalIDData.setText(String.valueOf(simulationManager.getSelectedAnimalID()));
        if (simulationManager.getSelectedAnimalID() >= 0) {
            this.genomeData.setText(data.getGen().toString());
            this.genomeActivedData.setText(Integer.toString(data.getActualGenIndex()));
            this.energyData.setText(Integer.toString(data.getEnergy()));
            this.grassEatenData.setText(Integer.toString(data.getPlantEaten()));
            this.numberOfChildrenData.setText(Integer.toString(data.getChildren()));
            this.lifeLengthData.setText(Integer.toString(data.getLiveLength()));
        }
        else {
            this.genomeData.setText("");
            this.genomeActivedData.setText("");
            this.energyData.setText("");
            this.grassEatenData.setText("");
            this.numberOfChildrenData.setText("");
            this.lifeLengthData.setText("");
        }
    }
}
