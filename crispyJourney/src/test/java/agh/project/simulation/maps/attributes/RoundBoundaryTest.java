package agh.project.simulation.maps.attributes;

import agh.project.enumerators.Direction;
import agh.project.enumerators.Rotation;
import agh.project.simulation.creations.Animal;
import agh.project.simulation.creations.attributes.Energy;
import agh.project.simulation.creations.attributes.Gen;
import agh.project.simulation.creations.attributes.Vector2d;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RoundBoundaryTest {

    @Test
    void moveAnimal() {
        /**
         * Given:
            RoundBoundary attribute

         * When:
            Animals going out of boundary

         * Then:
            Animals position change as if the map were a sphere
         */
        RoundBoundary roundBoundary = new RoundBoundary(10,10);
        Gen gen = new Gen(new ArrayList<>(List.of(Rotation.F)));
//        Gen.setGensNumber(1);
//        Gen.setChaoticGen(false);
//        Energy.setOneDayLost(1);

        Animal animal1 = new Animal(0, new Vector2d(1,0), Direction.SOUTH, new Energy(10), gen);
        Animal animal2 = new Animal(0, new Vector2d(0,1), Direction.WEST, new Energy(10), gen);
        Animal animal3 = new Animal(0, new Vector2d(10,0), Direction.EAST, new Energy(10), gen);
        Animal animal4 = new Animal(0, new Vector2d(0,10), Direction.NORTH, new Energy(10), gen);

        assertEquals(new Vector2d(1,10), roundBoundary.moveAnimal(new Vector2d(1,-1), animal1));
        assertEquals(new Vector2d(10,1), roundBoundary.moveAnimal(new Vector2d(-1,1), animal2));
        assertEquals(new Vector2d(0,0), roundBoundary.moveAnimal(new Vector2d(11,0), animal3));
        assertEquals(new Vector2d(0,0), roundBoundary.moveAnimal(new Vector2d(0,11), animal4));
    }
}