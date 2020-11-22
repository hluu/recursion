package backtraking;

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
        System.out.println("backtraking.EqualPartitions.main");

        test(new int[] {4,3,2,3,5,2,1}, 4, true);
        //test(new int[] {1, 1, 3, 2, 2}, 3, true);
        //test(new int[] {4, 4, 4, 4, 4}, 4, false);
       // test(new int[] {4, 3, 2, 3, 5, 2, 1}, 4, true);
       // test(new int[] {4, 3, 2, 3, 6, 2}, 4, false);
       // test(new int[] {5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5, 5}, 5, false)//test(new int[] {605,454,322,218,8,19,651,2220,175,710,2666,350,252,2264,327,1843},
        //        4,  true);
    }

    private static void test(int[] input, int k, boolean expected) {
        System.out.println("\ninput = [" + Arrays.toString(input) + "]");

        long startTime = System.nanoTime();
        boolean actual1 = equalPartitions(input, k);
        long endTime = System.nanoTime();

        System.out.printf("equalPartitions time: %d (ms)\n",
                (endTime-startTime)/100);

        System.out.println();
        startTime = System.nanoTime();
        boolean actual2 = equalPartitionsByBucket(input, k);
        endTime = System.nanoTime();

        System.out.printf("equalPartitionsByBucket time: %d (ms)\n",
                (endTime-startTime)/100);


        System.out.printf("expected: %b, actual1: %b, actual2: %b\n",
                expected, actual1, actual2);

        Assert.assertEquals(actual1, expected);
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

        // base case - when there are no more subproblems
        if (idx == input.length) {
            return areBucketValid(buckets, bucketTargetSum) ? true : false;
        } else {
            // the choices are different buckets
            int candidate = input[idx];
            // will try this candidate in all possible buckets
            for (int bucketIdx = 0; bucketIdx < buckets.length; bucketIdx++) {

                if (buckets[bucketIdx] + candidate <= bucketTargetSum) {
                    // make the choice
                    buckets[bucketIdx] += candidate;

                    // backtracking right here
                    if (canPartitionByNaiveApproach(input, idx+1,
                            buckets, bucketTargetSum)) {
                        return true;
                    }

                    // undo
                    System.out.println("backtrack: " + Arrays.toString(buckets));
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

    private static boolean equalPartitionsByBucket(int[] input, int k) {
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


        return canPartitionByBucket(input, 0, k, 0,  bucketSum,
                new boolean[input.length]);
    }

    /**
     * This approach tries to fill each bucket at a time before moving on to
     * another bucket
     * -  use an additional data structure to keep track of which numbers have been
     *    used already
     *
     * Resources:
     *  - https://github.com/bephrem1/backtobackswe/blob/master/Dynamic%20Programming%2C%20Recursion%2C%20%26%20Backtracking/PartitionIntoKEqualSumSubsets/PartitionIntoKEqualSumSubsets.java
     *
     *
     * @param inputs
     * @param bucketNo
     * @param bucketSumSoFar
     * @param bucketTargetSum
     * @return
     */
    private static boolean canPartitionByBucket(int[] inputs,
                                                int startIdx,
                                                int bucketNo,
                                                int bucketSumSoFar,
                                                int bucketTargetSum,
                                                boolean[] visited) {

        // if all buckets are filled with sum equals to bucketTargetSum
        // then we are good
        if (bucketNo == 0) {
            return true;
        }

        if (bucketSumSoFar == bucketTargetSum) {
            // notice we reset bucketSumSoFar to 0 when moving on to another
            // bucket
            return canPartitionByBucket(inputs,  0, bucketNo-1, 0,
                    bucketTargetSum, visited);
        }

        // choices - try all possible numbers for the current bucket
        for (int idx = startIdx; idx < inputs.length; idx++) {

            if (!visited[idx] && ((bucketSumSoFar + inputs[idx]) <= bucketTargetSum)) {
                visited[idx] = true;

                // since bucketSumSoFar is an intermediate variable,
                // no need to back track it
                if (canPartitionByBucket(inputs, startIdx+1, bucketNo,
                        bucketSumSoFar + inputs[idx],
                        bucketTargetSum, visited)) {
                    return true;
                }

                visited[idx] = false;
            }
        }

        return false;
    }
}
