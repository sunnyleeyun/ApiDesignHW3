package edu.cmu.cs780.hw3;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class GameTest {
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
    public void testHasWinner1() {
        // given
        // when
        Game game = new Game();
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
        Game game = new Game();
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
        Game game = new Game();
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
        Game game = new Game();
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
        Game game = new Game();
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
        Game game = new Game();
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
        Game game = new Game();

        // then
        String expected = "0000000\n" +
                "0000000\n" +
                "0000000\n" +
                "0000000\n" +
                "0000000\n" +
                "0000000\n";
        assertEquals(expected, game.prettyPrintBoard());
    }

    @Test
    public void testPlaceCheckerFirstMove() {
        // given
        int[][] newBoard = new int[ROW_SIZE][COL_SIZE];
        int checkersCount = 0;
        int currentPlayer = 1;
        Game game = new Game(newBoard, checkersCount, currentPlayer);

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
        assertEquals(expected, game.prettyPrintBoard());
    }

    @Test
    public void testPlaceCheckerSecondMove() {
        // given
        int[][] newBoard = new int[ROW_SIZE][COL_SIZE];
        int checkersCount = 0;
        int currentPlayer = 1;
        Game game = new Game(newBoard, checkersCount, currentPlayer);

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
        assertEquals(expected, game.prettyPrintBoard());
    }

}
