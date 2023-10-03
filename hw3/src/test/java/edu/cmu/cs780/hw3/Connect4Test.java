package edu.cmu.cs780.hw3;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Random;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

/**
 * Unit test for simple App.
 */
public class Connect4Test {
    private static final int SEED = 4;

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
        Random random = new Random(SEED);
        Connect4 game = new Connect4(random);

        // when
        int col = 0;
        game.placeChecker(col);

        // then
        String expected = "0000000\n" +
                "0000000\n" +
                "0000000\n" +
                "0000000\n" +
                "0000000\n" +
                "2000000\n";
        assertEquals(expected, game.toString());
    }

    @Test
    public void testPlaceCheckerSecondMove() {
        // given
        Random random = new Random(SEED);
        Connect4 game = new Connect4(random);

        // when
        int col = 0;
        game.placeChecker(col);
        game.placeChecker(col);

        // then
        String expected = "0000000\n" +
                "0000000\n" +
                "0000000\n" +
                "0000000\n" +
                "1000000\n" +
                "2000000\n";
        assertEquals(expected, game.toString());
    }

    @Test
    public void testPlaceCheckerWinMove() {
        // given
        Random random = new Random(SEED);
        Connect4 game = new Connect4(random);
        game.placeChecker(1); // 2
        game.placeChecker(0); // 1
        game.placeChecker(1); // 2
        game.placeChecker(0); // 1
        game.placeChecker(1); // 2
        game.placeChecker(0); // 1
        // { 0, 0, 0, 0, 0, 0, 0 },
        // { 0, 0, 0, 0, 0, 0, 0 },
        // { 0, 0, 0, 0, 0, 0, 0 },
        // { 1, 2, 0, 0, 0, 0, 0 },
        // { 1, 2, 0, 0, 0, 0, 0 },
        // { 1, 2, 0, 0, 0, 0, 0 }

        // when
        int col = 1;
        game.placeChecker(col); // player 2 place at col 1

        // then
        String expected = "0000000\n" +
                "0000000\n" +
                "0200000\n" +
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
        Random random = new Random(SEED);
        Connect4 game = new Connect4(random);
        game.placeChecker(1); // 2
        game.placeChecker(0); // 1
        game.placeChecker(1); // 2
        game.placeChecker(0); // 1
        game.placeChecker(1); // 2
        game.placeChecker(0); // 1
        game.placeChecker(0); // 2
        game.placeChecker(1); // 1
        game.placeChecker(0); // 2
        game.placeChecker(1); // 1
        game.placeChecker(0); // 2
        // { 2, 0, 0, 0, 0, 0, 0 },
        // { 2, 1, 0, 0, 0, 0, 0 },
        // { 2, 1, 0, 0, 0, 0, 0 },
        // { 1, 2, 0, 0, 0, 0, 0 },
        // { 1, 2, 0, 0, 0, 0, 0 },
        // { 1, 2, 0, 0, 0, 0, 0 }

        // when
        int col = 0;
        game.placeChecker(col); // player 1 place at col 0

        // then
        String expected = "This column is invalid. Please pick another one.";
        assertEquals(expected, outputStreamCaptor.toString().trim());
    }

    @Test
    public void testCanPlaceChecker2() {
        // given
        Random random = new Random(SEED);
        Connect4 game = new Connect4(random);
        game.placeChecker(1); // 2
        game.placeChecker(0); // 1
        game.placeChecker(1); // 2
        game.placeChecker(0); // 1
        game.placeChecker(1); // 2
        game.placeChecker(0); // 1
        game.placeChecker(0); // 2
        game.placeChecker(1); // 1
        game.placeChecker(0); // 2
        game.placeChecker(1); // 1
        game.placeChecker(0); // 2
        // { 2, 0, 0, 0, 0, 0, 0 },
        // { 2, 1, 0, 0, 0, 0, 0 },
        // { 2, 1, 0, 0, 0, 0, 0 },
        // { 1, 2, 0, 0, 0, 0, 0 },
        // { 1, 2, 0, 0, 0, 0, 0 },
        // { 1, 2, 0, 0, 0, 0, 0 }

        // when
        int col = 7;
        game.placeChecker(col); // player 1 place at col 7

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

    @Test
    public void testCurrentPlayer() {
        // given
        Random random = new Random(SEED);
        Connect4 game = new Connect4(random);
        game.placeChecker(1); // 2

        // when
        int actual = game.getCurrentPlayer();

        // then
        int expected = 1;
        assertEquals(expected, actual);
    }

}
