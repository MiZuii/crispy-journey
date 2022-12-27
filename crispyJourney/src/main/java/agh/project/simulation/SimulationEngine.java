package agh.project.simulation;

import agh.project.enumerators.Direction;
import agh.project.enumerators.Rotation;
import agh.project.interfaces.IEngine;
import agh.project.interfaces.WorldElement;

import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Vector;

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
    private int energy;
    private int energyFromGrass;
    public int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }
    private void spawnAnimals(int sizeAnimals, int sizeGrass, int genSize){
        //loop for creating animals
        for(int i=0;i<sizeAnimals;i++){
            Gen gen = new Gen(genSize);
            AnimalFactory animal = new AnimalFactory();

            int randomX = getRandomNumber(0, width);
            int randomY = getRandomNumber(0, height);
            Vector2d randomPosition = new Vector2d(randomX,randomY);

            int pick = new Random().nextInt(Direction.values().length);
            Direction randomDirection = Direction.values()[pick];

            pick = new Random().nextInt(Rotation.values().length);
            Rotation randomGen = Rotation.values()[pick];

            animal.createAnimal(randomPosition, randomDirection, energy, randomGen, , )
        }

        //loop for creating grass
        for(int i=0;i<grassPerDay;i++){
            GrassFactory grass = new GrassFactory();
            int randomX = getRandomNumber(0, width);
            int randomY = getRandomNumber(0, height);
            Vector2d randomPosition = new Vector2d(randomX,randomY);
            grass.createGrass(randomPosition, energyFromGrass, )


        }
    }
    public SimulationEngine(int animalStartSpawningNumber, int grassPerDay,
                            int mapHeight, int mapWidth, int grassEnergyProfit,
                            int minEnergyCopulation, int animalStartEnergy, int genomLength){
        this.animalStartSpawningNumber = animalStartSpawningNumber;
        this.grassPerDay = grassPerDay;
        this.height = mapHeight;
        this.width = mapWidth;
        this.energy = animalStartEnergy;
        spawnAnimals(animalStartSpawningNumber, grassPerDay, genomLength);
        this.energyFromGrass = grassEnergyProfit;

    }

    @Override
    public void run() {
        //call move on every animal, pass dayOfSimulation
        //determine which animals eat grass
        //determine which animals copulate
        //spawn new grass
    }
}
