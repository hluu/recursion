package common;

public class ListNode {
    int val;
    public ListNode next;

    ListNode(int x) {
        val = x;
    }

    @Override
    public String toString() {
        return val + "->" + ((next != null) ? next.toString() : "null");
    }

    public static ListNode create(int val) {
        return new ListNode(val);
    }
}
