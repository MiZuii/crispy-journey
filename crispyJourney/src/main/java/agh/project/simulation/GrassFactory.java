package agh.project.simulation;

public class GrassFactory {

    //    -----ATTRIBUTES------
    private int liveGrass;



    //    -----METHODS------
    public GrassFactory(){
        this.liveGrass = 0;
    }

    public Grass createGrass(Vector2d position,Energy energy){
        this.liveGrass += 1;
        return new Grass(position,energy);
    }

    public void deleteAnimal(){
        this.liveGrass -= 1;
    }
}
