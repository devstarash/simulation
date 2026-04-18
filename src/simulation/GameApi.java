package simulation;

import config.GameConfig;
import rendering.Rendered;

import java.util.InputMismatchException;
import java.util.Scanner;


public class GameApi {
    private int numberOfMove = 0;
    private final Scanner userInputScanner = new Scanner(System.in);
    private final Rendered gameRendererService = new Rendered();

    public void newGame() {
        int height = -1;
        int width = -1;
        try {
            while (height < 0 || width < 0) {
                System.out.println("Введите размеры карты (высоту и ширину) через пробел: ");
                height = userInputScanner.nextInt();
                width = userInputScanner.nextInt();
            }
        } catch (InputMismatchException misExc) {
            throw new InputMismatchException("При вводе параметров карты ожидаются 2 целых числа.");
        }
        startSimulation(width, height);
    }

    public void startSimulation(int width, int height) {
        Simulation game = new Simulation(width, height);
        gameRendererService.printWelcomeMessage();
        try {
            Thread.sleep(GameConfig.START_PAUSE);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            while (game.isSimulationActive()) {
                numberOfMove++;
                game.nextTurn(numberOfMove);
                int numberFreezingIntervals = 10;
                int freezingSecondsInterval = GameConfig.DELAY_BETWEEN_MOVE / numberFreezingIntervals;
                while (numberFreezingIntervals > 0) {
                    Thread.sleep(freezingSecondsInterval);
                    if (System.in.available() > 0) {
                        String userInput = userInputScanner.nextLine();
                        if (userInput.equalsIgnoreCase(GameConfig.STOP_GAME_LINE)) {
                            pauseGame();
                            break;
                        }
                    }
                    numberFreezingIntervals--;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        stopGame();
    }

    private void stopGame() {
        gameRendererService.printFinalMessage();
    }

    private void pauseGame() {
        String userInputLine = "";
        while (!userInputLine.equalsIgnoreCase(GameConfig.GAME_RESUME_LINE)) {
            System.out.printf("Ввведите '%s' для продолжения игры \n", GameConfig.GAME_RESUME_LINE);
            userInputLine = userInputScanner.nextLine();
        }
    }
}

