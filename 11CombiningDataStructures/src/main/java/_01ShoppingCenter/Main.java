package _01ShoppingCenter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by IntelliJ IDEA.
 * User: LAPD
 * Date: 9.2.2019 г.
 * Time: 13:37 ч.
 */
public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(bufferedReader.readLine());

        ShoppingCenter shoppingCenter = new ShoppingCenter();

        StringBuilder stringBuilder = new StringBuilder();

        for (int i = 0; i < n; i++) {

            String[] input = bufferedReader.readLine()
                    .split("\\s+", 2);

            String[] commandArgs = input[1]
                    .split(";");

            switch (input[0]) {
                case "AddProduct":
                    Product product = new Product(commandArgs[0],
                            Double.parseDouble(commandArgs[1]),
                            commandArgs[2]);

                    stringBuilder.append(shoppingCenter
                            .addProduct(product));
                    break;
                case "DeleteProducts":
                    if (commandArgs.length == 1) {
                        stringBuilder.append(shoppingCenter
                                .deleteProducts(commandArgs[0]));
                    } else {
                        stringBuilder.append(shoppingCenter
                                .deleteProducts(commandArgs[0],
                                        commandArgs[1]));
                    }
                    break;
                case "FindProductsByName":
                    stringBuilder.append(shoppingCenter
                            .findProductsByName(commandArgs[0]));
                    break;

                case "FindProductsByProducer":
                    stringBuilder.append(shoppingCenter
                            .findProductsByProducer(commandArgs[0]));
                    break;
                case "FindProductsByPriceRange":
                    stringBuilder.append(shoppingCenter
                            .findProductsByPriceRange(Double.parseDouble(commandArgs[0]),
                                    Double.parseDouble(commandArgs[1])));
                    break;
            }

            if (i % 50 == 0) {
                System.out.println(stringBuilder.toString().trim());
                stringBuilder = new StringBuilder();
            }
        }

        if (!stringBuilder.toString().trim().isEmpty()) {
            System.out.println(stringBuilder.toString().trim());
        }
    }
}