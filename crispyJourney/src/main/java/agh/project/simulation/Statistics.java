package agh.project.simulation;

import agh.project.enumerators.Rotation;
import agh.project.simulation.creations.Animal;
import agh.project.simulation.factories.AnimalFactory;
import agh.project.simulation.factories.GrassFactory;
import agh.project.simulation.maps.AnimalMap;

import java.util.*;

public class Statistics {
    public int currentGrassNumber;
    public int currentAnimalNumber;
    public int currentFreeSpaces;
    public Rotation currentMostPopularGenotype;
    public Rotation overallMostPopularGenotype;
    public double averageEnergy;
    public double averageLifeTime;
    private final int availableSpaces;
    public int maxChildren = 0;
    public int longestLife = 0;
    public int mostEnergy = 0;
    public int maxChildrenAnimal = -1;
    public int mostEnergyAnimal = -1;
    public int longestLifeAnimal = -1;

    private final AnimalFactory animalFactory;
    private final GrassFactory grassFactory;
    private final AnimalMap animalMap;
    public Statistics(AnimalFactory animalFactory, GrassFactory grassFactory, int availableSpaces, AnimalMap animalMap){
        this.animalFactory = animalFactory;
        this.grassFactory = grassFactory;
        this.availableSpaces = availableSpaces;
        this.animalMap = animalMap;
    }

    public double getAverageEnergy() {
        int avg = 0;
        int cnt = 0;
        Collection<Animal> cpy = new ArrayList<>(animalFactory.animals);
        for(Animal animal : cpy){

            if (animal.getEnergy() > mostEnergy) {
                mostEnergy = animal.getEnergy();
                mostEnergyAnimal = animal.id;
            }
            if (animal.age > longestLife) {
                longestLife = animal.age;
                longestLifeAnimal = animal.id;
            }
            if (animal.children > maxChildren) {
                maxChildren = animal.children;
                maxChildrenAnimal = animal.id;
            }

            //Animal is dead
            if (animal.deathDay == -1) continue;
            avg+=animal.getEnergy();
            cnt++;
        }
        return (double)avg/cnt;
    }

    public double getAverageLifeTime(){
        int cnt = 0;
        int avg = 0;
        Collection<Animal> cpy = new ArrayList<>(animalFactory.animals);
        for(Animal animal :cpy){
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
        Collection<Animal> cpy = new ArrayList<>(animalFactory.deathAnimals);
        for(Animal animal : cpy){
            cnt++;
            avg +=animal.age;

        }
        if(cnt != 0)
            return (double)avg/cnt;
        return -1;
    }

    public int getCurrentFreeSpaces(){
        return availableSpaces-animalMap.occupiedPosition.size();
    }

    public int getCurrentAnimalNumber(){
        return animalFactory.animals.size();
    }

    public int getCurrentGrassNumber(){
        return grassFactory.liveGrass;
    }
    public Rotation getCurrentMostPopularGenotype(){
        Integer[] genArray = new Integer[]{0, 0, 0, 0, 0, 0, 0, 0};
        Collection<Animal> cpy = new ArrayList<>(animalFactory.animals);
        for(Animal animal : cpy){
            //Animal is dead
            if(animal.deathDay == -1) continue;
            for(Rotation gen : animal.getGen().getGensList()){
                genArray[Rotation.valueOf(gen.name()).ordinal()] += 1;
            }
        }
        List<Integer> gList = Arrays.asList(genArray);
        int maxIndex = gList.indexOf(Collections.max(gList));
        return Rotation.values()[maxIndex];
    }

    public Rotation getOverallMostPopularGenotype(){
        Integer[] genArray = new Integer[]{0, 0, 0, 0, 0, 0, 0, 0};
        for(Animal animal : animalFactory.animals){
            for(Rotation gen : animal.getGen().getGensList()){
                genArray[Rotation.valueOf(gen.name()).ordinal()] += 1;
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

    public void update(){
        this.currentAnimalNumber = getCurrentAnimalNumber();
        this.currentGrassNumber = getCurrentGrassNumber();
        this.currentFreeSpaces = getCurrentFreeSpaces();
        this.currentMostPopularGenotype = getCurrentMostPopularGenotype();
        this.averageEnergy = getAverageEnergy();
        this.averageLifeTime = getAverageLifeTimeDeath();
        this.overallMostPopularGenotype = getOverallMostPopularGenotype();
    }
}
