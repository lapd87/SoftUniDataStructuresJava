package _03LongestSubsequence;

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
 * Time: 10:43 ч.
 */
public class _03LongestSubsequence {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        List<Integer> numbers = Arrays.stream(bufferedReader
                .readLine()
                .split("\\s+"))
                .filter(i -> !i.equals(""))
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toList());

        List<Integer> longestSubsequence = getLongestSubsequence(numbers);

        System.out.println(String.join(" ",
                longestSubsequence.stream()
                        .map(String::valueOf)
                        .collect(Collectors.toList())));
    }

    private static List<Integer> getLongestSubsequence(List<Integer> numbers) {

        int maxLength = 1;
        int count = 1;
        int element = numbers.get(0);

        for (int i = 0; i < numbers.size() - 1; i++) {
            if (numbers.get(i).equals(numbers.get(i + 1))) {
                count++;

                if (count > maxLength) {
                    maxLength = count;
                    element = numbers.get(i);
                }
            } else {
                count = 1;
            }
        }

        return Collections.nCopies(maxLength, element);
    }
}