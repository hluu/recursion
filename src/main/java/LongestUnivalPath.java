import common.Node;

/**
 * Given a binary tree, find the length of the longest path where each node
 * in the path has the same value. This path may or may not pass through the root.
 *
 * The length of path between two nodes is represented by the number of edges
 * between them.
 *
 * Example 1:
 *
 * Input:
 *
 *               5
 *              / \
 *             4   5
 *            / \   \
 *           1   1   5
 *
 * Output: 2
 *
 * Example 2:
 *
 * Input:
 *
 *               1
 *              / \
 *             4   5
 *            / \   \
 *           4   4   5
 *
 * Output: 2
 *
 *
 * https://leetcode.com/problems/longest-univalue-path/
 *
 *
 */
public class LongestUnivalPath {

    public static void main(String[] args) {
        System.out.println("LongestUnivalPath.main");

        test(createTree1(), 2);
        test(createTree2(), 2);
    }


    private static void test(Node root, int expected) {
        System.out.println("root = [" + root + "], expected = [" + expected + "]");

        int[] res = new int[1];
        helper(root, res);

        System.out.printf("expected: %d, actual: %d\n", expected, res[0]);
    }

    /**
     * This is performing DFS, as the stack unwinds, we compute the path
     *  - don't extend the streak if node and its children (left or right) is not the same
     *
     * Pattern:
     *  - post order traversal - useful when needing to know the answer from both children
     *  - extending the streak when constraint is met
     *  - use another variable to store the global solution among all the possible solution
     *  - return to the parent of the maximum value of the two in order for it to extend it
     *
     *
     * @param root
     * @param res
     * @return
     */
    private static int helper(Node root, int[] res) {
        // leaf node
        if (root == null) {
            return 0;
        }

        int leftSide = helper(root.left, res);
        int rightSide = helper(root.right, res);

        int updatedLeftSide = 0;
        int updatedRightSide = 0;
        if (root.left != null && root.value == root.left.value) {
            updatedLeftSide = leftSide + 1;
        }


        if (root.right != null && root.value == root.right.value) {
            updatedRightSide = rightSide + 1;
        }

        // extending the streak doesn't make sense
        //leftSide += (root.left != null && root.value == root.left.value) ? 1 : 0 ;
        //rightSide += (root.right != null && root.value == root.right.value) ? 1 : 0 ;

        res[0] = Math.max(updatedLeftSide+updatedRightSide, res[0]);

        return Math.max(updatedLeftSide, updatedRightSide);
    }

    /**
    *               5
    *              / \
    *             4   5
    *            / \   \
    *           1   1   5
     */
    private static Node createTree1() {
        Node root = Node.createNode(5);

        root.left = Node.createNode(4);
        root.right = Node.createNode(5);

        root.left.left = Node.createNode(1);
        root.left.right = Node.createNode(1);

        root.right.right = Node.createNode(5);

        return root;
    }

    /**
    *               1
    *              / \
    *             4   5
    *            / \   \
    *           4   4   5
     */
    private static Node createTree2() {
        Node root = Node.createNode(1);

        root.left = Node.createNode(4);
        root.right = Node.createNode(5);

        root.left.left = Node.createNode(4);
        root.left.right = Node.createNode(4);

        root.right.right = Node.createNode(5);

        return root;
    }

    /**
     * [                               5,
     *                     4,                        5,
     *             4,              4,           5,        3,
     *        4,       4,     null,    null,  null, 4,  null,null,
     *      4,null,  null,4,                     null,4,
     *    4,null        null,4,
     *  4]
     * @return
     */
    private static Node createTree3() {
        Node root = Node.createNode(5);

        root.left = Node.createNode(4);
        root.right = Node.createNode(5);

        root.left.left = Node.createNode(4);
        root.left.right = Node.createNode(4);

        root.right.left = Node.createNode(5);
        root.right.right = Node.createNode(3);

        root.left.left.left = Node.createNode(4);
        root.left.left.right = Node.createNode(4);

        root.right.left = Node.createNode(5);

        return root;
    }
}
