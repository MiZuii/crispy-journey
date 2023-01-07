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
        return !position.follows(new Vector2d(0, 0)) || !position.precedes(new Vector2d(width - 1, height - 1));
    }

    /**
     Send Animal to the Hell where it lose their energy and then place them on the random place on the map
     */
    @Override
    public Vector2d moveAnimal(Vector2d newPosition, Animal animal) {
        animal.toHell();

        return new Vector2d(ThreadLocalRandom.current().nextInt(0, width + 1),
                    ThreadLocalRandom.current().nextInt(0,height + 1));
    }
}
