package agh.project.simulation;

public class GrassFactory {

    //    -----ATTRIBUTES------
    private int liveGrass;
    private int grassId;


    //    -----METHODS------
    public GrassFactory(){
        this.liveGrass = 0;
        this.grassId = 0;
    }

    public Grass createGrass(Vector2d position,Energy energy, GrassMap grassMapObserver){
        this.liveGrass += 1;
        this.grassId += 1;
        return new Grass(this.grassId,position,energy, grassMapObserver);
    }

    public void deleteGrass(){
        this.liveGrass -= 1;
    }
}
