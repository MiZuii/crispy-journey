package agh.project.simulation;

public class Population {
    public String name;
    private final int mapHeight;
    private final int mapWidth;
    private final int grassEnergyProfit;
    private final int minEnergyCopulation;
    private final int animalStartEnergy;
    private final int dailyEnergyLost;
    private final int animalStartSpawningNumber;
    private final int grassPerDay;
    private final int refreshment;

    private final int energyPerCopulation;
    private final int maxMutationNumber;
    private final int genomLength;

    private final boolean moveFlag; // How animals move
    private final boolean mutationFlag; // How gens update
    private final boolean mapFlag; // Type of map (hell or earth)
    private final boolean grassFlag; // Where the grass should spawn


    public Population(String name, int mapHeight, int mapWidth, int grassEnergyProfit, int minEnergyCopulation,
                      int animalStartEnergy, int dailyEnergyLost, int animalStartSpawningNumber,
                      int grassPerDay, int refreshment, int energyPerCopulation, int maxMutationNumber,
                      int genomLength, boolean moveFlag, boolean mutationFlag, boolean mapFlag, boolean grassFlag) {
        this.name = name;
        this.mapHeight = mapHeight;
        this.mapWidth = mapWidth;
        this.grassEnergyProfit = grassEnergyProfit;
        this.minEnergyCopulation = minEnergyCopulation;
        this.animalStartEnergy = animalStartEnergy;
        this.dailyEnergyLost = dailyEnergyLost;
        this.animalStartSpawningNumber = animalStartSpawningNumber;
        this.grassPerDay = grassPerDay;
        this.refreshment = refreshment;
        this.energyPerCopulation = energyPerCopulation;
        this.maxMutationNumber = maxMutationNumber;
        this.genomLength = genomLength;
        this.moveFlag = moveFlag;
        this.mutationFlag = mutationFlag;
        this.mapFlag = mapFlag;
        this.grassFlag = grassFlag;
    }
}
