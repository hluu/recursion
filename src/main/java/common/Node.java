package common;

public class Node {
    public int value;
    public Node left;
    public Node right;

    public static Node createNode(int v) {
        Node n = new Node();
        n.value = v;

        return n;
    }

    @Override
    public String toString() {
        return value + "";
    }
}
