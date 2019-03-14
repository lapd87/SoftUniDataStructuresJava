package _02CalculateSequence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Created by IntelliJ IDEA.
 * User: LAPD
 * Date: 10.1.2019 г.
 * Time: 15:51 ч.
 */
public class _02CalculateSequence {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bufferedReader.readLine());

        Queue<Integer> queue = new ArrayDeque<>();
        queue.offer(n);

        String[] result = new String[50];

        for (int i = 0; i < 50; i++) {

            int currentNum = queue.poll();

            if (queue.size() < 50) {
                int next1 = currentNum + 1;
                int next2 = 2 * currentNum + 1;
                int next3 = currentNum + 2;

                queue.offer(next1);
                queue.offer(next2);
                queue.offer(next3);
            }

            result[i] = currentNum + "";
        }


        System.out.println(String.join(", ", result));
    }
}