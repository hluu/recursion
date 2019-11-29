/**
 * Given a binary tree, determine if it is a BST
 *
 * Definition of BST is
 * - all nodes on the left side of a node must be less or equals to node.value
 * - all nodes on the right side of a node must be greater than node.value
 *
 * Example:
 *                   5
 *                 /   \
 *               2      7
 *             /  \    /  \
 *            1   3   6    8
 *
 * Explanation
 * - use the min and max to help determining when a value of a node
 *   meets the definition of BST
 */
public class IsBST {
    public static void main(String[] args) {

        System.out.println(IsBST.class.getName());
        System.out.println("expect true: " + isBST(createGoodTree1()));
        System.out.println("expect false: " + isBST(createBadTree1()));
    }


    private static boolean isBST(Node n) {
        return isBSTHelper(n, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private static boolean isBSTHelper(Node n, int min, int max) {
        if (n == null) {
            return true;  // null means it is ok
        }

        // true if n.value meets the constraint  min < n.value <= max
        // false if n.value doesn't meet the constraint n.value < n or n.value > max

        if (n.value < min || n.value > max) {
            return false;
        }

        // if we get here, meaning current node meets the constraints
        // so keep traversing to left and right subtrees

        return isBSTHelper(n.left, min, n.value) &&
                isBSTHelper(n.right, n.value + 1, max);
    }


    private static Node createGoodTree1() {
        Node root = Node.createNode(5);

        root.left = Node.createNode(2);
        root.right = Node.createNode(7);

        root.left.left = Node.createNode(1);
        root.left.right = Node.createNode(3);

        root.right.left = Node.createNode(6);
        root.right.right = Node.createNode(8);

        return root;

    }

    private static Node createBadTree1() {
        Node root = Node.createNode(5);

        root.left = Node.createNode(2);
        root.right = Node.createNode(7);

        root.left.left = Node.createNode(1);
        root.left.right = Node.createNode(3);

        root.right.left = Node.createNode(6);
        root.right.right = Node.createNode(2);

        return root;

    }
}
