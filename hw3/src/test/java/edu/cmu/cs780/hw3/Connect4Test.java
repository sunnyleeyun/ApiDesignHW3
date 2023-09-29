package edu.cmu.cs780.hw3;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class Connect4Test {
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
    public void testPlaceCheckerFirstMove() {
        // given
        int[][] newBoard = new int[ROW_SIZE][COL_SIZE];
        int checkersCount = 0;
        int currentPlayer = 1;
        Connect4 game = new Connect4(newBoard, checkersCount, currentPlayer);

        // when
        int col = 0;
        game.placeChecker(col);

        // then
        String expected = "0000000\n" +
                "0000000\n" +
                "0000000\n" +
                "0000000\n" +
                "0000000\n" +
                "1000000\n";
        assertEquals(expected, game.toString());
    }

    @Test
    public void testPlaceCheckerSecondMove() {
        // given
        int[][] newBoard = new int[ROW_SIZE][COL_SIZE];
        int checkersCount = 1;
        int currentPlayer = 1;
        Connect4 game = new Connect4(newBoard, checkersCount, currentPlayer);

        // when
        int col = 0;
        game.placeChecker(col);
        game.placeChecker(col);

        // then
        String expected = "0000000\n" +
                "0000000\n" +
                "0000000\n" +
                "0000000\n" +
                "2000000\n" +
                "1000000\n";
        assertEquals(expected, game.toString());
    }

    @Test
    public void testPlaceCheckerWinMove() {
        // given
        int newBoard[][] = {
                { 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0 },
                { 1, 2, 0, 0, 0, 0, 0 },
                { 1, 2, 0, 0, 0, 0, 0 },
                { 1, 2, 0, 0, 0, 0, 0 }
        };
        int checkersCount = 6;
        int currentPlayer = 1;
        Connect4 game = new Connect4(newBoard, checkersCount, currentPlayer);

        // when
        int col = 0;
        game.placeChecker(col);

        // then
        String expected = "0000000\n" +
                "0000000\n" +
                "1000000\n" +
                "1200000\n" +
                "1200000\n" +
                "1200000\n";
        assertEquals(expected, game.toString());
        assertFalse(!game.isGameOver());
        assertTrue(game.hasWinner());
    }

    @Test
    public void testCanPlaceChecker1() {
        // given
        int newBoard[][] = {
                { 2, 0, 0, 0, 0, 0, 0 },
                { 2, 1, 0, 0, 0, 0, 0 },
                { 2, 1, 0, 0, 0, 0, 0 },
                { 1, 2, 0, 0, 0, 0, 0 },
                { 1, 2, 0, 0, 0, 0, 0 },
                { 1, 2, 0, 0, 0, 0, 0 }
        };
        int checkersCount = 11;
        int currentPlayer = 1;
        Connect4 game = new Connect4(newBoard, checkersCount, currentPlayer);

        // when
        int col = 0;
        game.placeChecker(col);

        // then
        String expected = "This column is invalid. Please pick another one.";
        assertEquals(expected, outputStreamCaptor.toString().trim());
    }

    @Test
    public void testCanPlaceChecker2() {
        // given
        int newBoard[][] = {
                { 2, 0, 0, 0, 0, 0, 0 },
                { 2, 1, 0, 0, 0, 0, 0 },
                { 2, 1, 0, 0, 0, 0, 0 },
                { 1, 2, 0, 0, 0, 0, 0 },
                { 1, 2, 0, 0, 0, 0, 0 },
                { 1, 2, 0, 0, 0, 0, 0 }
        };
        int checkersCount = 11;
        int currentPlayer = 1;
        Connect4 game = new Connect4(newBoard, checkersCount, currentPlayer);

        // when
        int col = 7;
        game.placeChecker(col);

        // then
        String expected = "This column is invalid. Please pick another one.";
        assertEquals(expected, outputStreamCaptor.toString().trim());
    }

    @Test
    public void testHasWinner1() {
        // given
        // when
        Connect4 game = new Connect4();
        int gameboard[][] = {
                { 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0 },
                { 2, 2, 2, 0, 0, 0, 0 },
                { 1, 1, 1, 1, 0, 0, 0 }
        };

        // then
        assertTrue(game.hasWinner(gameboard));
    }

    @Test
    public void testHasWinner2() {
        // given
        // when
        Connect4 game = new Connect4();
        int gameboard[][] = {
                { 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0 },
                { 1, 0, 0, 0, 0, 0, 0 },
                { 1, 2, 0, 0, 0, 0, 0 },
                { 1, 2, 0, 0, 0, 0, 0 },
                { 1, 2, 0, 0, 0, 0, 0 }
        };

        // then
        assertTrue(game.hasWinner(gameboard));
    }

    @Test
    public void testHasWinner3() {
        // given
        // when
        Connect4 game = new Connect4();
        int gameboard[][] = {
                { 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0 },
                { 2, 2, 2, 0, 0, 0, 0 },
                { 0, 0, 0, 1, 1, 1, 1 }
        };

        // then
        assertTrue(game.hasWinner(gameboard));
    }

    @Test
    public void testHasWinner4() {
        // given
        // when
        Connect4 game = new Connect4();
        int gameboard[][] = {
                { 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 1 },
                { 0, 2, 0, 0, 0, 0, 1 },
                { 0, 2, 0, 0, 0, 0, 1 },
                { 0, 2, 0, 0, 0, 0, 1 }
        };

        // then
        assertTrue(game.hasWinner(gameboard));
    }

    @Test
    public void testHasWinner5() {
        // given
        // when
        Connect4 game = new Connect4();
        int gameboard[][] = {
                { 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 1, 0, 0, 0 },
                { 0, 0, 1, 1, 0, 0, 0 },
                { 2, 1, 1, 2, 0, 0, 0 },
                { 1, 2, 2, 2, 0, 0, 0 }
        };

        // then
        assertTrue(game.hasWinner(gameboard));
    }

    @Test
    public void testHasWinner6() {
        // given
        // when
        Connect4 game = new Connect4();
        int gameboard[][] = {
                { 0, 0, 0, 0, 0, 0, 0 },
                { 0, 0, 0, 0, 0, 0, 0 },
                { 1, 0, 0, 0, 0, 0, 0 },
                { 2, 1, 0, 0, 0, 0, 0 },
                { 2, 1, 1, 2, 0, 0, 0 },
                { 1, 2, 2, 1, 0, 0, 0 }
        };

        // then
        assertTrue(game.hasWinner(gameboard));
    }

    @Test
    public void testEmptyPrettyPrintBoard() {
        // given
        // when
        Connect4 game = new Connect4();

        // then
        String expected = "0000000\n" +
                "0000000\n" +
                "0000000\n" +
                "0000000\n" +
                "0000000\n" +
                "0000000\n";
        assertEquals(expected, game.toString());
    }

}
