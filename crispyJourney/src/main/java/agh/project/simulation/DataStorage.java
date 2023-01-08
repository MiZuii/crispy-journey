package agh.project.simulation;

import agh.project.World;
import agh.project.enumerators.Rotation;
import agh.project.interfaces.WorldElement;
import agh.project.simulation.creations.Animal;
import agh.project.simulation.creations.Grass;
import agh.project.simulation.creations.attributes.Energy;
import agh.project.simulation.creations.attributes.Gen;
import agh.project.simulation.creations.attributes.Vector2d;
import agh.project.simulation.maps.AnimalMap;
import agh.project.simulation.maps.GrassMap;

import java.util.ArrayList;
import java.util.ConcurrentModificationException;

public class DataStorage {

    // list of variables that need to be contained
    // like:
    private  int populationSize;
    private  int grassPopulation;
    private int freeSquares;
    private  ArrayList<ArrayList<String>> map;
    private double averageLifeLength;
    private double averageLifeLengthOnlyDead;
    private Rotation popularGenom;
    private double averageEnergy;

//    Selected Animal features
    private ArrayList<Rotation> aGen;
    private int aActualGenIndex;
    private int aEnergy;
    private int aLiveLength;
    private int aPlantEaten;
    private int aDeathDate;
    private int simulationDay;
    private int aChildren;
    private int maxEnergy;
    private int maxEnergyAnimal;
    private int longestLife;
    private int longestLifeAnimal;
    private int mostChildren;
    private int mostChildrenAnimal;




    public DataStorage(int simulationDay, int populationSize, int grassPopulation, AnimalMap animalMap, GrassMap grassMap, int height, int width,
                       double averageLifeLength, double averageLifeLengthOnlyDead, Rotation popularGenom, double averageEnergy,
                       Gen gen, int actualGenIndex, Energy energy, int plantEaten, int liveLength, int deathDate, int children,
                       int maxEnergy, int maxEnergyAnimal,  int longestLife, int longestLifeAnimal, int mostChildren,
                       int mostChildrenAnimal) {

        this.maxEnergyAnimal = maxEnergyAnimal;
        this.maxEnergy = maxEnergy;
        this.longestLife = longestLife;
        this.longestLifeAnimal = longestLifeAnimal;
        this.mostChildren = mostChildren;
        this.mostChildrenAnimal = mostChildrenAnimal;
        this.simulationDay = simulationDay;
        this.populationSize = populationSize;
        this.freeSquares = 0;
        this.popularGenom = popularGenom;
        this.map = mapData(animalMap,grassMap,height,width);
        this.grassPopulation = grassPopulation;
        this.averageLifeLength = averageLifeLength;
        this.averageLifeLengthOnlyDead = averageLifeLengthOnlyDead;
//        this.averageEnergy = averageEnergy;

        this.aGen = gen.getGensList();
        this.aActualGenIndex = actualGenIndex;
        this.aEnergy = energy.energy;
        this.aPlantEaten = plantEaten;
        this.aLiveLength = liveLength;
        this.aDeathDate = deathDate;
        this.aChildren = children;
    }

    public DataStorage(Gen gen, int actualGenIndex, Energy energy, int plantEaten, int liveLength,
                       int deathDate, int children){

    }

    public int getChildren() {
        return aChildren;
    }

    private ArrayList<ArrayList<String>> mapData(AnimalMap animalMap, GrassMap grassMap,
                                                            int height, int width){

        ArrayList<ArrayList<String>> stringMap = new ArrayList<>();
        int animalCnt = 0;
        int energyCnt = 0;
        for (int i = 0; i < height; i++){

            stringMap.add(new ArrayList<String>());

            for(int j = 0; j < width; j++){

                String representation = "";

                ArrayList<WorldElement> animals = new ArrayList<>();
                ArrayList<WorldElement> grasses = new ArrayList<>();

                try{
                    animals = new ArrayList<>(animalMap.occupiedPosition.get(new Vector2d(i, j)));
                }
                catch(NullPointerException | ConcurrentModificationException ignored) {}

                try {
                    grasses = new ArrayList<>(grassMap.occupiedPosition.get(new Vector2d(i, j)));
                }
                catch(NullPointerException | ConcurrentModificationException ignored) {}

                if (animals.size() > 0){
                    for (WorldElement worldElement: animals){
                        Animal animal = (Animal) worldElement;

                        animalCnt+=1;
                        energyCnt += animal.getEnergy();

                        representation += animal.toString() + ":" + animal.energy.toString();
                        if (animal.getActualGenom() == this.popularGenom) {
                            representation += ":" + "1" + " ";
                        }
                        else {
                            representation += ":" + "0" + " ";
                        }

                    }
                }
                else if (grasses.size() > 0){
                    representation += "G";
                }
                stringMap.get(i).add(representation);
                if (representation.isBlank()) this.freeSquares += 1;
            }


        }
        if (animalCnt != 0) this.averageEnergy = (double)energyCnt/animalCnt;
        else this.averageEnergy = 0;
        return stringMap;
    }

    public int getSimulationDay() { return simulationDay; }

    public double getAverageEnergy() {
        return averageEnergy;
    }
    public int getPopulationSize() {
        return populationSize;
    }

    public int getGrassPopulation() {
        return grassPopulation;
    }

    public int getFreeSquares() {
        return freeSquares;
    }

    public ArrayList<ArrayList<String>> getMap() {
        return map;
    }

    public double getAverageLifeLength() {
        return averageLifeLength;
    }

    public double getAverageLifeLengthOnlyDead() {
        return averageLifeLengthOnlyDead;
    }

    public Rotation getPopularGenom() {
        return popularGenom;
    }

    public ArrayList<Rotation> getGen() {
        return aGen;
    }

    public int getActualGenIndex() {
        return aActualGenIndex;
    }

    public int getEnergy() {
        return aEnergy;
    }

    public int getLiveLength() {
        return aLiveLength;
    }

    public int getPlantEaten() {
        return aPlantEaten;
    }

    public int getDeathDate() {
        return aDeathDate;
    }

    public String getMaxEnergy() {
        return String.valueOf(maxEnergy) + " (" + String.valueOf(maxEnergyAnimal) + ")";
    }

    public String getMostChildren() {
        return String.valueOf(mostChildren) + " (" + String.valueOf(mostChildrenAnimal) + ")";
    }

    public String getLongestLife() {
        return String.valueOf(longestLife) + " (" + String.valueOf(longestLifeAnimal) + ")";
    }
}
