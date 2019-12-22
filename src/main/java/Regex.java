import org.testng.Assert;

/**
 * Given a string and a pattern, perform regex matching and return either true or false
 *
 * Simplify the problem by have 1 pattern '*'
 *
 * Example:
 *   String: "abcaad"
 *   Pattern: "ab*d*"
 *
 *
 * Approach:
 * - using two pointers to traverse both input and regex
 * - cases to consider
 * - when input[i] == regex[j] then advance both pointers
 * - when regex[j] == '*' then we have 2 choices
 *   - matching '*' with 1 or more characters by advancing the i pointer by 1
 *   - matching '*' with 0 or more characters by advancing the j pointer by 1
 *
 *
 * Edge case:
 * - when pattern has '*' at the of the regex like "ab*d*"
 */
public class Regex {
    public static void main(String[] args)  {
        System.out.println("Regex.main");

        test("abc", "abc", true);
        test("abc", "*", true);
        test("abc", "a*", true);
        test("abc", "a*c", true);
        test("abc", "a*c*", true);
        test("abdc", "a*c", true);
        test("abcaad", "ab*d*", true);
        test("abc", "abcde", false);
        test("abc", "de", false);
        test("abcaad", "ab*f*", false);
    }


    private static void test(String input, String pattern, boolean expected) {
        System.out.printf("\ninput: %s, pattern: %s\n", input, pattern);

        boolean actual = regex(input, pattern);

        System.out.printf("expected: %b, actual: %b\n", expected, actual);

        Assert.assertEquals(actual, expected);
    }

    private static boolean regex(String input, String pattern) {
        return regexHelper(input, pattern, 0, 0);
    }

    private static boolean regexHelper(String input, String pattern,
                                       int inputIdx,
                                       int patIdx) {

        // consider the cases where the pattern is shorter than input
        // and when the patter is longer than the input and ends with star
        if (inputIdx >= input.length() && patIdx >= pattern.length()) {
            return true;
        }

        // the index of the input could exceed the input length
        if (inputIdx > input.length()) {
            return false;
        }


        // this check below will cause the result to be wrong, why?
       /* if (patIdx >= input.length()) {
            return false;
        }*/


        if (inputIdx < input.length() &&
                (input.charAt(inputIdx) == pattern.charAt(patIdx))) {
            return regexHelper(input, pattern, inputIdx+1, patIdx+1);

        }

        if (pattern.charAt(patIdx) == '*') {
            // include the star, so advance the input index only
            // this will take care of matching 1,2,3 or n characters
            // for that star, eventually inputIdx will reach the end of the
            // input string - that is one path
            boolean excludeResult = regexHelper(input, pattern, inputIdx+1, patIdx);

            if (excludeResult) {
                return excludeResult;
            }

            // exclude the star, so advance both indexes
            return regexHelper(input, pattern, inputIdx, patIdx+1);
        }

        return false;



    }



}
