package agh.project.simulation;

import agh.project.World;
import agh.project.interfaces.WorldElement;
import agh.project.simulation.creations.Animal;
import agh.project.simulation.creations.Grass;
import agh.project.simulation.creations.attributes.Vector2d;
import agh.project.simulation.maps.AnimalMap;
import agh.project.simulation.maps.GrassMap;

import java.util.ArrayList;

public class DataStorage {

    // list of variables that need to be contained
    // like:
    private final int populationSize;
    private final int grassPopulation;
    private int freeSquares;
    private final ArrayList<ArrayList<String>> map;

    public DataStorage(int populationSize,int grassPopulation, AnimalMap animalMap, GrassMap grassMap,int height, int width) {

        this.populationSize = populationSize;
        this.freeSquares = 0;
        this.map = mapData(animalMap,grassMap,height,width);
        this.grassPopulation = grassPopulation;

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
