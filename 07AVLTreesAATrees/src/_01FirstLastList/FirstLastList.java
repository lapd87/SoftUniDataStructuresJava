package _01FirstLastList;

import com.google.common.collect.Lists;

import java.util.*;

public class FirstLastList<T extends Comparable<T>> implements IFirstLastList<T> {
    private ArrayList<T> elements;
    private Map<T, ArrayList<T>> elementCount;
    private Map<T, ArrayList<T>> elementCountReversed;

    public FirstLastList() {
        this.elements = new ArrayList<>();
        this.elementCount = new TreeMap<>();
        this.elementCountReversed = new TreeMap<>(Collections.reverseOrder());
    }

    @Override
    public void add(T element) {
        elements.add(element);
        elementCount.putIfAbsent(element, new ArrayList<>());
        elementCount.get(element).add(element);
        elementCountReversed.putIfAbsent(element, new ArrayList<>());
        elementCountReversed.get(element).add(element);
    }

    @Override
    public int getCount() {
        return this.elements.size();
    }

    @Override
    public Iterable<T> first(int count) {
        checkCount(count);

        return this.elements.subList(0, count);
    }

    @Override
    public Iterable<T> last(int count) {
        checkCount(count);

        return Lists.reverse(this.elements
                .subList(this.getCount() - count, this.getCount()));
    }

    @Override
    public Iterable<T> min(int count) {
        checkCount(count);

        List<T> result = new ArrayList<>();

        for (Map.Entry<T, ArrayList<T>> kv
                : this.elementCount.entrySet()) {

            for (T value : kv.getValue()) {
                if (count <= 0) {
                    return result;
                }

                result.add(value);
                count--;
            }
        }

        return result;
    }

    @Override
    public Iterable<T> max(int count) {
        checkCount(count);

        List<T> result = new ArrayList<>();

        for (Map.Entry<T, ArrayList<T>> kv
                : this.elementCountReversed.entrySet()) {

            for (T value : kv.getValue()) {
                if (count <= 0) {
                    return result;
                }

                result.add(value);
                count--;
            }
        }

        return result;
    }

    @Override
    public void clear() {
        this.elements = new ArrayList<>();
        this.elementCount = new TreeMap<>();
        this.elementCountReversed = new TreeMap<>(Collections.reverseOrder());
    }

    @Override
    public int removeAll(T element) {

        if (!this.elementCount.containsKey(element)){
            return 0;
        }

        this.elements.removeIf(e -> e.compareTo(element) == 0);

        this.elementCount.remove(element);

        return this.elementCountReversed.remove(element).size();
    }

    private void checkCount(int count) {
        if (count < 0 || count > this.getCount()) {
            throw new IllegalArgumentException();
        }
    }
}