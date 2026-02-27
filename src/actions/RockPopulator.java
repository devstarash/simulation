package actions;

import config.GameConfig;
import entities.Grass;
import entities.Rock;
import map.Coordinates;
import map.GameMap;

import java.util.List;

public class RockPopulator implements Populatable {
    @Override
    public void execute(GameMap map) {
        List<Coordinates> possibleCoordinates = getPossibleCoordinates(map);
        int count = (int) ((map.getHeight() * map.getWidth()) * GameConfig.ROCK_PERCENTAGE);
        while (!possibleCoordinates.isEmpty() && count > 0) {
            map.setEntity(possibleCoordinates.getLast(), new Rock());
            possibleCoordinates.removeLast();
            count--;
        }
    }
}
