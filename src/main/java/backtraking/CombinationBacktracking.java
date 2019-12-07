package backtraking;

import java.util.ArrayList;
import java.util.List;

/**
 * Leetcode 77 - https://leetcode.com/problems/combinations/
 *
 * Given two integers n and k, return all possible combinations of k numbers out of 1 ... n.
 *
 * Example:
 *
 * Input: n = 4, k = 2
 * Output:
 * [
 *   [2,4],
 *   [3,4],
 *   [2,3],
 *   [1,2],
 *   [1,3],
 *   [1,4],
 * ]
 */
public class CombinationBacktracking {
    public static void main(String[] args) {
        System.out.println("CombinationBacktracking.main");
        test(4, 2, 6);
    }

    private static void test(int n, int k, int expected) {
        System.out.println("n = [" + n + "], k = [" + k + "]");
        
        List<List<Integer>> result = new ArrayList<>();

        combinations(n, k, 1, new ArrayList<>(), result);

        System.out.println("=====> output <=======");
        for (List<Integer> line : result) {
            System.out.println("line = " + line);
        }
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

