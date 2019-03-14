package _03ArrayStack;

public class ArrayStack<E> {

    private static final int INITIAL_CAPACITY = 16;

    private E[] elements;
    private int size;

    public ArrayStack() {
        this(INITIAL_CAPACITY);
    }

    public ArrayStack(int capacity) {
        this.elements = (E[]) new Object[capacity];
        this.size = 0;
    }

    public int size() {
        return this.size;
    }

    private void setSize(int size) {
        this.size = size;
    }

    public void push(E element) {
        if (size >= this.elements.length) {
            resize();
        }

        this.elements[this.size++] = element;
    }

    public E pop() {
        checkValidSize();

        E result = this.elements[--this.size];

        this.elements[this.size] = null;

        return result;
    }

    public E[] toArray() {
        E[] result = (E[]) new Object[this.size];

        for (int i = 0; i < this.size; i++) {
            result[i] = this.elements[size - 1 - i];
        }

        return result;
    }

    private void checkValidSize() {
        if (this.size <= 0) {
            throw new IllegalArgumentException();
        }
    }

    private void resize() {
        int capacity = this.elements.length;

        if (size >= capacity) {
            capacity *= 2;
        } else if (size <= capacity / 4) {
            capacity /= 2;
        }

        E[] newArray = (E[]) new Object[capacity];
        System.arraycopy(elements, 0,
                newArray, 0, size);

        this.elements = newArray;
    }
}