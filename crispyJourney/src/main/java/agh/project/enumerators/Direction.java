package agh.project.enumerators;

import agh.project.simulation.creations.attributes.Vector2d;

public enum Direction {

/**    Direction enum tells animal where there are looking at. Additionally, this value determinate animal next moves
    Example: Animal with moves' list [North, SouthWest, West] and initial position on the map (1,1) will move:
    - North - (1, 1) -> (1, 2)
    - SouthWest - (1, 2) -> (0, 1)
    - West - (0, 1) -> (0, 0)
*/


//    ----ENUM VALUE-----
    NORTH,
    NORTH_EAST,
    EAST,
    SOUTH_EAST,
    SOUTH,
    SOUTH_WEST,
    WEST,
    NORTH_WEST;



//    -----METHODS------

    public Vector2d toVector2d(){
//        Returns the Vector2d that represents movement on the map in the direction of direction variable.
        return switch (this){
            case NORTH -> new Vector2d(0,1);
            case NORTH_EAST -> new Vector2d(1,1);
            case EAST -> new Vector2d(1,0);
            case SOUTH_EAST -> new Vector2d(1,-1);
            case SOUTH -> new Vector2d(0,-1);
            case SOUTH_WEST -> new Vector2d(-1,-1);
            case WEST -> new Vector2d(-1,0);
            case NORTH_WEST -> new Vector2d(-1,1);
        };
    }

    public Direction changeDirection(Rotation rotation){
//        Changes the actual Direction to Direction after rotation
        int index = this.ordinal();
        int rotationIndex = rotation.ordinal();
        Direction [] enumElements = Direction.values();
//        The order in the enums allows for such an operation
        return enumElements[(index + rotationIndex) % enumElements.length];

    }




}
