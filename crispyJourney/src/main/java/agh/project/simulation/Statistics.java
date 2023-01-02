package agh.project.simulation;

import agh.project.enumerators.Rotation;
import agh.project.simulation.creations.Animal;
import agh.project.simulation.factories.AnimalFactory;
import agh.project.simulation.factories.GrassFactory;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Statistics {
    public int currentGrassNumber;
    public int currentAnimalNumber;
    public int currentFreeSpaces;
    public Rotation currentMostPopularGenotype;
    public Rotation overallMostPopularGenotype;
    public double averageEnergy;
    public double averageLifeTime;
    private final int availableSpaces;

    private AnimalFactory animalFactory;
    private GrassFactory grassFactory;
    public Statistics(AnimalFactory animalFactory, GrassFactory grassFactory, int availableSpaces){
        this.animalFactory = animalFactory;
        this.grassFactory = grassFactory;
        this.availableSpaces = availableSpaces;
    }

    public double getAverageEnergy() {
        int avg = 0;
        int cnt = 0;
        for(Animal animal : animalFactory.animals){
            avg+=animal.getEnergy();
            cnt++;
        }
        return (double)avg/cnt;
    }

    public double getAverageLifeTime(){
        int cnt = 0;
        int avg = 0;
        for(Animal animal : animalFactory.animals){
            if(animal.deathDay != -1){
                cnt++;
                avg +=animal.age;
            }
        }
        if(cnt != 0)
            return (double)avg/cnt;
        return -1;
    }
    public double getAverageLifeTimeDeath(){
        int cnt = 0;
        int avg = 0;
        for(Animal animal : animalFactory.deathAnimals){
            if(animal.deathDay != -1){
                cnt++;
                avg +=animal.age;
            }
        }
        if(cnt != 0)
            return (double)avg/cnt;
        return -1;
    }

    public int getCurrentFreeSpaces(){
        return availableSpaces-animalFactory.animals.size();
    }

    public int getCurrentAnimalNumber(){
        return animalFactory.animals.size();
    }

    public int getCurrentGrassNumber(){
        return grassFactory.liveGrass;
    }
    public Rotation getCurrentMostPopularGenotype(){
        Integer[] genArray = new Integer[8];
        for(Animal animal : animalFactory.animals){
            for(Rotation gen : animal.getGen().getGensList()){
                genArray[Rotation.valueOf(gen.name()).ordinal()]++;
            }
        }
        List<Integer> gList = Arrays.asList(genArray);
        int maxIndex = gList.indexOf(Collections.max(gList));
        return Rotation.values()[maxIndex];
    }

    public Rotation getOverallMostPopularGenotype(){
        Integer[] genArray = new Integer[8];
        for(Animal animal : animalFactory.animals){
            for(Rotation gen : animal.getGen().getGensList()){
                genArray[Rotation.valueOf(gen.name()).ordinal()]++;
            }
        }
        for(Animal animal : animalFactory.deathAnimals){
            for(Rotation gen : animal.getGen().getGensList()){
                genArray[Rotation.valueOf(gen.name()).ordinal()]++;
            }
        }
        List<Integer> gList = Arrays.asList(genArray);
        int maxIndex = gList.indexOf(Collections.max(gList));
        return Rotation.values()[maxIndex];
    }

    public void update(boolean simulationOver){
        this.currentAnimalNumber = getCurrentAnimalNumber();
        this.currentGrassNumber = getCurrentGrassNumber();
        this.currentFreeSpaces = getCurrentFreeSpaces();
        this.currentMostPopularGenotype = getCurrentMostPopularGenotype();
        this.averageEnergy = getAverageEnergy();
        this.averageLifeTime = getAverageLifeTimeDeath();
        if(simulationOver)
            this.overallMostPopularGenotype = getOverallMostPopularGenotype();
    }
}
