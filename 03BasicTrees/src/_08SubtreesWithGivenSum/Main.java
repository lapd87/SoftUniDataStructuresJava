package _08SubtreesWithGivenSum;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by IntelliJ IDEA.
 * User: LAPD
 * Date: 13.1.2019 г.
 * Time: 14:59 ч.
 */
public class Main {

    static BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

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
//        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

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

    static String printTree() {
        return printTree(getRootNode(), 0, new StringBuilder());
    }

    static String printTree(Tree<Integer> current, int indent, StringBuilder builder) {

        builder.append(String.join("",
                Collections.nCopies(indent * 2, " ")))
                .append(current.value)
                .append("\n");

        for (Tree<Integer> child : current.children) {
            printTree(child, indent + 1, builder);
        }

        return builder.toString();

    }

    static List<Tree<Integer>> getLeafNodes() {
        return nodeByValue.values()
                .stream()
                .filter(x -> x.children.isEmpty())
                .collect(Collectors.toList());
    }

    static List<Tree<Integer>> getMiddleNodes() {
        return nodeByValue.values()
                .stream()
                .filter(x -> !x.children.isEmpty()
                        && x.parent != null)
                .collect(Collectors.toList());
    }

    static Tree<Integer> getDeepestNode() {
        return getDeepestNode(getRootNode(), 0,
                new TreeMap<>(Collections.reverseOrder()));
    }

    private static Tree<Integer> getDeepestNode(Tree<Integer> current,
                                                int depth, TreeMap<Integer,
            Tree<Integer>> nodeByDepth) {

        if (!nodeByDepth.containsKey(depth)) {
            nodeByDepth.put(depth, current);
        }

        for (Tree<Integer> child : current.children) {
            getDeepestNode(child, depth + 1, nodeByDepth);
        }

//        Collections.max(nodeByDepth.entrySet(),
//                Comparator.comparingInt(Map.Entry::getKey)).getValue();
        return nodeByDepth.firstEntry().getValue();
    }

    private static List<Tree<Integer>> getLongestPath() {
        List<Tree<Integer>> path = new ArrayList<>();

        Tree<Integer> current = getDeepestNode();

        while (current.parent != null) {
            path.add(current);
            current = current.parent;
        }

        path.add(current);

        Collections.reverse(path);

        return path;
    }

    private static List<List<Tree<Integer>>> getPathsWithSum(int sum) {

        return getPathsWithSum(getRootNode(), 0, sum,
                new ArrayList<>());
    }

    private static List<List<Tree<Integer>>> getPathsWithSum(Tree<Integer> current,
                                                             int currentSum, int targetSum,
                                                             List<List<Tree<Integer>>> result) {

        currentSum += current.value;

        if (currentSum < targetSum) {
            for (Tree<Integer> child : current.children) {
                getPathsWithSum(child, currentSum, targetSum,
                        result);
            }
        }

        if (currentSum == targetSum) {
            List<Tree<Integer>> sumPath = new ArrayList<>();

            Tree<Integer> parent = current;

            while (parent.parent != null) {
                sumPath.add(parent);
                parent = parent.parent;
            }

            sumPath.add(parent);

            Collections.reverse(sumPath);

            result.add(sumPath);
        }

        return result;
    }

    private static List<List<Tree<Integer>>> getTreesWithSum(int sum) {

        List<Tree<Integer>> subtreeRoots = new ArrayList<>();

        getSubtreeRootWithSum(getRootNode(), 0, sum, subtreeRoots);

        List<List<Tree<Integer>>> result = new ArrayList<>();

        for (Tree<Integer> subtreeRoot : subtreeRoots) {
            List<Tree<Integer>> subtreePath = new ArrayList<>();

            getAllChildren(subtreeRoot, subtreePath);

            result.add(subtreePath);
        }

        return result;
    }

    private static void getAllChildren(Tree<Integer> root, List<Tree<Integer>> subtreePath) {
        subtreePath.add(root);

        for (Tree<Integer> child : root.children) {
            getAllChildren(child, subtreePath);
        }
    }

    private static int getSubtreeRootWithSum(Tree<Integer> current,
                                             int currentSum, int targetSum,
                                             List<Tree<Integer>> result) {
        if (current == null) {
            return 0;
        }

        currentSum = current.value;

        for (Tree<Integer> child : current.children) {
            currentSum += getSubtreeRootWithSum(child, currentSum, targetSum, result);
        }

        if (currentSum == targetSum) {
            result.add(current);
        }

        return currentSum;
    }

    public static void main(String[] args) throws IOException {
        ReadTree();

        int sum = Integer.parseInt(bufferedReader.readLine());


        List<List<Tree<Integer>>> treesWithSum = getTreesWithSum(sum);

        List<String> resultValues = treesWithSum.stream()
                .map(list -> String.join(" ",
                        list.stream()
                                .map(x -> x.value + "")
                                .collect(Collectors.toList())))
                .collect(Collectors.toList());

        System.out.printf("Subtrees of sum %d:%n%s", sum,
                String.join(System.lineSeparator(), resultValues));
    }

}