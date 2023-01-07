package agh.project.simulation.creations;

import agh.project.enumerators.Direction;
import agh.project.interfaces.WorldElement;
import agh.project.simulation.Constants;
import agh.project.simulation.DataStorage;
import agh.project.simulation.creations.attributes.Energy;
import agh.project.simulation.creations.attributes.Gen;
import agh.project.simulation.creations.attributes.Vector2d;
import agh.project.simulation.factories.AnimalFactory;
import agh.project.simulation.maps.AnimalMap;
import agh.project.simulation.maps.GrassMap;

import java.util.Objects;

public class Animal implements WorldElement, Comparable<Object> {

//    -----Attributes------


    public final int id;
    public int age = 0;
    public int children = 0;
    public int grassEaten = 0;
    public int deathDay = -1;
    private Vector2d position;
    private Direction direction;
    public Energy energy;

    private Gen gen;
    private Constants constants;


//    ------Methods------
@Override
public int compareTo(Object o) {
    Animal other = (Animal) o;
    if(this.getEnergy() - other.getEnergy() != 0) return other.getEnergy() - this.getEnergy();

    if(this.age - other.age != 0) return other.age - this.age;

    return other.children - this.children;
}
    public Animal(int id, Vector2d position, Direction direction, Energy energy, Gen gen){
        this.id = id;
        this.position = position;
        this.direction = direction;
        this.energy = energy;
        this.gen = gen;
    }


    public Gen getGen() {
        return gen;
    }

    public int getEnergy(){
        return this.energy.energy;
    }
    public Energy getObjectEnergy(){
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


    public void setConstants(Constants constants) {
        this.constants = constants;
    }

    public void move() {
//        Losing energy
        this.energy.oneDay();

//      Moves Animal to the next position according to his genes
        if (!this.energy.enoughEnergy()) {
            this.constants.animalFactory.deleteAnimal(this);

            //Remove from the map
            this.constants.animalMap.remove(this);
        } else {

//            Getting older
            this.age += 1;

//        Moving and updating Gen
            this.direction = direction.changeDirection(gen.getNextRotation());
            gen.updateGen();
            Vector2d unitVector = direction.toVector2d();
            Vector2d newPosition = position.add(unitVector);

            this.constants.animalMap.changePosition(this.position, newPosition, this);
        }
    }

    public void toHell(){
        //Change the energy of the Animal
        this.energy.hellLoss();
    }



    public void changePosition(Vector2d newPosition){
        this.position = newPosition;
    }

    @Override
    public String toString() {
        return Integer.toString(this.id);
    }

    public void eat(Grass grass){
        this.energy.addEnergy(grass.getEnergy());

        grass.eatGrass();

        this.grassEaten++;
    }

    public DataStorage toDataStorage(){
        return new DataStorage(this.gen, this.gen.getActualGen(), this.energy, this.grassEaten, this.age,
                this.deathDay, this.children);
    }
}
