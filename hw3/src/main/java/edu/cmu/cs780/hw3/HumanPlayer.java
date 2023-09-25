package edu.cmu.cs780.hw3;

import java.util.Scanner;

/**
 * Homework 3
 * 
 * @author Zihao Yang (zihaoy2@andrew.cmu.edu), Yun Lee (yunl3@andrew.cmu.edu)
 */
public class HumanPlayer implements Player {
    /**
     * Integer variable for player ID.
     */
    private int playerId;

    /**
     * Constructor to create a human player with input player ID.
     * 
     * @param id Integer representation of the player id
     */
    public HumanPlayer(int id) {
        this.playerId = id;
    }

    /**
     * A method that asks the player to input a
     * column number between 1 and 7.
     * 
     * @return Integer representation of the chosen column number.
     */
    @Override
    public int selectColumn() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter a column (1-7) for your move: ");
        while (true) {
            try {
                int columnChoice = scanner.nextInt();
                if (columnChoice >= 1 && columnChoice <= 7) {
                    scanner.close();
                    return columnChoice;
                } else {
                    System.out.println("Invalid choice. Please enter a number between 1 to 7.");
                }
            } catch (Exception e) {
                System.out.println("Invalid input. Please enter a number between 1 to 7.");
                // Clear the invalid input
                scanner.next();
            }
            System.out.print("Enter a column (0-6) for your move: ");
        }
    }

    /**
     * Returns the player ID of a Player object.
     * 
     * @return Integer representation of player ID
     */
    @Override
    public int getPlayerId() {
        return this.playerId;
    }
}
