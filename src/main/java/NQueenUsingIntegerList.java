import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This version uses List<Integer> as the board (slate)
 */
public class NQueenUsingIntegerList {

    public static void main(String[] args) {
        System.out.println("NQueenUsingIntegerList.main");

        test(4);
    }

    private static void test(int boardSize) {
        System.out.println("\n==> test boardSize = " + boardSize );



        List<List<Integer>> result = driver(boardSize);

        System.out.println("==> there are: " + result.size() + " solutions");
        System.out.println("==> board positions <==");
            for (List<Integer> board : result) {
                System.out.println("board: " + board);
                for (int row = 0; row < boardSize; row++) {
                    int queenCol = board.get(row);
                    StringBuilder buf = new StringBuilder();
                    for (int col = 0; col < boardSize; col++) {
                        if (col == queenCol) {
                            buf.append("Q");
                        } else {
                            buf.append(".");
                        }
                    }
                    System.out.println(buf.toString());
                }
                System.out.println("-----------");
        }
    }

    private static List<List<Integer>> driver(int boardSize) {
        List<List<Integer>> coll = new ArrayList<>();

        helper(boardSize, 0, new ArrayList<>(), coll);

        return coll;
    }

    private static void helper(int boardSize, int row, List<Integer> soFar, List<List<Integer>> coll) {
        if (row == boardSize) {
            coll.add(new ArrayList<>(soFar));
            return;
        } else {

            for (int col = 0; col < boardSize; col++) {
                if (isSafeToPlaceQueenAt(soFar, row, col)) {
                    soFar.add(col);
                    helper(boardSize, row+1, soFar, coll);
                    soFar.remove(soFar.size()-1);
                }
            }
        }
    }

    private static boolean isSafeToPlaceQueenAt(List<Integer> board, int row2, int col2) {

        // check for column conflict
        for (int col1 : board) {
            if (col1 == col2) {
                return false;
            }
        }

        // check for diagonal conflict
        for (int row1 = 0; row1 < board.size(); row1++) {
            int xDist = Math.abs(row1 - row2);
            int yDist = Math.abs(board.get(row1) - col2);

            if (xDist == yDist) {
                return false;
            }
        }

        return true;
    }
}
