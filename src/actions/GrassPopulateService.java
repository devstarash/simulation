package actions;

import config.GameConfig;
import entities.Entity;
import entities.Grass;
import map.Coordinates;
import map.GameMap;

import java.util.List;

public class GrassPopulateService implements Populatable {
    @Override
    public void execute(GameMap map) {
        List<Coordinates> possibleMoveCoordinates = getPossibleCoordinates(map);
        int mapSize = map.getHeight() * map.getWidth();
        int numberPopulatedEntity = (int) (mapSize * GameConfig.GRASS_PERCENTAGE) - map.getCurrentGrassAmount();
        while (!possibleMoveCoordinates.isEmpty() && numberPopulatedEntity > 0) {
            Coordinates insertableCoordinates = possibleMoveCoordinates.getLast();
            Entity insertableEntity = new Grass();
            map.setEntity(insertableCoordinates, insertableEntity);
            possibleMoveCoordinates.removeLast();
            numberPopulatedEntity--;
        }
    }
}
