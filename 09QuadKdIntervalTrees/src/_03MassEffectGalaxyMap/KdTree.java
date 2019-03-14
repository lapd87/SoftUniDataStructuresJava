package _03MassEffectGalaxyMap;

import java.util.List;
import java.util.function.Consumer;

public class KdTree {

    public class Node {

        private StarCluster starCluster;
        private Node left;
        private Node right;

        public Node(StarCluster point) {
            this.setStarCluster(point);
        }

        public StarCluster getStarCluster() {
            return this.starCluster;
        }

        public void setStarCluster(StarCluster starCluster) {
            this.starCluster = starCluster;
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

    private Node root;

    public void eachInOrderInRange(Consumer<StarCluster> consumer,
                                   Rectangle rectangle) {
        this.eachInOrderInRange(this.root, consumer, rectangle);
    }

    private void eachInOrderInRange(Node node, Consumer<StarCluster> consumer,
                                    Rectangle rectangle) {
        if (node == null) {
            return;
        }

        StarCluster starCluster = node.getStarCluster();

        if (starCluster.isInside(rectangle)) {
            consumer.accept(starCluster);
        }

        int starClusterX = starCluster.getX();
        int rectangleX1 = rectangle.getX1();
        int rectangleX2 = rectangle.getX2();

        if (starClusterX < rectangleX1) {
            this.eachInOrderInRange(node.getRight(), consumer, rectangle);
        }

        if (starClusterX > rectangleX2) {
            this.eachInOrderInRange(node.getLeft(), consumer, rectangle);
        }

        if (starClusterX >= rectangleX1
                && starClusterX <= rectangleX2) {
            this.eachInOrderInRange(node.getRight(), consumer, rectangle);
            this.eachInOrderInRange(node.getLeft(), consumer, rectangle);
        }
    }

    public void buildFromList(List<StarCluster> starClusters) {

        this.root = buildFromList(starClusters, 0);
    }

    public Node buildFromList(List<StarCluster> starClusters,
                              int depth) {
        if (starClusters.size() == 0) {
            return null;
        }


        int median = starClusters.size() / 2;
        Node node = new Node(starClusters.get(median));

        if (median >= 1) {
            List<StarCluster> left = starClusters
                    .subList(0, median);
            List<StarCluster> right = starClusters
                    .subList(median+1, starClusters.size());

            node.left = buildFromList(left, depth + 1);
            node.right = buildFromList(right, depth + 1);
        }

        return node;
    }
}
