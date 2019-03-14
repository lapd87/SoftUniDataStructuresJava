package _001BinarySearchTree;

import java.util.LinkedList;
import java.util.function.Consumer;

public class BinarySearchTree<T extends Comparable<T>> {

    private Node root;

    public BinarySearchTree() {
        this.root = null;
    }

    private BinarySearchTree(Node root) {
        this();
        this.copy(root);
    }

    private void copy(Node node) {
        if (node == null) {
            return;
        }

        this.insert(node.value);
        this.copy(node.left);
        this.copy(node.right);
    }

    public Node getRoot() {
        return this.root;
    }

    public void insert(T value) {

        if (this.root == null) {
            this.root = new Node(value);
            return;
        }

        Node parent = null;
        Node current = this.root;

        while (current != null) {
            parent = current;

            if (current.value.compareTo(value) < 0) {
                current = current.right;
            } else if (current.value.compareTo(value) > 0) {
                current = current.left;
            } else {
                return;
            }
        }

        if (parent.value.compareTo(value) < 0) {
            parent.right = new Node(value);
        } else {
            parent.left = new Node(value);
        }
    }

    public boolean contains(T value) {

        Node current = this.root;

        while (current != null) {
            if (value.compareTo(current.value) < 0) {
                current = current.left;
            } else if (value.compareTo(current.value) > 0) {
                current = current.right;
            } else {
                break;
            }
        }

        return current != null;
    }

    public BinarySearchTree<T> search(T item) {

        Node current = this.root;

        while (current != null) {
            if (current.value.compareTo(item) < 0) {
                current = current.right;
            } else if (current.value.compareTo(item) > 0) {
                current = current.left;
            } else {
                break;
            }
        }

        return new BinarySearchTree<>(current);
    }

    public void eachInOrder(Consumer<T> consumer) {
        eachInOrder(this.root, consumer);
    }

    public void eachInOrder(Node root, Consumer<T> consumer) {
        if (root == null) {
            return;
        }

        if (root.left != null) {
            eachInOrder(root.left, consumer);
        }

        consumer.accept(root.value);

        if (root.right != null) {
            eachInOrder(root.right, consumer);
        }
    }

    public Iterable<T> range(T from, T to) {

        LinkedList<T> queue = new LinkedList<>();

        range(this.root, queue, from, to);

        return queue;
    }

    private void range(Node node, LinkedList<T> queue, T from, T to) {

        if (node == null) {
            return;
        }

        int nodeLow = from.compareTo(node.value);
        int nodeHigh = to.compareTo(node.value);

        if (nodeLow < 0) {
            range(node.right, queue, from, to);
        }

        if (nodeLow >= 0 && nodeHigh <= 0) {
            queue.addLast(node.value);
        }

        if (nodeHigh > 0) {
            range(node.left, queue, from, to);
        }
    }

    public void DeleteMin() {
        if (this.root == null) {
            return;
        }

        Node parent = null;
        Node min = this.root;

        while (min.left != null) {
            parent = min;
            min = min.left;
        }

        if (parent != null) {
            parent.left = min.right;
        } else {
            this.root = min.right;
        }
    }

    class Node {
        private T value;
        private Node left;
        private Node right;

        public Node(T value) {
            this.value = value;
        }

        public T getValue() {
            return this.value;
        }

        public void setValue(T value) {
            this.value = value;
        }

        public Node getLeft() {
            return this.left;
        }

        public void setLeft(Node left) {
            this.left = left;
        }

        public Node getRight() {
            return this.right;
        }

        public void setRight(Node right) {
            this.right = right;
        }
    }
}

