package entities;

import config.GameConfig;
import map.Coordinates;
import map.GameMap;

import java.util.List;

public class Predator extends Creature implements Predatory {
    private final int hitPower;

    public Predator(int speed, int hitPoints, int hitPower) {
        super(speed, hitPoints);
        this.hitPower = hitPower;
    }

    @Override
    public void makeMove(GameMap map, List<Coordinates> pathToFood) {
        if (pathToFood.isEmpty()) {
            hitPoints -= GameConfig.IDLE_HUNGER_DAMAGE;
        } else if (this.speed >= pathToFood.size()) {
            attack(pathToFood.getLast(), map);
        } else {
            map.moveEntity(this, pathToFood.get(this.speed - 1));
            hitPoints -= GameConfig.STEP_ENERGY_DRAIN;
        }
        if (hitPoints <= 0) {
            die(map);
        }
    }

    private void attack(Coordinates coordinates, GameMap map) {
        Entity entity = map.getEntity(coordinates);
        if (entity instanceof Herbivore herbivore) {
            if (this.hitPower >= herbivore.hitPoints) {
                this.hitPoints += herbivore.hitPoints;
                map.moveEntity(this, coordinates);
            } else {
                herbivore.hitPoints -= this.hitPower;
            }
        }
    }

    @Override
    public String getSprite() {
        return "\uD83D\uDC3A";
    }

    @Override
    public boolean isEdible(Entity target) {
        return target instanceof Mammalian;
    }
}
