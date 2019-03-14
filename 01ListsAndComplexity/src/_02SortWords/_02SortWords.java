package _02SortWords;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA.
 * User: LAPD
 * Date: 26.12.2018 г.
 * Time: 10:36 ч.
 */
public class _02SortWords {
    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println(Arrays.stream(bufferedReader
                .readLine()
                .split("\\s+"))
                .filter(i -> !i.equals(""))
                .sorted(String::compareTo)
                .collect(Collectors.joining(" ")));
    }
}