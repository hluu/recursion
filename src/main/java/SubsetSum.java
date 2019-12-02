import org.testng.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Print all subsets with sum equals to k
 * - all integers
 * - all integers with 0
 * - positive and negative integers
 *
 * What is the approach?
 * - building on top of what we know
 * - when it is best to compute the subset sum?
 *   - at the leaf?
 *   - or on the fly?
 *   - what are the pros and cons of each approach?
 *
 */
public class SubsetSum {
    public static void main(String[] args) {
        System.out.println("SubsetSum.main");

        test(new int[] {1,2,3,4,5}, 4);
        testCount(new int[] {1,2,3,4,5}, 4,2);
        testCount(new int[] {1,2,3,4,5}, 5,3);
        testCount(new int[] {1,2,3,4,5}, 6,3);
        testCount(new int[] {1,2,3,4,5}, 7,3);
        testCount(new int[] {1,2,3,4,5}, 8,3);
        testCount(new int[] {1,2,3,4,5}, 9,3);
        testCount(new int[] {1,2,3,4,5}, 10,3);
        testCount(new int[] {1,2,3,4,5}, 15,1);
    }

    private static void test(int[] input, int target) {
        System.out.printf("input: %s, target: %d\n", Arrays.toString(input),
                target);

        System.out.println("------ output printSubsetSums -------");
        printSubsetSums(input, 0, new ArrayList<>(), target);

        System.out.println("------ output printSubsetSumWithPruning -------");
        printSubsetSumWithPruning(input, 0, new ArrayList<>(), 0, target);

    }

    private static void testCount(int[] input, int target, int expected) {
        System.out.printf("\ntest count input: %s, target: %d\n", Arrays.toString(input),
                target);

        int actual = countSubsetSumWithPruning(input, 0, 0, target);

        System.out.printf("expected: %d, actual: %d\n", expected, actual);

        Assert.assertEquals(actual, expected);
    }

    private static void printSubsetSums(int[] input, int idx,
                                        List<Integer> path, int target) {

        // base case
        if (idx == input.length) {
            //int sumTmp = path.stream().reduce(0, (a, b) -> a + b);

            int sumTmp = path.stream().mapToInt(Integer::intValue).sum();
            if (sumTmp == target) {
                System.out.println(path);
            }
        } else {
            // exclude
            printSubsetSums(input, idx+1, path, target);

            // include, why do we need to create a new list?
            // because the newPath is passed down to another recursion path
            // that includes the input[idx]
            //List<Integer> newPath = new ArrayList<>(path);

            path.add(input[idx]);
            printSubsetSums(input, idx+1, path, target);

            path.remove(path.size()-1);
        }
    }

    private static void printSubsetSumWithPruning(int[] input, int idx,
                                      List<Integer> path, int sumSoFar,
                                                  int target) {

        // pruning if needed
        if (sumSoFar > target) {
            return;
        }

        // if match anytime then print and return
        if (sumSoFar == target) {
            System.out.println(path);
            return;
        }

        // if reach leaf node, then return
        if (idx == input.length) {
            return;
        } else {
            // exclude
            printSubsetSumWithPruning(input, idx+1, path,
                    sumSoFar, target);

            // include, why do we need to create a new list?
            // because the newPath is passed down to another recursion path
            // that includes the input[idx]
            //List<Integer> newPath = new ArrayList<>(path);
            //newPath.add(input[idx]);

            path.add(input[idx]);
            printSubsetSumWithPruning(input, idx+1, path,
                    sumSoFar+ input[idx], target);
            path.remove(path.size()-1);
        }
    }


    /**
     * It take a bit of internal visualization to digest how the
     * return count is added up.
     *
     * - could maintain a counter to keep track and maybe more intuitive to understand
     *
     * @param input
     * @param idx
     * @param sumSoFar
     * @param target
     * @return
     */
    private static int countSubsetSumWithPruning(int[] input, int idx,
                                                  int sumSoFar, int target) {

        // if match anytime then print and return
        if (sumSoFar == target) {
            return 1;
        }

        // if reach leaf node, then return
        if (idx == input.length || sumSoFar > target) {
            return 0;
        } else {
            // exclude
            return countSubsetSumWithPruning(input, idx+1, sumSoFar, target)
                    + countSubsetSumWithPruning(input, idx+1,
                    sumSoFar+ input[idx], target);
        }
    }

}
