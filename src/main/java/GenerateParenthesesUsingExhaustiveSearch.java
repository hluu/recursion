import common.ArrayUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * This class demonstrates the progression of applying the combinatorial template
 * - first start with just generate all possible combinations
 * - then apply the constraint
 *
 *
 * Given n pairs of parentheses, write a function to generate all combinations of well-formed parentheses.
 *
 * For example, given n = 3, a solution set is:
 *
 * [
 *   "((()))",
 *   "(()())",
 *   "(())()",
 *   "()(())",
 *   "()()()"
 * ]
 *
 * Constraints:
 * - what does well-formed actually mean?
 *   - number of left paran must be less than or equal to number of right paran
 *     - means can't be > than
 *     - this suggest that we need to keep track of them separately
 *
 *
 * https://leetcode.com/problems/generate-parentheses/
 *
 */
public class GenerateParenthesesUsingExhaustiveSearch {
    public static void main(String[] args) {
        System.out.println("GenerateParenthesesUsingExhaustiveSearch.main");

        test(1, 1);
        test(2, 2);
        test(3, 5);
    }

    private static void test(int n, int expected) {
        System.out.println("n = [" + n + "]");

        List<String> result1 = generateParanComb(n);
        ArrayUtils.printResult(result1, "generateParanComb");


        List<String> result2 = generateParanCombWithConstraints(n);
        ArrayUtils.printResult(result2, "generateParanCombWithConstraints");

    }

    /**
     * Basic combinatorial generation w/o applying constraints
     * @param n
     * @return
     */
    private static List<String> generateParanComb(int n) {
        List<String> result = new ArrayList<>();

        generateParanCombHelper(n*2, "", result);

        return result;
    }

    /**
     * Helper for basic combinatorial generation w/o applying constraints
     * @param n
     * @param soFar
     * @param coll
     */
    private static void generateParanCombHelper(int n, String soFar, List<String> coll) {
        if (n == 0) {
            coll.add(soFar);
        } else {
            generateParanCombHelper(n-1, soFar + "(", coll);
            generateParanCombHelper(n-1, soFar + ")", coll);
        }
    }

    /**
     * Basic combinatorial generation with applying constraints
     * @param n
     * @return
     */
    private static List<String> generateParanCombWithConstraints(int n) {
        List<String> result = new ArrayList<>();

        generateParanCombWithConstraintsbHelper(n,n, "", result);

        return result;
    }

    private static void generateParanCombWithConstraintsbHelper(int lParanCnt, int rParanCnt,
                                                                String soFar, List<String> coll) {

        // number of left paran must be <= to right paran
        if (lParanCnt > rParanCnt) {
            return;
        }

        // since we are decrementing left and right paran, it can't be less than 0
        if (lParanCnt < 0 || rParanCnt < 0) {
            return;
        }


        if (lParanCnt == 0 && rParanCnt == 0) {
            coll.add(soFar);
        } else {
            generateParanCombWithConstraintsbHelper(lParanCnt - 1, rParanCnt,
                    soFar + "(", coll);
            generateParanCombWithConstraintsbHelper(lParanCnt, rParanCnt - 1,
                    soFar + ")", coll);
        }
    }



}
