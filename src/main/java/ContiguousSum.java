import org.testng.Assert;

import java.util.Arrays;

/**
 * Given and integer array count number of contiguous
 * subarrays which sums up to K
 * input:
 * [0,0] K = 0
 * output:
 * 3
 * explanation:
 * [0]
 * [0,0]
 * [0]
 *
 * input:
 * [1,1,1] K= 2
 * output:
 * 2
 * explanation:
 * [1,1]
 * [1,1]
 *
 * input:
 * [0,1,2,-3,3] K=3
 * output:
 * 5
 *
 * [0, 1, 3, 0, 3]
 * [3, 3, 2, 0, 3]
 */
public class ContiguousSum {
    public static void main(String[] args) {
        System.out.println(ContiguousSum.class.getName());

        test(new int[] {0,0}, 0, 3);
        test(new int[] {1,1,1}, 2, 2);
        test(new int[] {0,1,2,-3,3}, 3, 5);
    }

    private static void test(int[] arr, int ts, int expected) {
        System.out.println("\narr: " + Arrays.toString(arr));

        int actual = helper(arr, ts);
        System.out.printf("expected: %d, actual: %d\n", expected, actual);

        Assert.assertEquals(actual, expected);
    }

    private static int helper(int[] arr, int targetSum) {
        int count = 0;
        for (int idx = 0; idx < arr.length; idx++) {
            int tmpSum  = 0;
            for (int k = idx; k < arr.length; k++) {
                tmpSum += arr[k];
                if (tmpSum == targetSum) {
                    count++;
                }
            }
        }
        return count;
    }
}
