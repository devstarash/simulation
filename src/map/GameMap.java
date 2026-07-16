package map;

import config.GameConfig;
import entities.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameMap {
    private final Map<Coordinates, Entity> map;
    private final Map<Entity, Coordinates> helpMap;
    private int herbivoresAlive = 0;
    private int predatorsAlive = 0;
    private int currentGrassAmount = 0;
    private final List<Creature> livingCreatures;
    private final int width;
    private final int height;

    public GameMap(int width, int height) {
        this.map = new HashMap<>();
        this.helpMap = new HashMap<>();
        this.width = width;
        this.height = height;
        livingCreatures = new ArrayList<>();
    }

    public GameMap() {
        this.map = new HashMap<>();
        this.helpMap = new HashMap<>();
        this.width = GameConfig.DEFAULT_MAP_SIZES;
        this.height = GameConfig.DEFAULT_MAP_SIZES;
        livingCreatures = new ArrayList<>();
    }

    public void setEntity(Coordinates coordinates, Entity entity) {
        removeEntity(coordinates);
        if (entity != null) {
            map.put(coordinates, entity);
            helpMap.put(entity, coordinates);
            onEntityAdded(entity);
        }
    }

    public boolean isInBounds(Coordinates coordinates) {
        boolean isXValid = coordinates.x() >= 0 && coordinates.x() < width;
        boolean isYValid = coordinates.y() >= 0 && coordinates.y() < height;
        return isXValid && isYValid;
    }

    public void removeEntity(Coordinates coordinates) {
        Entity removed = map.remove(coordinates);
        if (removed == null) {
            return;
        }
        helpMap.remove(removed);
        onEntityRemoved(removed);
    }

    public Entity getEntity(Coordinates coordinates) {
        if (isInBounds(coordinates)) {
            return map.get(coordinates);
        }
        return null;
    }

    public Coordinates getCoordinatesOfEntity(Entity entity) {
        return helpMap.get(entity);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void moveEntity(Entity entity, Coordinates targetCoordinates) {
        Coordinates coordinates = getCoordinatesOfEntity(entity);
        if (coordinates == null) {
            setEntity(targetCoordinates, entity);
        } else {
            removeEntity(coordinates);
            setEntity(targetCoordinates, entity);
        }
    }

    private void onEntityAdded(Entity entity) {
        if (entity instanceof Predator predator) {
            livingCreatures.add(predator);
            predatorsAlive++;
        } else if (entity instanceof Herbivore herbivore) {
            livingCreatures.add(herbivore);
            herbivoresAlive++;
        } else if (entity instanceof Grass) {
            currentGrassAmount++;
        }
    }

    private void onEntityRemoved(Entity entity) {
        if (entity instanceof Predator predator) {
            livingCreatures.remove(predator);
            predatorsAlive--;
        } else if (entity instanceof Herbivore herbivore) {
            livingCreatures.remove(herbivore);
            herbivoresAlive--;
        } else if (entity instanceof Grass) {
            currentGrassAmount--;
        }
    }

    public List<Creature> getLivingCreatures() {
        return livingCreatures;
    }

    public int getPredatorsAlive() {
        return predatorsAlive;
    }

    public int getHerbivoresAlive() {
        return herbivoresAlive;
    }

    public int getCurrentGrassAmount() {
        return currentGrassAmount;
    }
}
