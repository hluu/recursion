import org.testng.Assert;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Give NxN board, please N queens such that they can't attack each other
 * - Queen can attack another queen that is on the same row and column
 * - Queen can attack another queen that is on same diagonal line
 *
 * Resources:
 * - https://leetcode.com/problems/n-queens/
 */
public class NQueen {

    private static final int NO_VALUE = -1;

    public static void main(String[] args) {
        System.out.println("NQueen.main");

        //test(3);
        //test(4);
        //test(5);
        test(8);

        /*
        int[] board1 = new int[5];
        Arrays.fill(board1, 3);
        testValidBoard(board1, false);

        int[] board2 = new int[4];
        for (int col = 0; col < 4; col++) {
            board2[col] = col;
        }
        testValidBoard(board2, false);
        */

        test(4);

    }

    private static void test(int boardSize) {
        System.out.println("\n==> test boardSize = " + boardSize );

        int[] board = new int[boardSize];
        Arrays.fill(board, NO_VALUE);

        List<int[]> collector = new ArrayList<>();
        placeQueens(board, 0, collector);

        System.out.println("validBoard: " + isValidBoard(board));


        System.out.println("==> there are: " + collector.size() + " solutions");
        System.out.println("==> board positions <==");
        for (int[] positions : collector) {
            System.out.println(Arrays.toString(positions));
            printBoard(positions);
            System.out.println();
        }
    }

    private static void testValidBoard(int[] board, boolean expected) {
        System.out.println("\n==> test testValidBoard = " + Arrays.toString(board));

        boolean actual = isValidBoard(board);

        System.out.printf("expected: %b, actual: %b\n", expected, actual);

        Assert.assertEquals(actual, expected);
    }

    private static void placeQueens(int[] board, int row, List<int[]> coll) {
        if (row == board.length) {
            coll.add(board.clone());
            return;
        }

        for (int col = 0; col < board.length; col++) {
            if (isSafeToPlaceQueenAt(board, row, col)) {
                board[row] = col;

                placeQueens(board, row+1, coll);

                // backtrack
                board[row] = NO_VALUE;
            }
        }
    }

    private static boolean isSafeToPlaceQueenAt(int[] board, int row2, int col2) {

        // check for column conflict
        for (int col1 : board) {
            if (col1 == col2) {
                return false;
            }
        }

        // check for diagonal conflict
        for (int row1 = 0; row1 < board.length; row1++) {
            if (board[row1] != NO_VALUE) { // only if that row has a queen there
                int xDist = Math.abs(row1 - row2);
                int yDist = Math.abs(board[row1] - col2);

                if (xDist == yDist) {
                    return false;
                }
            }
        }

        return true;
    }


    /**
     * Assuming the column value to be >= 0
     *
     * @param board
     * @return
     */
    private static boolean isValidBoard(int[] board) {
        for (int row1 = 0; row1 < board.length; row1++) {
            int col1 = board[row1];
            if (col1 >= 0) {
                for (int row2 = 0; row2 < board.length; row2++) {
                    if (row1 != row2) {
                        // only make sense to check when different rows
                        int col2 = board[row2];
                        // check for column
                        if (col1 == col2) {
                            return false;
                        }

                        // check for diagonal
                        int xDist = Math.abs(row1 - row2);
                        int yDist = Math.abs(board[row1] - col2);

                        if (xDist == yDist) {
                            return false;
                        }

                    }
                }
            }
        }
        return true;
    }

    public static void printBoard(int[] board) {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board.length; col++) {
                if (board[row] == col) {
                    System.out.print("Q ");
                } else {
                    System.out.print(". ");
                }
            }
            System.out.println();
        }
    }
}
