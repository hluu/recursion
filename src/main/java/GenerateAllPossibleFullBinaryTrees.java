import common.ListNodeUtil;
import common.Node;

import java.util.*;

/**
 * A full binary tree is a binary tree where each node has exactly 0 or 2 children.
 *
 * Return a list of all possible full binary trees with N nodes.
 *
 * Each element of the answer is the root node of one possible tree.
 *
 * Each node of each tree in the answer must have node.val = 0.
 *
 * You may return the final list of trees in any order.
 *
 * https://leetcode.com/problems/all-possible-full-binary-trees/
 *
 * Approach:
 *  - This is a combinatorial generation problem for a tree with left and right children
 *  - Give a n number of nodes how many different possible arrange of a full binary tree
 *  - Important constraint - each node must have either 2 children or 0
 *  - Base case: n = 1, there is only one tree
 *  - For each of the leaf node, there are 2 choices
 *    - 0 children
 *    - 2 children
 */
public class GenerateAllPossibleFullBinaryTrees {

    public static void main(String[] args) {
        System.out.println("GenerateAllPossibleFullBinaryTrees.main");

        test(1, 1);
        test(3, 1);
        test(5, 2);
        test(7, 5);
        test(21, 5);
        /*test(2, 0);

        test(4, 0);

        test(6, 0);
        test(7, 5);
        test(9, 14);*/
    }

    private static void printElapsedTime(String msg, long startTime, long endtime) {
        long elapsedTime = endtime - startTime;

        System.out.printf("%s took %d (ms)\n", msg, elapsedTime / 1000);
    }

    private static void test(int n, int expected) {
        System.out.println("\nn = [" + n + "]");

        long startTime = System.nanoTime();
        List<Node> result1 = recursion(n);
        long stopTime = System.nanoTime();
        printElapsedTime("recursion", startTime, stopTime);


        startTime = System.nanoTime();
        List<Node> result2 = recursionWithDP(n);
        stopTime = System.nanoTime();
        printElapsedTime("recursionWithDP", startTime, stopTime);

        System.out.printf("expected: %d, actual1: %d, actual2: %d",
                expected, result1.size(), result2.size());

        /*System.out.println("\n====== serialize trees =======");
        for (Node node : result) {
            System.out.println(ListNodeUtil.serialize(node));
        }*/
    }

    /**
     * The challenge here is to generate all possible trees with certain constraint,
     * which is the full binary tree.
     *
     * For a particular size of N, there are x possible tree variations.
     * - left:  generate(x) => m number of possible trees
     * - right: generate(y) => n number of possible trees
     * - at the parent node of left and right, the number of possible combinations
     *   is all possible combinations of left and right
     *   - and therefore we use two for loop to generate one combination
     *     - one left tree with all possible right trees
     *
     * The tree combination is the all the possible combinations of
     * each left and right children.
     *
     * Therefore at any particular node, to generate all combinations,
     * - create an instance of a node
     * - attach all possible combinations of left and right trees
     *
     * How to generate a tree for size of n?
     * - base case when n == 1, there is only single tree of size 1 node
     * - each valid tree must have odd number of nodes
     * - generate from the bottom up
     *
     * Borrow from - https://leetcode.com/problems/all-possible-full-binary-trees/discuss/426972/java-short-recursive
     *
     * Pattern:
     * - Give n nodes
     *   - left side has m node, then right side has n - 1 - m nodes
     *     - m goes from 1 to (n-1)
     * - generate the trees for left side, generate for the right side
     * - combine them to generate all possible combinations
     *
     * This generates a lot of duplicate tree of size n
     *
     *
     * @param n
     * @return List<Node> - number of tree with n nodes
     */
    private static List<Node> recursion(int n) {
        if (n == 1) {
            // base case
            return Arrays.asList(Node.createNode(0));
        } else {
            // what are the choices
            List<Node> tmpResult = new ArrayList<>();

            // why n+2 step? because use 2 nodes each time (one for left, one for right)
            for (int size = 1; size <= (n-1); size = size+2) {
                // (1, n-1-1), (2, n-1-2), (3, n-1-3)....
                // left side
                List<Node> leftSideTrees = recursion(size);

                // right side
                List<Node> rightSideTrees = recursion(n - 1 - size);

                // generate all combinations
                for (Node leftTree : leftSideTrees) {
                    for (Node rightTree : rightSideTrees) {
                        Node parent = Node.createNode(0);
                        parent.left = leftTree;
                        parent.right = rightTree;
                        tmpResult.add(parent);
                    }
                }
            }

            return tmpResult;
        }
    }

    private static List<Node> recursionWithDP(int n) {
        Map<Integer, List<Node>> cache = new HashMap<>();

        return recursionWithDPHelper(n, cache);
    }


    private static List<Node> recursionWithDPHelper(int n,
                                                    Map<Integer, List<Node>> cache) {

        if (cache.containsKey(n)) {
            return cache.get(n);
        }

        List<Node> result = null;
        if (n == 1) {
            // base case
            result = Arrays.asList(Node.createNode(0));
        } else {
            // what are the choices
            List<Node> tmpResult = new ArrayList<>();

            // why n+2 step? because use 2 nodes each time (one for left, one for right)
            for (int size = 1; size <= (n-1); size = size+2) {
                // (1, n-1-1), (2, n-1-2), (3, n-1-3)....
                // left side
                List<Node> leftSideTrees = recursionWithDPHelper(size, cache);

                // right side
                List<Node> rightSideTrees = recursionWithDPHelper(n - 1 - size, cache);

                // generate all combinations
                for (Node leftTree : leftSideTrees) {
                    for (Node rightTree : rightSideTrees) {
                        Node parent = Node.createNode(0);
                        parent.left = leftTree;
                        parent.right = rightTree;
                        tmpResult.add(parent);
                    }
                }
            }

            result = tmpResult;
        }

        cache.put(n, result);
        return result;

    }

}
