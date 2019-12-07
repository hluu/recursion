import common.ArrayUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a collection of integers that might contain duplicates, nums, return all possible subsets (the power set).
 *
 * Note: The solution set must not contain duplicate subsets.
 *
 * Example:
 *
 * Input: [1,2,2]
 * Output:
 * [
 *   [2],
 *   [1],
 *   [1,2,2],
 *   [2,2],
 *   [1,2],
 *   []
 * ]
 *
 *
 */
public class GeneratePowersetWithDuplicates {
    public static void main(String[] args) {
        System.out.println(GeneratePowersetWithDuplicates.class.getName());
        test(new int[] {1,2,3}, 6);
        test(new int[] {1,2,2}, 6);
        test(new int[] {1,2,3,1,2}, 6);
        //test(new int[] {1,2,2,2}, 6);

    }

    private static void test(int[] input, int expectedNumCombo) {
        System.out.printf("input: %s\n", Arrays.toString(input));


        List<List<Integer>> coll1 = driver1(input);

        System.out.println("=====> output #1 <========");

        for (List<Integer> comb1 : coll1) {
            System.out.println(comb1.toString());
        }


        List<List<Integer>> coll2 = driver2(input);

        System.out.println("=====> output #2 <========");

        for (List<Integer> comb2 : coll2) {
            System.out.println(comb2.toString());
        }

        System.out.printf("expected # combo: %d, actual # combo: %d\n",
                coll2.size(), expectedNumCombo);


    }

    /**
     * Driver1 is calling the include/exclude approach
     *
     * @param input
     * @return
     */
    private static List<List<Integer>> driver1(int[] input) {
        List<List<Integer>> coll = new ArrayList<List<Integer>>();

        if (input == null || input.length == 0) {
            return coll;
        }

        // make sure to sort first to take advantage of the knowledge
        // of duplicate numbers are close to each other
        Arrays.sort(input);

        usingIncludeExcludePattern(input, 0, new ArrayList<>(), coll);

        return coll;
    }

    /**
     * Driver2 is calling the iteration approach
     *
     * @param input
     * @return
     */
    private static List<List<Integer>> driver2(int[] input) {
        List<List<Integer>> coll = new ArrayList<List<Integer>>();

        if (input == null || input.length == 0) {
            return coll;
        }

        // make sure to sort first to take advantage of the knowledge
        // of duplicate numbers are close to each other
        Arrays.sort(input);

        usingIncludeAndIterationPattern(input, 0, new ArrayList<>(), coll);

        return coll;
    }

    /**
     * The requirement is to return a list of combinations of integers
     *
     * This approach use
     * - mutable data structure
     * - include and exclude pattern
     *
     *
     * @param input - contains a distinct list of values
     * @param idx - using this to indicate the subproblem
     * @param path
     * @param coll
     */
    private static void usingIncludeExcludePattern(int[] input, int idx,
                                                   List<Integer> path,
                                                   List<List<Integer>> coll) {

        if (idx == input.length) {
            coll.add(new ArrayList<>(path));
        } else {

            int count = 1; // number of duplicates
            int nextIndex = idx+1; // next index to start from

            while (nextIndex <= input.length-1 &&
                    input[nextIndex] == input[idx]) {
                count++;
                nextIndex++;
            }

            for (int cnt = 0; cnt <= count; cnt++) {
                addToPath(input, path, idx, cnt);
                usingIncludeExcludePattern(input, idx+count, path, coll);
                popPath(path, cnt);
            }
        }
    }

    private static void popPath(List<Integer> path,  int cnt) {
        for (int copy = 1; copy <= cnt; copy++) {
            path.remove(path.size()-1);
        }
    }

    private static void addToPath(int[] input, List<Integer> path, int idx, int cnt) {
        for (int copy = 1; copy <= cnt; copy++) {
            path.add(input[idx]);
        }
    }

    private static void usingIncludeAndIterationPattern(int[] input, int idx,
                                                        List<Integer> path,
                                                        List<List<Integer>> coll) {
        if (idx >= input.length) {
            return;
        }

        for (int i = idx; i < input.length; i++) {
            if (!isDup(input, idx, i)) {
                path.add(input[i]);
                coll.add(new ArrayList<>(path));
                usingIncludeAndIterationPattern(input, i + 1, path, coll);
                path.remove(path.size() - 1);
            }
        }
    }

    private static boolean isDup(int[] num, int start, int end) {
        for (int i = start; i <= end - 1; i++) {
            if (num[i] == num[end]) return true;
        }
        return false;
    }

}
