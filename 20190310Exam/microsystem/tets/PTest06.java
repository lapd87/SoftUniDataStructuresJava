import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class PTest06 {

    private Microsystem microsystem;
    private Generator generator;

    static class Generator {
        private static final Random RANDOM = new Random();
        private double[] SCREEN_SIZE = {15.6, 14, 13.2, 16, 13.3};
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
    }

    @Before
    public void init() {
        this.microsystem = new MicrosystemImpl();
        this.generator = new Generator();
    }

    @Test
    public void contains_with_200000_entries() {

        List<Computer> computers = this.generator.generateComputers(200000);

        computers.forEach(comp -> this.microsystem.createComputer(comp));
        long start = System.currentTimeMillis();
        for (Computer computer : computers) {
            Assert.assertTrue(this.microsystem.contains(computer.getNumber()));
        }
        long stop = System.currentTimeMillis();

        long elapsedTime = stop - start;

//        throw new IllegalArgumentException("time: " + elapsedTime);
        Assert.assertTrue(elapsedTime <= 115);

    }


}
