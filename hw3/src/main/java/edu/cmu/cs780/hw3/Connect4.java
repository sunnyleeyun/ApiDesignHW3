package edu.cmu.cs780.hw3;

import java.util.Random;

/**
 * Represents the game logic and state for Connect4.
 * <p>
 * Connect4 is a two-player connection game in which the players first choose a
 * color and
 * then take turns dropping one colored disc from the top into a seven-column,
 * six-row vertically
 * suspended grid. The objective of the game is to connect four of one's own
 * discs of the
 * same color next to each other vertically, horizontally, or diagonally before
 * the opponent.
 * </p>
 * This class provides methods to:
 * <ul>
 * <li>Initialize a new game session.</li>
 * <li>Handle player moves and input.</li>
 * <li>Check for game-winning conditions.</li>
 * <li>Determine the current state of the game board.</li>
 * <li>Display the current player.</li>
 * <li>Display the current state of the game board.</li>
 * </ul>
 * 
 * Example usage:
 * 
 * <pre>
 * // Start a new game
 * Connect4 game = new Connect4();
 * 
 * // Get the current player
 * int currentPlayer = game.getCurrentPlayer();
 * System.out.println("It's player " + currentPlayer + "'s turn.");
 * 
 * // Make a move in the third column
 * game.placeChecker(2);
 * 
 * // Print the current game board
 * game.toString();
 * 
 * // Displays the current game status
 * // If the game has not yet ended, it indicates which player's turn it is to
 * // play
 * game.displayGameStatus();
 * 
 * // Check if the game is over
 * if (game.isGameOver()) {
 *     // If the game has concluded, the game status (either a win or a draw) is
 *     // displayed
 *     game.displayGameStatus();
 * }
 * 
 * </pre>
 * 
 * @author Zihao Yang (zihaoy2@andrew.cmu.edu), Yun Lee (yunl3@andrew.cmu.edu)
 */
public class Connect4 {
    /**
     * Number of rows in the game board.
     */
    private static final int ROW_SIZE = 6;
    /**
     * Number of columns in the game board.
     */
    private static final int COL_SIZE = 7;
    /**
     * Number of connected checkers required to win.
     */
    private static final int WIN_SIZE = 4;
    /**
     * Game board represented as a 2D array.
     */
    private int[][] gameBoard;
    /**
     * Count of checkers placed on the board.
     */
    private int checkerCount;
    /**
     * ID of the current player.
     */
    private int currentPlayer;

    /**
     * Initializes the game with a randomly chosen starting player and an empty
     * board.
     * The board is represented as a 2D array of integers, where:
     * <ul>
     * <li>0 represents an empty cell.</li>
     * <li>1 represents a checker from player 1.</li>
     * <li>2 represents a checker from player 2.</li>
     * </ul>
     */
    public Connect4() {
        int[][] newBoard = new int[ROW_SIZE][COL_SIZE];
        this.gameBoard = newBoard;
        this.checkerCount = 0;
        this.currentPlayer = selectStartPlayer();
    }

    /**
     * Initializes the game with a specified starting player and an empty board.
     * The board is represented as a 2D array of integers, where:
     * <ul>
     * <li>0 represents an empty cell.</li>
     * <li>1 represents a checker from player 1.</li>
     * <li>2 represents a checker from player 2.</li>
     * </ul>
     * 
     * @param startPlayer The number of player id to start the game. Valid values
     *                    are 1 or 2.
     * 
     * @throws IllegalArgumentException if the {@code startPlayer} is neither 1 nor
     *                                  2.
     */
    public Connect4(int startPlayer) {
        if (startPlayer != 1 && startPlayer != 2) {
            throw new IllegalArgumentException("Invalid startPlayer value. Valid values are 1 or 2.");
        }
        int[][] newBoard = new int[ROW_SIZE][COL_SIZE];
        this.gameBoard = newBoard;
        this.checkerCount = 0;
        this.currentPlayer = startPlayer;
    }

