package backtraking;

import java.util.ArrayList;
import java.util.List;

/**
 * Given a number n, generate all the strings with matching pairs of parentheses
 * consisting of only open and closed parentheses that are balanced.
 *
 * Balanced here means what you're likely very familiar with as a coder:
 * every open parenthesis must have a matching closed one and they must
 * be correctly nested. More accurately defined, a balanced string of
 * length 2n2n contains nn instances of '(' and nn instances of ')',
 * and every prefix of the string must contain at least as many open
 * parentheses as closed ones.
 *
 * For example, '(())' and '()()' are both balanced but ')(()' and '()))' are not.
 * For example:
 * - n = 1, output = {"()"}
 * - n = 2, output = {"(())", "()()"}
 * - n = 3, output = {"((()))", "(()())", "(())()", "()(())", "()()()"}
 *
 * Approach:
 * - String length is n*2
 * - equal number of open parentheses and close parentheses
 * - order matters
 * - number of close parentheses must be greater than or equal to close parentheses
 * - a string must start with '(' and must end at some point with ')'
 *
 * Resources:
 * -https://sahandsaba.com/interview-question-generating-all-balanced-parentheses.html
 * - https://leetcode.com/problems/generate-parentheses/
 */
public class GenerateParentheses {
    public static void main(String[] args) {
        System.out.println("backtraking.GenerateParentheses.main");

        for (int i = 1; i <= 3; i++) {
            test(i);
        }

    }

    private static void test(int n) {
        System.out.println("\nn = [" + n + "]");

        List<String> collector = new ArrayList<>();

        generateParens(n, n, "", collector);

        System.out.println("===> output generateParens <===");
        for (String s : collector) {
            System.out.println(s);
        }

        List<String> collector2 = new ArrayList<>();
        generateWithRemainParentheses(n, n, new StringBuilder(), collector2);
        System.out.println("===> output generateWithRemainParentheses <===");
        for (String s : collector2) {
            System.out.println(s);
        }

        List<String> collector3 = new ArrayList<>();
        generateWithUsedParentheses(n, 0, 0, new StringBuilder(), collector2);
        System.out.println("===> output generateWithUsedParentheses <===");
        for (String s : collector2) {
            System.out.println(s);
        }

    }

    /**
     * This one uses immutable data structure
     * @param leftParen
     * @param rightParen
     * @param soFar
     * @param coll
     */
    private static void generateParens(int leftParen, int rightParen,
                                       String soFar,
                                       List<String> coll) {

        if (rightParen == 0) {
            coll.add(soFar);
            return;
        } else {
            if (leftParen > 0) {
                generateParens(leftParen-1, rightParen,
                        soFar + "(", coll);
            }

            if (rightParen > leftParen) {
                generateParens(leftParen, rightParen-1,
                        soFar + ")", coll);
            }
        }
    }


    /**
     * This approach does the count down of the open and closed parentheses.
     *
     * The lCnt and rCnt represent the remaining open and closed parentheses
     *
     * @param lCnt
     * @param rCnt
     * @param soFar
     * @param coll
     */
    private static void generateWithRemainParentheses(int lCnt, int rCnt,
                                                                StringBuilder soFar,
                                                                List<String> coll) {

        // backtracking case
        // 1) too many left or right parentheses
        // 2) number of ')' is more than number of ')'
        // the statement below could be if we didn't check for lCnt > 0 before recurse
        // if (lCnt > rCnt || lCnt < 0 || rCnt < 0)

        if (lCnt > rCnt) {
            return;
        }

        // base cases
        if (lCnt == 0 && rCnt == 0) {
            coll.add(soFar.toString());
            return;
        }

        if (lCnt > 0) {
            // adding ( until can't do any more
            soFar.append('(');
            generateWithRemainParentheses(lCnt - 1, rCnt, soFar, coll);
            soFar.deleteCharAt(soFar.length() - 1);
        }

        if (rCnt > 0) {
            soFar.append(')');
            generateWithRemainParentheses(lCnt, rCnt - 1, soFar, coll);
            soFar.deleteCharAt(soFar.length() - 1);
        }
    }

    /**
     * This approach counts up from 1 to n.  lCnt and rCnt represent how many open and close
     * parentheses have been used so far. We need to compare with n
     *
     * @param lCnt
     * @param rCnt
     * @param soFar
     * @param coll
     */
    private static void generateWithUsedParentheses(int n, int lCnt, int rCnt,
                                                    StringBuilder soFar,
                                                    List<String> coll) {

        // backtracking case
        // 1) too many left or right parentheses
        // 2) number of ')' is more than number of ')'
        // the statement below could be if we didn't check for lCnt > 0 before recurse
        // if (lCnt > rCnt || lCnt < 0 || rCnt < 0)

        if (lCnt < rCnt) {
            return;
        }

        // base cases
        if (lCnt == 0 && rCnt == 0) {
            coll.add(soFar.toString());
            return;
        }

        if (lCnt < n ) {
            // adding ( until can't do any more
            soFar.append('(');
            generateWithUsedParentheses(n, lCnt + 1, rCnt, soFar, coll);
            soFar.deleteCharAt(soFar.length() - 1);
        }

        if (rCnt < n) {
            soFar.append(')');
            generateWithUsedParentheses(n, lCnt, rCnt + 1, soFar, coll);
            soFar.deleteCharAt(soFar.length() - 1);
        }
    }
}
