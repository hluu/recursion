package common;

import java.util.LinkedList;
import java.util.Queue;

public class ListNodeUtil {

    public static void main(String[] args) {
        test(createTree1(), "0,0,0,null,null,0,0,null,null,0,0");
        test(createTree2(), "0,0,0,null,null,0,0,0,0");
        test(createTree3(), "0,0,0,0,0,0,0");
        test(createTree4(), "0,0,0,0,0,null,null,null,null,0,0");
        test(createTree5(), "0,0,0,0,0,null,null,0,0");
    }

    public static void test(TreeNode node, String expected) {
        String actual = serialize(node);

        System.out.println("\nexpected: " + expected);
        System.out.println("  actual: " + actual);

    }

    /**
     *        0
     *      /   \
     *     0      0
     *          /   \
     *        0      0
     *             /   \
     *            0      0
     *
     * [0,0,0,null,null,0,0,null,null,0,0]
     *
     * @return
     */
    private static TreeNode createTree1() {
        TreeNode root = TreeNode.createNode(0);

        root.left = TreeNode.createNode(0);
        root.right = TreeNode.createNode(0);

        root.right.left = TreeNode.createNode(0);
        root.right.right = TreeNode.createNode(0);

        root.right.right.left = TreeNode.createNode(0);
        root.right.right.right = TreeNode.createNode(0);

        return root;
    }

    /**
     *        0
     *      /   \
     *     0     0
     *          / \
     *         0   0
     *        / \
     *       0   0
     *
     *  [0,0,0,null,null,0,0,0,0]
     *
     * @return
     */
    private static TreeNode createTree2() {
        TreeNode root = TreeNode.createNode(0);

        root.left = TreeNode.createNode(0);
        root.right = TreeNode.createNode(0);

        root.right.left = TreeNode.createNode(0);
        root.right.right = TreeNode.createNode(0);

        root.right.left.left = TreeNode.createNode(0);
        root.right.left.right = TreeNode.createNode(0);

        return root;
    }

    /**
     *        0
     *      /    \
     *     0      0
     *    / \    / \
     *   0   0  0   0
     *
     *   [0,0,0,0,0,0,0]
     *
     * @return
     */
    private static TreeNode createTree3() {
        TreeNode root = TreeNode.createNode(0);
        root.left = TreeNode.createNode(0);
        root.right = TreeNode.createNode(0);


        root.left.left = TreeNode.createNode(0);
        root.left.right = TreeNode.createNode(0);


        root.right.left = TreeNode.createNode(0);
        root.right.right = TreeNode.createNode(0);

        return root;
    }

    /**
     *        0
     *      /   \
     *     0     0
     *    / \
     *   0   0
     *      / \
     *     0   0
     *
     *  [0,0,0,0,0,null,null,null,null,0,0]
     *
     * @return
     */
    private static TreeNode createTree4() {
        TreeNode root = TreeNode.createNode(0);
        root.left = TreeNode.createNode(0);
        root.right = TreeNode.createNode(0);


        root.left.left = TreeNode.createNode(0);
        root.left.right = TreeNode.createNode(0);


        root.left.right.left = TreeNode.createNode(0);
        root.left.right.right = TreeNode.createNode(0);

        return root;
    }

    /**
     *        0
     *      /   \
     *     0     0
     *    / \
     *   0   0
     *  / \
     * 0   0
     *
     * [0,0,0,0,0,null,null,0,0]
     *
     * @return
     */
    private static TreeNode createTree5() {
        TreeNode root = TreeNode.createNode(0);
        root.left = TreeNode.createNode(0);
        root.right = TreeNode.createNode(0);


        root.left.left = TreeNode.createNode(0);
        root.left.right = TreeNode.createNode(0);


        root.left.left.left = TreeNode.createNode(0);
        root.left.left.right = TreeNode.createNode(0);

        return root;
    }

    /**
     * Serialize the tree into a string
     * @param node
     * @return
     */
    public static String serialize(TreeNode node) {
        StringBuilder buf = new StringBuilder();

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(node);

        Queue<String> outPutQueue = new LinkedList<>();
        outPutQueue.add(Integer.toString(node.val));

        // level by level traversal
        int level = 0;
        while (!queue.isEmpty()) {
            level++;
            int size = queue.size();
            int outputQueueSize = outPutQueue.size();

            for (int i = 1; i <= size; i++) {
                outputQueueSize--;
                TreeNode tmpNode = queue.poll();
                if (buf.length() > 0) {
                    buf.append(",");
                }
                buf.append(outPutQueue.poll());

                if (tmpNode.left != null) {
                    queue.offer(tmpNode.left);
                    outPutQueue.add(Integer.toString(tmpNode.left.val));
                } else {
                    outPutQueue.add("null");
                }

                if (tmpNode.right != null) {
                    queue.offer(tmpNode.right);
                    outPutQueue.add(Integer.toString(tmpNode.right.val));
                } else {
                    outPutQueue.add("null");
                }
            }

            while (outputQueueSize > 0) {
                buf.append(",");
                buf.append(outPutQueue.poll());
                outputQueueSize--;
            }
        }


        return buf.toString();
    }
}
