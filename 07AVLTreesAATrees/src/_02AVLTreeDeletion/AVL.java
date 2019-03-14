package _02AVLTreeDeletion;

import java.util.function.Consumer;

public class AVL<T extends Comparable<T>> {

    private Node<T> root;

    public Node<T> getRoot() {
        return this.root;
    }

    public boolean contains(T item) {
        Node<T> node = this.search(this.root, item);
        return node != null;
    }

    public void insert(T item) {
        this.root = this.insert(this.root, item);
    }

    public void eachInOrder(Consumer<T> consumer) {
        this.eachInOrder(this.root, consumer);
    }

    private void eachInOrder(Node<T> node, Consumer<T> action) {
        if (node == null) {
            return;
        }

        this.eachInOrder(node.getLeft(), action);
        action.accept(node.value);
        this.eachInOrder(node.getRight(), action);
    }

    private Node<T> insert(Node<T> node, T item) {
        if (node == null) {
            return new Node<>(item);
        }

        int cmp = item.compareTo(node.value);
        if (cmp < 0) {
            node.setLeft(this.insert(node.getLeft(), item));
        } else if (cmp > 0) {
            node.setRight(this.insert(node.getRight(), item));
        }

        node = this.balance(node);
        this.updateHeight(node);

        return node;
    }

    private Node<T> search(Node<T> node, T item) {
        if (node == null) {
            return null;
        }

        int cmp = item.compareTo(node.value);
        if (cmp < 0) {
            return search(node.getLeft(), item);
        } else if (cmp > 0) {
            return search(node.getRight(), item);
        }

        return node;
    }

    public void delete(T item) {
        if (this.root == null
                || !contains(item)) {
            return;
        }

        this.root = delete(this.root, item);
    }

    private Node<T> delete(Node<T> node, T item) {

        int cmp = item.compareTo(node.value);

        if (cmp < 0) {
            node.setLeft(delete(node.getLeft(), item));
        } else if (cmp > 0) {
            node.setRight(delete(node.getRight(), item));
        } else {

            if (node.getLeft() == null
                    || node.getRight() == null) {

                Node<T> temp = node.getLeft();

                if (node.getLeft() == null) {
                    temp = node.getRight();
                }

                node = temp;
            } else {
                Node<T> temp = getLeftmost(node.getRight());

                node.value = temp.value;

                node.setRight(delete(node.getRight(), temp.value));
            }
        }

        if (node == null) {
            return null;
        }

        node = balance(node);
        updateHeight(node);
        updateHeight(node);

        return node;
    }

    private Node<T> getLeftmost(Node<T> node) {
        Node<T> current = node;

        while (current.getLeft() != null) {
            current = current.getLeft();
        }

        return current;
    }

    public void deleteMin() {

        if (this.root == null) {
            return;
        }

        Node<T> leftmost = getLeftmost(this.root);

        delete(leftmost.value);
    }

    // BONUS
    public void deleteMax() {

        if (this.root == null) {
            return;
        }

        Node<T> rightmost = getRightmost(this.root);

        delete(rightmost.value);
    }

    private Node<T> getRightmost(Node<T> node) {
        Node<T> current = node;

        while (current.getRight() != null) {
            current = current.getRight();
        }

        return current;
    }

    private Node<T> balance(Node<T> node) {
        int balance = height(node.getLeft()) - height(node.getRight());
        if (balance > 1) {
            int childBalance = height(node.getLeft().getLeft()) - height(node.getLeft().getRight());
            if (childBalance < 0) {
                node.setLeft(RotateLeft(node.getLeft()));
            }

            node = RotateRight(node);
        } else if (balance < -1) {
            int childBalance = height(node.getRight().getLeft()) - height(node.getRight().getRight());
            if (childBalance > 0) {
                node.setRight(RotateRight(node.getRight()));
            }

            node = RotateLeft(node);
        }

        return node;
    }

    private Node<T> RotateRight(Node<T> node) {
        Node<T> left = node.getLeft();
        node.setLeft(left.getRight());
        left.setRight(node);

        updateHeight(node);

        return left;
    }

    private Node<T> RotateLeft(Node<T> node) {
        Node<T> right = node.getRight();
        node.setRight(right.getLeft());
        right.setLeft(node);

        updateHeight(node);

        return right;
    }

    private void updateHeight(Node<T> node) {
        node.height = Math.max(height(node.getLeft()), height(node.getRight())) + 1;
    }

    private int height(Node<T> node) {
        if (node == null) {
            return 0;
        }

        return node.height;
    }
}
