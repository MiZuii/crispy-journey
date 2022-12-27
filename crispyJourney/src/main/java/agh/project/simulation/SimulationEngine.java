package agh.project.simulation;

import agh.project.enumerators.Direction;
import agh.project.enumerators.Rotation;
import agh.project.interfaces.IEngine;
import agh.project.interfaces.WorldElement;
import agh.project.simulation.creations.Animal;
import agh.project.simulation.creations.Grass;
import agh.project.simulation.creations.attributes.Energy;
import agh.project.simulation.creations.attributes.Gen;
import agh.project.simulation.creations.attributes.Vector2d;
import agh.project.simulation.factories.AnimalFactory;
import agh.project.simulation.factories.GrassFactory;
import agh.project.simulation.maps.AnimalMap;
import agh.project.simulation.maps.GrassMap;

import java.util.Random;

public class SimulationEngine implements IEngine {

    private AnimalMap animalMap;
    private GrassMap grassMap;
    private int dayOfSimulation = 0;

    //set initial positions of animals
    //set initial positions of grass

    private int animalStartSpawningNumber;
    private int grassPerDay;
    private int width;
    private int height;
    private Energy energy;
    private Energy energyFromGrass;
    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
    private void spawnAnimals(int sizeAnimals, int sizeGrass, int genSize){
        //loop for creating animals
        for(int i=0;i<sizeAnimals;i++){

//            Create Random Gen for starting Animals
            Gen randomGen = Gen.getRandomGen();
            AnimalFactory animalFactory = new AnimalFactory();

            int randomX = getRandomNumber(0, width);
            int randomY = getRandomNumber(0, height);
            Vector2d randomPosition = new Vector2d(randomX,randomY);

            int pick = new Random().nextInt(Direction.values().length);
            Direction randomDirection = Direction.values()[pick];

            pick = new Random().nextInt(Rotation.values().length);

            Animal animal = animalFactory.createAnimal(randomPosition, randomDirection, energy, randomGen,
                    this.animalMap,this.grassMap);

            this.animalMap.place((WorldElement) animal);
        }

        //loop for creating grass
        for(int i=0;i<grassPerDay;i++){
            GrassFactory grassFactory = new GrassFactory();
            int randomX = getRandomNumber(0, width);
            int randomY = getRandomNumber(0, height);
            Vector2d randomPosition = new Vector2d(randomX,randomY);
            Grass grass = grassFactory.createGrass(randomPosition, energyFromGrass, this.grassMap);
            this.grassMap.place((WorldElement) grass);

        }
    }
    public SimulationEngine(int animalStartSpawningNumber, int grassPerDay,
                            int mapHeight, int mapWidth, int grassEnergyProfit,
                            int minEnergyCopulation, int animalStartEnergy, int genomLength){
        this.animalStartSpawningNumber = animalStartSpawningNumber;
        this.grassPerDay = grassPerDay;
        this.height = mapHeight;
        this.width = mapWidth;
        this.energy = new Energy(animalStartEnergy);
        spawnAnimals(animalStartSpawningNumber, grassPerDay, genomLength);
        this.energyFromGrass = new Energy(grassEnergyProfit);

//        Set all static variables in Gen and Energy

    }

    @Override
    public void run() {
        //call move on every animal, pass dayOfSimulation
        //determine which animals eat grass
        //determine which animals copulate
        //spawn new grass
    }
}
