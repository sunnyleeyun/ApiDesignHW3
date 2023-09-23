package edu.cmu.cs780.hw3;

/**
 * Homework 3
 * @author Zihao Yang (zihaoy2@andrew.cmu.edu)
 */
public class Board {
    /**
     * Integer variable indicating the number of rows.
     */
    private static final int ROWSIZE = 6;
    /**
     * Integer variable indicating the number of rows.
     */
    private static final int COLSIZE = 7;
    /**
     * Integer variable indicating the number of connected checkers to win.
     */
    private static final int WINSIZE = 4;
    /**
     * Integer array representing all possible directions on the board.
     * Horizontal, Vertical, Diagonal (Top-left to Bottom-right), and 
     * Diagonal (Top-right to Bottom-left)
     */
    private static final int[][] DIRECTIONS = new int[][]{{1, 0}, {0, 1}, {1, -1}, {1, 1}};
    /**
     * 2D Square array variable indicating the board of the game.
     */
    private int[][] gameBoard;

    /**
     * No-arg constructor of the Board class.
     */
    public Board() {
        int[][] newBoard = new int[ROWSIZE][COLSIZE];
        this.gameBoard = newBoard;
    }

    /**
     * Try to add a new checker (represented by playerNum) 
     * to the board. Return the newly inserted row number
     * if success, return -1 if impossible to do this column.
     * @param colNum The column number of the new checker
     * @param playerNum The player number (1 or 2)
     * @return The row number inserted to if can be added, 
     * return -1 if can't
     */
    public int addToBoard(int colNum, int playerNum) {
        for (int row = ROWSIZE; row >= 0; row--) {
            if (this.gameBoard[row][colNum] != 0) {
                this.gameBoard[row][colNum] = playerNum;
                return row;
            }
        }
        return -1;
    }

    /**
     * Return only the value of a corresponding cell.
     * @param row Row number of the cell in Integer
     * @param col Column number of the cell in Integer
     * @return The numerical value of the cell in Integer
     */
    public int getCellValue(int row, int col) {
        int res = this.gameBoard[row][col];
        return res;
    }

    /**
     * Search around the given cell index on four directions.
     * @param startRow The row number of the start position in Integer
     * @param startCol The column number of the start position in Integer
     * @param target The target value to be searched in Integer
     * @return True if 4 connected (horizontally, vertically, or diagonally)
     * target values starting at the given pair of row and column are found.
     * Return false otherwise.
     */
    public boolean checkDirections(int startRow, int startCol, int target) {
        for (int[] dir : DIRECTIONS) {
            int dRow = dir[0];
            int dCol = dir[1];
            if (checkSingleDirection(startRow, startCol, dRow, dCol, target)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Search around the given cell index on a single direction.
     * @param row The row number of the start position in Integer
     * @param col The column number of the start position in Integer
     * @param dRow The increment/decrement value on row numbers in Integer
     * @param dCol The increment/decrement value on row numbers in Integer
     * @param target The target value to be searched in Integer
     * @return
     */
    private boolean checkSingleDirection(int row, int col, int dRow, int dCol, int target) {
        int count = 0;
        // Check in both directions
        for (int i = -(WINSIZE - 1); i <= WINSIZE - 1; i++) {
            int newRow = row + i * dRow;
            int newCol = col + i * dCol;
            if (newRow >= 0 && newRow < ROWSIZE &&
                newCol >= 0 && newCol < COLSIZE &&
                this.gameBoard[newRow][newCol] == target) {
                count++;
                if (count == 4) {
                    return true;
                }
            } else {
                count = 0;
            }
        }
        return false;
    }
}
