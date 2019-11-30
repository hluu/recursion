import org.testng.Assert;

public class CheckPalindrome {
    public static void main(String[] args) {
        System.out.println("CheckPalindrome.main");

        test("h", true);
        test("hh", true);
        test("glass", false);
        test("civic", true);
        test("bob", true);

    }

    private static void test(String input, boolean expected) {
        boolean actual = check(input);
        System.out.printf("input = [%s], expected = %b, actual: %b\n",
                input, expected, actual);

        Assert.assertEquals(actual, expected);
    }
    /**
     * Check for a palindromic string using recursion
     *
     *
     *
     * @param input
     * @return
     */
    private static boolean check(String input) {
        if (input.length() == 1 || input.length() == 0) {
            return true;
        }

        if (input.charAt(0) != input.charAt(input.length()-1)) {
            return false;
        }

        // remove the first character and last character
        input = input.substring(1, input.length()-1);

        return check(input);

    }
}
