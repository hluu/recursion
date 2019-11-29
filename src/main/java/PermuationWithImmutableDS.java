import java.util.ArrayList;
import java.util.List;

/**
 * Compute the permutations of a list of characters or integers
 *
 * For example:
 *  input: "abc"
 *  output: "abc", "acb", "bac", "bca", "cab", "cba"
 *
 * permutate("abc", "")
 *   permutate("bc", "a")
 *     permutate("c", "ab")
 *       permutate("", "abc")
 *     permutate(("b", "ac")
 *       permutate("", "acb")
 *   permuate("ac", "b")
 *     permutate("c", "ba")
 *       permutate("", "bac")
 *     permutate("a", "bc")
 *       permutate("", "bca")
 *
 *
 */
public class PermuationWithImmutableDS {
    public static void main(String[] args) {
        System.out.println("PermuationWithImmutableDS.main");

        testPermutation("abc");
        testPermutation("vxyz");
    }

    private static void testPermutation(String input) {
        System.out.println("input = [" + input + "]");

        List<String> result = permute(input);

        for (String row : result) {
            System.out.println(row);
        }
    }

    private static List<String> permute(String input) {
        List<String> collector = new ArrayList<>();

        permuteHelper(input, "", collector);

        return collector;
    }

    private static void permuteHelper(String input, String soFar,
                                      List<String> collector) {

        if (input.isEmpty()) {
            collector.add(soFar);
        } else {
            for (int i = 0; i < input.length(); i++) {
                char charAtI = input.charAt(i);

                // substring (start, end) - end is exclusive
                String prefix = input.substring(0, i);
                String suffix = input.substring(i+1);
                // reduce by 1
                String newInput = prefix + suffix;

                permuteHelper(newInput, soFar+ charAtI, collector);
            }
        }
    }

}
