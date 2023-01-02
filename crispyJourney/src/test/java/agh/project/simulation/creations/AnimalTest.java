package agh.project.simulation.creations;

import agh.project.enumerators.Direction;
import agh.project.enumerators.Rotation;
import agh.project.simulation.SimulationEngine;
import agh.project.simulation.creations.attributes.Energy;
import agh.project.simulation.creations.attributes.Gen;
import agh.project.simulation.creations.attributes.Vector2d;
import agh.project.simulation.factories.AnimalFactory;
import agh.project.simulation.maps.AnimalMap;
import agh.project.simulation.maps.attributes.Hell;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {
    AnimalFactory animalFactory = new AnimalFactory();

    Gen gen1 = new Gen(new ArrayList<>(List.of(Rotation.F, Rotation.B, Rotation.BR)), 0);
    Gen gen2 = new Gen(new ArrayList<>(List.of(Rotation.L, Rotation.BR, Rotation.BL)), 1);
    Gen gen3 = new Gen(new ArrayList<>(List.of(Rotation.FL, Rotation.R, Rotation.F)), 2);


    Animal animal1 = animalFactory.createAnimal(new Vector2d(1,1), Direction.NORTH, new Energy(1), gen1);
    Animal animal2 = animalFactory.createAnimal(new Vector2d(2,4), Direction.NORTH, new Energy(7), gen2);
    Animal animal3 = animalFactory.createAnimal(new Vector2d(3,2), Direction.NORTH, new Energy(9), gen3);


    @Test
    void isAt() {
        /**
         * Given:
            Animals with start position

         * When:
            Animals have not moved yet

         * Then:
            Function confirm that animals stay in their initial position
         */
        assertTrue(animal1.isAt(new Vector2d(1,1)));
        assertTrue(animal2.isAt(new Vector2d(2,4)));
        assertTrue(animal3.isAt(new Vector2d(3,2)));

        assertFalse(animal1.isAt(new Vector2d(0,0)));
        assertFalse(animal1.isAt(new Vector2d(1,0)));
        assertFalse(animal1.isAt(new Vector2d(0,1)));
        assertFalse(animal2.isAt(new Vector2d(0,0)));
        assertFalse(animal2.isAt(new Vector2d(4,0)));
        assertFalse(animal2.isAt(new Vector2d(4,4)));
        assertFalse(animal3.isAt(new Vector2d(0,2)));
        assertFalse(animal3.isAt(new Vector2d(3,0)));
        assertFalse(animal3.isAt(new Vector2d(2,3)));

    }

    @Test
    void move() {
        /**
         * Given:
            Animals with their initial position, energy and Gen

         * When:
            Animals are moving respecting their gens. The chaoticGen is set to false. The oneDayLost is set to 1.
            There is animalMap (because of the specification of move function) but it is ignored in this test

         * Then:
            Animals position is changing after they move. If animal does not have enough energy it is deleted from
            Animal Factory ( of course it will be deleted from the map - this is tested in other Test File )
         */
        Animal.setAnimalFactory(animalFactory);
        AnimalMap animalMap = new AnimalMap(new Hell(1000, 1000));
        Animal.setMapObserver(animalMap);
        animalMap.place(animal1);
        animalMap.place(animal2);
        animalMap.place(animal3);

        Gen.setChaoticGen(false);
        Gen.setGensNumber(3);
        Energy.setOneDayLost(1);


        animal1.move();
        animal2.move();
        animal3.move();

        assertEquals(new Vector2d(1,2), animal1.getPosition());
        assertEquals(new Vector2d(3,3), animal2.getPosition());
        assertEquals(new Vector2d(3,3), animal3.getPosition());
        assertEquals(new Energy(0), animal1.getEnergy());
        assertEquals(new Energy(6), animal2.getEnergy());
        assertEquals(new Energy(8), animal3.getEnergy());

        animal1.move(); //Do not have enough energy
        animal2.move();
        animal3.move();

        assertEquals(new Vector2d(1,2), animal1.getPosition());
        assertEquals(new Vector2d(3,4), animal2.getPosition());
        assertEquals(new Vector2d(2,4), animal3.getPosition());
        assertEquals(new Energy(0), animal1.getEnergy());
        assertEquals(new Energy(5), animal2.getEnergy());
        assertEquals(new Energy(7), animal3.getEnergy());


        animal2.move();
        animal3.move();

        assertEquals(new Vector2d(2,4), animal2.getPosition());
        assertEquals(new Vector2d(3,5), animal3.getPosition());
        assertEquals(new Energy(4), animal2.getEnergy());
        assertEquals(new Energy(6), animal3.getEnergy());
    }

    @Test
    void toHell() {
        /**
         * Given:
            Animals

         * When:
            Animals going out of the map

         * Then:
            Animals lose their energy
         */
        Animal animal1 = new Animal(1,new Vector2d(1,1), Direction.NORTH, new Energy(10), gen1);
        Animal animal2 = new Animal(1,new Vector2d(1,1), Direction.NORTH, new Energy(15), gen1);
        Animal animal3 = new Animal(1,new Vector2d(1,1), Direction.NORTH, new Energy(20), gen1);

        Energy.setReproduceEnergy(5);
        animal1.toHell();
        animal2.toHell();
        animal3.toHell();

        assertEquals(new Energy(5), animal1.getEnergy());
        assertEquals(new Energy(10), animal2.getEnergy());
        assertEquals(new Energy(15), animal3.getEnergy());


    }
}