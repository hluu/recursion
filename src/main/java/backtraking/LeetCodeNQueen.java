package backtraking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * https://leetcode.com/problems/n-queens/
 *
 */
public class LeetCodeNQueen {
    public static void main(String[] args) {
        System.out.println("LeetCodeNQueen.main");

        test(1, 1);
        test(4, 2);
        test(8, 92);
    }

    private static void test(int boardSize, int expected) {
        System.out.printf("\n========= boardSize: %d\n", boardSize);

        List<List<Integer>> collector = new ArrayList<>();

        helper(boardSize, 0, new int[boardSize], collector);

        System.out.printf("# arrangements: %d\n", collector.size());

        for (List<Integer> board : collector) {
            System.out.println(board.toString());
        }

        List<List<String>> boardStringList = convertBoardToString(boardSize, collector);

        System.out.println("======= boardStringList ======");
        for (List<String> board : boardStringList) {
            System.out.println(board.toString());
        }
    }

    private static List<List<String>> convertBoardToString(int boardSize,
                                                           List<List<Integer>> boardList) {
        List<List<String>> boardStringList = new ArrayList<>();
        char[] rowChar = new char[boardSize];

        for (List<Integer> board : boardList) {
            List<String> boardString = new ArrayList<>();
            for (Integer col : board) {
                for (int i = 0; i < boardSize; i++) {
                    if (i == col) {
                        rowChar[i] = 'Q';
                    } else {
                        rowChar[i] = '.';
                    }
                }
                boardString.add(new String(rowChar));
            }
            boardStringList.add(boardString);
        }

        return boardStringList;
    }

    private static void helper(int boardSize, int currRow, int[] board,
                               List<List<Integer>> coll) {
        if (currRow == boardSize) {
            coll.add(Arrays.stream(board).boxed().collect(Collectors.toList()));
        } else {
            for (int col = 0; col < boardSize; col++) {
                if (isSafe(board, currRow, col)) {
                    board[currRow] = col;
                    helper(boardSize, currRow+1, board, coll);
                }
            }
        }
    }

    private static boolean isSafe(int[] board, int row2, int col2) {
        for (int row1 = 0; row1 < row2; row1++) {
            int col1 = board[row1];
            if (col2 == col1) return false;

            int xDist = Math.abs(col1 - col2);
            int yDist = Math.abs(row1 - row2);
            if (xDist == yDist) return false;
        }
        return true;

    }
}
