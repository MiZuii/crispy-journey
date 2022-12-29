package agh.project.simulation;

import agh.project.enumerators.Direction;
import agh.project.interfaces.WorldElement;
import agh.project.simulation.creations.Animal;
import agh.project.simulation.factories.AnimalFactory;

public class Statistics {
    public int currentGrassNumber;
    public int currentAnimalNumber;
    public int currentFreeSpaces;
    public Direction mostPopularGenotype;
    public double averageEnergy;
    public double averageLifeTime;

    private AnimalFactory animalFactory;

    public double getAverageEnergy() {
        int avg = 0;
        for(WorldElement animal : animalFactory.animals)
            avg+=animal.getEnergy();
        return avg;
    }

    public double getAverageLifeTime(){
        int cnt = 0;
        int avg;
        for(WorldElement animal : animalFactory.animals){
            if(animal.deathDay != -1){
                cnt++;
                avg +=
            }
        }
        if(cnt != 0)
            return avg/cnt;
        return -1;
    }
}
