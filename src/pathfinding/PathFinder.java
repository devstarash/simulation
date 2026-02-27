package pathfinding;

import entities.Entity;
import map.Coordinates;
import map.GameMap;

import java.util.*;

public class PathFinder {
    public List<Coordinates> findPath(Coordinates start, Class<? extends Entity> targetClass, GameMap map) {
        Map<Coordinates, Coordinates> reversedPath = new HashMap<>();
        Coordinates target = searchPath(start, targetClass, map, reversedPath);
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

    private Coordinates searchPath(Coordinates start, Class<? extends Entity> targetClass, GameMap map, Map<Coordinates, Coordinates> path) {
        Queue<Coordinates> queue = new LinkedList<>();
        queue.add(start);
        path.put(start, null);
        while (!queue.isEmpty()) {
            Coordinates targetCoordinates = queue.poll();
            Entity target = map.getEntity(targetCoordinates);
            if (targetClass.isInstance(target)) {
                return targetCoordinates;
            }
            for (Coordinates neighbor : getNeighbors(targetCoordinates, map)) {
                if (!path.containsKey(neighbor)) {
                    if (map.getEntity(neighbor) == null || targetClass.isInstance(map.getEntity(neighbor))) {
                        queue.add(neighbor);
                        path.put(neighbor, targetCoordinates);
                    }
                }
            }
        }
        return new Coordinates(-1, -1);
    }

    private List<Coordinates> getNeighbors(Coordinates coordinates, GameMap map) {
        List<Coordinates> neighbors = new ArrayList<>();
        List<Coordinates> offsetOptions = List.of(
                new Coordinates(1, 0), new Coordinates(-1, 0),
                new Coordinates(0, -1), new Coordinates(0, 1)
        );
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
