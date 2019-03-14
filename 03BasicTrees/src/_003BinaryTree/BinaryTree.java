package _003BinaryTree;

import java.util.Collections;
import java.util.function.Consumer;

public class BinaryTree<T> {

    public T value;
    public BinaryTree<T> leftChild;
    public BinaryTree<T> rightChild;

    public BinaryTree(T value) {
        this(value, null);
    }

    public BinaryTree(T value, BinaryTree<T> child) {
        this(value, child, null);
    }

    public BinaryTree(T value, BinaryTree<T> leftChild, BinaryTree<T> rightChild) {
        this.value = value;
        this.leftChild = leftChild;
        this.rightChild = rightChild;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
    }

    public BinaryTree<T> getLeftChild() {
        return leftChild;
    }

    public void setLeftChild(BinaryTree<T> leftChild) {
        this.leftChild = leftChild;
    }

    public BinaryTree<T> getRightChild() {
        return rightChild;
    }

    public void setRightChild(BinaryTree<T> rightChild) {
        this.rightChild = rightChild;
    }

    // append output to builder
    public String printIndentedPreOrder(int indent, StringBuilder builder) {

        builder.append(String.join("",
                Collections.nCopies(indent * 2, " ")))
                .append(this.value)
                .append("\n");

        if (this.leftChild != null) {
            this.leftChild.printIndentedPreOrder(indent + 1, builder);
        }

        if (this.rightChild != null) {
            this.rightChild.printIndentedPreOrder(indent + 1, builder);
        }

        return builder.toString();
    }

    public void eachInOrder(Consumer<T> consumer) {

        if (this.leftChild != null) {
            this.leftChild.eachInOrder(consumer);
        }

        consumer.accept(this.value);

        if (this.rightChild != null) {
            this.rightChild.eachInOrder(consumer);
        }
    }

    public void eachPostOrder(Consumer<T> consumer) {

        if (this.leftChild != null) {
            this.leftChild.eachPostOrder(consumer);
        }

        if (this.rightChild != null) {
            this.rightChild.eachPostOrder(consumer);
        }

        consumer.accept(this.value);
    }
}
