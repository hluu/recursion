import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Generate subsets of integers with mutable data structure
 */
public class GenerateSubsetWithMutableDS {
    public static void main(String[] args) {
        System.out.println("GenerateSubsetWithMutableDS.main");

        test(Arrays.asList(1,2,3,4,5),8);
    }

    private static void test(List<Integer> input, int expected) {
        List<List<Integer>> coll1 = new ArrayList<>();
        generateSubSetsIncludeFirst(input, 0, new ArrayList<>(), coll1);

        int actual1 = coll1.size();



        for (List<Integer> list : coll1) {
            System.out.println(list);
        }

        System.out.println("===> generateSubSetsExcludeFirst <===");

        List<List<Integer>> coll2 = new ArrayList<>();
        generateSubSetsExcludeFirst(input, 0, new ArrayList<>(), coll2);

        int actual2 = coll2.size();

        for (List<Integer> list : coll2) {
            System.out.println(list);
        }

        System.out.printf(
                "expected: %d, actual1: %d, actual2: %d\n",
                expected, actual1, actual2);
    }

    /**
     * This uses the include & exclude with mutable data structure
     * - include first
     *
     * @param input
     * @param currIdx
     * @param path
     * @param coll
     */
    private static void generateSubSetsIncludeFirst(List<Integer> input,
                                                    int currIdx,
                                                    List<Integer> path,
                                                    List<List<Integer>> coll) {
        if (currIdx == input.size()) {
            coll.add(new ArrayList<>(path));
        } else {
            // include
            path.add(input.get(currIdx));
            generateSubSetsIncludeFirst(input, currIdx+1, path, coll);

            path.remove(path.size()-1);


            // exclude
            generateSubSetsIncludeFirst(input, currIdx+1, path, coll);

        }
    }

    /**
     * This uses the include & exclude with mutable data structure
     * - exclude first
     *
     * @param input
     * @param currIdx
     * @param path
     * @param coll
     */
    private static void generateSubSetsExcludeFirst(List<Integer> input,
                                                    int currIdx,
                                                    List<Integer> path,
                                                    List<List<Integer>> coll) {
        if (currIdx == input.size()) {
            coll.add(new ArrayList<>(path));
        } else {
            // exclude
            generateSubSetsExcludeFirst(input, currIdx+1, path, coll);

            // include
            path.add(input.get(currIdx));
            generateSubSetsExcludeFirst(input, currIdx+1, path, coll);

            path.remove(path.size()-1);
        }
    }
}
