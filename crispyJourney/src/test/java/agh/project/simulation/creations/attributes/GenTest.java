package agh.project.simulation.creations.attributes;

import agh.project.enumerators.Direction;
import agh.project.enumerators.Rotation;
import agh.project.simulation.creations.Animal;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GenTest {
    ArrayList<Rotation> rotations1 = new ArrayList<>(List.of(Rotation.F, Rotation.B, Rotation.BR));
    ArrayList<Rotation> rotations2 = new ArrayList<>(List.of(Rotation.L, Rotation.BR, Rotation.BL));
    ArrayList<Rotation> rotations3 = new ArrayList<>(List.of(Rotation.FL, Rotation.R, Rotation.F));

    Gen gen1 = new Gen(rotations1, 0);
    Gen gen2 = new Gen(rotations2, 1);
    Gen gen3 = new Gen(rotations3, 2);


    private int countGens(Gen gen1, Rotation rotation){
        ArrayList<Rotation> gensList = gen1.getGensList();
        int counter = 0;
        for (Rotation r : gensList){
            if (r == rotation) counter += 1;
        }
        return counter;
    }


    @Test
    void newGens() {
        /**
         * Given:
            Animals (parents)

         * When:
            Animals have got energy that determine their child's Gens. The mutationGens is set to 0. The gensNumber is set to 10

         * Then:
            Check if child has got appropriate amount of genes from parents
         */
        Gen.setGensNumber(10);
        Gen.setMutationNumber(0);

        Gen gen1 = new Gen(new ArrayList<>(List.of(Rotation.F,Rotation.F,Rotation.F,Rotation.F,Rotation.F,Rotation.F,
                    Rotation.F, Rotation.F,Rotation.F,Rotation.F)));

        Gen gen2 = new Gen(new ArrayList<>(List.of(Rotation.L,Rotation.L,Rotation.L,Rotation.L,Rotation.L,Rotation.L,
                Rotation.L, Rotation.L,Rotation.L,Rotation.L)));

        Gen gen3 = new Gen(new ArrayList<>(List.of(Rotation.R,Rotation.R,Rotation.R,Rotation.R,Rotation.R,Rotation.R,
                Rotation.R, Rotation.R,Rotation.R,Rotation.R)));

        Gen gen4 = new Gen(new ArrayList<>(List.of(Rotation.B,Rotation.B,Rotation.B,Rotation.B,Rotation.B,Rotation.B,
                Rotation.B, Rotation.B,Rotation.B,Rotation.B)));

        Animal parent1 = new Animal(1,new Vector2d(1,1), Direction.NORTH, new Energy(4),gen1);
        Animal parent2 = new Animal(2,new Vector2d(1,1), Direction.NORTH, new Energy(6),gen2);
        Animal parent3 = new Animal(3,new Vector2d(1,1), Direction.NORTH, new Energy(9),gen3);
        Animal parent4 = new Animal(4,new Vector2d(1,1), Direction.NORTH, new Energy(1),gen4);

        assertEquals(4,countGens(Gen.newGens(parent1,parent2), Rotation.F));
        assertEquals(6,countGens(Gen.newGens(parent1,parent2), Rotation.L));
        assertEquals(3,countGens(Gen.newGens(parent1,parent3), Rotation.F));
        assertEquals(7,countGens(Gen.newGens(parent1,parent3), Rotation.R));
        assertEquals(8,countGens(Gen.newGens(parent1,parent4), Rotation.F));
        assertEquals(2,countGens(Gen.newGens(parent1,parent4), Rotation.B));
        assertEquals(4,countGens(Gen.newGens(parent2,parent3), Rotation.L));
        assertEquals(6,countGens(Gen.newGens(parent2,parent3), Rotation.R));
        assertEquals(1,countGens(Gen.newGens(parent3,parent4), Rotation.B));
        assertEquals(9,countGens(Gen.newGens(parent3,parent4), Rotation.R));


    }

    @Test
    void getNextRotation() {
        /**
         * Given:
            Gens

         * When:
            Current gens are set; GensNumber is set to 3

         * Then:
            Function returns the current gen representation
         */

        Gen.setGensNumber(3);

        assertEquals(Rotation.F, gen1.getNextRotation());
        assertEquals(Rotation.BR, gen2.getNextRotation());
        assertEquals(Rotation.F, gen3.getNextRotation());
    }

    @Test
    void updateGen() {
        /**
         * Given:
            Gens

         * When:
            Current Gens are updating. The chaoticGen variant is set to false (if chaoticGen is set to true there is the chance
            that the gen is updating in random way)

         * Then:
            Function returns the current gen representation after updating ( updating works )
         */
        Gen.setGensNumber(3);
        Gen.setChaoticGen(false);

        gen1.updateGen();
        gen2.updateGen();
        gen3.updateGen();

        assertEquals(Rotation.B,gen1.getNextRotation());
        assertEquals(Rotation.BL,gen2.getNextRotation());
        assertEquals(Rotation.FL,gen3.getNextRotation());
    }
}