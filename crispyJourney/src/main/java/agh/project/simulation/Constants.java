package agh.project.simulation;

import agh.project.simulation.factories.AnimalFactory;
import agh.project.simulation.maps.AnimalMap;

public class Constants {
//    Animal constants
    public AnimalMap animalMap;
    public AnimalFactory animalFactory;

//    Energy constants
    public int oneDayLost;
    public int reproduceEnergy;
    public int reproduceBoundary;

//    Gen constans
    public int genNumber;
    public int mutationNumber;
    public boolean chaoticGen;

    public boolean mutationVariant;

    public Constants(AnimalMap animalMap, AnimalFactory animalFactory, int oneDayLost, int reproduceEnergy, int reproduceBoundary, int genNumber, int mutationNumber, boolean chaoticGen,
                     boolean mutationVariant) {
        this.animalMap = animalMap;
        this.animalFactory = animalFactory;
        this.oneDayLost = oneDayLost;
        this.reproduceEnergy = reproduceEnergy;
        this.reproduceBoundary = reproduceBoundary;
        this.genNumber = genNumber;
        this.mutationNumber = mutationNumber;
        this.chaoticGen = chaoticGen;
        this.mutationVariant = mutationVariant;
    }
}
