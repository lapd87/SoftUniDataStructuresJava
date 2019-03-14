package _01SweepAndPrune;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;

public class Main {

    public static void main(String[] args) throws IOException {

        LinkedList<GameBox> gameBoxes = new LinkedList<>();

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int tickCounter = 0;
        String input;
        while (true) {
            if ("end".equals(input = bufferedReader.readLine())) {
                break;
            }

            String[] commandArgs = input.split("\\s+");

            switch (commandArgs[0]) {
                case "add":
                    gameBoxes.addFirst(new GameBox(commandArgs[1],
                            Integer.parseInt(commandArgs[2]),
                            Integer.parseInt(commandArgs[3])));

                    sort(gameBoxes);
                    break;
                case "start":
                    break;
                case "move":
                    gameBoxes.stream()
                            .filter(gameBox -> gameBox.getName()
                                    .equals(commandArgs[1]))
                            .findFirst()
                            .ifPresent(gameBox -> gameBox
                                    .move(Integer.parseInt(commandArgs[2]),
                                            Integer.parseInt(commandArgs[3])));
                    sort(gameBoxes);
                case "tick":
                    tickCounter++;

                    print(gameBoxes, tickCounter);
                    break;
            }

        }
    }

    private static void print(LinkedList<GameBox> gameBoxes, int tickCounter) {

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < gameBoxes.size(); i++) {
            GameBox current = gameBoxes.get(i);

            for (int j = i + 1; j < gameBoxes.size(); j++) {
                GameBox other = gameBoxes.get(j);

                if (current.getBounds().intersects(other.getBounds())) {
                    stringBuilder.append(String
                            .format("(%d) %s collides with %s",
                                    tickCounter,
                                    current.getName(),
                                    other.getName()))
                            .append(System.lineSeparator());
                }
            }
        }

        System.out.println(stringBuilder.toString().trim());
    }

    private static void sort(LinkedList<GameBox> gameBoxes) {

        int n = gameBoxes.size();
        for (int i = 1; i < n; i++) {
            GameBox key = gameBoxes.get(i);
            int j = i - 1;

            /* Move elements of arr[0..i-1], that are
               greater than key, to one position ahead
               of their current position */
            while (j >= 0 && gameBoxes.get(j).compareTo(key) > 0) {
                gameBoxes.set(j + 1, gameBoxes.get(j));
                j = j - 1;
            }
            gameBoxes.set(j + 1, key);
        }
    }
}
