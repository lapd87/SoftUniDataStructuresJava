package _01BinarySearchTree;

import java.util.Deque;
import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.Consumer;

public class BinarySearchTree<T extends Comparable<T>> {
    private Node root;
    private int nodesCount;

    public BinarySearchTree() {
        this.root = null;
        this.nodesCount = 0;
    }

    private BinarySearchTree(Node root) {
        this.preOrderCopy(root);
    }

    private void preOrderCopy(Node node) {
        if (node == null) {
            return;
        }

        this.insert(node.value);
        this.preOrderCopy(node.left);
        this.preOrderCopy(node.right);
    }

    public Node getRoot() {
        return this.root;
    }

    public int getNodesCount() {
        return this.nodesCount;
    }

    public void insert(T value) {
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
            if (item.compareTo(current.value) < 0) {
                current = current.left;
            } else if (item.compareTo(current.value) > 0) {
                current = current.right;
            } else {
                break;
            }
        }

        return new BinarySearchTree<>(current);
    }

    public void eachInOrder(Consumer<T> consumer) {
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

    public Iterable<T> range(T from, T to) {
        Deque<T> queue = new LinkedList<>();
        this.range(this.root, queue, from, to);
        return queue;
    }

    private void range(Node node, Deque<T> queue, T startRange, T endRange) {
        if (node == null) {
            return;
        }

        int compareStart = startRange.compareTo(node.value);
        int compareEnd = endRange.compareTo(node.value);
        if (compareStart < 0) {
            this.range(node.left, queue, startRange, endRange);
        }
        if (compareStart <= 0 && compareEnd >= 0) {
            queue.addLast(node.value);
        }
        if (compareEnd > 0) {
            this.range(node.right, queue, startRange, endRange);
        }
    }

    private T minValue(Node root) {
        T minv = root.value;
        while (root.left != null) {
            minv = root.left.value;
            root = root.left;
        }

        return minv;
    }

    public void deleteMin() {
        if (this.root == null) {
            throw new IllegalArgumentException("Tree is empty!");
        }

        Node parent = null;
        Node currentMin = this.root;

        while (currentMin.left != null) {
            parent = currentMin;
            parent.rootAndChildrenCount--;
            currentMin = currentMin.left;
        }

        if (parent == null) {
            this.root = this.root.right;
        } else {
            parent.left = currentMin.right;
        }

        this.nodesCount--;
    }

    public void deleteMax() {
        if (this.root == null) {
            throw new IllegalArgumentException("Tree is empty!");
        }

        Node parent = null;
        Node currentMax = this.root;

        while (currentMax.right != null) {
            parent = currentMax;
            currentMax = currentMax.right;
            parent.rootAndChildrenCount--;
        }

        if (parent == null) {
            this.root = this.root.left;
        } else {
            parent.right = currentMax.left;
        }

        this.nodesCount--;
    }

    public T ceil(T element) {
        Node parent = null;
        Node current = this.root;

        while (current != null) {
            int compare = current.value.compareTo(element);

            if (compare > 0) {
                parent = current;
                current = current.left;
            } else if (compare < 0) {
                if (parent != null
                        && current.right != null
                        && current.right.value.compareTo(element) < 0) {
                    return parent.value;
                }

                parent = current;
                current = current.right;
            } else {
                return current.value;
            }
        }

        if (parent != null
                && parent.value.compareTo(element) >= 0) {
            return parent.value;
        } else {
            return null;
        }
    }

    public T floor(T element) {
        Node parent = null;
        Node current = this.root;

        while (current != null) {
            int compare = current.value.compareTo(element);

            if (compare > 0) {
                if (parent != null
                        && parent.value.compareTo(element) <= 0) {
                    return parent.value;
                }

                parent = current;
                current = current.left;
            } else if (compare < 0) {
                parent = current;
                current = current.right;
            } else {
                return current.value;
            }
        }

        if (parent != null
                && parent.value.compareTo(element) <= 0) {
            return parent.value;
        } else {
            return null;
        }
    }

    public void delete(T key) {

        if (this.root == null) {
            throw new IllegalArgumentException("Tree is empty!");
        }

        AtomicInteger count = new AtomicInteger();
        range(key, key)
                .forEach(x -> count.getAndIncrement());

        //        assert current != null;
        if (count.get() == 0) {
            return;
        }

        Node parent = null;
        Node current = this.root;

        while (current != null) {

            int compare = current.value.compareTo(key);

            if (compare == 0) {
                break;
            }

            parent = current;
            parent.rootAndChildrenCount--;

            if (compare > 0) {
                current = current.left;
            } else {
                current = current.right;
            }
        }

        assert current != null;

        if (current.left == null
                && current.right == null) {

            if (parent == null) {
                this.root = null;
                this.nodesCount--;
                return;
            }

            setParentValue(parent, null, key);

        } else if (current.left == null) {

            if (parent == null) {
                this.root = current.right;
                this.nodesCount--;
                return;
            }

            setParentValue(parent, current.right, key);

        } else if (current.right == null) {

            if (parent == null) {
                this.root = current.left;
                this.nodesCount--;
                return;
            }

            setParentValue(parent, current.left, key);

        } else {

            Node rightsLeftmost = getRightsLeftmost(current);

            if (parent == null) {
                this.root = rightsLeftmost;
                this.nodesCount--;
                return;
            }

            setParentValue(parent, rightsLeftmost, key);
        }

        this.nodesCount--;
    }

    private Node getRightsLeftmost(Node current) {
        Node rightsLeftmostParent = null;
        Node rightsLeftmost = current.right;
        Node temp = current.right;

        while (temp != null) {
            rightsLeftmostParent = rightsLeftmost;
            rightsLeftmostParent.rootAndChildrenCount--;

            rightsLeftmost = temp;
            temp = temp.left;
        }

        if (rightsLeftmostParent != null) {
            rightsLeftmostParent.left = null;
        }

        rightsLeftmost.left = current.left;
        rightsLeftmost.right = current.right;

        return rightsLeftmost;
    }

    private void setParentValue(Node parent, Node child, T key) {
        if (parent.value.compareTo(key) > 0) {
            parent.left = child;
        } else {
            parent.right = child;
        }
    }

    public int rank(T item) {
        return this.rank(item, this.root);
    }

    private int rank(T value, Node node) {

        if (node == null) {
            return 0;
        }

        int compare = value.compareTo(node.value);

        if (compare < 0) {
            return this.rank(value, node.left);
        } else if (compare > 0) {
            return 1 + getRootAndChildrenCount(node.left)
                    + this.rank(value, node.right);
        } else {
            return getRootAndChildrenCount(node.left);
        }
    }

    private int getRootAndChildrenCount(Node node) {
        if (node == null) {
            return 0;
        } else {
            return node.rootAndChildrenCount;
        }
    }

    public T select(int n) {

        Node current = this.root;
        while (current != null) {
            int smallerCount = this.rank(current.value);

            if (smallerCount > n) {
                current = current.left;
            } else if (smallerCount < n) {
                current = current.right;
            } else {
                break;
            }
        }

        if (current == null) {
            throw new IllegalArgumentException("Tree is empty!");
        } else {
            return current.value;
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

