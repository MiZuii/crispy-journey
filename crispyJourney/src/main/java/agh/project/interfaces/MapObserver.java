package agh.project.interfaces;

import agh.project.simulation.Animal;
import agh.project.simulation.Vector2d;

public interface MapObserver {

    /**
     * Change position of the Animal on the map
    */
    public void changePosition(Vector2d oldPosition, Vector2d newPosition, Animal animal);


}
