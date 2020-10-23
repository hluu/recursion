import org.testng.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given an array of digits, return all possible combinations
 * of digits that are neighbors of each of those digits.
 *
 * Examples:
 *
 * Input: digits = []
 * Output = []
 *
 * Input: digits = [2,3]
 * Output: ["12","16","31","36","51","56"]
 *
 * Input: digits = [5]
 * Output: ["2","4","6",”8”]
 */
public class TelephoneNeighbors {
    public static void main(String[] args) {
        System.out.println("TelephoneNeighbors.main");

        test(new int[]{2}, Arrays.asList("1","3","5"));
        test(new int[]{5}, Arrays.asList("2","4","6","8"));
        test(new int[]{2,3}, Arrays.asList("12","16","31","36","51","56"));
        test(new int[]{2,5}, Arrays.asList("12", "14", "16", "18", "32", "34", "36", "38", "52", "54", "56", "58"));
        test(new int[]{1,2,7}, Arrays.asList("214", "218", "234", "238", "254", "258", "414", "418", "434", "438", "454", "458"));
    }

    private static void test(int[] digits, List<String> expected) {
        System.out.printf("\ntest: digits: %s\n", Arrays.toString(digits));

        List<String> actual = digitCombinations(digits);

        System.out.printf("expected: %s actual: %s\n", expected, actual);

        Assert.assertEquals(expected.size(), actual.size());
    }


    private static List<String> digitCombinations(int[] digits) {
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
        helper(digits, 0, neighbors, new StringBuilder(), result);
        return result;
    }

    private static void helper(int[] digits, int idx, int[][] neighborMap, StringBuilder soFar,
                               List<String> result) {
        if (idx == digits.length) {      // base case
            result.add(soFar.toString());
        } else {
            int[] neighbors = neighborMap[digits[idx]];
            for (int neighbor : neighbors) {
                soFar.append(neighbor);
                helper(digits, idx+1, neighborMap, soFar, result);
                soFar.deleteCharAt(soFar.length()-1);
            }
        }
    }
}
