package actions;

import entities.Creature;
import map.GameMap;

import java.util.ArrayList;
import java.util.List;

public class MoveCreaturesAction implements Actionable {
    @Override
    public void execute(GameMap map) {
        List<Creature> livingCreatures = map.getLivingCreatures();
        for (Creature creature : new ArrayList<>(livingCreatures)) {
            if (map.getCoordinatesOfEntity(creature) != null) {
                creature.makeMove(map);
            }
        }
    }
}
