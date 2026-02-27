package simulation;

import actions.MapPopulateService;
import actions.MoveCreaturesAction;
import actions.PopulateGrassAction;
import config.GameConfig;
import map.GameMap;
import rendering.Rendered;

public class Simulation {
    private final MoveCreaturesAction movingCreatures = new MoveCreaturesAction();
    private final PopulateGrassAction addingGrass = new PopulateGrassAction();
    private final GameMap map;

    public Simulation(int width, int height) {
        map = new GameMap(width, height);
        MapPopulateService populateService = new MapPopulateService();
        populateService.execute(map);
    }

    public void nextTurn(int numberOfMove) {
        movingCreatures.execute(map);
        Rendered.withdrawCard(map);
        if (numberOfMove % GameConfig.GRASS_GROWTH_INTERVAL == 0) {
            addingGrass.execute(map);
        }
    }

    public boolean isSimulationActive() {
        return map.getPredatorsAlive() >= 1;
    }
}

