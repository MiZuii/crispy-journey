package agh.project.simulation;

import agh.project.enumerators.Direction;
import agh.project.interfaces.WorldElement;
import agh.project.simulation.creations.Animal;
import agh.project.simulation.creations.Grass;
import agh.project.simulation.factories.AnimalFactory;
import agh.project.simulation.factories.GrassFactory;

public class Statistics {
    public int currentGrassNumber;
    public int currentAnimalNumber;
    public int currentFreeSpaces;
    public Direction mostPopularGenotype;
    public double averageEnergy;
    public double averageLifeTime;

    private AnimalFactory animalFactory;
    private GrassFactory grassFactory;
    public Statistics(AnimalFactory animalFactory, GrassFactory grassFactory){
        this.animalFactory = animalFactory;
        this.grassFactory = grassFactory;
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
    public double getAverageLiftTimeDeath(){
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
}
