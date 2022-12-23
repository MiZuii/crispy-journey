package agh.project.App;

import agh.project.gui.menu.MenuManager;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application{

    public PopulationsHolder populationsHolder;
    public MenuManager menuManager;

    @Override
    public void init() {}

    private void artificialInit() {
        populationsHolder = new PopulationsHolder();
        menuManager = new MenuManager(this);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        // running artificial initiator
        artificialInit();

        // run menu window
        menuManager.start();
    }
}
