package actions;

import config.GameConfig;
import entities.Entity;
import entities.Predator;
import map.Coordinates;
import map.GameMap;

import java.util.List;

public class PredatorPopulateService implements Populatable {
    @Override
    public void execute(GameMap map) {
        List<Coordinates> possibleMoveCoordinates = getPossibleCoordinates(map);
        int mapSize = map.getHeight() * map.getWidth();
        int numberPopulatedEntity = (int) Math.ceil(mapSize * GameConfig.PREDATOR_PERCENTAGE);
        while (!possibleMoveCoordinates.isEmpty() && numberPopulatedEntity > 0) {
            Entity insertableEntity = new Predator(GameConfig.PREDATOR_SPEED, GameConfig.PREDATOR_HEALTH, GameConfig.PREDATOR_DAMAGE);
            Coordinates insertableCoordinates = possibleMoveCoordinates.getLast();
            map.setEntity(insertableCoordinates, insertableEntity);
            possibleMoveCoordinates.removeLast();
            numberPopulatedEntity--;
        }
    }
}
