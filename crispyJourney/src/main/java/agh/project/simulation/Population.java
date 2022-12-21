package agh.project.simulation;

public class Population {
    public String name;
    public int mapHeight;
    public int mapWidth;
    public int grassEnergyProfit;
    public int minEnergyCopulation;
    public int animalStartEnergy;
    public int dailyEnergyLost;
    public int animalStartSpawningNumber;
    public int grassPerDay;
    public int refreshment;

    public int energyPerCopulation;
    public int maxMutationNumber;
    public int genomLength;

    public boolean moveFlag; // How animals move
    public boolean mutationFlag; // How gens update
    public boolean mapFlag; // Type of map (hell or earth)
    public boolean grassFlag; // Where the grass should spawn


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
