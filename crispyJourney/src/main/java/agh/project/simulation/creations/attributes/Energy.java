package agh.project.simulation.creations.attributes;

import agh.project.simulation.Constants;

import java.util.Objects;

public class Energy {

//    -----ATTRIBUTES-----
    private Constants constants;

    public int energy;


//    ------Methods-----
    public Energy(int energy){
        this.energy = energy;
    }

    public void oneDay(){
//      Changes current status of energy by oneDayLost variable
        this.energy = this.energy - this.constants.oneDayLost;
    }


    public int getEnergy() {
        return energy;
    }

    public boolean reproduceEvent(Energy partner2){
//        If animals (Animal partner1 and Animal partner2) can bread it changes their energy status and
//        returns appropriate information

        if (this.energy >= this.constants.reproduceBoundary && partner2.energy >= this.constants.reproduceBoundary){
            this.energy -= this.constants.reproduceEnergy;
            partner2.energy -= this.constants.reproduceEnergy;
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
        this.energy -= this.constants.reproduceEnergy;
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

    public void setConstants(Constants constants) {
        this.constants = constants;
    }

    public void addEnergy(int addingEnergy){
        this.energy += addingEnergy;
    }
}
