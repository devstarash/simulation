package actions;

import config.GameConfig;
import entities.Herbivore;
import entities.Predator;
import map.Coordinates;
import map.GameMap;

import java.util.List;

public class PredatorPopulator implements Populatable {
    @Override
    public void execute(GameMap map) {
        List<Coordinates> possibleCoordinates = getPossibleCoordinates(map);
        int count = (int) ((map.getHeight() * map.getWidth()) * GameConfig.PREDATOR_PERCENTAGE);
        while (!possibleCoordinates.isEmpty() && count > 0) {
            map.setEntity(possibleCoordinates.getLast(), new Predator(GameConfig.PREDATOR_SPEED, GameConfig.PREDATOR_HEALTH, GameConfig.PREDATOR_DAMAGE));
            possibleCoordinates.removeLast();
            count--;
        }
    }
}
