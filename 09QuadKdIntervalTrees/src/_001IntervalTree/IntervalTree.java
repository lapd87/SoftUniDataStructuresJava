package _001IntervalTree;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

public class IntervalTree {

    private class Node {

        private Interval interval;
        private double max;
        private Node right;
        private Node left;

        public Node(Interval interval) {
            this.interval = interval;
            this.max = interval.getHi();
        }
    }

    private Node root;

    public void insert(double lo, double hi) {
        this.root = this.insert(this.root, lo, hi);
    }

    public void eachInOrder(Consumer<Interval> consumer) {
        this.eachInOrder(this.root, consumer);
    }

    public Interval searchAny(double lo, double hi) {

        Node node = this.root;

        while (node != null
                && !node.interval.intersects(lo, hi)) {

            if (node.left != null
                    && node.left.max > lo) {
                node = node.left;
            } else {
                node = node.right;
            }
        }

        if (node == null) {
            return null;
        }

        return node.interval;
    }

    public Iterable<Interval> searchAll(double lo, double hi) {
        List<Interval> result = new ArrayList<>();
        this.searchAll(this.root, lo, hi, result);

        return result;
    }

    private void searchAll(Node node, double lo, double hi,
                           List<Interval> result) {

        if (node == null) {
            return;
        }

        if (node.left != null
                && node.left.max > lo) {
            this.searchAll(node.left, lo, hi, result);
        }

        if (node.interval.intersects(lo, hi)) {
            result.add(node.interval);
        }

        if (node.right != null
                && node.right.interval.getLo() < hi) {
            this.searchAll(node.right, lo, hi, result);
        }
    }

    private void eachInOrder(Node node, Consumer<Interval> consumer) {
        if (node == null) {
            return;
        }

        this.eachInOrder(node.left, consumer);
        consumer.accept(node.interval);
        this.eachInOrder(node.right, consumer);
    }

    private Node insert(Node node, double lo, double hi) {
        if (node == null) {
            return new Node(new Interval(lo, hi));
        }

        int cmp = Double.compare(lo, node.interval.getLo());
        if (cmp < 0) {
            node.left = insert(node.left, lo, hi);
        } else if (cmp > 0) {
            node.right = insert(node.right, lo, hi);
        }

        this.updateMax(node);

        return node;
    }

    private void updateMax(Node node) {
        Node maxChild = this.getMax(node.left, node.right);

        node.max = this.getMax(node, maxChild).max;
    }

    private Node getMax(Node node, Node other) {
        if (node == null) {
            return other;
        } else if (other == null) {
            return node;
        } else {
            return node.max > other.max ? node : other;
        }
    }
}
