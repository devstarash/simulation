package actions;

import config.GameConfig;
import entities.Herbivore;
import map.Coordinates;
import map.GameMap;

import java.util.List;

public class HerbivorePopulator implements Populatable {
    @Override
    public void execute(GameMap map) {
        List<Coordinates> possibleCoordinates = getPossibleCoordinates(map);
        int count = (int) ((map.getHeight() * map.getWidth()) * GameConfig.HERBIVORE_PERCENTAGE);
        while (!possibleCoordinates.isEmpty() && count > 0) {
            map.setEntity(possibleCoordinates.getLast(), new Herbivore(GameConfig.HERBIVORE_SPEED, GameConfig.HERBIVORE_HEALTH));
            possibleCoordinates.removeLast();
            count--;
        }

    }
}
