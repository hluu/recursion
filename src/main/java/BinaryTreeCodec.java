import common.TreeNode;

import java.util.Arrays;
import java.util.stream.Stream;

/**
 * Serialization is the process of converting a data structure or
 * object into a sequence of bits so that it can be stored in a file
 * or memory buffer, or transmitted across a network connection link
 * to be reconstructed later in the same or another computer environment.
 *
 * Design an algorithm to serialize and deserialize a binary tree.
 * There is no restriction on how your serialization/deserialization
 * algorithm should work. You just need to ensure that a binary tree
 * can be serialized to a string and this string can be deserialized
 * to the original tree structure.
 *
 * Example:
 *
 * You may serialize the following tree:
 *
 *     1
 *    / \
 *   2   3
 *      / \
 *     4   5
 *
 * as "[1,2,3,null,null,4,5]"
 */
public class BinaryTreeCodec {
    public static void main(String[] args) {
        System.out.println("BinaryTreeCodec.main");

        test(createTree1());
    }

    private static void test(TreeNode root) {
        String actual = serialize(root);

        System.out.printf("first s: %s\n", actual);

        TreeNode newRoot = deserialize(actual);

        System.out.printf("new   s: %s\n", serialize(newRoot));
    }

    private static String serialize(TreeNode node) {
        StringBuilder buf = new StringBuilder();

        serializeHelper(node, buf);
        return buf.toString();
    }


    /**
     * Using pre-order traversal (node, left, right)
     *
     * @param node
     * @param buf
     */
    private static void serializeHelper(TreeNode node, StringBuilder buf) {
        if (buf.length() > 0) {
            buf.append(",");
        }

        if (node == null) {
            buf.append("n");
        } else {
            buf.append(node.val);

            serializeHelper(node.left, buf);
            serializeHelper(node.right, buf);
        }
    }

    public static TreeNode deserialize(String data) {
        String[] tokens = data.split(",");

        Stream<String> stream = Arrays.stream(tokens);

        int[] idx = new int[1];

        TreeNode root = deserializeHelper(tokens, idx);
        return root;
    }

    /**
     * Deserialize using pre-order traversal (node, left, right)
     *
     * @param tokens
     * @param idx
     * @return
     */
    private static TreeNode deserializeHelper(String[] tokens, int[] idx) {

        String token = tokens[idx[0]];
        idx[0] = idx[0] + 1;

        if (token.equals("n")) {
            return null;
        }

        TreeNode tmpNode = TreeNode.createNode(Integer.parseInt(token));

        tmpNode.left = deserializeHelper(tokens, idx);
        tmpNode.right = deserializeHelper(tokens, idx);

        return  tmpNode;

    }

    /**
     *        0
     *      /   \
     *     1     2
     *          /   \
     *        3      4
     *             /   \
     *            5     6
     *
     * [0,0,0,null,null,0,0,null,null,0,0]
     *
     * @return
     */
    private static TreeNode createTree1() {
        TreeNode root = TreeNode.createNode(0);

        root.left = TreeNode.createNode(1);
        root.right = TreeNode.createNode(2);

        root.right.left = TreeNode.createNode(3);
        root.right.right = TreeNode.createNode(4);

        root.right.right.left = TreeNode.createNode(5);
        root.right.right.right = TreeNode.createNode(6);

        return root;
    }
}
