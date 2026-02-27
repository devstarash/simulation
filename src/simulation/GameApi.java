package simulation;

import config.GameConfig;
import rendering.Rendered;

import java.util.Scanner;


public class GameApi {
    private int numberOfMove = 0;
    private final Scanner userInputScanner = new Scanner(System.in);

    public void startSimulation(int width, int height) {
        Simulation game = new Simulation(width, height);
        Rendered.printWelcomeMessage();
        try {
            while (game.isSimulationActive()) {
                numberOfMove++;
                game.nextTurn(numberOfMove);
                int iterationCount = 10;
                int amountOfSeconds = GameConfig.DELAY_BETWEEN_MOVE / iterationCount;
                while (iterationCount > 0) {
                    Thread.sleep(amountOfSeconds);
                    if (System.in.available() > 0) {
                        String userInput = userInputScanner.nextLine();
                        if (userInput.equalsIgnoreCase("stop")) {
                            pauseGame();
                            break;
                        }
                    }
                    iterationCount--;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        stopGame();
    }

    private void stopGame() {
        System.out.println("Игра окончена! Все хищники умерли от голода.");
    }

    private void pauseGame() {
        String result = "";
        while (!result.equals("continue")) {
            System.out.println("Ввведите 'continue' для продолжения игры");
            result = userInputScanner.nextLine();
        }
    }
}

