package agh.project.simulation.maps;

import agh.project.interfaces.WorldElement;
import agh.project.interfaces.WorldMapBoundary;
import agh.project.simulation.creations.attributes.Vector2d;

import java.util.ArrayList;
import java.util.HashMap;

public class RectangularMap {
    //    -----ATRIBUTES------
    public HashMap<Vector2d, ArrayList<WorldElement>> occupiedPosition = new HashMap<Vector2d, ArrayList<WorldElement>>();
    public WorldMapBoundary boundary;

    //    ------METHODS------


    public RectangularMap(WorldMapBoundary boundary){
        this.boundary = boundary;
    }

    /**
     * Place the element on the appropriate place on the Map
     */
    public void place(WorldElement worldElement){
        ArrayList<WorldElement> worldElementsArrayList = (occupiedPosition.get(worldElement.getPosition()) == null)
                ? new ArrayList<WorldElement>() :
                occupiedPosition.get(worldElement.getPosition());

        worldElementsArrayList.add(worldElement);
        occupiedPosition.put(worldElement.getPosition(), worldElementsArrayList);

    }

    /**
     * Remove the element on the appropriate place on the Map
     */
    public  void remove(WorldElement worldElement){
        ArrayList<WorldElement> worldElementArrayList = occupiedPosition.get(worldElement.getPosition());
        worldElementArrayList.remove(worldElement);

        if (worldElementArrayList.size() == 0) occupiedPosition.remove(worldElement.getPosition());
    }

    /**
     * Check if the place isOccupied by object
     */
    public  boolean isOccupied(Vector2d position){
        return occupiedPosition.get(position) != null &&
                occupiedPosition.get(position).size() != 0;
    }








}
