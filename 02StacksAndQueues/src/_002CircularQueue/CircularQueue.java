package _002CircularQueue;

public class CircularQueue<E> {

    private static int INITIAL_CAPACITY = 16;

    private E[] elements;
    private int startIndex;
    private int endIndex;
    private int size;


    public CircularQueue() {
        this(INITIAL_CAPACITY);
    }

    public CircularQueue(int initialCapacity) {
        this.elements = (E[]) new Object[initialCapacity];
        this.startIndex = 0;
        this.endIndex = 0;
        this.size = 0;
    }

    public int size() {
        return this.size;
    }

    private void setSize(int size) {
        this.size = size;
    }

    public void enqueue(E element) {
        if (size >= this.elements.length) {
            resize();
        }

        this.elements[this.endIndex] = element;

        this.endIndex = (this.endIndex + 1) % this.elements.length;
        this.size++;
    }

    public E dequeue() {
        checkValidSize();

        E result = this.elements[this.startIndex];

        this.startIndex = (this.startIndex + 1) % this.elements.length;
        this.size--;

        return result;
    }

    public E[] toArray() {
        checkValidSize();

        E[] result = (E[]) new Object[this.size];

        int sourceInd = this.startIndex;
        for (int i = 0; i < this.size(); i++) {
            result[i] = this.elements[sourceInd];
            sourceInd = (sourceInd + 1) % this.elements.length;
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
            this.startIndex = 0;
            this.endIndex = this.size;
        } else if (size <= capacity / 4) {
            capacity /= 2;

            //TODO fix start/end indexes
        }

        E[] newArray = (E[]) new Object[capacity];
        System.arraycopy(elements, 0,
                newArray, 0, size);

        this.elements = newArray;
    }

}
