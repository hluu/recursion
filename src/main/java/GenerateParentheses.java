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
 */
public class GenerateParentheses {
    public static void main(String[] args) {
        System.out.println("GenerateParentheses.main");

        for (int i = 1; i <= 3; i++) {
            test(i);
        }

    }

    private static void test(int n) {
        System.out.println("\nn = [" + n + "]");

        List<String> collector = new ArrayList<>();

        generateParans(n, n, "", collector);

        System.out.println("===> output generateParans <===");
        for (String s : collector) {
            System.out.println(s);
        }

        List<List<Character>> collector2 = new ArrayList<>();

        generateParansMutableDS(n, n, new ArrayList<Character>(), collector2);

        System.out.println("===> output generateParansMutableDS <===");
        for (List<Character> s : collector2) {
            StringBuilder sb = new StringBuilder();
            for (Character c : s) {
                sb.append(c);
            }

            System.out.println(sb.toString());
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

    private static void generateParansMutableDS(int leftParan, int rightParan,
                                                List<Character> path,
                                                List<List<Character>> coll) {

        // base cases
        // 1) too many left or right paranthesis
        // 2) number of ')' is more than number of ')'
        if (leftParan > rightParan || leftParan < 0 || rightParan < 0) {
            return;
        }

        if (leftParan == 0 && rightParan == 0) {
            coll.add(new ArrayList<>(path));
            return;
        }

        // adding ( until can't do any more
        path.add('(');
        generateParansMutableDS(leftParan-1, rightParan,
                path, coll);
        path.remove(path.size()-1);


        path.add(')');
        generateParansMutableDS(leftParan, rightParan-1,
                path, coll);
        path.remove(path.size()-1);

    }


}
