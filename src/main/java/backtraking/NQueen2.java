package backtraking;

import backtraking.NQueen;

import java.util.ArrayList;
import java.util.List;

/**
 * This is similar to backtraking.NQueen, but the logic is slightly difference in that
 * - using List<Integer> as the the 1-D board
 * - place the queen at a particular column
 * - then check for validity
 */
public class NQueen2 {
    public static void main(String[] args) {
        System.out.println("backtraking.NQueen2.main");

        //test(3);
        //test(4);
        //test(5);
        test(8);
    }

    private static void test(int boardSize) {
        System.out.println("\nboardSize = [" + boardSize + "]");

        List<List<Integer>> collector = new ArrayList<>();

        placeQueens(boardSize, 0, new ArrayList<>(), collector);

        System.out.printf("==> Board positions: %d <==\n",
                collector.size());

        for (List<Integer> board : collector) {
            System.out.println(board);
            NQueen.printBoard(board.stream().mapToInt(Integer::intValue).toArray());
            System.out.println("validBoard: " + isValidBoard(board));
            System.out.println();
        }
    }

    /**
     *
     * @param boardSize
     * @param row
     * @param board
     * @param coll
     */
    private static void placeQueens(int boardSize, int row,
                                    List<Integer> board, List<List<Integer>> coll) {
        if (row == boardSize) {
            coll.add(new ArrayList<>(board));
            return;
        }

        for (int col = 0; col < boardSize; col++) {
            board.add(col);

            if (isValidBoard(board)) {
                placeQueens(boardSize, row+1, board, coll);
            }

            // backtrack
            board.remove(board.size()-1);
        }
    }

    /**
     * Each time this method is called, the assumption is
     * a queen was just placed at a new row
     *
     * @param board
     * @return boolean - if valid, else false
     */
    private static boolean isValidBoard(List<Integer> board) {
        int upToRow = board.size() - 1;

        for (int row = 0; row < upToRow; row++) {
            int diff = Math.abs(board.get(row) - board.get(upToRow));
            if (diff == 0) {
                // meaning same column
                return false;
            }

            int xDist = diff;
            int yDist = upToRow - row;
            if (xDist == yDist) {
                // same diagonal line
                return false;
            }
        }

        return true;
    }

}
