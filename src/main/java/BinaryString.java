import org.testng.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Given n, print all the binary strings of length n
 *
 * input: n = 3
 * output: 000, 001, 010, 011, 100, 101, 110, 111  ==> 2^n strings
 *
 * Approaches;
 * - iterator from 0 to 2^3 and convert each digit to binary string
 *
 * - recursion
 *   - BFS
 *     for each of the string returned by worker
 *      - create 2 more strings
 *        - append with 0 for the first one
 *        - append with 1 for the second one
 *   - DFS
 *     - build the binary string along the way
 *     - at each node extend the string by branching 2 ways
 *     - when it reach the bottom then print it out
 *
 *
 */
public class BinaryString {
    public static void main(String[] args) {
        System.out.println("BinaryString.main");

        test(3);
    }

    
    private static void test(int n) {
        System.out.println("n = [" + n + "]");

        System.out.println("====> BFS <=====");
        List<String> result1 = bottomUpBinaryString(n);
        
        for (String str : result1) {
            System.out.println("str = " + str);
        }

        System.out.println("====> DFS <=====");
        List<String> result2 = topDownBinaryString(n);

        for (String str : result2) {
            System.out.println("str = " + str);
        }

        Assert.assertEquals(result1.size(), result2.size());

        System.out.println("====> Mutable Ds <=====");
        mutableDS(n);
    }

    /**
     * Manager does a lot more work than subordinates.
     *
     * Space: O(2^n) because of building the result once
     * pop backup the stack
     *
     * @param n
     * @return
     */
    private static List<String> bottomUpBinaryString(int n) {
        if (n == 1) {
            return Arrays.asList("0","1");
        } else {
            List<String> tmp = bottomUpBinaryString(n-1);
            List<String> result = new ArrayList<>();
            for (String str : tmp) {
                result.add(str + "0");
                result.add(str + "1");
            }

            return result;
        }
    }

    /**
     * Build the result as the recursion travels down
     * to the stack
     *
     * @param n
     * @return
     */
    private static List<String> topDownBinaryString(int n) {
        List<String> collector = new ArrayList<>();

        topDownBinaryStringHelper("", n, collector);

        return collector;
    }

    /**
     * Perform DFS and build the binary string along the way.
     * Collect it when reach base case.
     *
     * The result is built as recursion goes deeper until the
     * leaf node.
     *
     * This is a top down approach.
     *
     * @param soFar
     * @param n
     * @param coll
     */
    private static void topDownBinaryStringHelper(String soFar, int n, List<String> coll) {
        if (n == 0) {
            coll.add(soFar);
        } else {
            topDownBinaryStringHelper(soFar + "0", n-1, coll);
            topDownBinaryStringHelper(soFar + "1", n-1, coll);
        }
    }

    /**
     * How would one solve this problem using iteration?
     *
     *
     * @param n
     * @return
     */
    private static List<String> binaryStringUsingIteration(int n) {
        // we know there will be 2^n binary strings
        // could use 0 or 1 bit as we generate a string

        return Collections.emptyList();

    }

    private static void mutableDS(int numDigits) {

        mutableDSHelper(numDigits, new ArrayList<Integer>());
    }

    private static void mutableDSHelper(int digit, List<Integer> soFar) {
        if (digit == 0) {
            System.out.println(soFar);
        } else {
            soFar.add(0);
            mutableDSHelper(digit-1, soFar);
            soFar.remove(soFar.size()-1);

            soFar.add(1);
            mutableDSHelper(digit-1, soFar);
            soFar.remove(soFar.size()-1);

        }
    }

}
