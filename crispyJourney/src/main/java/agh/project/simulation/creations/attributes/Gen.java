package agh.project.simulation.creations.attributes;

import agh.project.enumerators.Rotation;
import agh.project.simulation.creations.Animal;

import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Gen {
    //    -----Attributes-----
    private ArrayList<Rotation> gens;
    private int actualGen;
    public static int gensNumber;
    public static int mutationNumber;

    public static boolean chaoticGen;

//    -----Methods-----

    public Gen(ArrayList<Rotation> gens) {
        this.gens = gens;
        this.actualGen = 0;
    }

    public Gen(ArrayList<Rotation> gens, int start) {
        this.gens = gens;
        this.actualGen = start;
    }

    public ArrayList<Rotation> getGensList() {
        return gens;
    }

    private static void fillGen(Gen unfilled, Gen parent, int start, int end) {
//        Filled the gen Array at positions [start, end)

        ArrayList<Rotation> parentGens = parent.getGensList();
        ArrayList<Rotation> childGen = unfilled.getGensList();
        for (int i = start; i < end; i++) {
            childGen.add(i, parentGens.get(i));
        }
    }

    private static void mutation(Gen gen) {
        int mutationNumber = ThreadLocalRandom.current().nextInt(0, Gen.mutationNumber + 1);

        ArrayList<Rotation> genList = gen.getGensList();

        for (int i = 0; i < mutationNumber; i++) {
            int pick = new Random().nextInt(Rotation.values().length);
            Rotation newGen = Rotation.values()[pick];

            int index = ThreadLocalRandom.current().nextInt(0, gensNumber);
            genList.set(index, newGen);
        }
    }


    public static Gen newGens(Animal parent1, Animal parent2) {
//        Returns Gen of new Animal
        Energy energy1 = new Energy(parent1.getEnergy());
        Energy energy2 = new Energy(parent2.getEnergy());

        ArrayList<Rotation> genList = new ArrayList<>(gensNumber);
        Gen childGen = new Gen(genList);

//        Counts how many genes a child inherits from parent 1 and how many from parent 2
        int n1 = Math.round((float) energy1.getEnergy() / ((float) energy1.getEnergy() + (float) energy2.getEnergy())
                * Gen.gensNumber);

//        Determines from which side the genes are entered
        Random rd = new Random();
        if (rd.nextBoolean()) {
//            Start from left side
            fillGen(childGen, parent1.getGen(), 0, n1);
            fillGen(childGen, parent2.getGen(), n1, Gen.gensNumber);
        } else {
            fillGen(childGen, parent2.getGen(), 0, Gen.gensNumber - n1);
            fillGen(childGen, parent1.getGen(), Gen.gensNumber - n1, Gen.gensNumber);
        }

//        Random mutation
        mutation(childGen);
        return childGen;
    }


    public Rotation getNextRotation() {
//        Returns next move of Animal
        return this.gens.get(actualGen);
    }

    public void updateGen() {
//        Change actual Gen to the next in the queue

//        The animal does not have to rearrange its genes in a given order
        if (Gen.chaoticGen) {
            int random = ThreadLocalRandom.current().nextInt(0, 11);
            if (random < 2) randomUpdateGen();
            else this.actualGen = (actualGen + 1) % (this.gens.size());

        } else {
            this.actualGen = (actualGen + 1) % (this.gens.size());
        }
    }

    public void randomUpdateGen() {
        this.actualGen = ThreadLocalRandom.current().nextInt(0, this.gens.size());
    }

    public static void setGensNumber(int gensNumber) {
        Gen.gensNumber = gensNumber;
    }

    public static void setMutationNumber(int mutationNumber) {
        Gen.mutationNumber = mutationNumber;
    }

    public static void setChaoticGen(boolean chaoticGen) {
        Gen.chaoticGen = chaoticGen;
    }

    public static Gen getRandomGen() {
        ArrayList<Rotation> randomGens = new ArrayList<>();
        for (int i = 0; i <= gensNumber; i++) {
            int pick = new Random().nextInt(Rotation.values().length);
            Rotation newGen = Rotation.values()[pick];
            randomGens.add(newGen);
        }
        return new Gen(randomGens);
    }

    public int getActualGen() {
        return actualGen;
    }
}
