package agh.project.gui.menu;

import agh.project.simulation.Population;
import java.util.ArrayList;


public class PopulationsHolder {

    private ArrayList<Population> populationsList;

    public PopulationsHolder(){
        populationsList = new ArrayList<>();
    }

    public ArrayList<Population> getPopulationsList() {
        return populationsList;
    }

    public void addPopulation(Population newPopulation){
        populationsList.add(newPopulation);
    }

    public void removePopulation(Population toDelete){
        populationsList.remove(toDelete);
    }
}
