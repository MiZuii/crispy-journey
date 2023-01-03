package agh.project.simulation.factories;

import agh.project.enumerators.Direction;
import agh.project.interfaces.WorldElement;
import agh.project.simulation.SimulationEngine;
import agh.project.simulation.maps.AnimalMap;
import agh.project.simulation.maps.GrassMap;
import agh.project.simulation.creations.Animal;
import agh.project.simulation.creations.attributes.Gen;
import agh.project.simulation.creations.attributes.Energy;
import agh.project.simulation.creations.attributes.Vector2d;

import java.util.ArrayList;
import java.util.Random;

public class AnimalFactory {

//    -----ATTRIBUTES------
    public int actualIndex;
    public int liveAnimal;
    public ArrayList<Animal> animals;
    public ArrayList<Animal> deathAnimals;

    public SimulationEngine simulationEngine;

//    -----METHODS------
    public AnimalFactory(SimulationEngine simulationEngine){
        this.liveAnimal = 0;
        this.actualIndex = -1;
        this.animals = new ArrayList<Animal>();
        this.simulationEngine = simulationEngine;
        this.deathAnimals = new ArrayList<Animal>();
    }
    public AnimalFactory(){
        this.liveAnimal = 0;
        this.actualIndex = -1;
        this.animals = new ArrayList<Animal>();
        this.simulationEngine = null;
        this.deathAnimals = new ArrayList<Animal>();
    }
    public Animal createAnimal(Vector2d position, Direction direction, Energy energy, Gen gen){
        this.liveAnimal += 1;
        this.actualIndex += 1;
        this.animals.add(new Animal(actualIndex,position,direction,energy,gen));
        return animals.get(animals.size() - 1);
    }

    public Animal createChild(WorldElement par1, WorldElement par2){
        Animal parent1 = (Animal) par1;
        Animal parent2 = (Animal) par2;
        if (!Energy.reproduceEvent(new Energy(parent1.getEnergy()),new Energy(parent2.getEnergy()))) return null;

        this.liveAnimal += 1;
        this.actualIndex += 1;

//        The child is turned to a random side
        int pick = new Random().nextInt(Direction.values().length);
        Direction direction = Direction.values()[pick];

//        The child Gen starts from random
        Gen gen = Gen.newGens(parent1, parent2);
        gen.randomUpdateGen();

        this.animals.add(new Animal(actualIndex,parent1.getPosition(),direction,new Energy(Energy.reproduceEnergy * 2),gen));
        return animals.get(animals.size() - 1);
    }

    public void deleteAnimal(Animal animal){
        this.liveAnimal -= 1;
//        this.animals.remove(animal);
        this.deathAnimals.add(animal);
        if (simulationEngine != null) animal.deathDay = simulationEngine.getDayOfSimulation();
    }

}
