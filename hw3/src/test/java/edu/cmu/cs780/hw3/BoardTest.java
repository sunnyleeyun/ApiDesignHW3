package edu.cmu.cs780.hw3;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

public class BoardTest {
    private static final int ROW_SIZE = 6;
    private static final int COL_SIZE = 7;

    private final PrintStream standardOut = System.out;
    private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

    @Before
    public void setUp() {
        System.setOut(new PrintStream(outputStreamCaptor));
    }

    @After
    public void tearDown() {
        System.setOut(standardOut);
    }

    @Test
    public void testEmptyrettyPrintBoard() {
        Board board = new Board();
        String expected = "0000000\n" +
                "0000000\n" +
                "0000000\n" +
                "0000000\n" +
                "0000000\n" +
                "0000000\n";
        assertEquals(expected, board.prettyPrintBoard());
    }

    @Test
    public void testPlayFirstMove() {
        int[][] newBoard = new int[ROW_SIZE][COL_SIZE];
        int checkersCount = 0;
        Player[] players = { new HumanPlayer(1), new HumanPlayer(2) };
        Board board = new Board(newBoard, checkersCount, players);
        board.play();
        String expected = "0000000\n" +
                "0000000\n" +
                "0000000\n" +
                "0000000\n" +
                "0000000\n" +
                "1000000\n";
        assertEquals(expected, board.prettyPrintBoard());
    }

    @Test
    public void testPlayCannotPlaceChestAtColumn() {
        int[][] newBoard = new int[ROW_SIZE][COL_SIZE];
        for (int i = 0; i < ROW_SIZE; ++i) {
            newBoard[i][0] = 1;
        }
        int checkersCount = ROW_SIZE;
        Player[] players = { new HumanPlayer(1), new HumanPlayer(2) };
        Board board = new Board(newBoard, checkersCount, players);
        board.play();
        String expected = "This column is full!";
        assertEquals(expected, outputStreamCaptor.toString().trim());
    }

    @Test
    public void testPlayGameIsOver() {
        int[][] newBoard = new int[ROW_SIZE][COL_SIZE];
        for (int i = 0; i < ROW_SIZE; ++i) {
            for (int j = 0; j < COL_SIZE; ++j) {
                newBoard[i][0] = 9;
            }
        }
        int checkersCount = ROW_SIZE * COL_SIZE;
        Player[] players = { new HumanPlayer(1), new HumanPlayer(2) };
        Board board = new Board(newBoard, checkersCount, players);
        board.play();
        String expected = "Game is over. Please start another game.";
        assertEquals(expected, outputStreamCaptor.toString().trim());
    }

}
