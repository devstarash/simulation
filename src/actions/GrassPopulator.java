package actions;

import config.GameConfig;
import entities.Grass;
import map.Coordinates;
import map.GameMap;

import java.util.List;

public class GrassPopulator implements Populatable {
    @Override
    public void execute(GameMap map) {
        List<Coordinates> possibleCoordinates = getPossibleCoordinates(map);
        int count = (int) ((map.getHeight() * map.getWidth()) * GameConfig.GRASS_PERCENTAGE);
        while (!possibleCoordinates.isEmpty() && count > 0) {
            map.setEntity(possibleCoordinates.getLast(), new Grass());
            possibleCoordinates.removeLast();
            count--;
        }
    }
}
