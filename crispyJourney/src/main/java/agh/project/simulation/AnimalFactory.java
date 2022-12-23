package agh.project.simulation;

import agh.project.enumerators.Direction;
import agh.project.enumerators.Rotation;
import agh.project.interfaces.MapObserver;

import java.util.ArrayList;
import java.util.Random;

public class AnimalFactory {

//    -----ATTRIBUTES------
    public int actualIndex;
    public int liveAnimal;

    public ArrayList<Animal> animals;


//    -----METHODS------
    public AnimalFactory(){
        this.liveAnimal = 0;
        this.actualIndex = -1;
        this.animals = new ArrayList<Animal>();
    }

    public Animal createAnimal(Vector2d position, Direction direction, Energy energy, Gen gen,
                               MapObserver mapObserver, GrassMap grassMapObserver){
        this.liveAnimal += 1;
        this.actualIndex += 1;
        this.animals.add(new Animal(actualIndex,position,direction,energy,gen, mapObserver, grassMapObserver, this));
        return animals.get(animals.size() - 1);
    }

    public Animal createChild(Vector2d position, Energy energy, Gen gen, MapObserver mapObserver, GrassMap grassMapObserver){
        this.liveAnimal += 1;
        this.actualIndex += 1;

//        The child is turned to a random side
        int pick = new Random().nextInt(Direction.values().length);
        Direction direction = Direction.values()[pick];

//        The child Gen starts from random
        gen.randomUpdateGen();

        this.animals.add(new Animal(actualIndex,position,direction,energy,gen, mapObserver, grassMapObserver, this));
        return animals.get(animals.size() - 1);
    }

    public void deleteAnimal(Animal animal){
        this.liveAnimal -= 1;
        this.animals.remove(animal);
    }

}
