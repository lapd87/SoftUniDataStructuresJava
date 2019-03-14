import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class PTest20 {

    private Microsystem microsystem;
    private Generator<Computer> generator;

    static class Generator<K> {
        private static final Random RANDOM = new Random();
        private static final double[] SCREEN_SIZE = {15.6, 14, 13.2, 16, 13.3};
        private static final String[] COLORS = {"white", "gray", "black", "red", "pink", "orange", "yellow", "green", "magenta", "cyan", "blue"};

        List<Computer> generateComputers(int count) {
            List<Computer> computers = new ArrayList<>();
            for (int i = 1; i <= count; i++) {
                String color = COLORS[Math.abs(RANDOM.nextInt()) % COLORS.length];
                Brand brand = Brand.values()[Math.abs(RANDOM.nextInt()) % Brand.values().length];
                double price = 1000D + (5000D - 1000D) * RANDOM.nextDouble();
                double screenSize = SCREEN_SIZE[Math.abs(RANDOM.nextInt()) % SCREEN_SIZE.length];
                Computer computer = new Computer(i, brand, price, screenSize, color);
                computers.add(computer);
            }
            Collections.shuffle(computers);
            return computers;
        }

        <T> Optional<Map.Entry<T, Long>> getMostOccurrences(List<K> invoices, Function<K, T> function) {
            return invoices.stream()
                    .collect(Collectors.groupingBy(function, Collectors.counting()))
                    .entrySet()
                    .stream()
                    .max(Comparator.comparing(Map.Entry::getValue));
        }
    }

    @Before
    public void init() {
        this.microsystem = new MicrosystemImpl();
        this.generator = new Generator<>();
    }

    @Test
    public void getAllWithColor_with_100000_entries() {

        List<Computer> computers = this.generator.generateComputers(100000);
        computers.forEach(comp -> this.microsystem.createComputer(comp));
        String color = this.generator.getMostOccurrences(computers,Computer::getColor).get().getKey();
        long start = System.currentTimeMillis();
        this.microsystem.getAllWithColor(color);
        long stop = System.currentTimeMillis();

        long elapsedTime = stop - start;

//        throw new IllegalArgumentException("time: " + elapsedTime);
        Assert.assertTrue(elapsedTime <= 50);
    }


}
