package agh.project.simulation;

public class Population {
<<<<<<< HEAD
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
=======
    public int mapHeight;
    public int mapWidth;
    public int grassEnergyProfit;
    public int minEnergyCopulation;
    public int animalStartEnergy;
    public int dailyEnergyLost;
    public int animalStartSpawningNumber;
    public int grassPerDay;
    public int refreshment;
>>>>>>> 62399441921bab3593a4ac50c163889c4f17f54f

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
