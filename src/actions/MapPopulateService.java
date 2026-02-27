package actions;

import map.GameMap;

import java.util.ArrayList;
import java.util.List;

public class MapPopulateService implements Actionable {
    private final List<Populatable> populateService = new ArrayList<>();

    public MapPopulateService() {
        this.populateService.add(new HerbivorePopulator());
        this.populateService.add(new PredatorPopulator());
        this.populateService.add(new GrassPopulator());
        this.populateService.add(new RockPopulator());
        this.populateService.add(new TreePopulator());
    }

    @Override
    public void execute(GameMap map) {
        for (Populatable service : populateService) {
            service.execute(map);
        }

    }
}
