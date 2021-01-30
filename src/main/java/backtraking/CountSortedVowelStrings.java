package backtraking;

import org.testng.Assert;

import java.util.Arrays;

/**
 * Given an integer n, return the number of strings of length n that consist
 * only of vowels (a, e, i, o, u) and are lexicographically sorted.
 *
 * A string s is lexicographically sorted if for all valid i, s[i]
 * is the same as or comes before s[i+1] in the alphabet.
 *
 * Input: n = 1
 * Output: 5
 * Explanation: The 5 sorted strings that consist of vowels only are ["a","e","i","o","u"].
 *
 * Input: n = 2
 * Output: 15
 * Explanation: The 15 sorted strings that consist of vowels only are
 * ["aa","ae","ai","ao","au","ee","ei","eo","eu","ii","io","iu","oo","ou","uu"].
 * Note that "ea" is not a valid string since 'e' comes after 'a' in the alphabet.
 *
 * Example 3:
 *
 * Input: n = 33
 * Output: 66045
 */
public class CountSortedVowelStrings {
    public static void main(String[] args) {
        System.out.println("CountSortedVowelStrings.main");

        char[] vowels = new char[]{'a', 'e', 'i', 'o', 'u'};
        test(vowels, 1, 5);
        test(vowels, 2, 15);
        test(vowels, 3, 35);
        test(vowels, 4, 70);
        test(vowels, 5, 126);
        test(vowels, 6, 210);
        test(vowels, 7, 330);
        test(vowels, 33, 66045);

    }

    private static void test(char[] vowels, int n, int expected) {
        System.out.printf("vowels: %s, n: %d\n", Arrays.toString(vowels), n);

        int actual = countSortedVowelStr(vowels, n);
        System.out.printf("expected: %d, actual: %d\n", expected, actual);

        System.out.println("");

        Assert.assertEquals(actual, expected);
    }

    private static int countSortedVowelStr(char[] vowels,  int n) {
        int totalCount = countSortedVowelStrHelper(vowels, n, 0, new StringBuilder());
        return  totalCount;
    }

    private static int countSortedVowelStrHelper(char[] vowels, int n, int idx, StringBuilder buf) {

        if (buf.length()  == n) {
            //System.out.println(buf.toString());
            return 1;
        }
        int totalCnt = 0;
        for (int i = idx; i < vowels.length; i++) {
            if ((buf.length() == 0)  || vowels[i] >= buf. charAt(buf.length()-1)) {
                buf.append(vowels[i]);
                totalCnt += countSortedVowelStrHelper(vowels, n, i, buf);
                buf.deleteCharAt(buf.length() - 1);
           }
        }

        return totalCnt;
    }
}
