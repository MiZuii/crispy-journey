package agh.project.interfaces;

import javafx.scene.Scene;

/**
 * Interface managing classes responsible for
 * creating scenes
 *
 * @author MiZuii
 *
 */
public interface SceneCreator {

    /**
     *
     * Methode responsible for creating a new scene
     *
     * @return returns instance of javafx scene
     *
     */
    Scene createScene();
}
