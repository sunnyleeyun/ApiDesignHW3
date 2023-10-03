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
        int startPlayer = 2;
        Connect4 game = new Connect4(startPlayer);

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
        int startPlayer = 2;
        Connect4 game = new Connect4(startPlayer);

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
        int startPlayer = 2;
        Connect4 game = new Connect4(startPlayer);

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
    }

    @Test(expected = Exception.class)
    public void testCanPlaceChecker1() throws Exception {
        // given
        int startPlayer = 2;
        Connect4 game = new Connect4(startPlayer);
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
        // exceptions
    }

    @Test(expected = Exception.class)
    public void testCanPlaceChecker2() throws Exception {
        // given
        int startPlayer = 2;
        Connect4 game = new Connect4(startPlayer);
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
        // exceptions
    }

    @Test
    public void testHasWinner1() {
        // given
        // when
        int startPlayer = 2;
        Connect4 game = new Connect4(startPlayer);
        game.placeChecker(0); // 2
        game.placeChecker(0); // 1
        game.placeChecker(1); // 2
        game.placeChecker(1); // 1
        game.placeChecker(2); // 2
        game.placeChecker(2); // 1
        game.placeChecker(3); // 2
        // { 0, 0, 0, 0, 0, 0, 0 },
        // { 0, 0, 0, 0, 0, 0, 0 },
        // { 0, 0, 0, 0, 0, 0, 0 },
        // { 0, 0, 0, 0, 0, 0, 0 },
        // { 1, 1, 1, 0, 0, 0, 0 },
        // { 2, 2, 2, 2, 0, 0, 0 }

        // then
        assertTrue(game.isGameOver());
    }

    @Test
    public void testHasWinner2() {
        // given
        // when
        int startPlayer = 2;
        Connect4 game = new Connect4(startPlayer);
        game.placeChecker(0); // 2
        game.placeChecker(1); // 1
        game.placeChecker(0); // 2
        game.placeChecker(1); // 1
        game.placeChecker(0); // 2
        game.placeChecker(1); // 1
        game.placeChecker(0); // 2
        // { 0, 0, 0, 0, 0, 0, 0 },
        // { 0, 0, 0, 0, 0, 0, 0 },
        // { 2, 0, 0, 0, 0, 0, 0 },
        // { 2, 1, 0, 0, 0, 0, 0 },
        // { 2, 1, 0, 0, 0, 0, 0 },
        // { 2, 1, 0, 0, 0, 0, 0 }

        // then
        assertTrue(game.isGameOver());
    }

    @Test
    public void testHasWinner3() {
        // given
        // when
        int startPlayer = 2;
        Connect4 game = new Connect4(startPlayer);
        game.placeChecker(3); // 2
        game.placeChecker(3); // 1
        game.placeChecker(4); // 2
        game.placeChecker(4); // 1
        game.placeChecker(5); // 2
        game.placeChecker(5); // 1
        game.placeChecker(6); // 2
        // { 0, 0, 0, 0, 0, 0, 0 },
        // { 0, 0, 0, 0, 0, 0, 0 },
        // { 0, 0, 0, 0, 0, 0, 0 },
        // { 0, 0, 0, 0, 0, 0, 0 },
        // { 0, 0, 0, 1, 1, 1, 0 },
        // { 0, 0, 0, 2, 2, 2, 2 }

        // then
        assertTrue(game.isGameOver());
    }

    @Test
    public void testHasWinner4() {
        // given
        // when
        int startPlayer = 2;
        Connect4 game = new Connect4(startPlayer);
        game.placeChecker(6); // 2
        game.placeChecker(1); // 1
        game.placeChecker(6); // 2
        game.placeChecker(1); // 1
        game.placeChecker(6); // 2
        game.placeChecker(1); // 1
        game.placeChecker(6); // 2
        // { 0, 0, 0, 0, 0, 0, 0 },
        // { 0, 0, 0, 0, 0, 0, 0 },
        // { 0, 0, 0, 0, 0, 0, 2 },
        // { 0, 1, 0, 0, 0, 0, 2 },
        // { 0, 1, 0, 0, 0, 0, 2 },
        // { 0, 1, 0, 0, 0, 0, 2 }

        // then
        assertTrue(game.isGameOver());
    }

    @Test
    public void testHasWinner5() {
        // given
        // when
        int startPlayer = 2;
        Connect4 game = new Connect4(startPlayer);
        game.placeChecker(0); // 2
        game.placeChecker(1); // 1
        game.placeChecker(1); // 2
        game.placeChecker(0); // 1
        game.placeChecker(2); // 2
        game.placeChecker(2); // 1
        game.placeChecker(2); // 2
        game.placeChecker(2); // 1
        game.placeChecker(3); // 2
        game.placeChecker(3); // 1
        game.placeChecker(2); // 2
        game.placeChecker(3); // 1
        game.placeChecker(3); // 2
        // { 0, 0, 0, 0, 0, 0, 0 },
        // { 0, 0, 0, 0, 0, 0, 0 },
        // { 0, 0, 0, 2, 0, 0, 0 },
        // { 0, 0, 2, 1, 0, 0, 0 },
        // { 1, 2, 1, 1, 0, 0, 0 },
        // { 2, 1, 2, 2, 0, 0, 0 }

        // then
        assertTrue(game.isGameOver());
    }

    @Test
    public void testHasWinner6() {
        // given
        // when
        int startPlayer = 2;
        Connect4 game = new Connect4(startPlayer);
        game.placeChecker(3); // 2
        game.placeChecker(3); // 1
        game.placeChecker(2); // 2
        game.placeChecker(1); // 1
        game.placeChecker(2); // 2
        game.placeChecker(1); // 1
        game.placeChecker(1); // 2
        game.placeChecker(0); // 1
        game.placeChecker(0); // 2
        game.placeChecker(0); // 1
        game.placeChecker(0); // 2
        // { 0, 0, 0, 0, 0, 0, 0 },
        // { 0, 0, 0, 0, 0, 0, 0 },
        // { 2, 0, 0, 0, 0, 0, 0 },
        // { 1, 2, 0, 0, 0, 0, 0 },
        // { 2, 1, 2, 1, 0, 0, 0 },
        // { 1, 1, 2, 2, 0, 0, 0 }

        // then
        assertTrue(game.isGameOver());
    }

    @Test
    public void testHasWinner7() {
        // given
        // when
        int startPlayer = 2;
        Connect4 game = new Connect4(startPlayer);
        game.placeChecker(0); // 2
        game.placeChecker(3); // 1
        game.placeChecker(1); // 2
        game.placeChecker(4); // 1
        game.placeChecker(2); // 2
        game.placeChecker(5); // 1
        // { 0, 0, 0, 0, 0, 0, 0 },
        // { 0, 0, 0, 0, 0, 0, 0 },
        // { 0, 0, 0, 0, 0, 0, 0 },
        // { 0, 0, 0, 0, 0, 0, 0 },
        // { 0, 0, 0, 0, 0, 0, 0 },
        // { 2, 2, 2, 1, 1, 1, 0 }

        // then
        assertFalse(game.isGameOver());
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
        int startPlayer = 2;
        Connect4 game = new Connect4(startPlayer);
        game.placeChecker(1); // 2

        // when
        int actual = game.getCurrentPlayer();

        // then
        int expected = 1;
        assertEquals(expected, actual);
    }

}
