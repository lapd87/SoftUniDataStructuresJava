package _001Tree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
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
        throw new UnsupportedOperationException();
    }

    public Iterable<T> orderBFS() {
        throw new UnsupportedOperationException();
    }

}