package agh.project.simulation;

public class Population {
    private final int mapHeight;
    private final int mapWidth;
    private final int grassEnergyProfit;
    private final int minEnergyCopulation;
    private final int animalStartEnergy;
    private final int dailyEnergyLost;
    private final int animalStartSpawningNumber;
    private final int grassPerDay;
    private final int refreshment;

    public Population(int mapHeight, int mapWidth, int grassEnergyProfit,
                      int minEnergyCopulation, int animalStartEnergy, int dailyEnergyLost,
                      int animalStartSpawningNumber, int grassPerDay, int refreshment) {
        this.mapHeight = mapHeight;
        this.mapWidth = mapWidth;
        this.grassEnergyProfit = grassEnergyProfit;
        this.minEnergyCopulation = minEnergyCopulation;
        this.animalStartEnergy = animalStartEnergy;
        this.dailyEnergyLost = dailyEnergyLost;
        this.animalStartSpawningNumber = animalStartSpawningNumber;
        this.grassPerDay = grassPerDay;
        this.refreshment = refreshment;
    }
}
