import common.ListNode;

public class ReverseLinkedList {
    public static void main(String[] args) {
        System.out.println("ReverseLinkedList.main");


        test(createLL1());
    }

    private static void test(ListNode head) {
        System.out.println("===> original list");
        System.out.println(head);

        System.out.println("===> after reverse recursively");
        System.out.println(reverseCursively(head));

    }

    /**
     * A two step process
     * - find out the tail
     * - reverse the next pointer
     *
     * @param head
     * @return
     */
    private static ListNode reverseCursively(ListNode head) {
        if (head == null) {
            // validating arguments
            return head;
        }

        ListNode newHead = null;

        ListNode tmp = head;
        while (tmp.next != null) {
            tmp = tmp.next;
        }
        newHead = tmp;

        reverseCursivelyHelper(head);

        // reverse the link for the last node of new list
        head.next = null;

        return newHead;
    }

    /**
     * Go all the way to the last node
     * - base case: stop when reach null
     * - once it comes back, swap next pointer if next is not null
     *
     * @param node
     * @return
     */
    private static ListNode reverseCursivelyHelper(ListNode node) {
        if (node == null) {
            return null;
        }

        ListNode next = reverseCursivelyHelper(node.next);

        if (next != null) {
            next.next = node;
        }

        return node;
    }

    private static ListNode createLL1() {
        ListNode root = ListNode.create(1);

        root.next = ListNode.create(2);
        root.next.next = ListNode.create(3);
        root.next.next.next = ListNode.create(4);
        root.next.next.next.next = ListNode.create(5);

        return root;
    }
}
