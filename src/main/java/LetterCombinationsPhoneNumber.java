import java.util.*;

/**
 * Given a string containing digits from 2-9 inclusive, return all possible letter combinations
 * that the number could represent. Return the answer in any order.
 *
 * A mapping of digit to letters (just like on the telephone buttons) is given below.
 *
 * Note that 1 does not map to any letters.
 *
 * https://leetcode.com/problems/letter-combinations-of-a-phone-number/
 *
 * Input: digits = "23"
 * Output: ["ad","ae","af","bd","be","bf","cd","ce","cf"]
 */
public class LetterCombinationsPhoneNumber {
    public static void main(String[] args) {
        System.out.println("LetterCombinationsPhoneNumber.main");

        test("23");
    }

    private static void test(String digits) {
        System.out.printf("digits: %s\n", digits);

        List<String> result = new ArrayList<>();
        Map<Character, List<Character>> map = new HashMap<>();
        map.put('2', Arrays.asList('a','b','c'));
        map.put('3', Arrays.asList('d','e','f'));
        map.put('4', Arrays.asList('g','h','i'));
        map.put('5', Arrays.asList('j','k','l'));
        map.put('6', Arrays.asList('m','n','o'));
        map.put('7', Arrays.asList('p','q','r','s'));
        map.put('8', Arrays.asList('t','v','u'));
        map.put('9', Arrays.asList('w','x','y','z'));

        helper(map, digits, 0, new StringBuilder(), result);

        for (String combo : result) {
            System.out.println(combo);
        }
    }
    private static void helper(Map<Character, List<Character>> numberToLetters, String digits,
                               int idx, StringBuilder buf, List<String> collector) {
        if (idx == digits.length()) {
            collector.add(buf.toString());
        } else {
            for (Character letter : numberToLetters.get(digits.charAt(idx))) {
                buf.append(letter);
                helper(numberToLetters, digits, idx+1, buf, collector);
                buf.deleteCharAt(buf.length()-1);
            }
        }
    }

}
