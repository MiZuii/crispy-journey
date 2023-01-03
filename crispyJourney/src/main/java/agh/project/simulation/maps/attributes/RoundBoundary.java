package agh.project.simulation.maps.attributes;

import agh.project.interfaces.WorldMapBoundary;
import agh.project.simulation.creations.Animal;
import agh.project.simulation.creations.attributes.Vector2d;

public class RoundBoundary implements WorldMapBoundary {
    public final int width;
    public final int height;

    public RoundBoundary(int height, int width){
        this.width = width;
        this.height = height;
    }
    @Override
    public Vector2d moveAnimal(Vector2d newPosition, Animal animal) {
        return new Vector2d((newPosition.x + width + 1) % (width + 1), (newPosition.y + height + 1) % (height + 1));
    }

    @Override
    public boolean outOfBoundary(Vector2d position) {
        return !position.follows(new Vector2d(0, 0)) || !position.precedes(new Vector2d(width, height));
    }
}
