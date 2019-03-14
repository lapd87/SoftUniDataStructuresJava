package _01Dictionary;

/**
 * Created by IntelliJ IDEA.
 * User: LAPD
 * Date: 4.2.2019 г.
 * Time: 09:17 ч.
 */
public interface Dictionary<TKey, TValue> extends Iterable<KeyValue<TKey, TValue>> {


    void add(TKey key, TValue value);

    int size();

    int capacity();

    boolean addOrReplace(TKey key, TValue value);

    TValue get(TKey key);

    boolean tryGetValue(TKey key, TValue value);

    boolean containsKey(TKey key);

    boolean remove(TKey key);

    void clear();

    Iterable<TKey> keys();

    Iterable<TValue> values();
}
