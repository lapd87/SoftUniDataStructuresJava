package _002HeapSort;

public class Heap {

    public static <E extends Comparable<E>> void sort(E[] array) {

        int size = array.length;

        for (int i = size / 2; i >= 0; i--) {
            heapifyDown(array, i, size);
        }

        for (int i = size - 1; i > 0; i--) {
            swap(array, 0, i);
            heapifyDown(array, 0, i);
        }
    }

    private static <E extends Comparable<E>> void heapifyDown(E[] array, int parentIndex, int size) {

        while (parentIndex < size / 2) {

            int childIndex = 2 * parentIndex + 1;


            if (size > childIndex + 1) {
                int compareChilds = array[childIndex]
                        .compareTo(array[childIndex + 1]);

                if (compareChilds < 0) {
                    childIndex++;
                }
            }

            if (array[parentIndex]
                    .compareTo(array[childIndex]) > 0) {
                break;
            }

            swap(array, childIndex, parentIndex);

            parentIndex = childIndex;
        }

    }

    private static <E extends Comparable<E>> void swap(E[] array, int childIndex, int parentIndex) {
        E temp = array[parentIndex];
        array[parentIndex] = array[childIndex];
        array[childIndex] = temp;
    }
}
