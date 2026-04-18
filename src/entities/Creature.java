package entities;

import map.Coordinates;
import map.GameMap;
import pathfinding.PathFinder;

import java.util.List;

public abstract class Creature extends Entity {
    protected int speed;
    protected int hitPoints;

    public Creature(int speed, int hitPoints) {
        this.speed = speed;
        this.hitPoints = hitPoints;
    }

    public abstract void makeMove(GameMap map, List<Coordinates> pathToFood);

    protected void die(GameMap map) {
        Coordinates coordinates = map.getCoordinatesOfEntity(this);
        map.removeEntity(coordinates);
    }

    public abstract boolean isEdible(Entity target);

}
