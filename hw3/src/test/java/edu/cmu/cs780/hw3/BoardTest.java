package edu.cmu.cs780.hw3;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class BoardTest {
    @Test
    public void testEmptyOutputBoard() {
        Board board = new Board();
        String expected = "0000000\n" +
                "0000000\n" +
                "0000000\n" +
                "0000000\n" +
                "0000000\n" +
                "0000000\n";
        assertEquals(board.outputBoard(), expected);
    }
}
