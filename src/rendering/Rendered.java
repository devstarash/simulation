package rendering;

import config.GameConfig;
import entities.Entity;
import map.Coordinates;
import map.GameMap;

public class Rendered {
    public static void withdrawCard(GameMap map) {
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                Entity entity = map.getEntity(new Coordinates(x, y));
                if (entity == null){
                    System.out.print("\uD83D\uDFEB");

                }
                else {
                    String entitySprite = entity.getSprite();
                    System.out.print(entitySprite);
                }
            }
            System.out.println();
        }
        System.out.println("Количество травоядных на карте: " + map.getHerbivoresAlive());
        System.out.println("Количество хищников на карте: " + map.getPredatorsAlive());
        System.out.println("Количество травы на карте: " + map.getCurrentGrassAmount());
    }
    public static void printWelcomeMessage() {
        System.out.println("🌿 Добро пожаловать в Симуляцию Экосистемы! 🌿");
        System.out.println("Вы наблюдатель за миром.");
        System.out.println("Следите за балансом травоядных и хищников.");
        System.out.println("\nВведите 'stop' в любой момент, чтобы остановить время.\n");
        try {
            Thread.sleep(GameConfig.START_PAUSE);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
