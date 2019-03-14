package _02AStar;

import java.util.*;

public class AStar {

    private char[][] maze;

    public AStar(char[][] map) {
        this.maze = map;
    }

    public static int getH(Node current, Node goal) {
        int deltaX = Math.abs(current.getCol() - goal.getCol());
        int deltaY = Math.abs(current.getRow() - goal.getRow());

        return deltaX + deltaY;
    }

    public Iterable<Node> getPath(Node start, Node goal) {

        Map<Node, Node> childParent = new HashMap<>();
        Map<Node, Integer> nodeCost = new HashMap<>();

        childParent.put(start, null);
        nodeCost.put(start, 0);

        PriorityQueue<Node> visited = new PriorityQueue<>();
        visited.enqueue(start);

        while (visited.size() > 0) {
            Node current = visited.dequeue();

            if (current == goal) {
                break;
            }

            List<Node> neighbours = getNeighbours(current);

            for (Node neighbour : neighbours) {
                int newCost = nodeCost.get(current) + 1;

                if (!nodeCost.containsKey(neighbour)
                        || nodeCost.get(neighbour) > newCost) {
                    neighbour.setF(newCost + getH(neighbour, goal));

                    nodeCost.put(neighbour, newCost);
                    childParent.put(neighbour, current);

                    visited.enqueue(neighbour);
                }
            }
        }

        return reconstructPath(childParent, goal);
    }

    private Iterable<Node> reconstructPath(Map<Node, Node> childParent, Node goal) {

        List<Node> path = new ArrayList<>();

        Node parent = childParent.get(goal);

        if (parent == null) {
            path.add(0, null);
            return path;
        }

        path.add(0, goal);

        while (parent != null) {
            path.add(0, parent);
            parent = childParent.get(parent);
        }

        return path;
    }

    private List<Node> getNeighbours(Node current) {

        int row = current.getRow();
        int col = current.getCol();

        int mazeRowSize = this.maze.length;
        int mazeColSize = this.maze[0].length;

        List<Node> neighbours = new ArrayList<>();

        if (row + 1 < mazeRowSize && isNotWall(row + 1, col)) {
            neighbours.add(new Node(row + 1, col));
        }

        if (row - 1 >= 0 && isNotWall(row - 1, col)) {
            neighbours.add(new Node(row - 1, col));
        }

        if (col + 1 < mazeColSize && isNotWall(row, col + 1)) {
            neighbours.add(new Node(row, col + 1));
        }

        if (col - 1 >= 0 && isNotWall(row, col - 1)) {
            neighbours.add(new Node(row, col - 1));
        }

        return neighbours;
    }

    private boolean isNotWall(int row, int col) {
        return this.maze[row][col] != 'W';
    }
}
