package _001MaxBinaryHeap;

import java.util.ArrayList;
import java.util.List;

public class BinaryHeap<T extends Comparable<T>> {

    private List<T> heap;
    private int size;

    public BinaryHeap() {
        this.heap = new ArrayList<>();
        this.size = 0;
    }

    public int size() {
        return this.size;
    }

    public void insert(T element) {
        this.heap.add(element);
        heapifyUp(this.size++);
    }

    public T peek() {

        checkForEmpty();

        return this.heap.get(0);
    }


    public T pull() {
        checkForEmpty();

        T result = this.heap.get(0);

        swap(0, this.size - 1);
        this.heap.remove(--this.size);
        heapifyDown(0);

        return result;
    }

    private void heapifyUp(int childIndex) {

        int parentIndex = (childIndex - 1) / 2;
        int compare = this.heap.get(parentIndex)
                .compareTo(this.heap.get(childIndex));

        while (childIndex > 0 && compare < 0) {
            swap(childIndex, parentIndex);
            childIndex = parentIndex;
            parentIndex = (childIndex - 1) / 2;
        }
    }


    private void heapifyDown(int parentIndex) {
        while (parentIndex < this.size / 2) {
            int childIndex = 2 * parentIndex + 1;


            if (this.size > childIndex + 1) {
                int compareChilds = this.heap.get(childIndex)
                        .compareTo(this.heap.get(childIndex + 1));

                if (compareChilds < 0) {
                    childIndex++;
                }
            }

            if (this.heap.get(parentIndex)
                    .compareTo(this.heap.get(childIndex)) > 0) {
                break;
            }

            swap(childIndex, parentIndex);
        }
    }

    private void swap(int childIndex, int parentIndex) {
        T temp = this.heap.get(parentIndex);
        this.heap.set(parentIndex, this.heap.get(childIndex));
        this.heap.set(childIndex, temp);
    }

    private void checkForEmpty() {
        if (this.size <= 0) {
            throw new IllegalArgumentException();
        }
    }
}
