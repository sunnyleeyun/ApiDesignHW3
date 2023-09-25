package edu.cmu.cs780.hw3;

import java.util.Random;
import java.util.Scanner;

/**
 * Homework 3
 * 
 * @author Zihao Yang (zihaoy2@andrew.cmu.edu), Yun Lee (yunl3@andrew.cmu.edu)
 */
public class Game {
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

    private static final int SEED = 4;

    /**
     * Board variable representing the board of this game.
     */
    private Board board;

    /**
     * Array of players playing the game.
     */
    private Player[] players;
    /**
     * Integer variable indicating the number of checkers.
     */
    private int checkersCount;

    public Game() {

    }

    /**
     * Constructor to create a Game using two players.
     * 
     * @param firstPlayer  The first player of this game
     * @param secondPlayer The second player of this game
     */
    public Game(Player firstPlayer, Player secondPlayer) {
        this.board = new Board();
        this.players = randomizedPlayers(firstPlayer, secondPlayer);
        this.checkersCount = 0;
    }

    /**
     * Constructor to create a Game using two players.
     * 
     * @param firstPlayer  The first player of this game
     * @param secondPlayer The second player of this game
     */
    public Game(Player firstPlayer, Player secondPlayer, int[][] newBoard, int checkersCount, Player[] players) {
        this.board = new Board(newBoard, checkersCount, players);
        this.players = randomizedPlayers(firstPlayer, secondPlayer);
        this.checkersCount = 0;
    }

    /**
     * Randomly decide which player plays first.
     * 
     * @param firstPlayer  The first player of this game
     * @param secondPlayer The second player of this game
     * @return
     */
    private Player[] randomizedPlayers(Player firstPlayer, Player secondPlayer) {
        Player[] players = new Player[PLAYER_COUNT];
        Random rand = new Random(SEED);
        int value = 1 + rand.nextInt(2);
        if (value == 1) {
            players[0] = firstPlayer;
            players[1] = secondPlayer;
        } else {
            players[1] = firstPlayer;
            players[0] = secondPlayer;
        }
        return players;
    }

    /**
     * Return string of pretty printed board.
     */
    public String prettyPrintBoard() {
        return board.prettyPrint();
    }

    /**
     * Return true if current row is not full, else false.
     * 
     * @param col The column number of the new checker
     */
    public boolean canPlaceChecker(int col) {
        return this.gameBoard[0][col] == 0;
    }

    /**
     * Return Player object based on checkerCount.
     */
    // private Player getCurrentPlayer(Player[] players) {
    // return players[checkersCount % PLAYER_COUNT];
    // }

    /**
     * Return true if board is full, else false.
     */
    public boolean hasNoEmptySpace() {
        return checkersCount == ROW_SIZE * COL_SIZE;
    }

    /**
     * Add a new checker (represented by playerNum) to the board.
     * 
     * @param col       The column number of the new checker
     * @param playerNum The player number (1 or 2)
     */
    public void addToBoard(int col, int playerNum) {
        for (int row = ROW_SIZE - 1; row >= 0; row--) {
            if (this.gameBoard[row][col] == 0) {
                this.gameBoard[row][col] = playerNum;
                checkersCount++;
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

    /**
     * System print column full.
     */
    private void outputColumnFull() {
        System.out.print("This column is full!");
    }

    /**
     * System print game over.
     */
    private void outputGameIsOver() {
        System.out.print("Game is over. Please start another game.");
    }

    /**
     * Check whether the current player wins after it add a checker to
     * the board.
     * 
     * @param lastRow The row number the current player inserts a checker
     * @param lastCol The column number the current player inserts a checker
     * @return true if the current player wins, return false if it is not.
     */
    public boolean isWinner(int lastRow, int lastCol) {
        Player currentPlayer = getCurrentPlayer();
        return (this.board.checkDirections(lastRow, lastCol, currentPlayer.getPlayerId()));
    }

    /**
     * Plays the current turn in the game.
     *
     * This method checks if the game is over, and if so, it outputs a message
     * indicating that the game has ended. If the game is still ongoing, it proceeds
     * with the current player's turn. The player is prompted to select a column for
     * their checker placement, and if the selected column is valid (not full), the
     * player's checker is added to the game board. If the selected column is
     * already full, an appropriate message is displayed.
     */
    public void play() {
        board.play(getCurrentPlayer());
        // if (board.hasNoEmptySpace()) {
        // outputGameIsOver();
        // return;
        // }
        // Player currentPlayer = getCurrentPlayer();
        // int column = currentPlayer.selectColumn();
        // if (board.canPlaceChecker(column)) {
        // board.addToBoard(column, currentPlayer.getPlayerId());
        // } else {
        // outputColumnFull();
        // }
    }

    /**
     * Return Player object based on checkerCount.
     */
    private Player getCurrentPlayer() {
        return players[checkersCount % PLAYER_COUNT];
    }

    /**
     * System print game over.
     */
    private void outputGameIsOver() {
        System.out.print("Game is over. Please start another game.");
    }

    /**
     * System print column full.
     */
    private void outputColumnFull() {
        System.out.print("This column is full!");
    }
}
