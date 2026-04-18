package simulation;

import actions.GrassPopulateService;
import actions.MapPopulateService;
import actions.MoveCreaturesService;
import config.GameConfig;
import map.GameMap;
import rendering.Rendered;

public class Simulation {
    private final MoveCreaturesService movingCreaturesService = new MoveCreaturesService();
    private final GrassPopulateService grassPopulateService = new GrassPopulateService();
    private final GameMap map;
    private final Rendered gameRendererService;

    public Simulation(int width, int height) {
        map = new GameMap(width, height);
        MapPopulateService populatingService = new MapPopulateService();
        populatingService.execute(map);
        gameRendererService = new Rendered();
    }

    public void nextTurn(int numberOfMove) {
        movingCreaturesService.execute(map);
        // Трава вырастает спутся N шагов
        if (numberOfMove % GameConfig.GRASS_GROWTH_INTERVAL == 0) {
            grassPopulateService.execute(map);
        }
        gameRendererService.withdrawCard(map, numberOfMove);
    }

    public boolean isSimulationActive() {
        return map.getPredatorsAlive() >= 1;
    }
}

