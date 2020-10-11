package backtraking;

import java.util.ArrayList;
import java.util.List;

/**
 * Write a function diceSum that accepts two integer parameters: a
 * number of dice to roll, and a desired sum of all die values. Output
 * all combinations of die values that add up to exactly that sum.
 *
 * diceSum(2, 7);
 * - {1, 6}
 * - {2, 5}
 * - {3, 4}
 * - {4, 3}
 * - {5, 2}
 * - {6, 1}
 * diceSum(3, 7);
 * - {1, 1, 5}
 * - {1, 2, 4}
 * - {1, 3, 3}
 * - {1, 4, 2}
 * - {1, 5, 1}
 * - {2, 1, 4}
 * - {2, 2, 3}
 * - {2, 3, 2}
 * - {2, 4, 1}
 * - {3, 1, 3}
 * - {3, 2, 2}
 * - {3, 3, 1}
 * - {4, 1, 2}
 * - {4, 2, 1}
 * - {5, 1, 1}
 *
 */


public class DiceCount {
    public static void main(String[] args) {
        System.out.println("DiceCount.main");

        test(2, 7, 6);
        test(3, 7, 15);
    }

    private static void test(int numDice, int sum, int expected) {
        System.out.println("numDice = [" + numDice + "], sum = [" + sum + "]");

        List<List<Integer>> coll = new ArrayList<>();

        diceSum(numDice, sum, coll);

        System.out.println("===> output <====");
        for (List<Integer> row : coll) {
            System.out.println(row);
        }

        List<List<Integer>> coll2 = new ArrayList<>();

        diceSumUniqueHelper(numDice, sum, new ArrayList<>(), coll2, 1);

        System.out.println("===> output from diceSumUniqueHelper <====");
        for (List<Integer> row : coll2) {
            System.out.println(row);
        }
    }

    private static void diceSum(int numDice, int targetSum, List<List<Integer>> coll) {
        diceSumHelper(numDice, targetSum, new ArrayList<>(), coll);
    }

    private static void diceSumHelper(int numDice, int targetSum, List<Integer> path,
                                        List<List<Integer>> coll) {

        if (numDice == 0) {
            int sumTmp = path.stream().mapToInt(Integer::intValue).sum();
            if (sumTmp == targetSum) {
                coll.add(new ArrayList<>(path));
            }
        } else {
            for (int value = 1; value <= 6; value++) {
                path.add(value);
                diceSumHelper(numDice-1, targetSum, path, coll);
                path.remove(path.size()-1);
            }
        }
    }

    private static void diceSumUniqueHelper(int numDice, int targetSum, List<Integer> path,
                                            List<List<Integer>> coll, int lastDiceValue) {

        if (numDice == 0) {
            int sumTmp = path.stream().mapToInt(Integer::intValue).sum();
            if (sumTmp == targetSum) {
                coll.add(new ArrayList<>(path));
            }
        } else {
            for (int value = lastDiceValue; value <= 6; value++) {
                path.add(value);
                diceSumUniqueHelper(numDice-1, targetSum, path, coll, value);
                path.remove(path.size()-1);
            }
        }
    }
}
