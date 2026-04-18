package actions;

import entities.Creature;
import map.Coordinates;
import map.GameMap;
import pathfinding.PathFinder;

import java.util.ArrayList;
import java.util.List;

public class MoveCreaturesService implements Actionable {
    @Override
    public void execute(GameMap map) {
        PathFinder pathFinderService = new PathFinder(map);
        List<Creature> livingCreatures = map.getLivingCreatures(); // Карта возвращает всех живых существ
        for (Creature creature : new ArrayList<>(livingCreatures)) {
            Coordinates current = map.getCoordinatesOfEntity(creature);
            if (current != null) { // проверка на то, что существо не сьели в предыдущем ходе
                List<Coordinates> pathToFood = pathFinderService.findPath(current);
                creature.makeMove(map, pathToFood);
            }
        }
    }
}
