package _001LinkedList;

import java.util.Iterator;

public class LinkedList<E> implements Iterable<E> {

    private class Node {
        public E value;
        public Node nextNode;

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
    }

    private int size;
    private Node head;
    private Node tail;

    public LinkedList() {
        this.size = 0;
        this.head = this.tail = null;
    }

    public int size() {
        return this.size;
    }

    private void setSize(int size) {
        this.size = size;
    }

    public void addFirst(E item) {
        Node newHead = new Node(item);
        Node oldHead = this.head;

        newHead.setNextNode(oldHead);

        if (oldHead == null) {
            this.tail = newHead;
        }

        this.head = newHead;
        this.size++;
    }

    public void addLast(E item) {
        Node newTail = new Node(item);
        newTail.setNextNode(null);

        Node oldTail = this.tail;

        if (oldTail == null) {
            this.head = newTail;
        } else {
            oldTail.setNextNode(newTail);
        }

        this.tail = newTail;
        this.size++;
    }

    public E removeFirst() {
        checkValidSize();

        Node result = this.head;

        this.head = result.getNextNode();
        this.size--;

        return result.getValue();
    }


    public E removeLast() {
        checkValidSize();

        Node result = this.tail;

        if (this.size == 1) {
            this.head = null;
            this.tail = null;
        } else {

            Node temp = this.head;

            while (temp.getNextNode() != this.tail) {
                temp = temp.getNextNode();
            }

            temp.setNextNode(null);
            this.tail = temp;
        }

        this.size--;

        return result.getValue();
    }

    private void checkValidSize() {
        if (this.size <= 0) {
            throw new UnsupportedOperationException();
        }
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
}
