import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/combinations/
 *
 */
public class SubsetSize {

    public static void main (String[] args) {
        System.out.println("SubsetSize.main");

        List<List<Integer>> expected1 = new ArrayList<>();
        expected1.add(Arrays.asList(2,4));
        expected1.add(Arrays.asList(3,4));
        expected1.add(Arrays.asList(2,3));
        expected1.add(Arrays.asList(1,2));
        expected1.add(Arrays.asList(1,3));
        expected1.add(Arrays.asList(1,4));

        test(4,2, expected1);

    }

    private static void test(int n, int k, List<List<Integer>> expected) {
        System.out.printf("n: %d, k: %d\n", n,k);

        List<List<Integer>> actual = new ArrayList<>();

        combinations(n,k,1, new ArrayList<>(), actual);

        System.out.printf("expected: %s\n", expected);
        System.out.printf("  actual: %s\n", actual);
    }


    private static void combinations(int n, int k, int idx,
                                     List<Integer> path,
                                     List<List<Integer>> coll) {

        if (path.size() == k) {
            coll.add(new ArrayList<Integer>(path));
            return;
        }

        if (idx > n) {
            return;
        } else {
            combinations(n, k, idx+1, path, coll);
            path.add(idx);
            combinations(n, k, idx+1, path, coll);
            path.remove(path.size()-1);

        }

    }
}
