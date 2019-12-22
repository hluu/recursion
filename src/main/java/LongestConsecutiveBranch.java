import common.TreeNode;

/**
 *  Given a tree, write a function to find the length of the longest
 *  branch of nodes in increasing consecutive order.
 *
 *  For example:
 *            0
 *          /  \
 *         1    2
 *        / \  / \
 *       1  2 1  3
 */
public class LongestConsecutiveBranch {
    public static void main(String[] args) {
        System.out.println("LongestConsecutiveBranch.main");

        test(createTree1(), 3);
        test(createTree2(), 5);
    }

    private static void test(TreeNode root, int expected) {

        System.out.println("\n=======> testing <========");

        int actual = lsb(root);

        System.out.printf("expected: %d, actual: %d\n", expected, actual);
    }

    private static int lsb(TreeNode node) {
        if (node == null) {
            return 0;
        }

        int maxLeft = lsbHelper(node.left, node.val, 1);
        int maxRight = lsbHelper(node.right, node.val, 1);
        return Math.max(maxLeft, maxRight);
    }

    private static int lsbHelper(TreeNode node, int prevValue, int lenSoFar) {
        if (node == null) {
            return lenSoFar;
        } else {
            if (node.val == (prevValue) +1) {
                // consecutive
                return Math.max(lsbHelper(node.left, node.val, lenSoFar+1),
                        lsbHelper(node.right, node.val, lenSoFar+1));
            } else {
                // not consecutive, return max of left, right, and current length
                // start a new sequence
                int maxOfLeftRight= Math.max(lsbHelper(node.left, node.val, 1),
                        lsbHelper(node.right, node.val, 1));

                // make sure to also compare the lenSofar
                return Math.max(maxOfLeftRight, lenSoFar);

            }
        }

    }

    private static TreeNode createTree1() {
        TreeNode root = TreeNode.createNode(0);

        root.left = TreeNode.createNode(1);
        root.right = TreeNode.createNode(2);

        root.left.left = TreeNode.createNode(1);
        root.left.right = TreeNode.createNode(2);

        root.right.left = TreeNode.createNode(1);
        root.right.right = TreeNode.createNode(3);

        return root;

    }

    private static TreeNode createTree2() {
        TreeNode root = TreeNode.createNode(0);

        root.left = TreeNode.createNode(1);
        root.right = TreeNode.createNode(2);

        root.left.left = TreeNode.createNode(1);
        root.left.right = TreeNode.createNode(2);

        root.right.left = TreeNode.createNode(1);
        root.right.right = TreeNode.createNode(3);

        root.right.left.left = TreeNode.createNode(2);
        root.right.left.left.left = TreeNode.createNode(3);
        root.right.left.left.left.left = TreeNode.createNode(4);
        root.right.left.left.left.left.left = TreeNode.createNode(5);

        return root;

    }
}
