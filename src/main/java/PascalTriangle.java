import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Pascal's triangle definition:
 *  - recurrence: f(r,c)=f(r−1,c−1)+f(r−1,c)
 *  - base case: f(r,c) = 1   where c=1 or c=j
 */
public class PascalTriangle {
    public static void main(String[] args) {
        System.out.println("PascalTriangle.main");

        for (int i = 1; i <= 5; i++) {
            test(i);
        }

        System.out.println("===> getRow <===");
        for (int i = 0; i <= 5; i++) {
            System.out.println("row: " + i + " => " + getRow(i));
        }
    }

    private static void test(int numRow) {
        System.out.println("\nnumRow = [" + numRow + "]");

        List<List<Integer>> result = generate(numRow);

        for (List<Integer> row : result) {
            System.out.println(row);
        }
    }

    private static List<List<Integer>> generate(int numRow) {

        List<List<Integer>> result = new ArrayList<>();

        if (numRow > 0) {
            generateHelper(numRow, result);
        }

        return  result;
    }

    /**
     * The idea is the build up the pascal triangle from the base case
     *
     * @param rowNum
     * @return
     */
    private static List<Integer> generateHelper(int rowNum, List<List<Integer>> coll) {
        if (rowNum == 1) {
            List<Integer> result =  Arrays.asList(1);
            coll.add(result);
            return result;
        } else {
            List<Integer> row = generateHelper(rowNum-1, coll);

            List<Integer> newRow = new ArrayList<>();

            for (int col = 1; col <= rowNum; col++) {
                if (col == 1 || col == rowNum) {
                    newRow.add(1);
                } else {
                   newRow.add(row.get(col-2) + row.get(col-1));
                }
            }

            coll.add(newRow);

            return newRow;

        }
    }

    /**
     * Given a non-negative index k where k ≤ 33, return the kth index row of the Pascal's triangle.
     *
     * Note that the row index starts from 0.
     *
     * Example:
     *  - Input: 3
     *  - Output: [1,3,3,1]
     *
     * Could you optimize your algorithm to use only O(k) extra space?
     *
     * Approach:
     * - observation each row depends on only the previous row
     * - use only two lists
     *
     * @param rowIndex
     * @return
     */
    private static List<Integer> getRow(int rowIndex) {
        List<Integer> prevRow = new ArrayList<>();
        List<Integer> currRow = new ArrayList<>();

        getRowHelper(rowIndex, prevRow, currRow);

        return  currRow;
    }

    /**
     * Traverse all the way down to base case first, then
     * build up from there.
     *
     *
     * @param rowIndex
     * @param prevRow
     * @param currRow
     */
    private static void getRowHelper(int rowIndex,
                                     List<Integer> prevRow,
                                     List<Integer> currRow) {

        if (rowIndex == 0) {
            currRow.add(1);
            return;
        }

        // top down switch the currRow and prevRow
        getRowHelper(rowIndex-1, currRow, prevRow);

        for (int col=0; col <= rowIndex; col++) {
            if (col == 0 || col == rowIndex) {
               currRow.add(1);
            } else {
                // we know the length of current list will be larger than
                // prevRow, so use add when exceed the size
                // otherwise use set to override
                if (col >= currRow.size()) {
                    currRow.add(prevRow.get(col) + prevRow.get(col - 1));
                } else {
                    currRow.set(col, prevRow.get(col) + prevRow.get(col - 1));
                }
            }
        }
    }
}
