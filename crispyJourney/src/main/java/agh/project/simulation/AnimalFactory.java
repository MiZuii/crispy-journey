package agh.project.simulation;

import agh.project.enumerators.Direction;

public class AnimalFactory {

//    -----ATTRIBUTES------
    private int actualIndex;
    private int liveAnimal;



//    -----METHODS------
    public AnimalFactory(){
        this.liveAnimal = 0;
        this.actualIndex = -1;
    }

    public Animal createAnimal(Vector2d position, Direction direction, Energy energy, Gen gen){
        this.liveAnimal += 1;
        this.actualIndex += 1;
        return new Animal(actualIndex,position,direction,energy,gen);
    }

    public void deleteAnimal(){
        this.liveAnimal -= 1;
    }

}
