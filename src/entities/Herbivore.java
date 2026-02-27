package entities;

import config.GameConfig;
import map.Coordinates;
import map.GameMap;

import java.util.List;

public class Herbivore extends Creature {
    public Herbivore(int speed, int hitPoints) {
        super(speed, hitPoints);
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
        if (entity instanceof Grass grass) {
            this.hitPoints += grass.getNutritionValue();
            map.moveEntity(this, coordinates);
        }
    }

    @Override
    public String getSprite() {
        return "\uD83D\uDC11";
    }
}
