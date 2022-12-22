package agh.project.gui.menu;

import agh.project.App.App;
import agh.project.interfaces.WindowManager;
import javafx.stage.Stage;

public class MenuManager implements WindowManager {

    private App app;
    private Stage menuStageFX;
    public addPopulationEvent addPopulationButtonAction;
    public MenuStage menuStage;

    public MenuManager(App app){
        this.app = app;
        addPopulationButtonAction = new addPopulationEvent(app);
        menuStage = new MenuStage(app);
    }

    @Override
    public void start() {
        menuStageFX = menuStage.createStage();
        menuStageFX.show();
    }
}
