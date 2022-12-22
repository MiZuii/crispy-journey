package agh.project.gui.menu;

import agh.project.App.App;
import agh.project.gui.menu.events.addPopulationEvent;
import agh.project.interfaces.WindowManager;
import javafx.stage.Stage;

/**
 *
 * Class MenuManager is a class responsible for creating and managing
 * menu window.
 *
 * @author Piotr
 *
 */
public class MenuManager implements WindowManager {

    public MenuStage menuStage;

    public MenuManager(App app){
        menuStage = new MenuStage(app);
    }

    /**
     *
     * Methode responsible for creating window stage and showing it
     *
     */
    @Override
    public void start() {
        Stage menuStageFX = menuStage.createStage();
        menuStageFX.show();
    }
}
