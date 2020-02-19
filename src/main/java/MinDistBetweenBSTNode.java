import common.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a Binary Search Tree (BST) with the root node root, return the
 * minimum difference between the values of any
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

    private static void test(TreeNode node, int expected) {
        System.out.println("\nnode: " + node);

        int actual = minDist(node);

        System.out.printf("expected: %d, actual: %d\n", expected, actual);
    }

    /**
     * The intuition is if the numbers are sorted, then
     * it is easy to find the min distance between any two numbers.
     *
     *
     * Approach:
     *  - Perform in-order traversal to collect the numbers in sorted order
     *  - Iterate through the list to perform the comparison
     *
     *  Runtime: O(2n) => O(n)
     *  Space: O(n)
     *
     * @param node
     * @return
     */
    private static int minDist(TreeNode node) {
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

    private static void preOrderTraversal(TreeNode node, List<Integer> coll) {
        if (node == null) {
            return;
        }

        preOrderTraversal(node.left, coll);
        coll.add(node.val);
        preOrderTraversal(node.right, coll);
    }

    private static TreeNode createTree1() {
        TreeNode root = TreeNode.createNode(4);
        root.left  = TreeNode.createNode(2);
        root.right  = TreeNode.createNode(6);

        root.left.left  = TreeNode.createNode(1);
        root.left.right  = TreeNode.createNode(3);

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

    private static TreeNode createTree2() {
        TreeNode root = TreeNode.createNode(90);
        root.left  = TreeNode.createNode(69);


        root.left.left  = TreeNode.createNode(49);
        root.left.right  = TreeNode.createNode(89);

        root.left.left.right  = TreeNode.createNode(52);

        return root;
    }
}
