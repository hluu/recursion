package common;

public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public static TreeNode createNode(int v) {
        TreeNode n = new TreeNode();
        n.val = v;

        return n;
    }

    @Override
    public String toString() {
        return val + "";
    }
}
