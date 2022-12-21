package agh.project.gui.menu;

import agh.project.simulation.Population;

import java.io.*;
import java.util.ArrayList;


public class PopulationsHolder {

    private ArrayList<Population> populationsList;

    public PopulationsHolder(){
        populationsList = new ArrayList<>();
        addPresavedPopulations();
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

    private void addPresavedPopulations() {
        File[] savedPopulations = findPopulations();

        for (File population : savedPopulations) {
            try {
                addPopulation(createPopulationFromFile(population));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private Population createPopulationFromFile(File file) throws IOException {
        ArrayList<Integer> args = parseFileInput(file);

        String name = file.getName().split(".")[0];

        int[] intArgs = new int[11];
        for (int i=0; i<intArgs.length; i++) {
            intArgs[i] = args.get(i);
        }

        boolean[] booleanArgs = new boolean[4];
        for (int i=0; i<booleanArgs.length; i++) {
            if (args.get(i + 12) != 0) {booleanArgs[i] = true;}
            else {booleanArgs[i] = false;}
        }

        return new Population(name, intArgs, booleanArgs);
    }

    private ArrayList<Integer> parseFileInput(File file) throws IOException {
        FileReader fileReader = new FileReader(file);

        char[] content = new char[100];
        fileReader.read(content);
        String sContent = new String(content);
        String[] slicedContent = sContent.split("/");

        ArrayList<Integer> args = new ArrayList<>();
        for (String subString : slicedContent) {
            args.add(Integer.parseInt(subString));
        }
        return args;
    }

    private File[] findPopulations(){
        // f is a directory of populations relative to different project directories
        File f = new File(System.getProperty("user.dir") + "\\src\\main\\resources\\populations");

        File[] matchingFiles = f.listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.endsWith(".pop");
            }
        });

        return matchingFiles;
    }
}
