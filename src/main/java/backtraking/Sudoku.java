package backtraking;

import common.ArrayUtils;

/**
 * https://leetcode.com/problems/sudoku-solver/
 *
 * Write a program to solve a Sudoku puzzle by filling the empty cells.
 *
 * A sudoku solution must satisfy all of the following rules:
 *
 *     Each of the digits 1-9 must occur exactly once in each row.
 *     Each of the digits 1-9 must occur exactly once in each column.
 *     Each of the digits 1-9 must occur exactly once in each of the 9 3x3 sub-boxes of the grid.
 *
 * The '.' character indicates empty cells.
 */
public class Sudoku {
    public static void main(String[] args) {
        System.out.println("Sudoku.main");
        char[][] board = {
                {'5','3','.','.','7','.','.','.','.'},
                {'6','.','.','1','9','5','.','.','.'},
                {'.','9','8','.','.','.','.','6','.'},
                {'8','.','.','.','6','.','.','.','3'},
                {'4','.','.','8','.','3','.','.','1'},
                {'7','.','.','.','2','.','.','.','6'},
                {'.','6','.','.','.','.','2','8','.'},
                {'.','.','.','4','1','9','.','.','5'},
                {'.','.','.','.','8','.','.','7','9'}
        };

        solveSudoku(board);
    }

    private static void solveSudoku(char[][] board) { 
        char[] choices = {'1','2','3','4','5','6','7','8','9'};
         helper(board, 0,0,choices);
    }

    private static boolean helper(char[][] board, int row, int col, char[] choices) {

        // managing the row and column checking is simplified by
        // - fist check to see if we are done filling out the last column
        // - if not, then continue with the column
        // - if yes, then check the row to see if we done with the last row
        //   -if yes, then we are done for the entire board
        //   - if no, then reset column to 0 and move down to next row
        if (col == board.length) {
            if (row+1 == board.length) {
                ArrayUtils.printResult(board);
                return true;  // base case
            } else {
                // advancing to the correct col and row
                col = 0;
                row = row + 1;
            }
        }

        if (board[row][col] != '.') { // if digit, next cell
            return helper(board, row, col+1, choices);
        }

        // choices
        for (char choice : choices) {
            if (isItSafe(board, row, col, choice)) {
                board[row][col] = choice;
                //System.out.printf("making choice: board[%d][%d]=%c\n", row, col, choice);
                if (helper(board, row, col + 1, choices)) {
                    return true;  // this will short circuit the recursion tree
                }
            }
        }
        board[row][col] = '.';  // undo the choice because we couldn't find a valid combo at this level
        return false;
    }

    /**
     * Return false if choice exists in
     *   - same row
     *   - same col
     *   - same square
     *
     * @param board
     * @param row
     * @param col
     * @return
     */
    private static boolean isItSafe(char[][] board, int row, int col, char choice) {
        // same column, going down from row 0 to last row
        for (int row2 = 0; row2 < board.length; row2++) {
            if (board[row2][col] == choice) {
                return false;
            }
        }

        // same row, going from col=0 to the last column
        for (int col2 = 0; col2 < board.length; col2++) {
            if (board[row][col2] == choice)
                return false;
        }

        // square of 3x3
        int sRow = row / 3 * 3;
        int sCol = col / 3 * 3;
        for (int r = sRow; r < sRow + 3; r++) {
            for (int c = sCol; c < sCol + 3; c++) {
                if (board[r][c] == choice) {
                    return false;
                }
            }
        }

        return true;
    }
}