    /**
     * Determines if the game has reached a conclusion, either due to a win
     * condition or a draw.
     * 
     * <p>
     * The game is considered to be over under the following conditions:
     * </p>
     * <ul>
     * <li>A player forms a horizontal, vertical, or diagonal line of 4
     * checkers.</li>
     * <li>The board is full, signifying a draw, even if there isn't a distinct
     * winner.</li>
     * </ul>
     *
     * <p>
     * The method returns {@code true} if any of the above conditions are met,
     * indicating the game's conclusion.
     * Otherwise, it returns {@code false}, implying the game can still proceed.
     * </p>
     *
     * @return {@code true} if the game has reached a conclusion, either due to a
     *         win condition or a draw;
     *         {@code false} if the game can continue.
     */
    public boolean isGameOver() {
        return isBoardFull() || hasWinner();
    }

    /**
     * Prints out the current game status to the console.
     */
    public void displayGameStatus() {
        if (isBoardFull()) {
            System.out.println("It's a draw! \n");
        } else if (hasWinner()) {
            System.out.println("Winner is " + getWinner() + "! Good Game! \n");
        } else {
            System.out.println("Player " + currentPlayer + "'s turn.");
        }
    }

    /**
     * Retrieves the current player's identifier.
     *
     * @return An integer representing current player (either 1 or 2).
     */
    public int getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Randomly selects which player starts first.
     * 
     * @return an integer of the player id (1 or 2) to start first.
     */
    private int selectStartPlayer() {
        Random rand = new Random();
        int value = 1 + rand.nextInt(2);
        return value;
    }

