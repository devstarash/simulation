package pathfinding;

import entities.Creature;
import entities.Entity;
import map.Coordinates;
import map.GameMap;

import java.util.*;

public class PathFinder {
    private final GameMap map;

    public PathFinder(GameMap map) {
        this.map = map;
    }

    public List<Coordinates> findPath(Coordinates start) {
        Map<Coordinates, Coordinates> reversedPath = new HashMap<>();
        Coordinates target = searchPath(start, reversedPath);
        if (target.equals(new Coordinates(-1, -1))) {
            return Collections.emptyList();
        }
        return reconstructPath(reversedPath, target);
    }

    private List<Coordinates> reconstructPath(Map<Coordinates, Coordinates> path, Coordinates target) {
        List<Coordinates> correctPath = new ArrayList<>();
        Coordinates current = target;
        while (current != null) {
            correctPath.add(current);
            current = path.get(current);
        }
        Collections.reverse(correctPath);
        correctPath.removeFirst();
        return correctPath;
    }

    private Coordinates searchPath(Coordinates start, Map<Coordinates, Coordinates> path) {
        Creature startingCreature = (Creature) map.getEntity(start);
        Queue<Coordinates> queue = new LinkedList<>();
        queue.add(start);
        path.put(start, null);
        while (!queue.isEmpty()) {
            Coordinates targetCoordinates = queue.poll();
            Entity target = map.getEntity(targetCoordinates);
            if (startingCreature.isEdible(target)) {
                return targetCoordinates;
            }
            for (Coordinates neighbor : getNeighbors(targetCoordinates)) {
                if (!path.containsKey(neighbor)) {
                    Entity currentNeighbor = map.getEntity(neighbor);
                    if (currentNeighbor == null || startingCreature.isEdible(currentNeighbor)) { //
                        queue.add(neighbor);
                        path.put(neighbor, targetCoordinates);
                    }
                }
            }
        }
        return new Coordinates(-1, -1);
    }

    private List<Coordinates> getNeighbors(Coordinates coordinates) {
        List<Coordinates> neighbors = new ArrayList<>();
        List<Coordinates> offsetOptions = List.of(new Coordinates(1, 0), new Coordinates(-1, 0), new Coordinates(0, -1), new Coordinates(0, 1));
        for (Coordinates offset : offsetOptions) {
            Coordinates newCoordinates = new Coordinates(coordinates.x() + offset.x(), coordinates.y() + offset.y());
            boolean isValidCoordinates = map.isInBounds(newCoordinates);
            if (isValidCoordinates) {
                neighbors.add(newCoordinates);
            }
        }
        return neighbors;
    }
}
