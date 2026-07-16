package actions;

import map.GameMap;

import java.util.ArrayList;
import java.util.List;

public class MapPopulateService implements Actionable {
    private final List<Populatable> populateEntityService = new ArrayList<>();

    public MapPopulateService() {
        this.populateEntityService.add(new HerbivorePopulateService());
        this.populateEntityService.add(new PredatorPopulateService());
        this.populateEntityService.add(new GrassPopulateService());
        this.populateEntityService.add(new RockPopulateService());
        this.populateEntityService.add(new TreePopulateService());
    }

    @Override
    public void execute(GameMap map) {
        for (Populatable entityPopulatorType : populateEntityService) {
            entityPopulatorType.execute(map);
        }

    }
}
