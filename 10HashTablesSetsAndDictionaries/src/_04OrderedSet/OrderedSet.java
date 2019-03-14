package _04OrderedSet;

/**
 * Created by IntelliJ IDEA.
 * User: LAPD
 * Date: 4.2.2019 г.
 * Time: 09:48 ч.
 */
public interface OrderedSet <T extends Comparable<T>>
        extends Iterable {

    void add(T element);

    void remove(T element);

    boolean contains(T element);

    int count();
}
