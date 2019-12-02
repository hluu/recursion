import java.util.ArrayList;
import java.util.List;

/**
 * Let's start by looking at a simple problem: given an alphabet A
 * which is guaranteed to be a non-empty list of distinct single character
 * strings, and a non-negative integer n, generate all strings of length n.
 *
 * For example, given A = ['0', '1', '2'] and n = 2 we expect the output to be:
 *
 * 00
 * 01
 * 02
 * 10
 * 11
 * 12
 * 20
 * 21
 * 22
 *
 * To make sure the question is clear, let's look at another example.
 * Given A = ['a', 'b'] and n = 3 we expect the output to be:
 *
 * aaa
 * aab
 * aba
 * abb
 * baa
 * bab
 * bba
 * bbb
 *
 * Runtime:
 *  - k = len(string)
 *  - O(k^n)
 */
public class GenerateStringsLengthN {
    public static void main(String[] args) {
        System.out.println("GenerateStringsLengthN.main");

        test("012", 2, 9);
        test("ab", 3, 8);
    }

    private static void test(String input, int n, int expected) {
        System.out.println("\ninput = [" + input + "], n = [" + n + "]");

        printString(input.toCharArray(), n);

    }

    private static void printString(char[] letters, int n) {
        System.out.println("===> Immutable DS <==== ");
        printStringHelper(letters, n, "");

        System.out.println("===> Mutable DS <==== ");
        printStringMDSHelper(letters, n, new ArrayList<>());
    }

    private static void printStringHelper(char[] letters, int soFarN, String path) {
        if (soFarN == 0) {
            System.out.println(path);
            return;
        }

        for (int i = 0;i < letters.length; i++) {
            printStringHelper(letters, soFarN-1,  path + letters[i]);
        }
    }

    private static void printStringMDSHelper(char[] letters, int soFarN,
                                             List<Character> path) {
        if (soFarN == 0) {
            System.out.println(path);
            return;
        }

        for (int i = 0;i < letters.length; i++) {
            path.add(letters[i]);
            printStringMDSHelper(letters, soFarN-1,  path);
            path.remove(path.size()-1);
        }
    }
}
