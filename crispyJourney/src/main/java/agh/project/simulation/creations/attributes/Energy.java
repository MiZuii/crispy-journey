package agh.project.simulation.creations.attributes;

import java.util.Objects;

public class Energy {

//    -----ATTRIBUTES-----
    public static int oneDayLost;
    public static int reproduceEnergy;
    public static int reproduceBoundary;

    public int energy;


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

    public static void setOneDayLost(int oneDayLost){
        Energy.oneDayLost = oneDayLost;
    }

    public static void setReproduceEnergy(int reproduceEnergy){
        Energy.reproduceEnergy = reproduceEnergy;
    }

    public static void setReproduceBoundary(int reproduceBoundary){
        Energy.reproduceBoundary = reproduceBoundary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Energy energy1)) return false;
        return energy == energy1.energy;
    }

    @Override
    public String toString() {
        return Integer.toString(this.energy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(energy);
    }

    public void addEnergy(int addingEnergy){
        this.energy += addingEnergy;
    }
}
