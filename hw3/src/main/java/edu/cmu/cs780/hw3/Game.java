package edu.cmu.cs780.hw3;

import java.util.Random;

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

    private static final int SEED = 4;

    /**
     * 2D array variable indicating the board of the game.
     */
    private int[][] gameBoard;

    /**
     * Integer variable indicating the number of checkers.
     */
    private int checkersCount;

    private int currentPlayer;

    public Game() {
        int[][] newBoard = new int[ROW_SIZE][COL_SIZE];
        this.gameBoard = newBoard;
        this.checkersCount = 0;
        this.currentPlayer = randomStart();
    }

    public Game(int[][] newBoard, int checkersCount, int currentPlayer) {
        this.gameBoard = newBoard;
        this.checkersCount = checkersCount;
        this.currentPlayer = currentPlayer;
    }

    public boolean isNotOver() {
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
     * Return 1 or 2 randomly selected which player plays first.
     * 
     * @return
     */
    private int randomStart() {
        Random rand = new Random(SEED);
        int value = 1 + rand.nextInt(2);
        return value;
    }

    /**
     * Return string of pretty printed board.
     */
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

    /**
     * Return true if current row is not full, else false.
     * 
     * @param col The column number of the new checker
     */
    public boolean canPlaceChecker(int col) {
        return this.gameBoard[0][col] == 0;
    }

    /**
     * Return true if board is full, else false.
     */
    public boolean hasNoEmptySpace() {
        return checkersCount == ROW_SIZE * COL_SIZE;
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
     * Check whether the current player wins after it add a checker to
     * the board.
     * 
     * @return true if the current player wins, return false if it is not.
     */
    public boolean hasWinner() {
        return hasWinner(gameBoard);
    }

    public boolean hasWinner(int[][] gameBoard) {
        return verticalHasWinner(gameBoard) || horizontalHasWinner(gameBoard) || diagonalSWNEHasWinner(gameBoard)
                || diagonalNWSEHasWinner(gameBoard);
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
    public void placeChecker(int col) {
        if (canPlaceChecker(col)) {
            addToBoard(col, currentPlayer);
            switchRole();
        } else {
            System.out.println("This column is full.\n");
        }
    }

    private void switchRole() {
        this.currentPlayer = this.currentPlayer == 1 ? 2 : 1;
    }

    /**
     * Add a new checker (represented by playerNum) to the board.
     * 
     * @param col       The column number of the new checker
     * @param playerNum The player number (1 or 2)
     */
    private void addToBoard(int col, int playerNum) {
        for (int row = ROW_SIZE - 1; row >= 0; row--) {
            if (this.gameBoard[row][col] == 0) {
                this.gameBoard[row][col] = playerNum;
                checkersCount++;
                break;
            }
        }
    }

    /**
     * Return true if board is full, else false.
     */
    private boolean boardIsFull() {
        return checkersCount == ROW_SIZE * COL_SIZE;
    }

    private int winnerPlayer() {
        return currentPlayer == 1 ? 2 : 1;
    }

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
