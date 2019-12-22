import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Given a non-empty string s and a dictionary wordDict containing a
 * list of non-empty words, determine if s can be
 * segmented into a space-separated sequence of one or more dictionary words.
 *
 *
 * Note:
 *
 * The same word in the dictionary may be reused multiple times in the
 * segmentation.
 * You may assume the dictionary does not contain duplicate words.
 *
 * Input: s = "leetcode", wordDict = ["leet", "code"]
 * Output: true
 *
 * Input: s = "applepenapple", wordDict = ["apple", "pen"]
 * Output: true
 *
 * Input: s = "catsandog", wordDict = ["cats", "dog", "sand", "and", "cat"]
 * Output: false
 *
 * Input: s = "aaaaaaa", wordDict = ["aaaa","aaa"]
 * Expected: true
 *
 * Approaches:
 * - Matching each word in dictionary with prefix of string and reduce string to smaller
 * - Matching all possible substrings with words in the dictionary
 *
 * Resources:
 *  - https://leetcode.com/problems/word-break/discuss/424754/Simplified-Word-Break-1-2-(Java)-using-DP
 */
public class WordBeak {
    public static void main(String[] args) {
        System.out.println("WordBeak.main");


        test("aaaaa", new HashSet<String>(Arrays.asList( "bc", "de","a")), true);
        /*
        test("applepenapple", new HashSet<String>(Arrays.asList("apple", "pen")), true);
        test("catsandog", new HashSet<String>(Arrays.asList("cats", "dog", "and", "cat")), false);
        test("leetcode", new HashSet<String>(Arrays.asList("leet", "code")), true);

        test("catsandog", new HashSet<String>(Arrays.asList("cats", "dog", "and", "cat")), false);
        test("aaaaaaa", new HashSet<String>(Arrays.asList("aaaa","aaa")), true);
        test("aaaaaaaaaaa", new HashSet<String>(Arrays.asList( "bc", "de","a")), true);
        test("aaaaaaaaaab", new HashSet<String>(Arrays.asList( "bc", "de", "a")), false);
        test("cars", new HashSet<String>(Arrays.asList("car","ca", "rs")), true);
        test("cars", new HashSet<String>(Arrays.asList("car","ca", "rs")), true);

        String input ="aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaab";
        Set<String> dict = new HashSet<>(Arrays.asList(
                "a","aa","aaa","aaaa","aaaaa","aaaaaa","aaaaaaa","aaaaaaaa","aaaaaaaaa","aaaaaaaaaa"));

        test(input, dict, true);
        */
    }

    private static void test(String input, Set<String> dict, boolean expected) {
        System.out.println("\ninput = [" + input + "], dict = [" + dict + "]");

        boolean actual1 = wordBreakBF(input, dict);
        printCallCount("wordBreakBF: " );


        boolean actual2 = wordBreakDFButtomUp(input, dict);
        printCallCount("wordBreakDFButtomUp: " );

        boolean actual3 = wordBreakDFBUSubstring(input, dict);
        printCallCount("wordBreakDFBUSubstring: " );

        System.out.printf("expected: %b, actual1: %b, actual2: %b, actual3: %b\n",
                expected, actual1, actual2, actual3);
    }

    private static int callCount;

    /**
     * Iterate through the dictionary,
     *  - match prefix and suffix
     *  - if match remove the prefix or suffix
     *  - pass the shorter string input recursion
     *
     *  - return true when input is empty
     *
     *  Runtime: O(2^n) why?
     *  - for each word that matches the prefix, we recurse down with a new substring
     *
     * @param input
     * @param dict
     * @return
     */
    private static boolean wordBreakBF(String input, Set<String> dict) {

        callCount++;

        if (input.isEmpty()) {
            return true;
        }

        /**
         *
         * For every word in the dictionary, check to see if it is a prefix
         * - if so, remove it from the input and recursive on the remaining
         * - O(2^n)
         *
         * while going through the while loop, shouldn't modify
         * input, instead use a different variable.
         *
         * The input string should be the same as we are iterating
         * through the dictionary
         *
         */
        for (String word : dict) {
            if (input.length() >= word.length()) {
                if (input.startsWith(word)) {
                    // chop off the prefix
                    String newInput = input.substring(word.length());

                    // what if there is no match
                    if (wordBreakBF(newInput, dict)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * This approach uses an array of length that equals to the input of the string
     * to keep track of which
     *
     * - for each position i in the string from 0 to len(string)
     *   - for each word in the dictionary
     *   - see if input(i, i+len(word) == word
     *     - set marker to true
     * - use the index with true val as a starting point to continue the checking
     *
     * Runtime: O(input length * size of the dictionary)
     *
     * @param input
     * @param dict
     * @return
     */
    private static boolean wordBreakDFButtomUp(String input, Set<String> dict) {
        boolean[] markerArr = new boolean[input.length()+1];
        markerArr[0] = true;

        for (int i = 0; i < input.length(); i++) {

            if (!markerArr[i]) {
                // skip the ones that are false
                continue;
            }

            for (String word : dict) {

                int end = i + word.length();

                if (end > input.length()) {
                    continue;
                }
                callCount++;

                if (input.substring(i, end).equals(word)) {
                    markerArr[end] = true;
                }
            }
        }
        System.out.println(Arrays.toString(markerArr));
        return markerArr[input.length()];

    }


    /**
     * This approach combines the substrings and markers to speed up the
     * checking
     *
     *
     * @param input
     * @param dict
     * @return
     */
    private static boolean wordBreakDFBUSubstring(String input, Set<String> dict) {
        boolean[] markerArr = new boolean[input.length()+1];
        markerArr[0] = true;

        // right <= input.length() because the right part is exclusive
        for (int right = 1; right <= input.length(); right++) {
            for (int left = 0; left < right; left++) {
                // don't bother with marker with false val
                if (markerArr[left]) {
                    callCount++;

                    String subString = input.substring(left, right);
                    if (dict.contains(subString)) {
                        // advance the marker to right position
                        markerArr[right] = true;
                        break;
                    }
                }
            }
        }

        System.out.println(Arrays.toString(markerArr));
        return markerArr[input.length()];
    }

    private static void printCallCount(String input) {
        System.out.println(input + ": " + callCount);
        callCount = 0;
    }
}
