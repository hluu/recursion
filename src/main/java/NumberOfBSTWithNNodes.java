import common.PrintUtil;

/**
 * Given a number N, calculate number of binary search trees with n nodes
 * those can be formed using number 1 to N as nodes.
 *
 * For example, with N = 1
 *      1
 *
 * For example, with N = 2
 *      2        1
 *    /            \
 *   1              2
 *
 * For example, with N = 3,
 * there are 5 different trees which can be created as shown below.
 *
 * Recurrence:
 *   C(0) = 0
 *   C(n) = Sum( C(i) * C(n-i)) for i from 0 to N
 *
 * https://algorithmsandme.com/number-of-binary-search-trees-with-n-nodes/
 */
public class NumberOfBSTWithNNodes {
    private static boolean debug = false;
    public static void main(String[] args) {
        System.out.println("NumberOfBSTWithNNodes.main");

        test(0, 0);
        test(1, 1);
        test(2, 2);
        test(3, 5);
        test(4, 14);
        test(5, 42);
    }

    private static void test(int n, int expected) {
        System.out.println("\nn = [" + n + "]");

        int actual = calculate(n, 0);

        System.out.printf("expected: %d, actual: %d\n",
                expected, actual);
    }


    private static int calculate(int n, int level) {
        if (n <= 1) {
            return 1;
        }

        // print level
        PrintUtil.printf(debug,"level => %d\n", level);
        String levelStr = "";
        for (int i = 0; i < level; i++) {
            levelStr += " ";
        }

        int sum = 0;
        for (int i = 1; i <= n; i++) {
            int leftNum = i-1;
            int rightNum = (n-i);

            int leftSide = calculate(leftNum, level+1);
            int rightSide = calculate(rightNum, level+1);

            sum += (leftSide * rightSide);

            PrintUtil.printf(debug, "%d%s(%d,%d): sum: %d\n", level, levelStr,
                    leftNum, rightNum, sum);

        }

        return sum;
    }
}
