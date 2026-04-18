package actions;

import config.GameConfig;
import entities.Entity;
import entities.Herbivore;
import map.Coordinates;
import map.GameMap;

import java.util.List;

public class HerbivorePopulateService implements Populatable {
    @Override
    public void execute(GameMap map) {
        List<Coordinates> possibleMoveCoordinates = getPossibleCoordinates(map);
        int mapSize = map.getHeight() * map.getWidth();
        int numberPopulatedEntity = (int) Math.ceil(mapSize * GameConfig.HERBIVORE_PERCENTAGE);
        while (!possibleMoveCoordinates.isEmpty() && numberPopulatedEntity > 0) {
            Entity insertableEntity = new Herbivore(GameConfig.HERBIVORE_SPEED, GameConfig.HERBIVORE_HEALTH);
            Coordinates insertableCoordinates = possibleMoveCoordinates.getLast();
            map.setEntity(insertableCoordinates, insertableEntity);
            possibleMoveCoordinates.removeLast();
            numberPopulatedEntity--;
        }

    }
}
