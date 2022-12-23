package agh.project.simulation;

import agh.project.interfaces.WorldMapBoundary;

public class RoundBoundary implements WorldMapBoundary {

    public final int width;
    public final int height;

    public RoundBoundary(int width, int height){
        this.width = width;
        this.height = height;
    }
    @Override
    public Vector2d moveAnimal(Vector2d newPosition, Animal animal) {
        Vector2d roundPosition = new Vector2d(newPosition.x % width, newPosition.y % height);
        animal.changePosition(roundPosition);
        return animal.getPosition();
    }

    @Override
    public boolean outOfBoundary(Vector2d position) {
        return position.x <= width && position.y <= height && position.x >= 0 && position.y >= 0;
    }
}
