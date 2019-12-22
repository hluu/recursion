import common.Node;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a Binary Search Tree (BST) with the root node root, return the minimum difference between the values of any
 * two different nodes in the tree.
 *
 *
 * Input: [4,2,6,1,3,null,null]
 * Output: 1
 * Explanation:
 * Note that root is a TreeNode object, not an array.
 *
 * The given tree [4,2,6,1,3,null,null] is represented by the following diagram:
 *
 *           4
 *         /   \
 *       2      6
 *      / \
 *     1   3
 *
 * Input:  [90,69,null,49,89,null,52,null,null,null,null]
 *
 *                90
 *              /    \
 *            69
 *          /   \
 *        49     89
 *         \
 *         52
 *
 * Output: 1  (90 - 89)
 *
 * https://leetcode.com/problems/minimum-distance-between-bst-nodes/
 */
public class MinDistBetweenBSTNode {
    public static void main(String[] args) {
        System.out.println("MinDistBetweenBSTNode.main");

        test(createTree1(), 1);
        test(createTree2(), 1);
    }

    private static void test(Node node, int expected) {
        System.out.println("\nnode: " + node);

        int actual = minDist(node);

        System.out.printf("expected: %d, actual: %d\n", expected, actual);
    }

    private static int minDist(Node node) {
        List<Integer> list = new ArrayList<>();

        // collect the list
        preOrderTraversal(node, list);

        System.out.println("list: " + list);

        int size = list.size();

        int minDiff = Integer.MAX_VALUE;
        for (int i = 0; i <= size-2; i++) {
            minDiff = Math.min(minDiff, list.get(i+1) - list.get(i));
        }

        return minDiff;
    }

    private static void preOrderTraversal(Node node, List<Integer> coll) {
        if (node == null) {
            return;
        }

        preOrderTraversal(node.left, coll);
        coll.add(node.value);
        preOrderTraversal(node.right, coll);
    }

    private static Node createTree1() {
        Node root = Node.createNode(4);
        root.left  = Node.createNode(2);
        root.right  = Node.createNode(6);

        root.left.left  = Node.createNode(1);
        root.left.right  = Node.createNode(3);

        return root;
    }

    /**
    *
    *                90
    *              /    \
    *            69
    *          /   \
    *        49     89
    *         \
    *         52
     *
     **/

    private static Node createTree2() {
        Node root = Node.createNode(90);
        root.left  = Node.createNode(69);


        root.left.left  = Node.createNode(49);
        root.left.right  = Node.createNode(89);

        root.left.left.right  = Node.createNode(52);

        return root;
    }
}
