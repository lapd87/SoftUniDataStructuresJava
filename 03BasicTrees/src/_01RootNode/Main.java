package _01RootNode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by IntelliJ IDEA.
 * User: LAPD
 * Date: 13.1.2019 г.
 * Time: 14:59 ч.
 */
public class Main {

    static Map<Integer, Tree<Integer>> nodeByValue = new HashMap<>();

    static Tree<Integer> getTreeNodeByValue(int value) {
        if (!nodeByValue.containsKey(value)) {
            nodeByValue.put(value, new Tree<>(value));
        }

        return nodeByValue.get(value);
    }

    static void addEdge(int parent, int child) {
        Tree<Integer> parentNode = getTreeNodeByValue(parent);
        Tree<Integer> childNode = getTreeNodeByValue(child);

        parentNode.children.add(childNode);
        childNode.parent = parentNode;
    }

    static void ReadTree() throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        int nodeCount = Integer.parseInt(bufferedReader.readLine());

        for (int i = 0; i < nodeCount - 1; i++) {
            int[] edge = Arrays.stream(bufferedReader.readLine()
                    .split("\\s+"))
                    .mapToInt(Integer::parseInt)
                    .toArray();

            addEdge(edge[0], edge[1]);
        }
    }

    static Tree<Integer> getRootNode() {
        return nodeByValue.values()
                .stream()
                .filter(x -> x.parent == null)
                .findFirst()
                .get();
    }

    public static void main(String[] args) throws IOException {

        ReadTree();
        Tree<Integer> rootNode = getRootNode();

        System.out.printf("Root node: %s", rootNode.value);
    }
}