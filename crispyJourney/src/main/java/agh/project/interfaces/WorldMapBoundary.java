package agh.project.interfaces;

import agh.project.simulation.Animal;
import agh.project.simulation.Vector2d;

public interface WorldMapBoundary {
    public Vector2d moveAnimal(Vector2d newPosition, Animal animal);
    public boolean outOfBoundary(Vector2d position);
}
