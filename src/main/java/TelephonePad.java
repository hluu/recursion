import org.testng.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Letter Combinations of a Phone Number
 *
 * Given a string containing digits from 2-9 inclusive, return all possible letter
 * combinations that the number could represent. Return the answer in any order.
 *
 * A mapping of digit to letters (just like on the telephone buttons) is given below.
 * Note that 1 does not map to any letters.
 *
 * https://leetcode.com/problems/letter-combinations-of-a-phone-number/
 *
 * Input: digits = "23"
 * Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
 *
 * Input: digits = ""
 * Output: []
 *
 * Input: digits = "2"
 * Output: ["a","b","c"]
 */
public class TelephonePad {
    public static void main(String[] args) {
        System.out.println("TelephonePad.main");

        test("", new ArrayList<>());
        test("2", Arrays.asList("a","b","c"));
        test("23", Arrays.asList("ad","ae","af", "bd","be", "bf","cd","ce","cf"));
        test("234", Arrays.asList("adg","adh", "adi", "aeg","aeh","aei","afg", "afh", "afi",
                                         "bdg","bdh", "bdi", "beg","beh","bei","bfg", "bfh", "bfi",
                                         "cdg","cdh", "cdi", "ceg","ceh","cei","cfg", "cfh", "cfi"));
    }

    private static void test(String digits, List<String> expected) {
        System.out.printf("test: digits: %s\n", digits);

        List<String> actual = letterCombinations(digits);

        System.out.println(actual);

        System.out.printf("expected: %s actual: %s\n", expected, actual);

        Assert.assertEquals(expected.size(), actual.size());
    }


    private static List<String> letterCombinations(String digits) {
        String[][] telPad = {
                {},
                {},
                {"a","b","c"},
                {"d","e","f"},
                {"g","h","i"},
                {"j","k","l"},
                {"m","n","o"},
                {"p","q","r","s"},
                {"t","u","v"},
                {"w","x","y","z"},
        };

        List<String> result = new ArrayList<String>();
        if (digits == null || digits.isEmpty()) {
            return result;
        }
        helper(digits.toCharArray(), 0, telPad, new StringBuilder(), result);
        return result;
    }

    private static void helper(char[] digits, int idx, String[][] telPad, StringBuilder soFar,
                               List<String> result) {
        if (idx == digits.length) {      // base case
            result.add(soFar.toString());
        } else {
            int digit = Integer.parseInt(Character.toString(digits[idx]));
            String[] letters = telPad[digit];
            for (String letter : letters) {
                soFar.append(letter);
                helper(digits, idx+1, telPad, soFar, result);
                soFar.deleteCharAt(soFar.length()-1);
            }
        }
    }
}
