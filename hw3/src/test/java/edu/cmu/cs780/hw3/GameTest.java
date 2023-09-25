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

    // @Test
    // public void testPlayFirstMoveSeed4() {
    // // given
    // int[][] newBoard = new int[ROW_SIZE][COL_SIZE];
    // int checkersCount = 0;
    // Player[] players = { new HumanPlayer(1), new HumanPlayer(2) };
    // Player p1 = new HumanPlayer(1);
    // Player p2 = new HumanPlayer(2);
    // Game game = new Game(p1, p2, newBoard, checkersCount, players);

    // // when
    // String colNum = "1";
    // String data = colNum;
    // InputStream in = new ByteArrayInputStream(data.getBytes());
    // System.setIn(in);
    // game.play();

    // // then
    // String expected = "0000000\n" +
    // "0000000\n" +
    // "0000000\n" +
    // "0000000\n" +
    // "0000000\n" +
    // "2000000\n";
    // assertEquals(expected, game.prettyPrintBoard());
    // }

}
