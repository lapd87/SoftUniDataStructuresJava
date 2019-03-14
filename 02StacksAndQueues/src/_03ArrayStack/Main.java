package _03ArrayStack;

import java.util.Arrays;

/**
 * Created by IntelliJ IDEA.
 * User: LAPD
 * Date: 11.1.2019 г.
 * Time: 10:58 ч.
 */
public class Main {
    public static void main(String[] args) {
        ArrayStack<Integer> stack = new ArrayStack<>();

        stack.push(1);
        stack.push(2);

        System.out.println(Arrays.toString(stack.toArray()));

        System.out.println(stack.pop());

        System.out.println(stack.size());
    }
}