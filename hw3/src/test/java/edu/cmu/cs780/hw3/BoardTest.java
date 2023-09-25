package edu.cmu.cs780.hw3;

import static org.junit.Assert.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
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
        // given
        // when
        Board board = new Board();

        // then
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
        // given
        int[][] newBoard = new int[ROW_SIZE][COL_SIZE];
        int checkersCount = 0;
        Player[] players = { new HumanPlayer(1), new HumanPlayer(2) };
        Board board = new Board(newBoard, checkersCount, players);

        // when
        String data = "1";
        InputStream in = new ByteArrayInputStream(data.getBytes());
        System.setIn(in);
        board.play();

        // then
        String expected = "0000000\n" +
                "0000000\n" +
                "0000000\n" +
                "0000000\n" +
                "0000000\n" +
                "1000000\n";
        assertEquals(expected, board.prettyPrintBoard());
    }

    @Test
    public void testPlaySecondMove() {
        // given
        int[][] newBoard = new int[ROW_SIZE][COL_SIZE];
        // first player already put a checker at column 0
        newBoard[ROW_SIZE - 1][0] = 1;
        int checkersCount = 1;
        Player[] players = { new HumanPlayer(1), new HumanPlayer(2) };
        Board board = new Board(newBoard, checkersCount, players);

        // when
        String data = "1";
        InputStream in = new ByteArrayInputStream(data.getBytes());
        System.setIn(in);
        board.play();

        // then
        String expected = "0000000\n" +
                "0000000\n" +
                "0000000\n" +
                "0000000\n" +
                "2000000\n" +
                "1000000\n";
        assertEquals(expected, board.prettyPrintBoard());
    }

    @Test
    public void testPlayCannotPlaceChestAtColumn() {
        // given
        int[][] newBoard = new int[ROW_SIZE][COL_SIZE];
        for (int i = 0; i < ROW_SIZE; ++i) {
            newBoard[i][0] = 1;
        }
        int checkersCount = ROW_SIZE;
        Player[] players = { new HumanPlayer(1), new HumanPlayer(2) };
        Board board = new Board(newBoard, checkersCount, players);

        // when
        String data = "1";
        InputStream in = new ByteArrayInputStream(data.getBytes());
        System.setIn(in);
        board.play();

        // then
        String expected = "Enter a column (1-7) for your move: \n" + "This column is full!";
        assertEquals(expected, outputStreamCaptor.toString().trim());
    }

    @Test
    public void testPlayGameIsOver() {
        // given
        int[][] newBoard = new int[ROW_SIZE][COL_SIZE];
        for (int i = 0; i < ROW_SIZE; ++i) {
            for (int j = 0; j < COL_SIZE; ++j) {
                newBoard[i][0] = 9;
            }
        }
        int checkersCount = ROW_SIZE * COL_SIZE;
        Player[] players = { new HumanPlayer(1), new HumanPlayer(2) };
        Board board = new Board(newBoard, checkersCount, players);

        // when
        board.play();

        // then
        String expected = "Game is over. Please start another game.";
        assertEquals(expected, outputStreamCaptor.toString().trim());
    }

}
