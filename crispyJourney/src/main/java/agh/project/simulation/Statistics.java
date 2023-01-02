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
        int cnt = 0;
        for(WorldElement animal : animalFactory.animals){
            avg+=animal.getEnergy();
            cnt++;

        }
        return (double)avg/cnt;
    }

    public double getAverageLifeTime(){
        int cnt = 0;
        int avg = 0;
        for(WorldElement worldElement : animalFactory.animals){
            Animal animal = (Animal) worldElement;
            if(animal.deathDay != -1){
                cnt++;
//                avg +=
            }
        }
        if(cnt != 0)
            return (double)avg/cnt;
        return -1;
    }
}
