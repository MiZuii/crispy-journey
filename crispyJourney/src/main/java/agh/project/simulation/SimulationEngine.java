package agh.project.simulation;

import agh.project.World;
import agh.project.enumerators.Direction;
import agh.project.enumerators.Rotation;
import agh.project.gui.simulation.CSVCreator;
import agh.project.gui.simulation.SimulationManager;
import agh.project.interfaces.IEngine;
import agh.project.interfaces.WorldElement;
import agh.project.interfaces.WorldMapBoundary;
import agh.project.simulation.creations.Animal;
import agh.project.simulation.creations.Grass;
import agh.project.simulation.creations.attributes.Energy;
import agh.project.simulation.creations.attributes.Gen;
import agh.project.simulation.creations.attributes.Vector2d;
import agh.project.simulation.factories.AnimalFactory;
import agh.project.simulation.factories.GrassFactory;
import agh.project.simulation.maps.AnimalMap;
import agh.project.simulation.maps.GrassMap;
import agh.project.simulation.maps.attributes.Hell;
import agh.project.simulation.maps.attributes.RoundBoundary;


import java.util.*;

import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;


public class SimulationEngine extends Thread implements IEngine {

    public AtomicBoolean newDataToReceive = new AtomicBoolean(false);
    public AtomicInteger simulationSpeed = new AtomicInteger(50);

    private AnimalMap animalMap;
    private GrassMap grassMap;
    private int dayOfSimulation = 0;
    private final double equator = 0.2;    //How much of height does equator take
    private int grassPerDay;

    private int width;
    private int height;
    private Energy animalStartEnergy;
    private Energy grassEnergyProfit;
    private AnimalFactory animalFactory;
    private GrassFactory grassFactory;

    private Statistics statistics;

    private int highlightedAnimalId = -2;

    public SimulationEngine(Population population, SimulationManager simulationManager, CSVCreator csvCreator) {
        // this is the tight structure of constructor
        // if the csvCreator is not null the method addData should be called here after each day
        // to add data to the csv file
        this.height = population.mapHeight;
        this.width = population.mapWidth;
        this.grassPerDay = population.grassPerDay;
        this.animalStartEnergy = new Energy(population.animalStartEnergy);
        this.grassEnergyProfit = new Energy(population.grassEnergyProfit);

        //Set all static variables in Gen and Energy
        Energy.setReproduceBoundary(population.minEnergyCopulation);
        Energy.setReproduceEnergy(population.energyPerCopulation);
        Energy.setOneDayLost(population.dailyEnergyLost);

        Gen.setGensNumber(population.genomLength);
        Gen.setMutationNumber(population.maxMutationNumber);
        Gen.setChaoticGen(population.mutationFlag);

        //seting maps
        WorldMapBoundary worldMapBoundary;
        if (population.mapFlag){ // Hell
            worldMapBoundary = new Hell(population.mapHeight, population.mapWidth);
        }
        else { // RoundMap
            worldMapBoundary = new RoundBoundary(population.mapHeight, population.mapWidth);
        }
        this.animalMap = new AnimalMap(worldMapBoundary);
        this.grassMap = new GrassMap(worldMapBoundary);

        Animal.setMapObserver(animalMap);

        //setting factories
        this.grassFactory = new GrassFactory();
        this.animalFactory = new AnimalFactory(this);

        Animal.setAnimalFactory(animalFactory);

        //setting initial positions
        spawnAnimals(population.animalStartSpawningNumber);
        spawnGrass(grassPerDay);

        //setting statistics
        this.statistics = new Statistics(this.animalFactory, this.grassFactory, this.height*this.width);
    }


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


            int randomX = getRandomNumber(0, width);
            int randomY = getRandomNumber(0, height);
            Vector2d randomPosition = new Vector2d(randomX, randomY);

            int pick = new Random().nextInt(Direction.values().length);
            Direction randomDirection = Direction.values()[pick];

            Animal animal = animalFactory.createAnimal(randomPosition, randomDirection, animalStartEnergy, randomGen);

            this.animalMap.place((WorldElement) animal);
        }
    }




    public int getDayOfSimulation() {
        return dayOfSimulation;
    }

    public synchronized DataStorage getData() {

        return new DataStorage(this.animalFactory.liveAnimal,this.grassFactory.liveGrass, this.animalMap,
                this.grassMap, this.height, this.width, statistics.getAverageLifeTime(), statistics.getAverageLifeTimeDeath(),
                statistics.getCurrentMostPopularGenotype(), statistics.averageEnergy);
    }

    public synchronized DataStorage getAnimalData(int id){
        Animal animal = this.animalFactory.animals.get(id);
        return animal.toDataStorage();
    }


    @Override
    public void run() {
        while (true) {

            Collection <ArrayList<WorldElement>> copy = new ArrayList<>(animalMap.occupiedPosition.values());

            for (ArrayList<WorldElement> animals : copy) {

                ArrayList<WorldElement> deepcopy = new ArrayList<>(animals);

                for (WorldElement animal : deepcopy) {
                    Animal castedAnimal = (Animal) animal;
                    castedAnimal.move();
                }
            }

            Collection <ArrayList<WorldElement>> copy2 = new ArrayList<>(animalMap.occupiedPosition.values());

            for (ArrayList<WorldElement> animals : copy2) {

                ArrayList<WorldElement> animalsCopy = new ArrayList<>(animals);

                Collections.sort(animalsCopy); //sort by energy, age, children

                //eating
                Animal animalToEat = (Animal) animalsCopy.get(0);
                if (grassMap.occupiedPosition.containsKey(animalToEat.getPosition())) {
                    Grass grassToEat = (Grass) grassMap.occupiedPosition.get(animalToEat.getPosition()).get(0);
                    animalToEat.eat(grassToEat);
                }

                //copulating
                for (int i = 0; i < animalsCopy.size() / 2; i+=2) {
                    if(i+1>=animalsCopy.size())
                        break;
                    this.animalMap.place((WorldElement)animalFactory.createChild(animalsCopy.get(i), animalsCopy.get(i+1)) );
                }
                //TODO deathDay
                spawnGrass(grassPerDay);
                dayOfSimulation += 1;
                statistics.update(false);
            }
            statistics.update(true);

            // a day has passed so new data should be generated
            // gui uses this boolean to determine if it should
            // get new data using this.getData()
            newDataToReceive.set(true);


            // interval speed can be adjusted from gui level -> simulation speed is atomic
            try {
                sleep(simulationSpeed.get());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
