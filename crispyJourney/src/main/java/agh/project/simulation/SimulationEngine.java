package agh.project.simulation;

import agh.project.enumerators.Direction;
import agh.project.enumerators.Rotation;
import agh.project.gui.simulation.CSVCreator;
import agh.project.gui.simulation.SimulationManager;
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
    private int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }

    private int getRandomY(){
        //equator
        if(getRandomNumber(0, 5)!=0)
            return getRandomNumber((height/2-(int)(equator*(height/2))),(height/2+(int)(equator*(height/2)))+1);
        //not equator
        else{
            //upper part
            if(getRandomNumber(0,2)==1)
                return getRandomNumber((height/2+(int)(equator*(height/2)))+1, height);
            //lower part
            else
                return getRandomNumber(0, (height/2-(int)(equator*(height/2))));
        }
    }

    private void spawnGrass(int sizeGrass){
        //loop for creating grass
        for(int i=0;i<sizeGrass;i++){
            GrassFactory grassFactory = new GrassFactory();

            int randomX = getRandomNumber(0, width);
            int randomY = getRandomY();

            Vector2d randomPosition = new Vector2d(randomX,randomY);
            Grass grass = grassFactory.createGrass(randomPosition, grassEnergyProfit, this.grassMap);
            this.grassMap.place((WorldElement) grass);
        }
    }
    private void spawnAnimals(int sizeAnimals){
        //loop for creating animals
        for(int i=0;i<sizeAnimals;i++){
//          Create Random Gen for starting Animals
            Gen randomGen = Gen.getRandomGen();
            AnimalFactory animalFactory = new AnimalFactory();

            int randomX = getRandomNumber(0, width);
            int randomY = getRandomNumber(0, height);
            Vector2d randomPosition = new Vector2d(randomX,randomY);

            int pick = new Random().nextInt(Direction.values().length);
            Direction randomDirection = Direction.values()[pick];

            Animal animal = animalFactory.createAnimal(randomPosition, randomDirection, animalStartEnergy, randomGen);

            this.animalMap.place((WorldElement) animal);
        }
    }

    public SimulationEngine(Population population, SimulationManager simulationManager, CSVCreator csvCreator) {
        // this is the tight structure of constructor
        // if the csvCreator is not null the method addData should be called here after each day
        // to add data to the csv file
    }
    public SimulationEngine(int mapHeight, int mapWidth, int grassEnergyProfit,
                            int minEnergyCopulation, int animalStartEnergy, int dailyEnergyLost,
                            int animalStartSpawningNumber, int grassPerDay, int refreshment,
                            int energyPerCopulation, int maxMutationNumber, int genomLength,
                            boolean mutationFlag){

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
        while(true){
            //TODO
            //call move on every animal, pass dayOfSimulation to set deathDay
            //determine which animals eat grass
            //determine which animals copulate
            spawnGrass(grassPerDay);
            dayOfSimulation += 1;

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
