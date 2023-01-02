package agh.project.simulation.creations;

import agh.project.interfaces.WorldElement;
import agh.project.simulation.factories.GrassFactory;
import agh.project.simulation.maps.GrassMap;
import agh.project.simulation.creations.attributes.Vector2d;
import agh.project.simulation.creations.attributes.Energy;

import java.util.Objects;

public class  Grass implements WorldElement {

    //    -----ATTRIBUTES-----
    private final int id;
    private Vector2d position;
    private Energy energy;

    private GrassMap grassMapObserver;
    public GrassFactory grassFactory;


    //    -----METHODS-----
    @Override
    public int compareTo(Object o) {
        // We do not sort grass but there has to be this method
        return 0;
    }
    public Grass(int id, Vector2d position, Energy energy, GrassMap grassMapObserver, GrassFactory grassFactory) {
        this.id = id;
        this.position = position;
        this.energy = energy;
        this.grassMapObserver = grassMapObserver;
        this.grassFactory = grassFactory;
    }

    public Vector2d getPosition() {
        return position;
    }

    @Override
    public int getEnergy() {
        return energy.energy;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Grass grass)) return false;
        return id == grass.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public void eatGrass(){
        this.grassMapObserver.remove(this);
        this.grassFactory.deleteGrass();
    }
}
