package agh.project.simulation;

public class Population {
    public String name;
    public boolean isDefault;
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


    public Population(String name, boolean isDefault, int[] properties, boolean[] flags) {

        this.name = name;
        this.isDefault = isDefault;

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

    @Override
    public String toString() {
        StringBuilder ans = new StringBuilder();
        if (this.isDefault) ans.append(1);
        else ans.append(0);
        ans.append("/");
        ans.append(this.mapHeight);
        ans.append("/");
        ans.append(this.mapWidth);
        ans.append("/");
        ans.append(this.grassEnergyProfit);
        ans.append("/");
        ans.append(this.minEnergyCopulation);
        ans.append("/");
        ans.append(this.animalStartEnergy);
        ans.append("/");
        ans.append(this.dailyEnergyLost);
        ans.append("/");
        ans.append(this.animalStartSpawningNumber);
        ans.append("/");
        ans.append(this.grassPerDay);
        ans.append("/");
        ans.append(this.refreshment);
        ans.append("/");
        ans.append(this.energyPerCopulation);
        ans.append("/");
        ans.append(this.maxMutationNumber);
        ans.append("/");
        ans.append(this.genomLength);
        ans.append("/");

        if (moveFlag) {
            ans.append(1);
        }
        else {
            ans.append(0);
        }
        ans.append("/");

        if (mutationFlag) {
            ans.append(1);
        }
        else {
            ans.append(0);
        }
        ans.append("/");

        if (mapFlag) {
            ans.append(1);
        }
        else {
            ans.append(0);
        }
        ans.append("/");

        if (grassFlag) {
            ans.append(1);
        }
        else {
            ans.append(0);
        }
        ans.append("/");

        return ans.toString();
    }
}
