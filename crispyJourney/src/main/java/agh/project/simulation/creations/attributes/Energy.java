package agh.project.simulation.creations.attributes;

public class Energy {

//    -----ATTRIBUTES-----
    public static int oneDayLost;
    public static int reproduceEnergy;
    public static int reproduceBoundary;
    private int energy;


//    ------Methods-----
    public Energy(int energy){
        this.energy = energy;
    }
    public void oneDay(){
//      Changes current status of energy by oneDayLost variable
        this.energy = this.energy - Energy.oneDayLost;
    }


    public int getEnergy() {
        return energy;
    }

    public static boolean reproduceEvent(Energy partner1, Energy partner2){
//        If animals (Animal partner1 and Animal partner2) can bread it changes their energy status and
//        returns appropriate information

        if (partner1.energy >= reproduceBoundary && partner2.energy >= reproduceBoundary){
            partner1.energy -= reproduceEnergy;
            partner2.energy -= reproduceEnergy;
            return true;
        }
        return false;
    }

    public boolean enoughEnergy(){
        return this.energy >= 1;
    }

    /**
     * Lose energy by going to hell (it is the same amount of energy as during breeding)
     */
    public void hellLoss(){
        this.energy -= reproduceEnergy;
    }

    public void setOneDayLost(int oneDayLost){
        Energy.oneDayLost = oneDayLost;
    }

    public void setReproduceEnergy(int reproduceEnergy){
        Energy.reproduceEnergy = reproduceEnergy;
    }

    public void setReproduceBoundary(int reproduceBoundary){
        Energy.reproduceBoundary = reproduceBoundary;
    }

}