package _02CountSymbols;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: LAPD
 * Date: 4.2.2019 г.
 * Time: 09:25 ч.
 */
public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        char[] input = bufferedReader.readLine()
                .toCharArray();

        Dictionary<Character, Integer> charCount = new HashTable<>(input.length);

        for (char c : input) {

            if (!charCount.containsKey(c)) {
                charCount.add(c, 0);
            }

            charCount.addOrReplace(c, charCount.get(c) + 1);
        }

        List<Character> keys = new ArrayList<>(input.length);
        charCount.forEach(kv -> keys.add(kv.getKey()));
        keys.sort(Comparator.naturalOrder());

        for (Character key : keys) {
            System.out.println(String.format("%s: %d time/s",
                    key, charCount.get(key)));
        }
    }
}