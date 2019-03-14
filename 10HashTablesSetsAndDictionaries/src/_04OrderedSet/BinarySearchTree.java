package _04OrderedSet;

import java.util.*;
import java.util.function.Consumer;

public class BinarySearchTree<T extends Comparable<T>>
        implements OrderedSet<T> {
    private Node root;
    private int nodesCount;

    public BinarySearchTree() {
        this.root = null;
        this.nodesCount = 0;
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
    public Iterator<T> iterator() {

        List<T> nodes = new ArrayList<>();

        this.eachInOrder(nodes::add);

        return nodes.iterator();
    }

    private void insert(T value) {

        if (this.contains(value)) {
            return;
        }

        this.nodesCount++;

        if (this.root == null) {
            this.root = new Node(value);
            return;
        }

        Node parent = null;
        Node current = this.root;
        while (current != null) {
            parent = current;
            parent.rootAndChildrenCount++;

            if (value.compareTo(current.value) < 0) {
                current = current.left;
            } else if (value.compareTo(current.value) > 0) {
                current = current.right;
            } else {
                return;
            }
        }

        Node newNode = new Node(value);
        if (value.compareTo(parent.value) < 0) {
            parent.left = newNode;
        } else {
            parent.right = newNode;
        }
    }

    private void delete(T item) {
        if (this.root == null) {
            return;
        }

        Node parent = null;
        Node forDeleting = this.root;

        while (forDeleting != null) {

            forDeleting.rootAndChildrenCount--;

            int cmp = forDeleting.value.compareTo(item);

            if (cmp == 0) {
                break;
            }

            parent = forDeleting;

            if (cmp > 0) {
                forDeleting = forDeleting.left;
            } else {
                forDeleting = forDeleting.right;
            }
        }

        if (forDeleting == null) {
            return;
        }

        if (forDeleting.left == null
                && forDeleting.right == null) {

            changeParent(parent, forDeleting, null);

            if (parent == null) {
                this.root.value = null;
            }

        } else if (forDeleting.right == null) {

            forDeleting.left.rootAndChildrenCount
                    = forDeleting.rootAndChildrenCount - 1;

            changeParent(parent, forDeleting, forDeleting.left);

        } else if (forDeleting.right.left == null) {

            forDeleting.right.rootAndChildrenCount
                    = forDeleting.rootAndChildrenCount - 1;

            forDeleting.right.left = forDeleting.left;

            changeParent(parent, forDeleting, forDeleting.right);

        } else {

            Node prev = forDeleting.right;
            Node crnt = prev.left;

            while (crnt.left != null) {
                crnt.rootAndChildrenCount--;
                prev = crnt;
                crnt = crnt.left;
            }

            prev.left = null;

            crnt.rootAndChildrenCount
                    = forDeleting.rootAndChildrenCount - 1;

            crnt.left = forDeleting.left;
            crnt.right = forDeleting.right;

            changeParent(parent, forDeleting, crnt);
        }
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

    private void changeParent(Node parent, Node forDeleting, Node newNode) {
        if (parent == null) {
            this.root = newNode;
        } else if (parent.left == forDeleting) {
            parent.left = newNode;
        } else if (parent.right == forDeleting) {
            parent.right = newNode;
        }
    }

    class Node {
        private T value;
        private Node left;
        private Node right;

        private int rootAndChildrenCount;

        public Node(T value) {
            this.value = value;
            this.rootAndChildrenCount = 1;
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

