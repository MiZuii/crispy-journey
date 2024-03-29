package agh.project.enumerators;

public enum Rotation {

/**    -----ENUM VALUES-----
       The values of this type represent what angle our animal will turn. In particular:
        * F - animal will not turn
        * L - animal will turn 90 degrees non-clockwise
        * B - animal will turn 180 degrees
        * R - animal will turn 90 degrees clockwise
        Some combination of these values also have the meaning. Markers work the same way as the compass rose
*/
    F,  //N
    FR, //NE
    R,  //E
    BR,
    B,
    BL,
    L,
    FL,
    ;

    @Override
    public String toString() {
        return switch (this){
            case F -> "F";
            case B -> "B";
            case L -> "L";
            case R -> "R";
            case BL -> "BL";
            case FL -> "FL";
            case BR -> "BR";
            case FR -> "FR";
        };
    }
}
