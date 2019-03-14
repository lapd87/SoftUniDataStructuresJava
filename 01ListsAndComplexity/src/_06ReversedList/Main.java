package _06ReversedList;

/**
 * Created by IntelliJ IDEA.
 * User: LAPD
 * Date: 26.12.2018 г.
 * Time: 16:11 ч.
 */
public class Main {
    public static void main(String[] args) {

        ReversedList<Integer> list = new ReversedList<>();

        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);

        System.out.println(list.get(0));
        System.out.println(list.get(4));

        print(list);

        list.set(0, 0);

        print(list);

        list.removeAt(0);

        print(list);

        System.out.println(list.removeAt(0));
        System.out.println(list.removeAt(0));

        print(list);
    }

    static void print(ReversedList<Integer> list) {
        for (Integer i : list) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}