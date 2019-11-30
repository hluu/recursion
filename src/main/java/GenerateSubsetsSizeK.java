import org.testng.Assert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Generate all subsets of size k
 *
 * Runtime: O(k choose n)
 */
public class GenerateSubsetsSizeK {
    public static void main(String[] args) {
        System.out.println("GenerateSubsetsSizeK.main");

        int[] input = new int[] {1,2,3,4,5};
        test(input, 1, nChooseK(input.length,1));
        test(input, 2, nChooseK(input.length,2));
        test(input, 3, nChooseK(input.length,3));
        test(input, 4, nChooseK(input.length,4));
        test(input, 5, nChooseK(input.length,5));
    }

    private static void test(int[] input, int k, int expected) {
        System.out.printf("input: %s, k: %d, expected: %d\n ",
                Arrays.toString(input), k, expected);

        List<List<Integer>> collector = new ArrayList<>();

        generateSubsets(input,0, k, new ArrayList<Integer>(), collector);

        System.out.println("==> output <==");
        for (List<Integer> list : collector) {
            System.out.println(list);
        }

        System.out.printf("expected: %d, actual: %d\n",
                expected, collector.size());

        Assert.assertEquals(collector.size(),expected);
        System.out.println();
    }

    private static void generateSubsets(int[] input, int idx, int k,
                                       List<Integer> tmp,
                                       List<List<Integer>> coll){


        if (tmp.size() == k) {
            coll.add(new ArrayList<>(tmp));
        } else if (idx == input.length) {
            return;
        } else {
            // exclude
            generateSubsets(input, idx+1, k, tmp, coll);

            // include
            List<Integer> path = new ArrayList<>(tmp);
            path.add(input[idx]);
            generateSubsets(input, idx+1, k, path, coll);
            path.remove(path.size()-1);
        }

    }

    /**
     * Formula:  n!/ ((k!)(n-k)!)
     * @param n
     * @param k
     * @return
     */
    private static int nChooseK(int n, int k) {
        long nFactorial = factorial(n);
        long kFactorial = factorial(k);
        long nMinusKFactorial = factorial(n-k);

        return (int) (nFactorial / (kFactorial * nMinusKFactorial));
    }

    private static long factorial(int number) {
        long result = 1;

        for (int factor = 2; factor <= number; factor++) {
            result *= factor;
        }

        return result;
    }
}
