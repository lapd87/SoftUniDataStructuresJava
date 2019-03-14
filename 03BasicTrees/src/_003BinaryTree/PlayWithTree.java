package _003BinaryTree;

public class PlayWithTree {

    public static void main(String[] args) {
        System.out.println();
        BinaryTree<String> binaryTree =
                new BinaryTree<>("*",
                        new BinaryTree<>("+",
                                new BinaryTree<>("3"),
                                new BinaryTree<>("2")),
                        new BinaryTree<>("-",
                                new BinaryTree<>("9"),
                                new BinaryTree<>("6")));

        System.out.println("Binary tree (indented, pre-order):");
        String output = binaryTree.printIndentedPreOrder(0, new StringBuilder());
        System.out.println(output);

        System.out.println("Binary tree nodes (in-order):");
        binaryTree.eachInOrder(e -> System.out.print(" " + e));
        System.out.println();

        System.out.println("Binary tree nodes (post-order):");
        binaryTree.eachPostOrder(e -> System.out.print(" " + e));
        System.out.println();
    }
}
