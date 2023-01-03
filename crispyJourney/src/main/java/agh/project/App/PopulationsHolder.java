package agh.project.App;

import agh.project.simulation.Population;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;

/**
 *
 * Class responsible for holding Population objects
 * and sharing utilities like getting or adding new populations
 *
 * @author Piotr
 *
 */
public class PopulationsHolder {

    private final ArrayList<Population> populationsList;
    private final HashSet<String> populationsNames;
    private final ArrayList<Population> savedPopulationsList;
    private final HashSet<String> savedPopulationsNames;


    public PopulationsHolder(){
        populationsList = new ArrayList<>();
        populationsNames = new HashSet<>();
        savedPopulationsList = new ArrayList<>();
        savedPopulationsNames = new HashSet<>();
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
     * Getter for saved populations array.
     *
     * @return Array of saved populations
     */
    public ArrayList<Population> getSavedPopulationsList() {
        return savedPopulationsList;
    }

    /**
     * Getter for populations names.
     *
     * @return Array of populations names.
     */
    public HashSet<String> getPopulationsNames() {
        return populationsNames;
    }

    /**
     * Getter for saved populations names.
     *
     * @return Array of saved populations names.
     */
    public HashSet<String> getSavedPopulationsNames() {
        return savedPopulationsNames;
    }

    /**
     * Methode adds a new population to population array.
     *
     * @param newPopulation Population to add.
     */
    public void addPopulation(Population newPopulation){
        populationsList.add(newPopulation);
        populationsNames.add(newPopulation.name);
    }

    /**
     * Adds populations and population name to corresponding structures.
     * Should be only used for reservation that are saved on disk.
     *
     * @param newPopulation Population to add.
     */
    public void addSavedPopulation(Population newPopulation) {
        savedPopulationsList.add(newPopulation);
        savedPopulationsNames.add(newPopulation.name);
    }

    /**
     * Remove saved population, and it's name from saved populations list.
     *
     * @param populationToRemove Population to remove
     */
    public void removeSavedPopulation(Population populationToRemove) {
        File popToDelete = new File(System.getProperty("user.dir") +
                "\\src\\main\\resources\\populations\\" +
                populationToRemove.name + ".pop");

        // if delete operation failed -> stop deleting
        if (!popToDelete.delete()) {return;}

        savedPopulationsList.remove(populationToRemove);
        savedPopulationsNames.remove(populationToRemove.name);
    }

    /**
     * Methode removes given population from list of populations
     *
     * @param toDelete Population to delete
     */
    public void removePopulation(Population toDelete){
        populationsList.remove(toDelete);
        populationsNames.remove(toDelete.name);
    }

    /**
     * Methode adding saved to files populations to populations array.
     */
    private void addPresavedPopulations() {
        File[] savedPopulations = findPopulations();
        for (File population : savedPopulations) {
            try {
                Population pop = createPopulationFromFile(population);
                addPopulation(pop);
                addSavedPopulation(pop);
            } catch (IOException e) {
                e.printStackTrace();
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
        boolean isDefault = args.get(0) == 1;

        int[] intArgs = new int[12];
        for (int i=0; i<intArgs.length; i++) {
            intArgs[i] = args.get(i+1);
        }

        boolean[] booleanArgs = new boolean[4];
        for (int i=0; i<booleanArgs.length; i++) {
            booleanArgs[i] = args.get(i + 12) != 0;
        }

        return new Population(name, isDefault, intArgs, booleanArgs);
    }

    /**
     * Methode creating File with pop extension from population file.
     * The files are saved to path of user.dir\src\main\resources\populations
     *
     * @param population population to save
     */
    public void createPopulationFile(Population population) {
        String filePath = System.getProperty("user.dir") +
                "\\src\\main\\resources\\populations\\" +
                population.name + ".pop";
        File popFile = new File(filePath);

        // if there is a file with specified name the IOException is thrown
        try {
            if (!popFile.createNewFile()) {throw new IOException("File creation failed.");}
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

        char[] content = new char[1000];
        if (fileReader.read(content) > 999) {
            throw new IOException("File " + file + " is too large to parse or is corrupted.");
        }
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
        return f.listFiles((dir, name) -> name.endsWith(".pop"));
    }
}
