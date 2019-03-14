package _04LinkedStack;

public class LinkedStack<E> {

    private Node<E> firstNode;
    private int size;

    public LinkedStack() {
        this.size = 0;
        this.firstNode = null;
    }

    public LinkedStack(Node<E> firstNode) {
        this();
        this.firstNode = firstNode;
        this.size = 1;
    }

    public int size() {
        return this.size;
    }

    private void setSize(int size) {
        this.size = size;
    }

    public void push(E element) {

        Node<E> newFirstNode = new Node<>(element);

        newFirstNode.setNextNode(this.firstNode);

        this.firstNode = newFirstNode;

        this.size++;
    }

    public E pop() {
        checkValidSize();

        E result = this.firstNode.value;

        this.firstNode = this.firstNode.getNextNode();

        this.size--;

        return result;
    }

    public E[] toArray() {

        E[] result = (E[]) new Object[this.size];

        Node<E> current = this.firstNode;
        int index = 0;

        while (current != null) {
            result[index++] = current.value;

            current = current.getNextNode();
        }

        return result;
    }

    private void checkValidSize() {
        if (this.size <= 0) {
            throw new IllegalArgumentException();
        }
    }

    private class Node<E> {

        private E value;
        private Node<E> nextNode;

        public Node(E value) {
            this.value = value;
        }

        public Node(E value, Node<E> nextNode) {
            this.value = value;
            this.nextNode = nextNode;
        }

        public Node<E> getNextNode() {
            return this.nextNode;
        }

        public void setNextNode(Node<E> nextNode) {
            this.nextNode = nextNode;
        }
    }
}