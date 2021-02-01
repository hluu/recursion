package backtraking;

import org.testng.Assert;

/**
 * https://leetcode.com/problems/backspace-string-compare/
 *
 * Walk the string from the back
 *  - when see #, count how many
 *  - back that many characters and replace with #
 *  - be careful of reaching the front
 *  - then compare two char arrays
 */
public class BackspaceStringCompare {
    public static void main(String[] args) {
        System.out.println("BackspaceStringCompare.main");

        test("ab#c", "ad#c", true);
        test("ab##", "c#d#", true);
        test("a##c", "#a#c", true);
        test("a#c", "b", false);
        test("###", "", true);
        test("###", "#", true);
        test("a###", "a#", true);
        test("###a", "#a", true);
    }

    private static void test(String s, String t, boolean expected) {
        System.out.printf("s: %s, t: %s\n", s, t);

        boolean actual = bsc(s,t);

        System.out.printf("expected: %b, actual: %b\n", expected, actual);

        Assert.assertEquals(actual, expected);
    }

    private static boolean bsc(String s, String t) {
        StringBuilder sb1 = transform(s);
        StringBuilder sb2 = transform(t);

        if (sb1.length() != sb2.length()) {
            return false;
        }

        for (int i = 0; i < sb1.length(); i++) {
            if (sb1.charAt(i) != sb2.charAt(i)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Iterate through the input
     * - copy over to  sb if not #
     * - remove previous if # (when sb has content)
     * @param input
     * @return
     */
    private static StringBuilder transform(String input) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i  < input.length();  i++) {
            char currChar  =  input.charAt(i);
            if (currChar == '#') {
                if (sb.length() >  0) {
                    sb.deleteCharAt(sb.length()-1);
                }
            } else {
                sb.append(currChar);
            }
        }
        return sb;
    }
}
