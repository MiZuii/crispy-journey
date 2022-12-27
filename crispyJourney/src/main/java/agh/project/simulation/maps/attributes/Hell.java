package agh.project.simulation.maps.attributes;

import agh.project.interfaces.WorldMapBoundary;
import agh.project.simulation.creations.Animal;
import agh.project.simulation.creations.attributes.Vector2d;

import java.util.concurrent.ThreadLocalRandom;

public class Hell implements WorldMapBoundary {
    public final int height;
    public final int width;

    public Hell(int height, int width){
        this.height = height;
        this.width = width;
    }

    /**
     * Check if animal is going to be out of the Map
     */
    public boolean outOfBoundary(Vector2d position){
        return position.x <= width && position.y <= height &&
                position.x >= 0 && position.y >= 0;
    }

    /**
     Send Animal to the Hell where it lose their energy and then place them on the random place on the map
     */
    @Override
    public Vector2d moveAnimal(Vector2d newPosition, Animal animal) {
        animal.toHell();
        Vector2d outOfHellPosition = new Vector2d(ThreadLocalRandom.current().nextInt(0, width + 1),
                    ThreadLocalRandom.current().nextInt(0,height + 1));
        animal.changePosition(outOfHellPosition);

        return outOfHellPosition;
    }
}
