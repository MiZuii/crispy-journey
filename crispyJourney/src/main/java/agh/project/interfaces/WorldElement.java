package agh.project.interfaces;

import agh.project.simulation.creations.attributes.Energy;
import agh.project.simulation.creations.attributes.Vector2d;

public interface WorldElement extends Comparable<Object> {
    public Vector2d getPosition();
    public int getEnergy();
}
