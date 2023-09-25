package edu.cmu.cs780.hw3;

/**
 * Homework 3
 * 
 * @author Zihao Yang (zihaoy2@andrew.cmu.edu), Yun Lee (yunl3@andrew.cmu.edu)
 */
public class Board {
    /**
     * Integer variable indicating the number of rows.
     */
    private static final int ROW_SIZE = 6;
    /**
     * Integer variable indicating the number of rows.
     */
    private static final int COL_SIZE = 7;
    /**
     * Integer variable indicating the number of connected checkers to win.
     */
    private static final int WIN_SIZE = 4;

    private static final int PLAYER_COUNT = 2;
    /**
     * Integer array representing all possible directions on the board.
     * Horizontal, Vertical, Diagonal (Top-left to Bottom-right), and
     * Diagonal (Top-right to Bottom-left)
     */
    private static final int[][] DIRECTIONS = new int[][] { { 1, 0 }, { 0, 1 }, { 1, -1 }, { 1, 1 } };
    /**
     * 2D Square array variable indicating the board of the game.
     */
    private int[][] gameBoard;

    private int checkersCount;

    private Player[] players;

    /**
     * No-arg constructor of the Board class.
     */
    public Board() {
        int[][] newBoard = new int[ROW_SIZE][COL_SIZE];
        this.gameBoard = newBoard;
        this.checkersCount = 0;
        // @FIXME: random selection
        // 0 always start first
        this.players = new Player[PLAYER_COUNT];
        this.players[0] = new HumanPlayer(1);
        this.players[1] = new HumanPlayer(2);
    }

    public Board(int[][] newBoard, Player[] players) {
        this.gameBoard = newBoard;
        this.checkersCount = 0;
        this.players = players;
    }

    public String prettyPrintBoard() {
        String res = "";
        for (int i = 0; i < ROW_SIZE; ++i) {
            for (int j = 0; j < COL_SIZE; ++j) {
                res += String.valueOf(gameBoard[i][j]);
            }
            res += '\n';
        }
        return res;
    }

    public void play() {
        if (gameIsOver()) {
            outputGameIsOver();
            return;
        }
        Player currentPlayer = getCurrentPlayer();
        // int column = currentPlayer.selectColumn();
        int column = 0;
        if (canPlaceChecker(column)) {
            addToBoard(column, currentPlayer.getPlayerId());
            checkersCount++;
        } else {
            outputColumnFull();
        }
    }

    private void outputColumnFull() {
        System.out.print("This column is full!");
    }

    private void outputGameIsOver() {
        System.out.print("Game is over. Please start another game.");
    }

    private boolean canPlaceChecker(int col) {
        return this.gameBoard[0][col] == 0;
    }

    private Player getCurrentPlayer() {
        return players[checkersCount % 2];
    }

    private boolean gameIsOver() {
        return checkersCount == ROW_SIZE * COL_SIZE;
    }

    /**
     * Try to add a new checker (represented by playerNum)
     * to the board. Return the newly inserted row number
     * if success, return -1 if impossible to do this column.
     * 
     * @param col       The column number of the new checker
     * @param playerNum The player number (1 or 2)
     */
    public void addToBoard(int col, int playerNum) {
        for (int row = ROW_SIZE - 1; row >= 0; row--) {
            if (this.gameBoard[row][col] == 0) {
                this.gameBoard[row][col] = playerNum;
                break;
            }
        }
    }

    /**
     * Return only the value of a corresponding cell.
     * 
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
     * 
     * @param startRow The row number of the start position in Integer
     * @param startCol The column number of the start position in Integer
     * @param target   The target value to be searched in Integer
     * @return True if 4 connected (horizontally, vertically, or diagonally)
     *         target values starting at the given pair of row and column are found.
     *         Return false otherwise.
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
     * 
     * @param row    The row number of the start position in Integer
     * @param col    The column number of the start position in Integer
     * @param dRow   The increment/decrement value on row numbers in Integer
     * @param dCol   The increment/decrement value on row numbers in Integer
     * @param target The target value to be searched in Integer
     * @return
     */
    private boolean checkSingleDirection(int row, int col, int dRow, int dCol, int target) {
        int count = 0;
        // Check in both directions
        for (int i = -(WIN_SIZE - 1); i <= WIN_SIZE - 1; i++) {
            int newRow = row + i * dRow;
            int newCol = col + i * dCol;
            if (newRow >= 0 && newRow < ROW_SIZE &&
                    newCol >= 0 && newCol < COL_SIZE &&
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
