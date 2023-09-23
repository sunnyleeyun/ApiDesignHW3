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
     * to the board. Return true if success, return false if
     * failed.
     * @param colNum The column number of the new checker
     * @param playerNum The player number (1 or 2)
     * @return True if can be added, false if can't
     */
    public boolean addToBoard(int colNum, int playerNum) {
        for (int row = ROWSIZE; row >= 0; row--) {
            if (this.gameBoard[row][colNum] != 0) {
                this.gameBoard[row][colNum] = playerNum;
                return true;
            }
        }
        return false;
    }
}
