package actions;

import config.GameConfig;
import entities.Entity;
import entities.Tree;
import map.Coordinates;
import map.GameMap;

import java.util.List;

public class TreePopulateService implements Populatable {
    @Override
    public void execute(GameMap map) {
        List<Coordinates> possibleMoveCoordinates = getPossibleCoordinates(map);
        int mapSize = map.getHeight() * map.getWidth();
        int numberPopulatedEntity = (int) Math.ceil(mapSize * GameConfig.TREE_PERCENTAGE);
        while (!possibleMoveCoordinates.isEmpty() && numberPopulatedEntity > 0) {
            Coordinates insertableCoordinates = possibleMoveCoordinates.getLast();
            Entity insertableEntity = new Tree();
            map.setEntity(insertableCoordinates, insertableEntity);
            possibleMoveCoordinates.removeLast();
            numberPopulatedEntity--;
        }
    }
}
