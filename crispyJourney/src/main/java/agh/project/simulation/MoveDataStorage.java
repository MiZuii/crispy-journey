package agh.project.simulation;

import agh.project.interfaces.WorldElement;

public class MoveDataStorage {
    public String typeOfObject;
    public int previousX;
    public int previousY;
    public int newX;
    public int newY;

    public boolean oldPositionEmpty;

    //Jak byśmy chcieli zrobić że co ruch zwierzaka mapka się aktualizuje
    public MoveDataStorage(String type, int x1, int y1, int x2, int y2,
                           boolean oldPositionEmpty){

    }
}
