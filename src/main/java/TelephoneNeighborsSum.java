import org.testng.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an array of digits, print all the combinations of digits that are neighbors of each of
 * those digits when their sum is equal to k.
 *
 * Examples:
 *
 * Input: digits = [2,3]  and k = 9
 * Output: ["36"]
 *
 * Input: digits = [1,2,7] and k = 9
 * Output: ["234","414"]
 */
public class TelephoneNeighborsSum {
    public static void main(String[] args) {
        System.out.println("TelephoneNeighbors.main");
        test(new int[] {2,3}, 9, Arrays.asList("36"));
        test(new int[] {1,2,7}, 9, Arrays.asList("234", "414"));
    }

    private static void test(int[] digits, int k, List<String> expected) {
        System.out.printf("\ntest: digits: %s k: %d\n", Arrays.toString(digits), k);

        List<String> actual = digitCombinationSum(digits, k);

        System.out.printf("expected: %s actual: %s\n", expected, actual);

        Assert.assertEquals(expected.size(), actual.size());
    }

    private static List<String> digitCombinationSum(int[] digits, int k) {
        int[][] neighbors = {
                {8},      // 0
                {2,4},    // 1
                {1,3,5},  // 2
                {2,6},    // 3
                {1,5},    // 4
                {2,4,6,8},// 5
                {3,5},    // 6
                {4,8},    // 7
                {5,7,9,0},// 8
                {6,8},    // 9
        };

        List<String> result = new ArrayList<String>();
        if (digits == null || digits.length == 0) {
            return result;
        }
        helper(digits, 0, neighbors, k, new StringBuilder(), result);
        return result;
    }

    private static void helper(int[] digits, int idx, int[][] neighborMap, int targetSum,
                               StringBuilder soFar, List<String> result) {

        if (targetSum < 0) {
           // System.out.printf("%d < 0  returning \n", targetSum);
            return;
        }

        if (idx == digits.length) {
            if (targetSum == 0) {
                result.add(soFar.toString());
            }
            return;
        } else {
            int[] neighbors = neighborMap[digits[idx]];
            for (int neighbor : neighbors) {
                soFar.append(neighbor);
                helper(digits, idx+1, neighborMap, targetSum-neighbor, soFar, result);
                soFar.deleteCharAt(soFar.length()-1);
            }
        }
    }
}
