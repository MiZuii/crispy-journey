package agh.project.simulation.maps;

import agh.project.enumerators.Direction;
import agh.project.enumerators.Rotation;
import agh.project.simulation.creations.Animal;
import agh.project.simulation.creations.attributes.Energy;
import agh.project.simulation.creations.attributes.Gen;
import agh.project.simulation.creations.attributes.Vector2d;
import agh.project.simulation.factories.AnimalFactory;
import agh.project.simulation.maps.attributes.Hell;
import agh.project.simulation.maps.attributes.RoundBoundary;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RectangularMapTest {
    AnimalFactory animalFactory = new AnimalFactory();
    Gen gen1 = new Gen(new ArrayList<>(List.of(Rotation.F, Rotation.B, Rotation.BR)), 0);

    Animal animal1 = animalFactory.createAnimal(new Vector2d(1,1), Direction.NORTH, new Energy(10), gen1);
    Animal animal2 = animalFactory.createAnimal(new Vector2d(2,4), Direction.NORTH, new Energy(7), gen1);
    Animal animal3 = animalFactory.createAnimal(new Vector2d(3,2), Direction.NORTH, new Energy(9), gen1);
    Animal animal4 = animalFactory.createAnimal(new Vector2d(3,2), Direction.NORTH, new Energy(9), gen1);


    @Test
    void place() {
        /**
         * Given:
            RectangularMaps with two different type of boundaries

         * When:
            Animals are placing on the map

         * Then:
            Animals are placed in the right place in the Hashmap
         */
        RectangularMap rectangularMapHell = new RectangularMap(new Hell(10,10));
        RectangularMap rectangularMapRound = new RectangularMap(new RoundBoundary(10,10));

        rectangularMapHell.place(animal1);
        rectangularMapHell.place(animal2);
        rectangularMapHell.place(animal3);

        rectangularMapRound.place(animal1);
        rectangularMapRound.place(animal2);
        rectangularMapRound.place(animal3);

        assertArrayEquals(new ArrayList<Animal>(List.of(animal1)).toArray(), rectangularMapHell.occupiedPosition.get(new Vector2d(1,1)).toArray());
        assertArrayEquals(new ArrayList<Animal>(List.of(animal2)).toArray(), rectangularMapHell.occupiedPosition.get(new Vector2d(2,4)).toArray());
        assertArrayEquals(new ArrayList<Animal>(List.of(animal3)).toArray(), rectangularMapHell.occupiedPosition.get(new Vector2d(3,2)).toArray());

        assertArrayEquals(new ArrayList<Animal>(List.of(animal1)).toArray(), rectangularMapRound.occupiedPosition.get(new Vector2d(1,1)).toArray());
        assertArrayEquals(new ArrayList<Animal>(List.of(animal2)).toArray(), rectangularMapRound.occupiedPosition.get(new Vector2d(2,4)).toArray());
        assertArrayEquals(new ArrayList<Animal>(List.of(animal3)).toArray(), rectangularMapRound.occupiedPosition.get(new Vector2d(3,2)).toArray());

        rectangularMapRound.place(animal4);
        rectangularMapHell.place(animal4);

        assertArrayEquals(new ArrayList<Animal>(List.of(animal3,animal4)).toArray(), rectangularMapHell.occupiedPosition.get(new Vector2d(3,2)).toArray());
        assertArrayEquals(new ArrayList<Animal>(List.of(animal3,animal4)).toArray(), rectangularMapRound.occupiedPosition.get(new Vector2d(3,2)).toArray());


    }

    @Test
    void remove() {
        RectangularMap rectangularMapHell = new RectangularMap(new Hell(10,10));
        RectangularMap rectangularMapRound = new RectangularMap(new RoundBoundary(10,10));

        rectangularMapHell.place(animal1);
        rectangularMapHell.place(animal2);
        rectangularMapHell.place(animal3);
        rectangularMapHell.place(animal4);

        rectangularMapRound.place(animal1);
        rectangularMapRound.place(animal2);
        rectangularMapRound.place(animal3);
        rectangularMapRound.place(animal4);

        rectangularMapHell.remove(animal1);
        rectangularMapRound.remove(animal1);
        assertNull(rectangularMapHell.occupiedPosition.get(new Vector2d(1,1)));
        assertNull(rectangularMapRound.occupiedPosition.get(new Vector2d(1,1)));

        rectangularMapHell.remove(animal2);
        rectangularMapRound.remove(animal2);
        assertNull(rectangularMapHell.occupiedPosition.get(new Vector2d(2,4)));
        assertNull(rectangularMapRound.occupiedPosition.get(new Vector2d(2,4)));

        rectangularMapHell.remove(animal3);
        rectangularMapRound.remove(animal3);
        assertArrayEquals(new ArrayList<Animal>(List.of(animal4)).toArray(), rectangularMapHell.occupiedPosition.get(new Vector2d(3,2)).toArray());
        assertArrayEquals(new ArrayList<Animal>(List.of(animal4)).toArray(), rectangularMapRound.occupiedPosition.get(new Vector2d(3,2)).toArray());

        rectangularMapHell.remove(animal4);
        rectangularMapRound.remove(animal4);
        assertNull(rectangularMapHell.occupiedPosition.get(new Vector2d(3,2)));
        assertNull(rectangularMapRound.occupiedPosition.get(new Vector2d(3,2)));



    }

    @Test
    void isOccupied() {
        RectangularMap rectangularMapHell = new RectangularMap(new Hell(10,10));
        RectangularMap rectangularMapRound = new RectangularMap(new RoundBoundary(10,10));

        rectangularMapHell.place(animal1);
        rectangularMapHell.place(animal2);
        rectangularMapHell.place(animal3);
        rectangularMapHell.place(animal4);

        rectangularMapRound.place(animal1);
        rectangularMapRound.place(animal2);
        rectangularMapRound.place(animal3);
        rectangularMapRound.place(animal4);

        assertTrue(rectangularMapHell.isOccupied(new Vector2d(1,1)));
        assertTrue(rectangularMapHell.isOccupied(new Vector2d(2,4)));
        assertTrue(rectangularMapHell.isOccupied(new Vector2d(3,2)));
        assertFalse(rectangularMapHell.isOccupied(new Vector2d(5,5)));
        assertFalse(rectangularMapHell.isOccupied(new Vector2d(5,0)));

        assertTrue(rectangularMapRound.isOccupied(new Vector2d(1,1)));
        assertTrue(rectangularMapRound.isOccupied(new Vector2d(2,4)));
        assertTrue(rectangularMapRound.isOccupied(new Vector2d(3,2)));
        assertFalse(rectangularMapRound.isOccupied(new Vector2d(5,5)));
        assertFalse(rectangularMapRound.isOccupied(new Vector2d(5,0)));

    }
}