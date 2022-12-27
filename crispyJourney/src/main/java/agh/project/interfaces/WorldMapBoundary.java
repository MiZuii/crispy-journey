package agh.project.interfaces;

import agh.project.simulation.creations.Animal;
import agh.project.simulation.creations.attributes.Vector2d;

public interface WorldMapBoundary {
    public Vector2d moveAnimal(Vector2d newPosition, Animal animal);
    public boolean outOfBoundary(Vector2d position);
}
