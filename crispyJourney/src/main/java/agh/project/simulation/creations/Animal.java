package agh.project.simulation.creations;

import agh.project.enumerators.Direction;
import agh.project.interfaces.WorldElement;
import agh.project.simulation.creations.attributes.Energy;
import agh.project.simulation.creations.attributes.Gen;
import agh.project.simulation.creations.attributes.Vector2d;
import agh.project.simulation.factories.AnimalFactory;
import agh.project.simulation.maps.AnimalMap;
import agh.project.simulation.maps.GrassMap;

import java.util.Objects;

public class Animal implements WorldElement {

//    -----Attributes------


    public final int id;
    public int age = 0;
    public int children = 0;
    public int grassEaten = 0;
    private Vector2d position;
    private Direction direction;
    private Energy energy;

    private Gen gen;
    private  static AnimalMap mapObserver;
    private static GrassMap grassMapObserver;

    private static AnimalFactory animalFactory;


//    ------Methods------

    public Animal(int id, Vector2d position, Direction direction, Energy energy, Gen gen,
                  AnimalMap animalMapObserver, GrassMap grassMapObserver,
                  AnimalFactory animalFactory){
        this.id = id;
        this.position = position;
        this.direction = direction;
        this.energy = energy;
        this.gen = gen;
        Animal.mapObserver = animalMapObserver;
        Animal.grassMapObserver = grassMapObserver;
        Animal.animalFactory = animalFactory;
    }


    public Gen getGen() {
        return gen;
    }

    public Energy getEnergy(){
        return this.energy;
    }

    public Vector2d getPosition(){
        return this.position;
    }

    public Direction getDirection() {
        return this.direction;
    }

    public boolean isAt(Vector2d other){
        return other.x == this.position.x && other.y == this.position.y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Animal animal)) return false;
        return id == animal.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


    public void move(){
//      Moves Animal to the next position according to his genes
        if (!this.energy.enoughEnergy()){
            animalFactory.deleteAnimal(this);

            //Remove from the map
            mapObserver.changePosition(this.position, null, this);
        }

        Vector2d oldPosition = this.position;

//        Getting older
        this.age += 1;

//        Moving and updating Gen
        this.direction = direction.changeDirection(gen.getNextRotation());
        gen.updateGen();
        Vector2d unitVector = direction.toVector2d();
        this.position = position.add(unitVector);

        mapObserver.changePosition(oldPosition, this.position, this);
    }

    public void toHell(){
        //Change the energy of the Animal
        this.energy.hellLoss();
    }

    public void changePosition(Vector2d newPosition){
        this.position = newPosition;
    }
}
