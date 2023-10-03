package edu.cmu.cs780.hw3;

import java.util.Random;

/**
 * Homework 3
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

    private Random random;

    /**
     * Initializes the game with an empty board, and randomly decides the starting
     * player.
     */
    public Connect4() {
        this.random = new Random();
        int[][] newBoard = new int[ROW_SIZE][COL_SIZE];
        this.gameBoard = newBoard;
        this.checkerCount = 0;
        this.currentPlayer = selectStartPlayer();
    }

    /**
     * Initializes the game with an empty board, and randomly decides the starting
     * player.
     */
    public Connect4(Random random) {
        this.random = random;
        int[][] newBoard = new int[ROW_SIZE][COL_SIZE];
        this.gameBoard = newBoard;
        this.checkerCount = 0;
        this.currentPlayer = selectStartPlayer();
    }

    /**
     * Determines if the game has reached an end condition or it can be continued.
     * This method also prints out the game status to the console.
     *
     * @return {@code true} if the game has reached a draw or a win condition,
     *         {@code false} if
     *         the game is not over.
     */
    public boolean isGameOver() {
        if (isBoardFull()) {
            System.out.println("It's a draw! \n");
            return true;
        } else if (hasWinner()) {
            System.out.println("Winner is " + getWinner() + "! Good Game! \n");
            return true;
        } else {
            System.out.println("Player " + currentPlayer + ". Pick a column.");
            return false;
        }
    }

    /**
     * Retrieves the current player's identifier.
     *
     * @return An integer representing current player, which can be either 1 or 2.
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
        int value = 1 + random.nextInt(2);
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
    public boolean hasWinner() {
        return verticalHasWinner() || horizontalHasWinner() || diagonalSWNEHasWinner()
                || diagonalNWSEHasWinner();
    }

    /**
     * Places a checker in the specified column.
     * 
     * If the chosen column is valid (not full), the current player's checker is
     * added to the game board and the role is switched to the other player. If
     * the chosen column is already full, an appropriate message is displayed.
     * 
     * @param columnNum the index-based column number (0 to 6 inclusive) where
     *                  the current player wants to place their checker.
     */
    public void placeChecker(int columnNum) {
        if (isColumnAvailable(columnNum)) {
            addToBoard(columnNum, currentPlayer);
            switchRole();
        } else {
            System.out.println("This column is invalid. Please pick another one.\n");
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