    /**
     * Transforms the board into a {@code String} to be printed in the terminal.
     * 
     * @return a {@code String} of formulated board.
     */
    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < ROW_SIZE; ++i) {
            for (int j = 0; j < COL_SIZE; ++j) {
                res.append(this.gameBoard[i][j]);
            }
            res.append('\n');
        }
        return res.toString();
    }

    /**
     * Determines if a checker can be placed in the specified column.
     * 
     * @param columnNum the column number to check (0 to 6 inclusive).
     * @return {@code true} if the column is not full and can accept a checker, else
     *         {@code false}.
     */
    private boolean isColumnAvailable(int columnNum) {
        return (columnNum >= 0 && columnNum <= 6) && this.gameBoard[0][columnNum] == 0;
    }

    /**
     * Determines if there is a winner based on the current game board state.
     * 
     * @return {@code true} if the current player wins, {@code false} otherwise.
     */
    private boolean hasWinner() {
        return verticalHasWinner() || horizontalHasWinner() || diagonalSWNEHasWinner()
                || diagonalNWSEHasWinner();
    }

    /**
     * Appends a checker to the specified column.
     * 
     * If the chosen column is valid (not full), the current player's checker is
     * added to the game board, the role is switched to the other player, and true
     * is returned. If the chosen column is invalid or full, appropriate exceptions
     * are thrown.
     * 
     * @param columnNum the index-based column number (0 to 6 inclusive) where
     *                  the current player wants to place their checker.
     * @return true if the checker was successfully placed, otherwise exceptions are
     *         thrown.
     * @throws IndexOutOfBoundsException if the columnNum is outside the valid
     *                                   range.
     * @throws IllegalArgumentException  if the chosen column is already full.
     */
    public boolean placeChecker(int columnNum) {
        if (columnNum < 0 || columnNum > 6) {
            throw new IndexOutOfBoundsException("Invalid column number. Valid columns are from 0 to 6 inclusive.");
        }

        if (isColumnAvailable(columnNum)) {
            addToBoard(columnNum, currentPlayer);
            switchRole();
            return true;
        } else {
            throw new IllegalArgumentException("This column is full. Please pick another one.");
        }
    }

    /**
     * Switches the current player.
     * 
     * If the current player is player 1, it switches to player 2 and vice versa.
     */
    private void switchRole() {
        this.currentPlayer = this.currentPlayer == 1 ? 2 : 1;
    }

    /**
     * Adds the current player's checker to the specified column on the board.
     * 
     * @param columnNum column where the checker is to be placed.
     * @param playerId  the player id (either 1 or 2).
     */
    private void addToBoard(int columnNum, int playerId) {
        for (int row = ROW_SIZE - 1; row >= 0; row--) {
            if (this.gameBoard[row][columnNum] == 0) {
                this.gameBoard[row][columnNum] = playerId;
                checkerCount++;
                break;
            }
        }
    }

    /**
     * Determines if the board is full.
     * 
     * @return {@code true} if the board is full, otherwise {@code false}.
     */
    private boolean isBoardFull() {
        return checkerCount == ROW_SIZE * COL_SIZE;
    }

    /**
     * Retrieves the ID of the potential winning player.
     * 
     * This method assumes that if a win condition is met, the current player
     * would be the one who just played and won the game.
     * 
     * @return the potential winner's ID in integer.
     */
    private int getWinner() {
        return currentPlayer == 1 ? 2 : 1;
    }

    /**
     * Determines if there are four vertically connected checkers on the board.
     *
     * @return {@code true} if there are four vertically connected checkers,
     *         {@code false}
     *         otherwise.
     */
    private boolean verticalHasWinner() {
        for (int col = 0; col < COL_SIZE; ++col) {
            for (int row = 0; row < ROW_SIZE - WIN_SIZE + 1; ++row) {
                int curr = this.gameBoard[row][col];
                if (curr == 0) {
                    continue;
                }
                int k;
                for (k = 1; k < WIN_SIZE; ++k) {
                    if (curr != this.gameBoard[row + k][col]) {
                        break;
                    }
                }
                if (k == WIN_SIZE) {
                    return true;
                }
                row = row + k;
            }
        }
        return false;
    }

    /**
     * Determines if there are four horizontally connected checkers on the board.
     *
     * @return {@code true} if there are four horizontally connected checkers,
     *         {@code false}
     *         otherwise.
     */
    private boolean horizontalHasWinner() {
        for (int row = 0; row < ROW_SIZE; ++row) {
            for (int col = 0; col < COL_SIZE - WIN_SIZE + 1; ++col) {
                int curr = this.gameBoard[row][col];
                if (curr == 0) {
                    continue;
                }
                int k;
                for (k = 1; k < WIN_SIZE; ++k) {
                    if (curr != this.gameBoard[row][col + k]) {
                        break;
                    }
                }
                if (k == WIN_SIZE) {
                    return true;
                }
                col = col + k;
            }
        }
        return false;
    }

    /**
     * Determines if there are four diagonally connected checkers
     * in the south-west to north-east direction.
     *
     * @return {@code true} if there are four diagonally connected checkers in the
     *         south-west to north-east direction, {@code false} otherwise.
     */
    private boolean diagonalSWNEHasWinner() {
        for (int row = ROW_SIZE - WIN_SIZE + 1; row < ROW_SIZE; ++row) {
            for (int col = 0; col < COL_SIZE - WIN_SIZE + 1; ++col) {
                int curr = this.gameBoard[row][col];
                if (curr == 0) {
                    continue;
                }
                int k;
                for (k = 1; k < WIN_SIZE; ++k) {
                    if (curr != this.gameBoard[row - k][col + k]) {
                        break;
                    }
                }
                if (k == WIN_SIZE) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Determines if there are four diagonally connected checkers
     * in the north-west to south-east direction.
     *
     * @return {@code true} if there are four diagonally connected checkers in the
     *         north-west to south-east direction, {@code false} otherwise.
     */
    private boolean diagonalNWSEHasWinner() {
        for (int row = 0; row < ROW_SIZE - WIN_SIZE + 1; ++row) {
            for (int col = 0; col < COL_SIZE - WIN_SIZE + 1; ++col) {
                int curr = this.gameBoard[row][col];
                if (curr == 0) {
                    continue;
                }
                int k;
                for (k = 1; k < WIN_SIZE; ++k) {
                    if (curr != this.gameBoard[row + k][col + k]) {
                        break;
                    }
                }
                if (k == WIN_SIZE) {
                    return true;
                }
            }
        }
        return false;
    }
}
