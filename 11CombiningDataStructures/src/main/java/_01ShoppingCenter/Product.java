package _01ShoppingCenter;

/**
 * Created by IntelliJ IDEA.
 * User: LAPD
 * Date: 9.2.2019 г.
 * Time: 13:37 ч.
 */
public class Product implements Comparable<Product> {

    private String name;
    private double price;
    private String producer;

    public Product(String name, double price, String producer) {
        this.name = name;
        this.price = price;
        this.producer = producer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    @Override
    public String toString() {
        return String.format("{%s;%s;%.2f}%s",
                this.name,
                this.producer,
                this.price,
                System.lineSeparator());
    }

    @Override
    public int compareTo(Product other) {

        int cmp = this.name.compareTo(other.name);

        if (cmp != 0) {
            return cmp;
        }

        cmp = this.producer.compareTo(other.producer);

        if (cmp != 0) {
            return cmp;
        }

        return Double.compare(this.price, other.price);
    }
}