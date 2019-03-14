package _03Phonebook;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by IntelliJ IDEA.
 * User: LAPD
 * Date: 4.2.2019 г.
 * Time: 09:25 ч.
 */
public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        Dictionary<String, String> phonebook = new HashTable<>();

        String input;
        while (true) {
            if ("search".equals(input = bufferedReader.readLine())) {
                break;
            }

            String[] inputArgs = input.split("-");

            phonebook.addOrReplace(inputArgs[0], inputArgs[1]);
        }

        while (true) {
            if ("end".equals(input = bufferedReader.readLine())) {
                break;
            }

            if (phonebook.containsKey(input)) {
                System.out.println(String
                        .format("%s -> %s",
                                input, phonebook.get(input)));
            } else {
                System.out.println(String
                        .format("Contact %s does not exist.",
                                input));
            }
        }
    }
}