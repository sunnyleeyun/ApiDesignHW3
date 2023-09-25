package edu.cmu.cs780.hw3;

import java.util.Random;

/**
 * Homework 3
 * 
 * @author Zihao Yang (zihaoy2@andrew.cmu.edu), Yun Lee (yunl3@andrew.cmu.edu)
 */
public class Game {
    /**
     * Board variable representing the board of this game.
     */
    private Board gameBoard;
    /**
     * Player variable representing the first player of this game.
     */
    private Player playerA;
    /**
     * Player variable representing the second player of this game.
     */
    private Player playerB;
    /**
     * Player variable representing the player in turn.
     */
    private Player playerInTurn;
    /**
     * Integer variable indicating the number of checkers.
     */
    private int checkerCount;

    /**
     * Constructor to create a Game using two players.
     * 
     * @param firstPlayer  The first player of this game
     * @param secondPlayer The second player of this game
     */
    public Game(Player firstPlayer, Player secondPlayer) {
        this.gameBoard = new Board();
        this.playerA = firstPlayer;
        this.playerB = secondPlayer;
        this.playerInTurn = playFirst(firstPlayer, secondPlayer);
        this.checkerCount = 0;
    }

    /**
     * Randomly decide which player plays first.
     * 
     * @param firstPlayer  The first player of this game
     * @param secondPlayer The second player of this game
     * @return The player that plays first
     */
    private Player playFirst(Player firstPlayer, Player secondPlayer) {
        Random rand = new Random();
        int value = 1 + rand.nextInt(2);
        if (value == 1) {
            return firstPlayer;
        } else {
            return secondPlayer;
        }
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
        return (this.gameBoard.checkDirections(lastRow, lastCol, this.playerInTurn.getPlayerId()));
    }

    // TO DO: play the game
    public void startGame() {

    }
}
