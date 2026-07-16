package rendering;

import config.GameConfig;
import entities.Entity;
import map.Coordinates;
import map.GameMap;

public class Rendered {
    public void withdrawCard(GameMap map, int numberOfMove) {
        System.out.printf("Номер хода: %s \n", numberOfMove);
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                Coordinates currentCoordinates = new Coordinates(x, y);
                Entity entity = map.getEntity(currentCoordinates);
                if (entity == null) {
                    System.out.print(GameConfig.GAME_LANDSCAPE_SPRITE);

                } else {
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

    public void printWelcomeMessage() {
        System.out.println("🌿 Добро пожаловать в Симуляцию Экосистемы! 🌿");
        System.out.println("Вы наблюдатель за миром.");
        System.out.println("Следите за балансом травоядных и хищников.");
        System.out.printf("\nВведите '%s' в любой момент, чтобы остановить время.\n", GameConfig.STOP_GAME_LINE);
    }

    public void printFinalMessage() {
        System.out.println("Игра окончена! Все хищники умерли от голода.");
    }
}
