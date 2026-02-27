package actions;

import config.GameConfig;
import entities.Grass;
import map.Coordinates;
import map.GameMap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PopulateGrassAction implements Actionable {
    @Override
    public void execute(GameMap map) {
        List<Coordinates> possibleCoordinates = new ArrayList<>();
        int mapSize = map.getHeight() * map.getWidth();
        if ((double) map.getCurrentGrassAmount() / mapSize >= GameConfig.GRASS_PERCENTAGE) {
            return;
        }
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                Coordinates current = new Coordinates(x, y);
                if (map.getEntity(current) == null) {
                    possibleCoordinates.add(current);
                }
            }
        }
        Collections.shuffle(possibleCoordinates);
        double missingInterest = GameConfig.GRASS_PERCENTAGE - (double) map.getCurrentGrassAmount() / mapSize;
        int count = (int) Math.ceil(missingInterest * mapSize);
        while (count > 0 && !possibleCoordinates.isEmpty()) {
            Coordinates coordinates = possibleCoordinates.removeLast();
            map.setEntity(coordinates, new Grass());
            count--;
        }
    }
}
