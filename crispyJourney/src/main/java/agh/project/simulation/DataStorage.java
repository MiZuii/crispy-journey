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

    private ArrayList<Rotation> gen;
    private int actualGenIndex;
    private int energy;
    private int liveLength;
    private int plantEaten;
    private int deathDate;


    public DataStorage(int populationSize,int grassPopulation, AnimalMap animalMap, GrassMap grassMap,int height, int width,
                       double averageLifeLength, double averageLifeLengthOnlyDead, Rotation popularGenom) {

        this.populationSize = populationSize;
        this.freeSquares = 0;
        this.map = mapData(animalMap,grassMap,height,width);
        this.grassPopulation = grassPopulation;
        this.averageLifeLength = averageLifeLength;
        this.averageLifeLengthOnlyDead = averageLifeLengthOnlyDead;
        this.popularGenom = popularGenom;
    }

    public DataStorage(Gen gen, int actualGenIndex, Energy energy, int plantEaten, int liveLength,
                       int deathDate){
        this.gen = gen.getGensList();
        this.actualGenIndex = actualGenIndex;
        this.energy = energy.energy;
        this.plantEaten = plantEaten;
        this.liveLength = liveLength;
        this.deathDate = deathDate;
    }

    private ArrayList<ArrayList<String>> mapData(AnimalMap animalMap, GrassMap grassMap,
                                                            int height, int width){

        ArrayList<ArrayList<String>> stringMap = new ArrayList<>();
        for (int i = 0; i< height; i++){
            stringMap.add(new ArrayList<String>());
            String representation = "";
            for(int j = 0; j < width; j++){
                ArrayList<WorldElement> animals = animalMap.occupiedPosition.get(new Vector2d(i, j));
                ArrayList<WorldElement> grasses = grassMap.occupiedPosition.get(new Vector2d(i, j));

                if (animals.size() > 0){
                    for (WorldElement worldElement: animals){
                        Animal animal = (Animal) worldElement;
                        representation += animal.toString() + " ";
                    }
                    break;
                }
                if (grasses.size() > 0){
                    for (WorldElement worldElement: grasses){
                        Grass grass = (Grass) worldElement;
                        representation += "G";
                    }
                }
            }
            if (representation.length() == 0) this.freeSquares += 1;
            stringMap.get(i).add(representation);
        }
        return stringMap;
    }

}
