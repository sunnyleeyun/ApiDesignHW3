package edu.cmu.cs780.hw3;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Rule;
import org.junit.Test;

public class BoardTest {
    private static final int ROW_SIZE = 6;
    private static final int COL_SIZE = 7;

    @Test
    public void testEmptyOutputBoard() {
        Board board = new Board();
        String expected = "0000000\n" +
                "0000000\n" +
                "0000000\n" +
                "0000000\n" +
                "0000000\n" +
                "0000000\n";
        assertEquals(expected, board.outputBoard());
    }

    @Test
    public void testPlayFirstMove() {
        int[][] newBoard = new int[ROW_SIZE][COL_SIZE];
        Player[] players = { new HumanPlayer(1), new HumanPlayer(2) };
        Board board = new Board(newBoard, players);
        board.play();
        String expected = "0000000\n" +
                "0000000\n" +
                "0000000\n" +
                "0000000\n" +
                "0000000\n" +
                "1000000\n";
        System.out.println(board.outputBoard());
        assertEquals(expected, board.outputBoard());
    }

}
