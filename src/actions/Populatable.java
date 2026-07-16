package actions;

import map.Coordinates;
import map.GameMap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public interface Populatable extends Actionable {
    default List<Coordinates> getPossibleCoordinates(GameMap map) {
        List<Coordinates> possibleCoordinates = new ArrayList<>();
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                Coordinates currentCoordinates = new Coordinates(x, y);
                if (map.getEntity(currentCoordinates) == null) {
                    possibleCoordinates.add(currentCoordinates);
                }
            }
        }
        Collections.shuffle(possibleCoordinates);
        return possibleCoordinates;
    }
}
