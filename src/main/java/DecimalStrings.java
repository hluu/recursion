import java.util.ArrayList;
import java.util.List;

/**
 * Give n digits, generate all possible decimal strings
 *
 *
 */
public class DecimalStrings {
    public static void main(String[] args) {
        System.out.println("DecimalStrings.main");

        test(1);
        test(2);
        test(3);
    }

    private static void test(int n) {
        System.out.println("n = [" + n + "]");

        mutableDSHelper(n, new ArrayList<>());
        System.out.println();
    }

    private static void mutableDSHelper(int digit, List<Integer> soFar) {
        if (digit == 0) {
            System.out.println(soFar);
        } else {
            for (int choice = 0; choice <= 9; choice++) {
                soFar.add(choice);
                mutableDSHelper(digit - 1, soFar);
                soFar.remove(soFar.size() - 1);
            }
        }
    }
}
