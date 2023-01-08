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
    public AtomicInteger simulationSpeed = new AtomicInteger(400);
    public AtomicBoolean running = new AtomicBoolean(true);

    private AnimalMap animalMap;
    private GrassMap grassMap;
    private int dayOfSimulation = 0;

    private int grassPerDay;

    private int width;
    private int height;
    private Energy animalStartEnergy;
    private Energy grassEnergyProfit;
    private AnimalFactory animalFactory;
    private GrassFactory grassFactory;

    private Statistics statistics;

    private final double equator = 0.2;    //How much of height does equator take
    private int equatorLowIndex;
    private int equatorHighIndex;
    private ArrayList<Vector2d> equatorGrass;
    private ArrayList<Vector2d> nonEquatorGrass;

    private Constants simulationConstants;

    private int highlightedAnimalId = -2;

    private SimulationManager simulationManager;
    private CSVCreator csvCreator;

    public SimulationEngine(Population population, SimulationManager simulationManager, CSVCreator csvCreator) {
        // this is the tight structure of constructor
        // if the csvCreator is not null the method addData should be called here after each day
        // to add data to the csv file
        this.height = population.mapHeight;
        this.width = population.mapWidth;
        this.grassPerDay = population.grassPerDay;
        this.animalStartEnergy = new Energy(population.animalStartEnergy);
        this.grassEnergyProfit = new Energy(population.grassEnergyProfit);
        this.simulationSpeed.set(population.refreshment);
        this.simulationManager = simulationManager;
        this.csvCreator = csvCreator;


        //Set equatorIndex
        setEquatorIndex();


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


        //setting factories
        this.grassFactory = new GrassFactory();
        this.animalFactory = new AnimalFactory(this);

        //Setting all constants
        Constants simulationConstants = new Constants(this.animalMap,this.animalFactory,
                population.dailyEnergyLost, population.energyPerCopulation, population.minEnergyCopulation,
                population.genomLength, population.maxMutationNumber, population.moveFlag, population.mutationFlag);
        this.simulationConstants = simulationConstants;

        this.animalFactory.setConstants(this.simulationConstants);



        //setting initial positions
        spawnAnimals(population.animalStartSpawningNumber);
        spawnGrass(grassPerDay);

        //setting statistics
        this.statistics = new Statistics(this.animalFactory, this.grassFactory, this.height*this.width, this.animalMap);
    }

    private void fillGrassArrays(){
        this.equatorGrass = new ArrayList<Vector2d>();
        this.nonEquatorGrass = new ArrayList<Vector2d>();

        for (int i = 0; i < width; i+=1){
            for (int j = 0; j < height; j+=1){
                Vector2d position = new Vector2d(i, j);
                if (this.grassMap.isOccupied(position)) continue;

                if (isEquator(i, j)) this.equatorGrass.add(position);
                else this.nonEquatorGrass.add(position);
            }
        }
    }

    private boolean isEquator(int x, int y){
        return this.equatorLowIndex <= y && y <= this.equatorHighIndex;
    }

    private void setEquatorIndex(){
        this.equatorLowIndex = height / 2 - (int) (equator * (height / 2));
        this.equatorHighIndex = height / 2 + (int) (equator * (height / 2));
    }


    private int getRandomNumber(int min, int max) {
        return (int) ((Math.random() * (max - min)) + min);
    }


    private void spawnGrass(int sizeGrass) {
        //Setting domain
        fillGrassArrays();

        for (int i = 0; i < sizeGrass; i++){
            boolean growFlag = false;

//            Equator
            if (getRandomNumber(0, 5) != 0){
                if (this.equatorGrass.size() > 0){
                    int index = getRandomNumber(0,this.equatorGrass.size());
                    Vector2d spawnPosition = this.equatorGrass.get(index);
                    equatorGrass.remove(spawnPosition);

                    Grass grass = this.grassFactory.createGrass(spawnPosition, new Energy(this.grassEnergyProfit.energy),this.grassMap);
                    this.grassMap.place((WorldElement) grass);
                    growFlag = true;
                }
            }
//            Grass has not planted yet
            if (!growFlag){
                if(this.nonEquatorGrass.size() > 0){
                    int index = getRandomNumber(0, this.nonEquatorGrass.size());
                    Vector2d spawnPosition = this.nonEquatorGrass.get(index);
                    nonEquatorGrass.remove(spawnPosition);

                    Grass grass = this.grassFactory.createGrass(spawnPosition, new Energy(this.grassEnergyProfit.energy),this.grassMap);
                    this.grassMap.place((WorldElement) grass);
                }
            }
        }
    }

    private void spawnAnimals(int sizeAnimals) {
        //loop for creating animals

        for (int i = 0; i < sizeAnimals; i++) {
//          Create Random Gen for starting Animals

            Gen nonExistingGen = new Gen(new ArrayList<>(List.of(Rotation.F)));
            nonExistingGen.setConstants(this.simulationConstants);

            Gen randomGen = nonExistingGen.getRandomGen();
            randomGen.setConstants(this.simulationConstants);

            int randomX = getRandomNumber(0, width);
            int randomY = getRandomNumber(0, height);
            Vector2d randomPosition = new Vector2d(randomX, randomY);

            int pick = new Random().nextInt(Direction.values().length);
            Direction randomDirection = Direction.values()[pick];

            Energy animalEnergy = new Energy(this.animalStartEnergy.energy);
            animalEnergy.setConstants(simulationConstants);

            Animal animal = animalFactory.createAnimal(randomPosition, randomDirection, animalEnergy, randomGen);

            this.animalMap.place((WorldElement) animal);
        }
    }
    public int getDayOfSimulation() {
        return dayOfSimulation;
    }

    public synchronized DataStorage getData() {
        int id = this.simulationManager.getSelectedAnimalID();

        if (id != -1) {
            Animal selectedAnimal = this.animalFactory.animals.get(id);

            return new DataStorage(this.dayOfSimulation, this.animalFactory.liveAnimal, this.grassFactory.liveGrass, this.animalMap,
                    this.grassMap, this.height, this.width, statistics.getAverageLifeTime(), statistics.getAverageLifeTimeDeath(),
                    statistics.getCurrentMostPopularGenotype(), statistics.averageEnergy, selectedAnimal.getGen(),
                    selectedAnimal.getGen().getActualGen(), selectedAnimal.energy, selectedAnimal.grassEaten,
                    selectedAnimal.age, selectedAnimal.deathDay, selectedAnimal.children, statistics.mostEnergy, statistics.mostEnergyAnimal, statistics.longestLife, statistics.longestLifeAnimal,
                    statistics.maxChildren, statistics.maxChildrenAnimal);
        }
        return new DataStorage(this.dayOfSimulation, this.animalFactory.liveAnimal, this.grassFactory.liveGrass, this.animalMap,
                this.grassMap, this.height, this.width, statistics.getAverageLifeTime(), statistics.getAverageLifeTimeDeath(),
                statistics.getCurrentMostPopularGenotype(), statistics.averageEnergy,new Gen(new ArrayList<>(List.of(Rotation.F))), -1, new Energy(-1),
                -1,-1,-1,-1, statistics.mostEnergy, statistics.mostEnergyAnimal, statistics.longestLife, statistics.longestLifeAnimal,
                statistics.maxChildren, statistics.maxChildrenAnimal);
    }

    public synchronized DataStorage getAnimalData(int id){
        Animal animal = this.animalFactory.animals.get(id);
        return animal.toDataStorage();
    }

    private synchronized void pause(){
        if (!this.running.get()) {
            try {
                newDataToReceive.set(true);
                this.wait();
            } catch (InterruptedException e) {
                throw new RuntimeException();
            }
            running.set(true);
        }
    }

    public void oneDay(){
        dayOfSimulation += 1;
        if(csvCreator != null)
            csvCreator.addData(statistics);

        HashMap<Animal, Integer> test = new HashMap<>();

        Collection <ArrayList<WorldElement>> copy = new ArrayList<>(animalMap.occupiedPosition.values());

        for (ArrayList<WorldElement> animals : copy) {

            ArrayList<WorldElement> deepcopy = new ArrayList<>(animals);
            for (WorldElement animal : deepcopy) {
                Animal castedAnimal = (Animal) animal;
                if (test.get(castedAnimal) == null) {
                    castedAnimal.move();
                }
                test.put(castedAnimal, 1);
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

                Animal newAnimal = animalFactory.createChild(animalsCopy.get(i), animalsCopy.get(i + 1));
                if (newAnimal != null) {
                    this.animalMap.place((WorldElement) newAnimal);
                }
            }


        }
        spawnGrass(grassPerDay);
        statistics.update();

        // a day has passed so new data should be generated
        // gui uses this boolean to determine if it should
        // get new data using this.getData()
        newDataToReceive.set(true);
    }
    @Override
    public void run() {
        while (true) {

            try {
                this.pause(); // checks if GUI button send a pause request -> if tes the thread is paused and waits for notify
            }
            catch (RuntimeException e){
                this.interrupt();
            }

            oneDay();

            // interval speed can be adjusted from gui level -> simulation speed is atomic
            try {
                sleep(simulationSpeed.get());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
