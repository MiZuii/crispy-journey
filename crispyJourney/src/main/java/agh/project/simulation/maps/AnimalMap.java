package agh.project.simulation.maps;

import agh.project.interfaces.WorldElement;
import agh.project.interfaces.WorldMapBoundary;
import agh.project.simulation.creations.Animal;
import agh.project.simulation.creations.attributes.Vector2d;

public class AnimalMap extends RectangularMap {


    public AnimalMap(WorldMapBoundary boundary){
        super(boundary);
    }

    /**
     * Change the position of the Animal according to the map type
     */

    public void changePosition(Vector2d oldPosition, Vector2d newPosition, Animal animal) {
        remove((WorldElement) animal);

        if (this.boundary.outOfBoundary(newPosition)) {
            //Move animal
            Vector2d position = boundary.moveAnimal(newPosition, animal);
            animal.changePosition(position);
        }
        else{
            animal.changePosition(newPosition);
        }
        //Place the Animal on the Map
        place((WorldElement) animal);




    }
}
