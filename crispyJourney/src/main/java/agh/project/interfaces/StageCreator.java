package agh.project.interfaces;

import javafx.stage.Stage;

/**
 * Interface managing classes responsible for
 * creating stages
 *
 * @author MiZuii
 *
 */
public interface StageCreator {

    /**
     *
     * Methode responsible for creating a new stage
     *
     * @return returns instance of javafx scene
     *
     */
    Stage createStage();
}
