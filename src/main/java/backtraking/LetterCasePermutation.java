package backtraking;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string S, we can transform every letter individually to be lowercase or uppercase to create another string.
 * Return a list of all possible strings we could create.
 *
 * Examples:
 * Input: S = "a1b2"
 * Output: ["a1b2", "a1B2", "A1b2", "A1B2"]
 *
 * Input: S = "3z4"
 * Output: ["3z4", "3Z4"]
 *
 * Input: S = "12345"
 * Output: ["12345"]
 *
 * https://leetcode.com/problems/letter-case-permutation/
 */
public class LetterCasePermutation {

    public static void main(String[] args) {
        System.out.println("LetterCasePermutation.main");
        test("12345", 1);
        test("a1b2", 4);
        test("3z4", 2);
        test("t0d0", 4);
    }

    private static void test(String input, int expected) {

        System.out.println("\ninput = [" + input + "]");

        List<String> result = letterCasePermutation(input);

        System.out.printf("expected: %d, actual: %d\n",
                expected, result.size());

        for (String s : result) {
            System.out.println(s);
        }


        System.out.println("=====> lcpWithMutableDS <========");
        List<String> result2 = lcpWithMutableDS(input);

        System.out.printf("expected: %d, actual2: %d\n",
                expected, result2.size());

        for (String s : result2) {
            System.out.println(s);
        }
    }

    private static List<String> letterCasePermutation(String input) {
        List<String> result = new ArrayList<>();

        helper(input, 0, "", result);
        return result;
    }

    private static void helper(String input, int idx, String soFar,
                               List<String> coll) {

        // base case is when we get to the end of the string
        if (idx == input.length()) {
            coll.add(soFar);
            return;
        } else {

            char currChar = input.charAt(idx);
            if (Character.isAlphabetic(currChar)) {
                helper(input, idx + 1, soFar + Character.toLowerCase(currChar), coll);
                helper(input, idx + 1, soFar + Character.toUpperCase(currChar), coll);
            } else {
                helper(input, idx + 1, soFar + currChar, coll);
            }
        }
    }

    private static List<String> lcpWithMutableDS(String input) {
        List<String> result = new ArrayList<>();

        char[] buffer = new char[input.length()];

        helperWithMutableDS(input, 0, buffer, result);
        return result;
    }

    private static void helperWithMutableDS(String input, int idx, char[] buffer,
                                            List<String> coll) {

        if (idx == input.length()) {
            coll.add(new String(buffer));
            return;
        } else {
            char currChar = input.charAt(idx);
            if (Character.isAlphabetic(currChar)) {
                buffer[idx] = Character.toLowerCase(currChar);
                helperWithMutableDS(input, idx + 1, buffer, coll);
                buffer[idx] = Character.toUpperCase(currChar);
                helperWithMutableDS(input, idx + 1, buffer, coll);
            } else {
                buffer[idx] = currChar;
                helperWithMutableDS(input, idx + 1, buffer, coll);
            }
        }
    }

}
