import java.util.LinkedList;
import java.util.List;

/**
 * All the different ways of grouping a set of characters in a string
 *
 * Input: “abcd” becomes “(a)(bcd)”, “(ab)(cd)”, and “(abc)(d)”
 *
 *
 */
public class GroupingASet {


    public static void  main(String[] args) {

        test("abcd");
        test("abcde");
    }

    private static void test(String input) {
        System.out.println("\ninput = [" + input + "]");

        List<String> result = parentheses("abcd");
        for (String output : result) {
            System.out.println(output);
        }
    }


    /**
     * Break the problem down to the left and right hand side
     * - going from left to right
     * - divide and conquer
     * - then combine both left and right and add parenthesis around it
     * - base case is when a single character
     *
     * @param s
     * @return
     */
    private static List<String> parentheses(String s) {
        if (s.length() == 1) {
            List<String> result = new LinkedList<String>();
            result.add(s);
            return result;
        }

        List<String> results = new LinkedList<String>();

        for (int i = 1; i < s.length(); i++) {
            List<String> left = parentheses(s.substring(0, i));
            List<String> right = parentheses(s.substring(i, s.length()));

           // System.out.printf("left: %d, right: %d\n",
             //       left.size(), right.size());
            for (String s1 : left) {
                for (String s2 : right) {
                    results.add("(" + s1 + s2 + ")");
                }
            }
        }

        return results;
    }

}
