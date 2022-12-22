package agh.project.App;

import agh.project.simulation.Population;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * Class responsible for holding Population objects
 * and sharing utilities like getting or adding new populations
 *
 * @author Piotr
 *
 */
public class PopulationsHolder {

    private ArrayList<Population> populationsList;
    private HashSet<String> populationNames;

    public PopulationsHolder(){
        populationsList = new ArrayList<>();
        populationNames = new HashSet<>();
        addPresavedPopulations();
    }

    /**
     * Getter for populations array.
     *
     * @return Array of populations
     */
    public ArrayList<Population> getPopulationsList() {
        return populationsList;
    }

    /**
     * Methode adds a new population to population array.
     *
     * @param newPopulation Population to add.
     */
    public void addPopulation(Population newPopulation){
        populationsList.add(newPopulation);
        populationNames.add(newPopulation.name);
    }

    /**
     * Methode removes given population from list of populations
     *
     * @param toDelete Population to delete
     */
    public void removePopulation(Population toDelete){
        populationsList.remove(toDelete);
        populationNames.remove(toDelete.name);
    }

    /**
     * Methode adding saved to files populations to populations array.
     */
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

    /**
     * Methode creating population objects from given file.
     *
     * @param file File holding population content
     *
     * @return Population Object
     *
     * @throws IOException  Throws if file doesn't exist -> parseFileInput cant create FileReader
     */
    private Population createPopulationFromFile(File file) throws IOException {
        ArrayList<Integer> args = parseFileInput(file);

        String name = file.getName().split("\\.")[0];

        int[] intArgs = new int[12];
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

    /**
     * Methode creating File with pop extension from population file.
     * The files are saved to path of user.dir\src\main\resources\populations
     *
     * @param population population to save
     */
    private void createPopulationFile(Population population) {
        String filePath = System.getProperty("user.dir") +
                "\\src\\main\\resources\\populations\\" +
                population.name + ".pop";
        File popFile = new File(filePath);

        // if there is a file with specified name the IOException is thrown
        try {
            popFile.createNewFile();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // write file content / if there was exception while creating file
        // the file writer won't work
        try {
            FileWriter writer = new FileWriter(filePath);
            writer.write(population.toString());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Methode responsible for parsing .pop files content. The content
     *      is a single line string of ints separated by "/" each number
     *      corresponds to single field in Population object. It only parses
     *      content, doesn't create population objects.
     *
     * @param file File from which content of population is read.
     *
     * @return Returns Array of ints which are arguments needed for creating population object
     *
     * @throws IOException Exception is thrown when the file doesn't exist -> FileReader can't be created
     */
    private ArrayList<Integer> parseFileInput(File file) throws IOException {
        FileReader fileReader = new FileReader(file);

        char[] content = new char[100];
        fileReader.read(content);
        String sContent = new String(content);
        String[] slicedContent = sContent.split("/");

        ArrayList<Integer> args = new ArrayList<>();
        for (int i=0; i<slicedContent.length-1; i++) {
            args.add(Integer.parseInt(slicedContent[i]));
        }
        return args;
    }

    /**
     * Methode responsible for finding all saved in file populations as files/
     *
     * @return List of File object corresponding to
     *          populations.
     */
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
