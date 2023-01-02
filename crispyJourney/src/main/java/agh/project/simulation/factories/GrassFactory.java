package agh.project.simulation.factories;

import agh.project.simulation.maps.GrassMap;
import agh.project.simulation.creations.Grass;
import agh.project.simulation.creations.attributes.Energy;
import agh.project.simulation.creations.attributes.Vector2d;

public class GrassFactory {

    //    -----ATTRIBUTES------
    public int liveGrass;
    private int grassId;


    //    -----METHODS------
    public GrassFactory(){
        this.liveGrass = 0;
        this.grassId = 0;
    }

    public Grass createGrass(Vector2d position, Energy energy, GrassMap grassMapObserver){
        this.liveGrass += 1;
        this.grassId += 1;
        return new Grass(this.grassId,position,energy, grassMapObserver, this);
    }

    public void deleteGrass(){
        this.liveGrass -= 1;
    }
}
