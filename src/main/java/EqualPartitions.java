import org.testng.Assert;

import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * Given an array of unsorted positive integers that can have duplicates,
 * determine if it is possible to split them into equal k partitions
 *
 * For example:
 *   input: {4,3,2,3,5,2,1} k = 4
 *
 *   buckets: {5}, {1,4}, {2,3}, {2,3}
 *
 * Approach
 * - backtracking and dynamic programming approach
 *
 * - first determine if the sum is divisible by k
 * - then figure out which number goes into which buckets
 *   - how to know which number combinations in which bucket
 *   - we don't know, therefore we try all possible ways
 * - things to note: each number can go into only 1 bucket
 *
 * - we know there are k buckets
 *   - fill one bucket at a time (subproblems)
 *   - when all the buckets are filled, the check to
 *
 * Resources:
 *  - https://leetcode.com/problems/partition-to-k-equal-sum-subsets
 */
public class EqualPartitions {
    public static void main(String[] args) {
        System.out.println("EqualPartitions.main");

        test(new int[] {4, 3, 2, 3, 5, 2, 1}, 4, true);
        test(new int[] {4, 3, 2, 3, 6, 2}, 4, false);
        test(new int[] {5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5}, 5, false);
    }

    private static void test(int[] input, int k, boolean expected) {
        System.out.println("\ninput = [" + Arrays.toString(input) + "]");

        boolean actual = equalPartitions(input, k);

        System.out.printf("expected: %b, actual: %b\n", expected, actual);

        Assert.assertEquals(actual, expected);
    }


    /**
     * Return true if the given input can be partitioned into k non-empty subsets
     * with equal sum.
     *
     * @param input
     * @param k
     * @return
     */
    private static boolean equalPartitions(int[] input, int k) {
        // validation on k
        // can't be 0 or negative
        // can't be greater than input.length

        if (k < 1 || k > input.length) {
            return false;
        }

        int total = IntStream.of(input).sum();
        int bucketSum = total / k;
        System.out.printf("total: %d, k: %d, bucketSum: %d\n",
                total, k, bucketSum);

        if ((total % k) != 0) {
            return false;
        }


        return canPartitionByNaiveApproach(input, 0, new int[k], bucketSum);
    }

    /**
     * This approach tries to place all the numbers into all buckets.
     * Then validate to see if bucket sums equal to bucketSum
     *
     * @param input
     * @param idx
     * @param buckets
     * @param bucketTargetSum
     * @return true if can partition, otherwise false
     */
    private static boolean canPartitionByNaiveApproach(int[] input,
                                                       int idx,
                                                       int[] buckets,
                                                       int bucketTargetSum) {

        // base case
        if (idx == input.length) {
            return areBucketValid(buckets, bucketTargetSum) ? true : false;
        } else {
            // the choices are different buckets
            int candidate = input[idx];
            for (int bucketIdx = 0; bucketIdx < buckets.length; bucketIdx++) {

                if (buckets[bucketIdx] + candidate <= bucketTargetSum) {
                    // make the choice
                    buckets[bucketIdx] += candidate;

                    if (canPartitionByNaiveApproach(input, idx+1,
                            buckets, bucketTargetSum)) {
                        return true;
                    }

                    // backtrack
                    buckets[bucketIdx] -= candidate;
                }
            }
            // if we get here, then it is not possible
            return false;
        }
    }

    private static boolean areBucketValid(int[] buckets, int bucketTargetSum) {
        for (int sum : buckets) {
            if (sum != bucketTargetSum) {
                return false;
            }
        }
        return true;
    }
}
