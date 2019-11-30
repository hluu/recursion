import java.util.*;

/**
 * Compute the permutations of a list of characters or integers using mutable
 * data structure
 *
 * Approach
 * - easy to work with an mutable array
 * -
 *
 */
public class PermutationWithMutableDS {

    public static void main(String[] args) {
        System.out.println(PermutationWithMutableDS.class.getName());

        //testPermutation(new int[] {3,2,1,0});
        testPermutationWithDuplicates(new int[] {1,2,1});
        testPermutationWithDuplicates(new int[] {1,2,1,2});
        testPermutationWithDuplicates(new int[] {2,2,3,0});
    }

    private static void testPermutation(int[] input) {
        System.out.println("\ninput = [" + Arrays.toString(input) + "]");

        List<int[]> result = permute(input);

        for (int[] row : result) {
            System.out.println("Arrays.toString(row) = " + Arrays.toString(row));
        }
    }

    private static void testPermutationWithDuplicates(int[] input) {
        System.out.println("\ninput = [" + Arrays.toString(input) + "]");

        List<int[]> result = permuteWithDuplicates(input);

        for (int[] row : result) {
            System.out.println("Arrays.toString(row) = " + Arrays.toString(row));
        }
    }

    private static List<int[]> permute(int[] input) {
        List<int[]> collector = new ArrayList<>();

        permuteHelper(input, 0, collector);

        return collector;
    }

    /**
     * This example is using mutable data structure to minimize object
     * creation.
     *
     *
     * So need to keep undo the work as it comes back from recursion.
     * Is that clear, make sure to understand that well
     *
     * @param input
     * @param startIdx
     * @param collector
     */
    private static void permuteHelper(int[] input, int startIdx,
                                                List<int[]> collector) {

        if (startIdx >= input.length) {
            collector.add(input.clone());
        } else {
            // for each element in input starting at startIdx,
            // place it at startIdx and permute the remaining
            for (int i = startIdx; i < input.length; i++) {
                // move element at i to startIdx
                swap(input, i, startIdx);

                // pass down the subproblem (startIdx+1)
                permuteHelper(input, startIdx+1, collector);

                // restore it back to original state
                swap(input, i, startIdx);
            }
        }
    }


    private static List<int[]> permuteWithDuplicates(int[] input) {
        List<int[]> collector = new ArrayList<>();

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
                                      List<int[]> collector) {

        if (startIdx >= input.length) {
            collector.add(input.clone());
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
                swap(input, i, startIdx);

                // pass down the subproblem (startIdx+1)
                permuteWithDuplicatesHelper(input, startIdx+1, collector);

                // restore it back to original state
                swap(input, i, startIdx);
            }
        }
    }

    private static void swap(int[] input, int i, int j) {
        int tmp = input[i];
        input[i] = input[j];
        input[j] = tmp;
    }
}
