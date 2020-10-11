package backtraking;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a string s, partition s such that every substring of the partition
 * is a palindrome.
 *
 * Return all possible palindrome partitioning of s.
 *
 * Input: "aab"
 * Output:
 * [
 *   ["aa","b"],
 *   ["a","a","b"]
 * ]
 *
 * https://leetcode.com/problems/palindrome-partitioning/
 *
 * 1st level: a, aa, aab
 * 2nd level: a, ab
 * 3rd level: b
 */
public class PalindromePartitioning {
    public static void main(String[] args) {
        System.out.println("PalindromePartitioning.main");

       /* testPalindrome("a");
        testPalindrome("aa");
        testPalindrome("aab");
        testPalindrome("civic");
        testPalindrome("civvic");*/

       //test("a");
       //test("aab");
        //test("civic");

        subStringRecursion("aab", 0, 0);
    }


    private static void test(String input) {
        System.out.printf("input: %s\n", input);

        List<List<String>> coll = new ArrayList<>();
        helper(input, 0, "", new ArrayList<>(), coll, 0);

        System.out.printf("========= Palindrome strings ===========");
        System.out.println("all: " + coll);
        for (List<String> str: coll) {
            System.out.printf("%s\n", str);
        }
    }

    private static void subStringRecursion(String s, int idx, int level) {
        if (idx == s.length()) {
            return;
        } else {
            for (int i = idx; i < s.length(); i++) {
                String subString = s.substring(idx, i+1);
                printWithLevel(level, subString);
                subStringRecursion(s, i+1, level+1);
            }
        }
    }

    /**
     * Comparing characters from the outer edge and move toward the middle
     *
     * Make to handle the case where the number of letters is odd and even
     * - length is 5 => 5/2 = 2
     * - length is 6 => 6/2 = 3
     *
     * @param str
     * @return
     */
    private static boolean isPalindrome(String str) {
        int i = 0;
        while (i <= str.length()/2) {
            if (str.charAt(i) != str.charAt(str.length()-1-i)) {
                return false;
            }
            i++;
        }
        return true;
    }

    private static void helper(String input, int idx, String soFar,
                               List<String> perString,
                               List<List<String>> coll, int level) {
        if (idx == input.length()) {
           coll.add(new ArrayList<>(perString));
        } else {
            for (int i = idx; i < input.length(); i++) {
                String soFarTemp = input.substring(idx, i+1);
                if (isPalindrome(soFarTemp)) {
                    perString.add(soFarTemp);
                    printWithLevel(level, soFarTemp);
                    helper(input, i + 1, soFarTemp, perString, coll, level + 1);
                    perString.remove(perString.size()-1);
                }
            }
        }
    }

    private static void printWithLevel(int level, String msg) {
        System.out.printf("%2d ", level);
        for (int i = 0; i < level; i++) {
            System.out.print(" ");
        }
        System.out.println(msg);
    }
}
