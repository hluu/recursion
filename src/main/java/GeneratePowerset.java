import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Given a set of integers, generate all possible combinations
 *
 * AKA - subsets, power set
 *
 */
public class GeneratePowerset {
    public static void main(String[] args) {
        System.out.println(GeneratePowerset.class.getName());
        test(new int[] {1,2,3,4}, 16);

        generateStringSubsets("abc");
    }

    private static void test(int[] input, int expectedNumCombo) {
        System.out.printf("input: %s\n", Arrays.toString(input));

        List<List<Integer>> coll1 = new ArrayList<List<Integer>>();

        usingIncludeExcludePattern(input, 0, new ArrayList<>(), coll1);

        System.out.println("=====> output <========");

        for (List<Integer> comb : coll1) {
            System.out.println(comb.toString());
        }

        System.out.printf("expected # combo: %d, actual # combo: %d\n",
                coll1.size(), expectedNumCombo);

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

        // base case
        if (idx == input.length) {
            coll.add(new ArrayList<>(path));
            return;
        } else {
            // exclude
            usingIncludeExcludePattern(input, idx+1, path, coll);

            // include, why do we need to create a new list?
            // because the newPath is passed down to another recursion path
            // that includes the input[idx]
            List<Integer> newPath = new ArrayList<>(path);
            newPath.add(input[idx]);
            usingIncludeExcludePattern(input, idx+1, newPath, coll);
        }
    }

    private static void generateStringSubsets(String input) {
        System.out.println("\ngenerateStringMutableDS: " + input);
        generateStringMutableDS(input, "");

        System.out.println("\ngenerateStringImmutableDS: " + input);
        generateStringImmutableDS(input,0, new StringBuffer());

    }

    /**
     * Using immutable data structure
     * Why does it print c first? does that make sense?
     *
     * @param remaining
     * @param soFar
     */
    private static void generateStringMutableDS(String remaining, String soFar) {
        if (remaining.isEmpty()) {
            System.out.println(soFar);
            return;
        }

        String tmpStr = remaining.substring(1);
        // exclude
        generateStringMutableDS(tmpStr, soFar);
        generateStringMutableDS(tmpStr, soFar + remaining.charAt(0));
    }

    /**
     * Collector as @code{StringBuffer} will collect 2^n strings
     *
     * @param input
     * @param idx
     * @param collector
     */
    private static void generateStringImmutableDS(String input, int idx,
                                                  StringBuffer collector) {
        if (idx == input.length()) {
            System.out.println(collector.toString());

            return;
        }

        // exclude
        generateStringImmutableDS(input, idx+1, collector);
        collector.append(input.charAt(idx));

        generateStringImmutableDS(input, idx+1, collector);
        collector.deleteCharAt(collector.length()-1);
    }
}
