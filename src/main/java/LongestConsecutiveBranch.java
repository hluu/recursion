import common.Node;

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

    private static void test(Node root, int expected) {

        System.out.println("\n=======> testing <========");

        int actual = lsb(root);

        System.out.printf("expected: %d, actual: %d\n", expected, actual);
    }

    private static int lsb(Node node) {
        if (node == null) {
            return 0;
        }

        int maxLeft = lsbHelper(node.left, node.value, 1);
        int maxRight = lsbHelper(node.right, node.value, 1);
        return Math.max(maxLeft, maxRight);
    }

    private static int lsbHelper(Node node, int prevValue, int lenSoFar) {
        if (node == null) {
            return lenSoFar;
        } else {
            if (node.value == (prevValue) +1) {
                // consecutive
                return Math.max(lsbHelper(node.left, node.value, lenSoFar+1),
                        lsbHelper(node.right, node.value, lenSoFar+1));
            } else {
                // not consecutive, return max of left, right, and current length
                // start a new sequence
                int maxOfLeftRight= Math.max(lsbHelper(node.left, node.value, 1),
                        lsbHelper(node.right, node.value, 1));

                // make sure to also compare the lenSofar
                return Math.max(maxOfLeftRight, lenSoFar);

            }
        }

    }

    private static Node createTree1() {
        Node root = Node.createNode(0);

        root.left = Node.createNode(1);
        root.right = Node.createNode(2);

        root.left.left = Node.createNode(1);
        root.left.right = Node.createNode(2);

        root.right.left = Node.createNode(1);
        root.right.right = Node.createNode(3);

        return root;

    }

    private static Node createTree2() {
        Node root = Node.createNode(0);

        root.left = Node.createNode(1);
        root.right = Node.createNode(2);

        root.left.left = Node.createNode(1);
        root.left.right = Node.createNode(2);

        root.right.left = Node.createNode(1);
        root.right.right = Node.createNode(3);

        root.right.left.left = Node.createNode(2);
        root.right.left.left.left = Node.createNode(3);
        root.right.left.left.left.left = Node.createNode(4);
        root.right.left.left.left.left.left = Node.createNode(5);

        return root;

    }
}
