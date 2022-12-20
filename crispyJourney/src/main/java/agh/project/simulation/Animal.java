package agh.project.simulation;

import agh.project.enumerators.Direction;

import java.util.Objects;

public class Animal {

//    -----Attributes------
    // How old is the animal ???
    // How many children animal has ???
    public final int id;
    private Vector2d position;
    private Direction direction;
    private Energy energy;

    private Gen gen;


//    ------Methods------

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
//        Moves Animal to the next position according to his genes
        if (!this.energy.enoughEnergy()); //Animal to delete (not implemented yet)

        this.direction = direction.changeDirection(gen.getNextRotation());
        gen.updateGen();

        Vector2d unitVector = direction.toVector2d();
        this.position = position.add(unitVector);

/**
 In addition, you will have to call up the methods responsible for breeding, eating and falling into hell.
 To implement them, you need a map that does not exist yet
  */



    }

}
