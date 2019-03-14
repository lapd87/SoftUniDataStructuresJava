package _01ShoppingCenter;

import java.util.*;

/**
 * Created by IntelliJ IDEA.
 * User: LAPD
 * Date: 9.2.2019 г.
 * Time: 13:37 ч.
 */
public class ShoppingCenter {

    private static final String NO_PRODUCTS = "No products found" + System.lineSeparator();

    private Map<String, TreeMap<Product, Integer>> producerProduct;
    private Map<String, TreeMap<Product, Integer>> nameProduct;
    private Map<String, TreeMap<Product, Integer>> nameProducerProduct;
    private TreeMap<Double, Map<Product, Integer>> priceProduct;

    public ShoppingCenter() {
        this.producerProduct = new HashMap<>();
        this.nameProduct = new HashMap<>();
        this.nameProducerProduct = new HashMap<>();
        this.priceProduct = new TreeMap<>();
    }

    public String addProduct(Product product) {

        this.producerProduct
                .putIfAbsent(product.getProducer(),
                        new TreeMap<>());
        this.producerProduct
                .get(product.getProducer())
                .merge(product, 1, Integer::sum);

        this.nameProduct
                .putIfAbsent(product.getName(),
                        new TreeMap<>());
        this.nameProduct
                .get(product.getName())
                .merge(product, 1, Integer::sum);

        this.nameProducerProduct
                .putIfAbsent(product.getName() + "@"
                                + product.getProducer(),
                        new TreeMap<>());
        this.nameProducerProduct
                .get(product.getName() + "@"
                        + product.getProducer())
                .merge(product, 1, Integer::sum);

        this.priceProduct
                .putIfAbsent(product.getPrice(),
                        new HashMap<>());
        this.priceProduct
                .get(product.getPrice())
                .merge(product, 1, Integer::sum);

        return "Product added" + System.lineSeparator();
    }

    public String deleteProducts(String producer) {

        if (!this.producerProduct.containsKey(producer)
                || this.producerProduct.get(producer).isEmpty()) {
            return NO_PRODUCTS;
        }

        TreeMap<Product, Integer> products = this.producerProduct
                .get(producer);

        for (Product product : products.keySet()) {
            this.nameProducerProduct
                    .get(product.getName() + "@" + producer)
                    .clear();
            this.nameProduct
                    .get(product.getName())
                    .remove(product);
            this.priceProduct
                    .get(product.getPrice())
                    .remove(product);
        }

        int result = getCount(products);

        this.producerProduct
                .get(producer)
                .clear();

        return String.format("%d products deleted%s",
                result,
                System.lineSeparator());
    }

    public String deleteProducts(String name, String producer) {

        if (!this.nameProducerProduct
                .containsKey(name + "@" + producer)
                || this.nameProducerProduct
                .get(name + "@" + producer).isEmpty()) {
            return NO_PRODUCTS;
        }

        TreeMap<Product, Integer> products = this.nameProducerProduct
                .get(name + "@" + producer);

        for (Product product : products.keySet()) {
            this.producerProduct
                    .get(producer)
                    .remove(product);
            this.nameProduct
                    .get(product.getName())
                    .remove(product);
            this.priceProduct
                    .get(product.getPrice())
                    .remove(product);
        }

        int result = getCount(products);

        this.nameProducerProduct
                .get(name + "@" + producer)
                .clear();

        return String.format("%d products deleted%s",
                result,
                System.lineSeparator());
    }

    public String findProductsByName(String name) {

        if (!this.nameProduct.containsKey(name)
                || this.nameProduct.get(name).isEmpty()) {
            return NO_PRODUCTS;
        }

        TreeMap<Product, Integer> resultMap = this.nameProduct.get(name);

        return getResultString(resultMap);
    }

    public String findProductsByProducer(String producer) {

        if (!this.producerProduct.containsKey(producer)
                || this.producerProduct.get(producer).isEmpty()) {
            return NO_PRODUCTS;
        }

        TreeMap<Product, Integer> resultMap = this.producerProduct.get(producer);

        return getResultString(resultMap);
    }

    public String findProductsByPriceRange(double from, double to) {
        Collection<Map<Product, Integer>> resultMaps = this.priceProduct
                .subMap(from, true, to, true)
                .values();

        TreeMap<Product, Integer> resultMap = new TreeMap<>();
        for (Map<Product, Integer> map : resultMaps) {
            for (Map.Entry<Product, Integer> entry : map.entrySet()) {
                resultMap.merge(entry.getKey(), entry.getValue(), Integer::sum);
            }
        }

        int count = getCount(resultMap);

        if (count == 0) {
            return NO_PRODUCTS;
        }

        return getResultString(resultMap);
    }

    private int getCount(TreeMap<Product, Integer> resultMap) {
        return resultMap.values()
                .stream()
                .mapToInt(v -> v)
                .sum();
    }

    private String getResultString(TreeMap<Product, Integer> resultMap) {
        StringBuilder stringBuilder = new StringBuilder();

        resultMap.keySet()
                .forEach(k -> {
                    for (int i = 0; i < resultMap.get(k); i++) {
                        stringBuilder.append(k);
                    }
                });

        return stringBuilder.toString();
    }
}