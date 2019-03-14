package _08SubtreesWithGivenSum;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by IntelliJ IDEA.
 * User: LAPD
 * Date: 13.1.2019 г.
 * Time: 14:55 ч.
 */
public class Tree<T> {

    public T value;
    public Tree<T> parent;
    public List<Tree<T>> children;

    public Tree(T value, Tree<T>... children) {
        this.value = value;
        this.parent = null;
        this.children = new ArrayList<>();

        for (Tree<T> child : children) {
            this.children.add(child);
            child.parent = this;
        }
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public Tree<T> getParent() {
        return parent;
    }

    public void setParent(Tree<T> parent) {
        this.parent = parent;
    }

    public List<Tree<T>> getChildren() {
        return children;
    }

    public void setChildren(List<Tree<T>> children) {
        this.children = children;
    }
}