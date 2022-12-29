package agh.project.simulation;

import agh.project.World;
import agh.project.enumerators.Direction;
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


import java.util.*;

import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;


public class SimulationEngine extends Thread implements IEngine {

    private AnimalMap animalMap;
    private GrassMap grassMap;
    private int dayOfSimulation = 0;
    private final double equator = 0.2;    //How much of height does equator take
    private final int grassPerDay;

    private final int width;
    private final int height;
    private final Energy animalStartEnergy;
    private final Energy grassEnergyProfit;

    private int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    private int getRandomY() {
        //equator
        if (getRandomNumber(0, 5) != 0)
            return getRandomNumber((height / 2 - (int) (equator * (height / 2))), (height / 2 + (int) (equator * (height / 2))) + 1);
            //not equator
        else {
            //upper part
            if (getRandomNumber(0, 2) == 1)
                return getRandomNumber((height / 2 + (int) (equator * (height / 2))) + 1, height);
                //lower part
            else
                return getRandomNumber(0, (height / 2 - (int) (equator * (height / 2))));
        }
    }

    private void spawnGrass(int sizeGrass) {
        //loop for creating grass
        for (int i = 0; i < sizeGrass; i++) {
            GrassFactory grassFactory = new GrassFactory();

            int randomX = getRandomNumber(0, width);
            int randomY = getRandomY();

            Vector2d randomPosition = new Vector2d(randomX, randomY);
            Grass grass = grassFactory.createGrass(randomPosition, grassEnergyProfit, this.grassMap);
            this.grassMap.place((WorldElement) grass);
        }
    }

    private void spawnAnimals(int sizeAnimals) {
        //loop for creating animals
        for (int i = 0; i < sizeAnimals; i++) {
//          Create Random Gen for starting Animals
            Gen randomGen = Gen.getRandomGen();
            AnimalFactory animalFactory = new AnimalFactory();

            int randomX = getRandomNumber(0, width);
            int randomY = getRandomNumber(0, height);
            Vector2d randomPosition = new Vector2d(randomX, randomY);

            int pick = new Random().nextInt(Direction.values().length);
            Direction randomDirection = Direction.values()[pick];

            Animal animal = animalFactory.createAnimal(randomPosition, randomDirection, animalStartEnergy, randomGen);

            this.animalMap.place((WorldElement) animal);
        }
    }

    public SimulationEngine(int mapHeight, int mapWidth, int grassEnergyProfit,
                            int minEnergyCopulation, int animalStartEnergy, int dailyEnergyLost,
                            int animalStartSpawningNumber, int grassPerDay, int refreshment,
                            int energyPerCopulation, int maxMutationNumber, int genomLength,
                            boolean mutationFlag) {

        this.height = mapHeight;
        this.width = mapWidth;
        this.grassPerDay = grassPerDay;
        this.animalStartEnergy = new Energy(animalStartEnergy);
        this.grassEnergyProfit = new Energy(grassEnergyProfit);

        //Set all static variables in Gen and Energy
        Energy.setReproduceBoundary(minEnergyCopulation);
        Energy.setReproduceEnergy(energyPerCopulation);
        Energy.setOneDayLost(dailyEnergyLost);

        Gen.setGensNumber(genomLength);
        Gen.setMutationNumber(maxMutationNumber);
        Gen.setChaoticGen(mutationFlag);

        //setting initial positions
        spawnAnimals(animalStartSpawningNumber);
        spawnGrass(grassPerDay);

        //starting simulation
        run();
    }

    public synchronized DataStorage getData() {
        // this is only an example
        // don't pass references to object because the
        // objects are not synchronized
        // only basic types can be passed otherwise it can
        // cause memory access problems

        return new DataStorage(5);
    }

    @Override
    public void run() {
        while (true) {
            //call move on every animal
            //TODO set deathDay
            for (ArrayList<WorldElement> animals : animalMap.occupiedPosition.values()) {
                for (WorldElement animal : animals) {
                    Animal castedAnimal = (Animal) animal;
                    castedAnimal.move();
                }
            }

            for (ArrayList<WorldElement> animals : this.animalMap.occupiedPosition.values()) {
                Collections.sort(animals);


                Animal animalToEat = (Animal) animals.get(0);
                if (grassMap.occupiedPosition.containsKey(animalToEat.getPosition()))
                    animalToEat.eat(grassEnergyProfit.energy);

                for (int i = 0; i < animals.size() / 2; i++) {

                }

                spawnGrass(grassPerDay);
                dayOfSimulation += 1;
            }

        }
    }
}
