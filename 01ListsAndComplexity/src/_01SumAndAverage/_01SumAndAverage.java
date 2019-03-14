package _01SumAndAverage;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA.
 * User: LAPD
 * Date: 26.12.2018 г.
 * Time: 10:08 ч.
 */
public class _01SumAndAverage {
    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        List<String> numbers = Arrays.stream(bufferedReader
                .readLine()
                .split("\\s+"))
                .filter(i -> !i.equals(""))
                .collect(Collectors.toList());

        if (numbers.size() == 0) {
            System.out.println("Sum=0; Average=0.00");
            return;
        }

        System.out.printf("Sum=%d; Average=%.2f%n",
                numbers.stream()
                        .mapToInt(Integer::parseInt)
                        .sum(),
                numbers.stream()
                        .mapToInt(Integer::parseInt)
                        .average()
                        .orElse(0.0));
    }
}