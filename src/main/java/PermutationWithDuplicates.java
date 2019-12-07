import common.ArrayUtils;

import java.util.*;

public class PermutationWithDuplicates {
    public static void main(String[] args) {
        System.out.println("PermutationWithDuplicates.main");

        testPermutationWithDuplicates(new int[] {1,2,1});
        //testPermutationWithDuplicates(new int[] {1,2,1,2});
        //testPermutationWithDuplicates(new int[] {2,2,3,0});
        //testPermutationWithDuplicates(new int[] {1,2,3,1,2});
    }


    private static void testPermutationWithDuplicates(int[] input) {
        System.out.println("\ninput = [" + Arrays.toString(input) + "]");

        List<String> result = permuteWithDuplicates(input);

        Collections.sort(result);
        for (String row : result) {
            System.out.println(row);
        }
    }

    private static List<String> permuteWithDuplicates(int[] input) {
        List<String> collector = new ArrayList<>();

        permuteWithDuplicatesHelper(input, 0, collector);

        return collector;
    }

    /**
     * This example is using mutable data structure to minimize object
     * creation and handle duplicates
     *
     * - the key is to keep track of what has been before inside the for loop
     * - and skip an element if we've seen it before
     *
     *
     * @param input
     * @param startIdx
     * @param collector
     */
    private static void permuteWithDuplicatesHelper(int[] input, int startIdx,
                                                    List<String> collector) {

        if (startIdx >= input.length) {
            collector.add(Arrays.toString(input));
        } else {
            Set<Integer> seenSoFar = new HashSet<>();
            // for each element in input starting at startIdx,
            // place it at startIdx and permute the remaining
            for (int i = startIdx; i < input.length; i++) {

                if (seenSoFar.contains(input[i])) {
                    continue;
                }

                seenSoFar.add(input[i]);
                // move element at i to startIdx
                ArrayUtils.swap(input, i, startIdx);

                // pass down the subproblem (startIdx+1)
                permuteWithDuplicatesHelper(input, startIdx+1, collector);

                // restore it back to original state
                ArrayUtils.swap(input, i, startIdx);
            }
        }
    }


}
