import common.TreeNode;

/**
 * Given a binary tree, determine if it is a BST
 *
 * Definition of BST is
 * - all nodes on the left side of a node must be less or equals to node.val
 * - all nodes on the right side of a node must be greater than node.val
 *
 * Example:
 *                   5
 *                 /   \
 *               2      7
 *             /  \    /  \
 *            1   3   6    8
 *
 * Explanation
 * - use the min and max to help determining when a val of a node
 *   meets the definition of BST
 */
public class IsBST {
    public static void main(String[] args) {

        System.out.println(IsBST.class.getName());
        System.out.println("expect true: " + isBST(createGoodTree1()));
        System.out.println("expect false: " + isBST(createBadTree1()));
    }


    private static boolean isBST(TreeNode n) {
        return isBSTHelper(n, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private static boolean isBSTHelper(TreeNode n, int min, int max) {
        if (n == null) {
            return true;  // null means it is ok
        }

        // true if n.val meets the constraint  min < n.val <= max
        // false if n.val doesn't meet the constraint n.val < n or n.val > max

        if (n.val < min || n.val > max) {
            return false;
        }

        // if we get here, meaning current node meets the constraints
        // so keep traversing to left and right subtrees

        return isBSTHelper(n.left, min, n.val) &&
                isBSTHelper(n.right, n.val + 1, max);
    }


    private static TreeNode createGoodTree1() {
        TreeNode root = TreeNode.createNode(5);

        root.left = TreeNode.createNode(2);
        root.right = TreeNode.createNode(7);

        root.left.left = TreeNode.createNode(1);
        root.left.right = TreeNode.createNode(3);

        root.right.left = TreeNode.createNode(6);
        root.right.right = TreeNode.createNode(8);

        return root;

    }

    private static TreeNode createBadTree1() {
        TreeNode root = TreeNode.createNode(5);

        root.left = TreeNode.createNode(2);
        root.right = TreeNode.createNode(7);

        root.left.left = TreeNode.createNode(1);
        root.left.right = TreeNode.createNode(3);

        root.right.left = TreeNode.createNode(6);
        root.right.right = TreeNode.createNode(2);

        return root;

    }
}
