package _04RemoveOddOccurrences;

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
 * Time: 15:06 ч.
 */
public class _04RemoveOddOccurrences {
    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        List<String> input = Arrays.stream(bufferedReader
                .readLine()
                .split("\\s+"))
                .filter(i -> !i.equals(""))
                .collect(Collectors.toList());

        int index = 0;

        while (index < input.size()) {

            String current = input.get(index);

            int occurrences = Collections.frequency(input, current);

            if (occurrences % 2 != 0) {
                input.removeAll(Collections.singleton(current));
            } else {
                index++;
            }
        }

        System.out.println(String.join(" ", input));
    }
}