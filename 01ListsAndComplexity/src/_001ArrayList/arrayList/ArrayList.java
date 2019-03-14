package _001ArrayList.arrayList;

public class ArrayList<T> {

    private static int INITIAL_CAPACITY = 2;

    private T[] elements;

    private int count;

    public ArrayList(int capacity) {
        count = 0;
        elements = (T[]) new Object[capacity];
    }

    public ArrayList() {
       this(INITIAL_CAPACITY);
    }


    public int getCount() {
        return this.count;
    }


    public T get(int index) {

        checkIndex(index);

        return elements[index];
    }

    public void add(T element) {

        if (count >= elements.length) {
            resize();
        }

        this.elements[this.count++] = element;

    }

    public T removeAt(int index) {

        checkIndex(index);

        T element = this.elements[index];

        shift(index);

        this.count--;

        if (this.count <= elements.length / 4
                && elements.length > 2) {
            resize();
        }

        return element;
    }

    public void set(int index, T item) {

        checkIndex(index);

        this.elements[index] = item;
    }

    private void checkIndex(int index) {
        if (index < 0 || index >= getCount()) {
            throw new IllegalArgumentException();
        }
    }

    private void resize() {
        int capacity = elements.length;
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

        T[] newArray = (T[]) new Object[elements.length];

        System.arraycopy(this.elements, index + 1,
                newArray, index, this.count - index);

        this.elements = newArray;
    }
}
