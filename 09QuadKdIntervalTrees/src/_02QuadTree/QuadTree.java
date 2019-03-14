package _02QuadTree;

import java.util.ArrayList;
import java.util.List;

import static _02QuadTree.Node.MAX_ITEM_COUNT;

public class QuadTree<T extends Boundable> {

    public static final int DEFAULT_MAX_DEPTH = 5;

    private int maxDepth;

    private Node<T> root;

    private Rectangle bounds;

    private int count;

    public QuadTree(int width, int height) {
        this(width, height, DEFAULT_MAX_DEPTH);
    }

    public QuadTree(int width, int height, int maxDepth) {
        this.root = new Node<>(0, 0, width, height);
        this.bounds = this.root.getBounds();
        this.maxDepth = maxDepth;
    }

    public Rectangle getBounds() {
        return this.bounds;
    }

    private void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }

    public int getCount() {
        return this.count;
    }

    private void setCount(int count) {
        this.count = count;
    }

    public boolean insert(T item) {
        return this.insert(item, 1);
    }

    private boolean insert(T item, int depth) {

        if (!item.getBounds().isInside(this.bounds)) {
            return false;
        }

        Node<T> current = this.root;

        while (current.getChildren() != null) {
            int quadrant = getQuadrant(current, item.getBounds());

            try {
                current = current.getChildren()[quadrant];
                depth++;
            } catch (IndexOutOfBoundsException iob) {
                break;
            }
        }

        current.getItems().add(item);

        split(current, depth);

        this.count++;

        return true;
    }

    public List<T> report(Rectangle bounds) {

        List<T> collisions = new ArrayList<>();

        getCollisions(this.root, bounds, collisions);

        return collisions;
    }

    private void getCollisions(Node<T> node, Rectangle bounds,
                               List<T> collisions) {

        int quadrant = getQuadrant(node, bounds);

        if (quadrant == -1) {
            //bounds doesnt fit in quadrant
            //check all items in node children for intersection
            //add all node items and children intersections
            getSubtreeElements(node, bounds, collisions);
            return;
        }

        if (node.getChildren() != null) {
            getCollisions(node.getChildren()[quadrant],
                    bounds, collisions);
        }

        collisions.addAll(node.getItems());
    }

    private void getSubtreeElements(Node<T> node, Rectangle bounds,
                                    List<T> collisions) {

        if (node.getChildren() != null) {
            for (Node<T> child : node.getChildren()) {
                if (child.getBounds().intersects(bounds)) {
                    getSubtreeElements(child, bounds,
                            collisions);
                }
            }
        }

        collisions.addAll(node.getItems());
    }

    private int getQuadrant(Node<T> node, Rectangle bounds) {

        Node<T>[] nodeChildren = node.getChildren();

        if (nodeChildren != null) {

            for (int quadrant = 0; quadrant < MAX_ITEM_COUNT; quadrant++) {

                if (bounds.isInside(nodeChildren[quadrant].getBounds())) {
                    return quadrant;
                }
            }
        }

        return -1;
    }

    private void split(Node<T> node, int depth) {

        if (!node.shouldSplit()
                || depth >= this.maxDepth) {
            return;
        }

        Rectangle nodeBounds = node.getBounds();

        int leftWidth = nodeBounds.getWidth() / 2;
        int rightWidth = nodeBounds.getWidth() - leftWidth;
        int topHeight = nodeBounds.getHeight() / 2;
        int bottomHeight = nodeBounds.getHeight() - topHeight;

        int horMidpoint = nodeBounds.getMidX();
        int verMidpoint = nodeBounds.getMidY();

        int boundsX1 = nodeBounds.getX1();
        int boundsY1 = nodeBounds.getY1();

        node.setChildren(new Node[4]);

        // x is horizontal axis
        // x0,y0 is top left corner
        // topLeft = 1, topRight = 0
        // bottomLeft = 2; bottomRight = 3
        node.getChildren()[0] = new Node<>(horMidpoint,
                boundsY1, rightWidth, topHeight);
        node.getChildren()[1] = new Node<>(boundsX1,
                boundsY1, leftWidth, topHeight);
        node.getChildren()[2] = new Node<>(boundsX1,
                verMidpoint, leftWidth, bottomHeight);
        node.getChildren()[3] = new Node<>(horMidpoint,
                verMidpoint, rightWidth, bottomHeight);

        for (int i = 0; i < node.getItems().size(); i++) {

            T item = node.getItems().get(i);
            int quadrant = getQuadrant(node, item.getBounds());

            if (quadrant != -1) {
                node.getItems().remove(i--);

                node.getChildren()[quadrant]
                        .getItems()
                        .add(item);
            }
        }

        for (Node<T> child : node.getChildren()) {
            split(child, depth + 1);
        }
    }
}
