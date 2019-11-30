import java.util.ArrayList;
import java.util.List;

/**
 * Given a number n, generate all the strings with matching pairs of parentheses
 *
 * For example:
 * - n = 1, output = {"()"}
 * - n = 2, output = {"(())", "()()"}
 * - n = 3, output = {"((()))", "(()())", "(())()", "()(())", "()()()"}
 *
 * Approach:
 * - String length is n*2
 * - equal number of open parantheses and close parantheses
 * - order matters
 * - number of close paratheses must be greaer than or equal to close parentheses
 *
 */
public class GenerateParentheses {
    public static void main(String[] args) {
        System.out.println("GenerateParentheses.main");

        for (int i = 1; i <= 5; i++) {
            test(i);
        }

    }

    private static void test(int n) {
        System.out.println("\nn = [" + n + "]");

        List<String> collector = new ArrayList<>();

        generateParans(n, n, "", collector);

        System.out.println("===> output <===");
        for (String s : collector) {
            System.out.println(s);
        }
    }

    private static void generateParans(int leftParan, int rightParan,
                                       String soFar,
                                       List<String> coll) {

        if (rightParan == 0) {
            coll.add(soFar);
            return;
        } else {
            if (leftParan > 0) {
                generateParans(leftParan-1, rightParan,
                        soFar + "(", coll);
            }

            if (rightParan > leftParan) {
                generateParans(leftParan, rightParan-1,
                        soFar + ")", coll);
            }
        }
    }


}
