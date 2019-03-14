package _05BalancedOrderedSet;

import java.util.*;
import java.util.function.Consumer;

public class RedBlackTree<T extends Comparable<T>>
        implements BalancedOrderedSet<T> {

    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private Node root;
    private int nodesCount;

    public RedBlackTree() {
    }

    @Override
    public void add(T element) {
        this.insert(element);
    }

    @Override
    public void remove(T element) {
        this.delete(element);
    }

    @Override
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

    @Override
    public int count() {
        return this.nodesCount;
    }

    @Override
    public Iterator iterator() {
        List<T> nodes = new ArrayList<>();

        this.eachInOrder(nodes::add);

        return nodes.iterator();
    }

    private void insert(T value) {
        if (this.contains(value)) {
            return;
        }

        this.nodesCount++;
        this.root = insert(value, this.root);
        this.root.color = BLACK;
    }

    private Node insert(T value, Node node) {

        if (node == null) {
            node = new Node(value);
        } else if (value.compareTo(node.value) < 0) {
            node.left = insert(value, node.left);
        } else if (value.compareTo(node.value) > 0) {
            node.right = insert(value, node.right);
        }

        node = balance(node);

        return node;
    }

    private void delete(T key) {
        this.root = delete(this.root, key);
    }

    private Node delete(Node root, T key) {
        if (root == null) {
            return root;
        }

        if (key.compareTo(root.value) < 0) {
            root.left = delete(root.left, key);
        } else if (key.compareTo(root.value) > 0) {
            root.right = delete(root.right, key);
        } else {
            if (root.left == null) {
                return root.right;
            } else if (root.right == null) {
                return root.left;
            }

            root.value = minValue(root.right);

            root.right = delete(root.right, root.value);
        }

        return root;
    }

    private void eachInOrder(Consumer<T> consumer) {
        this.eachInOrder(this.root, consumer);
    }

    private void eachInOrder(Node node, Consumer<T> consumer) {
        if (node == null) {
            return;
        }

        this.eachInOrder(node.left, consumer);
        consumer.accept(node.value);
        this.eachInOrder(node.right, consumer);
    }

    private Node balance(Node node) {
        if (isRed(node.right) && !isRed(node.left)) {
            node = rotateLeft(node);
        }
        if (isRed(node.left) && isRed(node.left.left)) {
            node = rotateRight(node);
        }
        if (isRed(node.left) && isRed(node.right)) {
            flipColors(node);
        }

        node.childrenCount = 1 + getNodesCount(node.left)
                + getNodesCount(node.right);

        return node;
    }

    private boolean isRed(Node node) {
        return node != null && node.color == RED;
    }

    private Node rotateLeft(Node node) {

        Node temp = node.right;
        node.right = temp.left;
        temp.left = node;

        temp.childrenCount = node.childrenCount;
        node.childrenCount = 1 + getNodesCount(node.left)
                + getNodesCount(node.right);

        temp.color = node.color;
        node.color = RED;

        return temp;
    }

    private Node rotateRight(Node node) {

        Node temp = node.left;
        node.left = temp.right;
        temp.right = node;

        temp.childrenCount = node.childrenCount;
        node.childrenCount = 1 + getNodesCount(node.left)
                + getNodesCount(node.right);

        temp.color = node.color;
        node.color = RED;

        return temp;
    }

    private void flipColors(Node node) {

        node.color = RED;

        if (node.left != null) {
            node.left.color = BLACK;
        }

        if (node.right != null) {
            node.right.color = BLACK;
        }
    }

    private int getNodesCount(Node node) {
        return node == null ? 0 : node.childrenCount;
    }

    private T minValue(Node root) {
        T minv = root.value;
        while (root.left != null) {
            minv = root.left.value;
            root = root.left;
        }

        return minv;
    }

    class Node {

        private T value;
        private Node left;
        private Node right;
        private boolean color;

        private int childrenCount;

        public Node(T value) {
            this.value = value;
            this.childrenCount = 1;
            this.color = RED;
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

        @Override
        public String toString() {
            return this.value + "";
        }
    }
}
