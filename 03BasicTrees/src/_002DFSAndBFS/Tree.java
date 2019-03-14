package _002DFSAndBFS;

import java.util.*;
import java.util.function.Consumer;

public class Tree<T> {

    public T value;
    public List<Tree<T>> children;

    public Tree(T value, Tree<T>... children) {
        this.value = value;
        this.children = new ArrayList<>();
        this.children.addAll(Arrays.asList(children));
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public List<Tree<T>> getChildren() {
        return children;
    }

    private void setChildren(List<Tree<T>> children) {
        this.children = children;
    }

    // append output to builder
    public String print(int indent, StringBuilder builder) {
        builder.append(String.join("",
                Collections.nCopies(indent * 2, " ")))
                .append(this.value)
                .append("\n");

        for (Tree<T> child : this.children) {
            child.print(indent + 1, builder);
        }

        return builder.toString();
    }

    public void each(Consumer<T> consumer) {
        consumer.accept(this.value);

        for (Tree<T> child : this.children) {
            child.each(consumer);
        }
    }

    public Iterable<T> orderDFS() {

        List<T> result = new ArrayList<>();

        DFS(this, result);

        return result;
    }

    private void DFS(Tree<T> tree, List<T> result) {

        for (Tree<T> child : tree.children) {
            DFS(child, result);
        }

        result.add(tree.value);
    }

    public Iterable<T> orderBFS() {

        List<T> result = new ArrayList<>();

        LinkedList<Tree<T>> queue = new LinkedList<>();

        queue.addLast(this);

        while (queue.size() > 0) {

            Tree<T> current = queue.removeFirst();

            result.add(current.value);

            for (Tree<T> child : current.children) {
                queue.addLast(child);
            }
        }

        return result;
    }

}