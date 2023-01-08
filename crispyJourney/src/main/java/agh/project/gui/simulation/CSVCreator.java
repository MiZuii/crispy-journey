package agh.project.gui.simulation;

import agh.project.simulation.Statistics;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

public class CSVCreator {

    private final String filePath;

    public CSVCreator(String simulationName) {
        this.filePath = simulationName+"-data.csv";
        deletePrevious(this.filePath);
    }

    private void deletePrevious(String toBeDeleted){
        File file = new File(toBeDeleted);
        file.delete();
    }

    public synchronized void addData(Statistics statistics) {
        String currentGrassNumber = String.valueOf(statistics.currentGrassNumber);
        String currentAnimalNumber = String.valueOf(statistics.currentAnimalNumber);
        String currentFreeSpaces = String.valueOf(statistics.currentFreeSpaces);
        String currentMostPopularGenotype = String.valueOf(statistics.currentMostPopularGenotype);
        String averageEnergy = String.valueOf(statistics.averageEnergy);
        String averageLifeTime = String.valueOf(statistics.averageLifeTime);
        String overallMostPopularGenotype = String.valueOf(statistics.overallMostPopularGenotype);

        String lineToAdd = currentGrassNumber+","+currentAnimalNumber+","+currentFreeSpaces+","+currentMostPopularGenotype+","+averageEnergy+","+averageLifeTime+","+overallMostPopularGenotype;

        try{
            FileWriter fw = new FileWriter(this.filePath, true);
            BufferedWriter bw = new BufferedWriter(fw);
            PrintWriter pw = new PrintWriter(bw);

            pw.println(lineToAdd);
            pw.flush();
            pw.close();
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}
