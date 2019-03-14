package _01TextEditor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by IntelliJ IDEA.
 * User: LAPD
 * Date: 26.1.2019 г.
 * Time: 18:23 ч.
 */
public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        TextEditor textEditor = new TextEditorImpl();

        String input;
        while (true) {
            if ("end".equals(input = bufferedReader.readLine())) {
                break;
            }

            String[] commandArgs = input
                    .split("\\s+");

            switch (commandArgs[0]) {
                case "login":
                    textEditor.login(commandArgs[1]);
                    continue;
                case "logout":
                    textEditor.logout(commandArgs[1]);
                    continue;
                case "users":
                    String prefix = "";
                    if (commandArgs.length == 2) {
                        prefix = commandArgs[1];
                    }
                    textEditor.users(prefix)
                            .forEach(System.out::println);
                    continue;
            }

            String username = commandArgs[0];
            switch (commandArgs[1]) {
                case "insert":
                    String str = input.substring(input.indexOf("\""));
                    textEditor.insert(username,
                            Integer.parseInt(commandArgs[2]),
                            str);
                    continue;
                case "prepend":
                    str = input.substring(input.indexOf("\""));
                    textEditor.prepend(username, str);
                    continue;
                case "substring":
                    textEditor.substring(username,
                            Integer.parseInt(commandArgs[2]),
                            Integer.parseInt(commandArgs[3]));
                    continue;
                case "delete":
                    textEditor.delete(username,
                            Integer.parseInt(commandArgs[2]),
                            Integer.parseInt(commandArgs[3]));
                    continue;
                case "clear":
                    textEditor.clear(username);
                    continue;
                case "length":
                    System.out.println(textEditor.length(username));
                    continue;
                case "print":
                    System.out.println(textEditor.print(username));
                    continue;
                case "undo":
                    textEditor.undo(username);
                    break;
                case "logout":
                    textEditor.logout(commandArgs[0]);
                    break;
            }
        }
    }
}