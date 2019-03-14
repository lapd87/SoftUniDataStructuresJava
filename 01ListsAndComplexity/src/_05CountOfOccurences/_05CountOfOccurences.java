package _05CountOfOccurences;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA.
 * User: LAPD
 * Date: 26.12.2018 г.
 * Time: 15:18 ч.
 */
public class _05CountOfOccurences {
    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        List<String> input = Arrays.stream(bufferedReader
                .readLine()
                .split("\\s+"))
                .filter(i -> !i.equals(""))
                .collect(Collectors.toList());

        while (input.size() > 0) {

            String current = input.get(0);

            int occurrences = Collections.frequency(input, current);

            input.removeAll(Collections.singleton(current));

            System.out.printf("%s -> %d times%n",
                    current, occurrences);
        }
    }
}