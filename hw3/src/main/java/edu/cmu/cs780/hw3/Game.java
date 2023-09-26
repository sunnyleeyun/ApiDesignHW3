package edu.cmu.cs780.hw3;

import java.util.Random;

/**
 * Homework 3
 * 
 * @author Zihao Yang (zihaoy2@andrew.cmu.edu), Yun Lee (yunl3@andrew.cmu.edu)
 */
public class Game {
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
    private int checkersCount;
    /**
     * ID of the current player.
     */
    private int currentPlayer;

    /**
     * Initializes the game with an empty board, sets the checkers count to zero,
     * and randomly decides the starting player.
     */
    public Game() {
        int[][] newBoard = new int[ROW_SIZE][COL_SIZE];
        this.gameBoard = newBoard;
        this.checkersCount = 0;
        this.currentPlayer = randomStart();
    }

    /**
     * Constructor to create a Game instance using a provided game board, 
     * the current number of checkers, and the starting player.
     * 
     * @param newBoard      The board state of the game.
     * @param checkersCount Current number of checkers on the board.
     * @param currentPlayer The ID of the player set to play next.
     */
    public Game(int[][] newBoard, int checkersCount, int currentPlayer) {
        this.gameBoard = newBoard;
        this.checkersCount = checkersCount;
        this.currentPlayer = currentPlayer;
    }

    /**
     * Determines if the game can continue or if it has reached an end condition.
     * This method also prints out the game status to the console.
     *
     * @return Returns true if the game can continue, false if the game has reached a draw or a win condition.
     */
    public boolean canGameContinue() {
        if (boardIsFull()) {
            System.out.println("It's a draw! \n");
            return false;
        } else if (hasWinner()) {
            System.out.println("Winner is " + winnerPlayer() + "! Good Game! \n");
            return false;
        } else {
            System.out.println("Player " + currentPlayer + ". Pick a column.");
            return true;
        }
    }

    /**
     * Randomly selects which player starts first.
     * @return Returns an Integer representation of the player id (1 or 2) 
     * to start first.
     */
    private int randomStart() {
        Random rand = new Random();
        int value = 1 + rand.nextInt(2);
        return value;
    }

    /**
     * Transform the board into a string to be printed in the terminal.
     * @return Returns a String representation of pretty printed board.
     */
    public String prettyPrintBoard() {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < ROW_SIZE; ++i) {
            for (int j = 0; j < COL_SIZE; ++j) {
                res.append(gameBoard[i][j]);
            }
            res.append('\n');
        }
        return res.toString();
    }

    /**
     * Checks if a checker can be placed in the specified column.
     * @param col The column number to check (0 to 6 inclusive).
     * @return Returns true if the column is not full and can accept a checker, else false.
     */
    private boolean canPlaceChecker(int col) {
        return this.gameBoard[0][col] == 0 && (col >= 0 && col <= 6);
    }

    /**
     * Determines if there's a winner based on the current game board state.
     * 
     * @return true if the current player wins, false otherwise.
     */
    public boolean hasWinner() {
        return hasWinner(this.gameBoard);
    }

    /**
     * Determines if there's a winner on a given game board.
     * 
     * @param gameBoard The current state of the game board.
     * @return Returns true if there's a winner, false otherwise.
     */
    public boolean hasWinner(int[][] gameBoard) {
        return verticalHasWinner(gameBoard) || horizontalHasWinner(gameBoard) ||             diagonalSWNEHasWinner(gameBoard) || diagonalNWSEHasWinner(gameBoard);
    }

    /**
     * Places a checker in the specified column.
     * 
     * If the chosen column is valid (not full), the current player's checker is 
     * added to the game board and the role is switched to the other player. If 
     * the chosen column is already full, an appropriate message is displayed.
     * 
     * @param col The index-based column number where the current player wants 
     * to place their checker.
     */
    public void placeChecker(int col) {
        if (canPlaceChecker(col)) {
            addToBoard(col, currentPlayer);
            switchRole();
        } else {
            System.out.println("This column is full.\n");
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
     * @param col      Column where the checker is to be placed.
     * @param playerId The player id (either 1 or 2).
     */
    private void addToBoard(int col, int playerId) {
        for (int row = ROW_SIZE - 1; row >= 0; row--) {
            if (this.gameBoard[row][col] == 0) {
                this.gameBoard[row][col] = playerId;
                checkersCount++;
                break;
            }
        }
    }

    /**
     * Determines if the board is full.
     * 
     * @return Returns true if the board is full, otherwise false.
     */
    private boolean boardIsFull() {
        return checkersCount == ROW_SIZE * COL_SIZE;
    }

    /**
     * Retrieves the ID of the potential winning player.
     * 
     * This method assumes that if a win condition is met, the current player
     * would be the one who just played and won the game.
     * 
     * @return Integer representation of the potential winner's ID.
     */
    private int winnerPlayer() {
        return currentPlayer == 1 ? 2 : 1;
    }

    /**
     * Determines if there are four vertically connected checkers on the board.
     *
     * @param gameBoard 2D array representing the current state of the game board.
     * @return Returns true if there are four vertically connected checkers, false otherwise.
     */
    private boolean verticalHasWinner(int[][] gameBoard) {
        for (int col = 0; col < COL_SIZE; ++col) {
            for (int row = 0; row < ROW_SIZE - WIN_SIZE + 1; ++row) {
                int curr = gameBoard[row][col];
                if (curr == 0) {
                    continue;
                }
                int k;
                for (k = 1; k < WIN_SIZE; ++k) {
                    if (curr != gameBoard[row + k][col]) {
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
     * @param gameBoard 2D array representing the current state of the game board.
     * @return Returns true if there are four horizontally connected checkers, false otherwise.
     */
    private boolean horizontalHasWinner(int[][] gameBoard) {
        for (int row = 0; row < ROW_SIZE; ++row) {
            for (int col = 0; col < COL_SIZE - WIN_SIZE + 1; ++col) {
                int curr = gameBoard[row][col];
                if (curr == 0) {
                    continue;
                }
                int k;
                for (k = 1; k < WIN_SIZE; ++k) {
                    if (curr != gameBoard[row][col + k]) {
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
     * @param gameBoard 2D array representing the current state of the game board.
     * @return Returns true if there are four diagonally connected checkers in the 
     * south-west to north-east direction, false otherwise.
     */
    private boolean diagonalSWNEHasWinner(int[][] gameBoard) {
        for (int row = ROW_SIZE - WIN_SIZE + 1; row < ROW_SIZE; ++row) {
            for (int col = 0; col < COL_SIZE - WIN_SIZE + 1; ++col) {
                int curr = gameBoard[row][col];
                if (curr == 0) {
                    continue;
                }
                int k;
                for (k = 1; k < WIN_SIZE; ++k) {
                    if (curr != gameBoard[row - k][col + k]) {
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
     * @param gameBoard 2D array representing the current state of the game board.
     * @return true if there are four diagonally connected checkers in the 
     * north-west to south-east direction, false otherwise.
     */
    private boolean diagonalNWSEHasWinner(int[][] gameBoard) {
        for (int row = 0; row < ROW_SIZE - WIN_SIZE + 1; ++row) {
            for (int col = 0; col < COL_SIZE - WIN_SIZE + 1; ++col) {
                int curr = gameBoard[row][col];
                if (curr == 0) {
                    continue;
                }
                int k;
                for (k = 1; k < WIN_SIZE; ++k) {
                    if (curr != gameBoard[row + k][col + k]) {
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
