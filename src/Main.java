import simulation.GameApi;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int width;
        int height;
        do {
            System.out.println("Ввведите параметры ширины и высоты карты (Они должны быть больше 0).");
            width = scanner.nextInt();
            height = scanner.nextInt();
        } while (width < 0 || height < 0);
        GameApi game = new GameApi();
        game.startSimulation(width, height);

    }
}
