import org.testng.Assert;


import java.util.HashMap;
import java.util.Map;

/**
 * A message containing letters from A-Z is being encoded to numbers
 * using the following mapping:
 *
 * 'A' -> 1
 * 'B' -> 2
 * ...
 * 'Z' -> 26
 *
 * Given a non-empty string containing only digits, determine
 * the total number of ways to decode it.
 *
 * Example 1:
 * - Input: "12"
 * - Output: 2
 * - Explanation: It could be decoded as "AB" (1 2) or "L" (12).
 *
 * Example 2:
 * - Input: "226"
 * - Output: 3
 * - Explanation: It could be decoded as "BZ" (2 26), "VF" (22 6), or "BBF" (2 2 6).
 *
 * Approach:
 * - if split into 2 paths
 *   - one path 1 character prefix peeled off
 *   - one path 2 characters prefix peeled off
 * - base case
 *   - when a single character remains
 *   - when 2 characters with val < 27
 *   - when input can be splitted into its own parts
 *
 *   https://leetcode.com/problems/decode-ways/
 */
public class DecodeWays {
    public static void main(String[] args) {
        System.out.println("DecodeWays.main");

       test("0", 0); // edge case
       test("01", 0); // edge case
       // another edge case
        test("6065812287883668764831544958683283296479682877898293612168136334983851946827579555449329483852397155", 0);

       test("10", 1);
       test("12", 2);
        test("26", 2);
        test("111", 3);
        test("1111", 5);
        test("226", 3);
        test("2986", 1);
        test("2266", 3);
        test("7777", 1);
        test("1787897759966261825913315262377298132516969578441236833255596967132573482281598412163216914566534565", 5898240);

    }

    private static void test(String input, int expected) {
        System.out.println("input = [" + input + "]");

        //int actual = numWaysDriver(input);
        int actual = numWaysDriver(input);

        System.out.printf("expected: %d, actual: %d\n",
                expected, actual);

        Assert.assertEquals(actual, expected);
    }

    private static int numWaysDriver(String input) {
        if (input.isEmpty()) {
            return 0;
        }

        //return numWaysHelper(input);
        return numWaysDPHelper(input ,new HashMap<>());
    }

    private static int numWaysHelper(String remaining) {
        if (remaining.isEmpty()){
            return 1;
        }

        if (remaining.charAt(0) == '0') {
            return 0;
        }


        if (remaining.length() == 1) {
            return 1;
        }


        int oneCharPathCount = numWaysHelper(remaining.substring(1));

        int twoCharPathCount = 0;
        String twoCharPrefix = remaining.substring(0, 2);
        if (Integer.parseInt(twoCharPrefix) > 26) {
            twoCharPathCount = 0;
        } else {
            twoCharPathCount = numWaysHelper(remaining.substring(2));
        }

        return oneCharPathCount + twoCharPathCount;
    }


    /**
     * Because of overlapping subproblems, we can leverage DP to store
     * the previous calculated results to speed up the computation.
     *
     * Use a map to store the key and count:
     * - key is the remain string
     * - val is count
     *
     *
     *
     * @param remaining
     * @param cache
     * @return
     */
    private static int numWaysDPHelper(String remaining, Map<String, Integer> cache) {

        if (cache.containsKey(remaining)) {
            return cache.get(remaining);
        }

        if (remaining.isEmpty()){
            return 1;
        }

        if (remaining.charAt(0) == '0') {
            return 0;
        }


        if (remaining.length() == 1) {
            return 1;
        }


        int oneChar = numWaysDPHelper(remaining.substring(1), cache);

        int twoChar = 0;
        String twoCharPrefix = remaining.substring(0, 2);
        if (Integer.parseInt(twoCharPrefix) > 26) {
            twoChar = 0;
        } else {
            twoChar = numWaysDPHelper(remaining.substring(2), cache);
        }

        cache.put(remaining, oneChar+twoChar);

        return oneChar + twoChar;
    }

}
