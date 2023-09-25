package edu.cmu.cs780.hw3;

/**
 * Homework 3
 * 
 * @author Zihao Yang (zihaoy2@andrew.cmu.edu), Yun Lee (yunl3@andrew.cmu.edu)
 */
public interface Player {
    /**
     * A method that let the player to select a
     * column number to add checker to the board.
     */
    int selectColumn();

    /**
     * A method that returns the player ID.
     */
    int getPlayerId();
}
