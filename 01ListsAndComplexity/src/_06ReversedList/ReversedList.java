package _06ReversedList;

import java.util.Iterator;

/**
 * Created by IntelliJ IDEA.
 * User: LAPD
 * Date: 26.12.2018 г.
 * Time: 16:01 ч.
 */
public class ReversedList<T> implements Iterable<T> {

    private static int INITIAL_CAPACITY = 2;

    private int capacity;

    private T[] elements;

    private int count;

    public ReversedList(int capacity) {
        this.count = 0;
        this.elements = (T[]) new Object[capacity];
        this.capacity = capacity;
    }

    public ReversedList() {
        this(INITIAL_CAPACITY);
    }

    public int count() {
        return this.count;
    }

    public int capacity() {
        return capacity;
    }

    public T get(int index) {

        checkIndex(index);

        return elements[count - 1 - index];
    }

    public void set(int index, T item) {

        checkIndex(index);

        this.elements[count - 1 - index] = item;
    }

    public void add(T element) {

        if (count >= capacity) {
            resize();
        }

        this.elements[this.count++] = element;
    }

    public T removeAt(int index) {

        checkIndex(index);

        index = count - 1 - index;

        T element = this.elements[index];

        shift(index);

        this.count--;

        if (this.count <= capacity / 4
                && capacity > 2) {
            resize();
        }

        return element;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= count) {
            throw new IllegalArgumentException();
        }
    }

    private void resize() {
        if (count >= capacity) {
            capacity *= 2;
        } else if (count <= capacity / 4) {
            capacity /= 2;
        }

        T[] newArray = (T[]) new Object[capacity];
        System.arraycopy(elements, 0,
                newArray, 0, count);

        this.elements = newArray;
    }

    private void shift(int index) {

        System.arraycopy(elements, index + 1,
                elements, index, count - 1 - index);

        elements[count - 1] = null;
    }


    @Override
    public Iterator<T> iterator() {
        return new ListIterator();
    }

    private final class ListIterator implements Iterator<T> {
        private int counter;

        public ListIterator() {
            this.counter = 0;
        }

        @Override
        public boolean hasNext() {
            return count > this.counter;
        }

        @Override
        public T next() {
            return get(this.counter++);
        }
    }
}