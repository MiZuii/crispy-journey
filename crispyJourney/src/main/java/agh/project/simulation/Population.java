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


    public Population(String name, int[] properties, boolean[] flags) {

        this.name = name;

        this.mapHeight = properties[0];
        this.mapWidth = properties[1];
        this.grassEnergyProfit = properties[2];
        this.minEnergyCopulation = properties[3];
        this.animalStartEnergy = properties[4];
        this.dailyEnergyLost = properties[5];
        this.animalStartSpawningNumber = properties[6];
        this.grassPerDay = properties[7];
        this.refreshment = properties[8];
        this.energyPerCopulation = properties[9];
        this.maxMutationNumber = properties[10];
        this.genomLength = properties[11];

        this.moveFlag = flags[0];
        this.mutationFlag = flags[1];
        this.mapFlag = flags[2];
        this.grassFlag = flags[3];
    }
}
