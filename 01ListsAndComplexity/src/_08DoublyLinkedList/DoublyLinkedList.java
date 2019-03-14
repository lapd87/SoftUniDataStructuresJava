package _08DoublyLinkedList;

import java.util.Iterator;
import java.util.function.Consumer;

public class DoublyLinkedList<E> implements Iterable<E> {

    private class Node {
        public E value;
        public Node nextNode;
        public Node prevNode;

        public Node(E value) {
            this.value = value;
        }

        public E getValue() {
            return value;
        }

        private void setValue(E value) {
            this.value = value;
        }

        public Node getNextNode() {
            return nextNode;
        }

        public void setNextNode(Node nextNode) {
            this.nextNode = nextNode;
        }

        public Node getPrevNode() {
            return prevNode;
        }

        public void setPrevNode(Node prevNode) {
            this.prevNode = prevNode;
        }
    }

    private Node head;
    private Node tail;

    private int size;

    public int size() {
        return this.size;
    }

    private void setSize(int size) {
        this.size = size;
    }

    public void addFirst(E element) {

        if (this.size == 0) {
            this.head = this.tail = new Node(element);
        } else {
            Node newHead = new Node(element);
            newHead.nextNode = this.head;
            this.head.prevNode = newHead;
            this.head = newHead;
        }

        this.size++;
    }

    public void addLast(E element) {
        if (this.size == 0) {
            this.head = this.tail = new Node(element);
        } else {
            Node newTail = new Node(element);
            newTail.prevNode = this.tail;
            this.tail.nextNode = newTail;
            this.tail = newTail;
        }

        this.size++;
    }

    public E removeFirst() {
        if (this.size == 0) {
            throw new IllegalArgumentException();
        }

        E firstElement = this.head.value;
        this.head = this.head.nextNode;

        if (this.head != null) {
            this.head.prevNode = null;
        } else {
            this.tail = null;
        }

        this.size--;

        return firstElement;
    }

    public E removeLast() {
        if (this.size == 0) {
            throw new IllegalArgumentException();
        }

        E lastElement = this.tail.value;
        this.tail = this.tail.prevNode;

        if (this.tail != null) {
            this.tail.nextNode = null;
        } else {
            this.head = null;
        }

        this.size--;

        return lastElement;
    }

    public E[] toArray() {

        E[] resultArray = (E[]) new Object[size];

        Node current = this.head;
        int index = 0;

        while (current != null) {
            resultArray[index++] = current.value;

            current = current.getNextNode();
        }

        return resultArray;
    }

    @Override
    public Iterator<E> iterator() {

        return new ListIterator();
    }

    private final class ListIterator implements Iterator<E> {
        private Node current;

        private ListIterator() {
            this.current = head;
        }

        @Override
        public boolean hasNext() {
            return this.current != null;
        }

        @Override
        public E next() {
            E returnValue = this.current.getValue();
            this.current = this.current.getNextNode();
            return returnValue;
        }
    }

    @Override
    public void forEach(Consumer<? super E> action) {

        Node currentNode = this.head;

        while (currentNode != null) {
            action.accept(currentNode.value);
            currentNode = currentNode.nextNode;
        }

    }


}
