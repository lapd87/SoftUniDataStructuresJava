package _01ReverseNumbers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * Created by IntelliJ IDEA.
 * User: LAPD
 * Date: 10.1.2019 г.
 * Time: 15:51 ч.
 */
public class _01ReverseNumbers {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String[] input = bufferedReader.readLine()
                .split("\\s+");

        Stack<String> stack = new Stack<>();

        for (String s : input) {
            stack.push(s);
        }

        StringBuilder result = new StringBuilder();
        while (stack.size() > 0) {
            result.append(stack.pop()).append(" ");
        }

        System.out.println(result.toString().trim());
    }
}