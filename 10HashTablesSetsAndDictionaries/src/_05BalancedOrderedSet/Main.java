package _05BalancedOrderedSet;

/**
 * Created by IntelliJ IDEA.
 * User: LAPD
 * Date: 4.2.2019 г.
 * Time: 10:11 ч.
 */
public class Main {
    public static void main(String[] args) {

        BalancedOrderedSet<Integer> orderedSet = new RedBlackTree<>();

        orderedSet.add(17);
        orderedSet.add(9);
        orderedSet.add(12);
        orderedSet.add(19);
        orderedSet.add(6);
        orderedSet.add(25);
        orderedSet.add(10);
        orderedSet.add(13);

        orderedSet.remove(17);
        orderedSet.remove(9);
        orderedSet.remove(12);

        orderedSet.forEach(System.out::println);
    }
}