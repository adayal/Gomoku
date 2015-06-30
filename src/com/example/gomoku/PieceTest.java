package com.example.gomoku;
/**
 *  Tests the Piece class
 *
 * @author Amit
 * @author Gina
 * @author jay
 * @version Nov 8, 2014
 */
public class PieceTest extends student.TestCase
{
    /**
     * tests the only method of the piece class
     */
    public void testPieceClass()
    {
        Piece piece = new Piece("Red");
        assertEquals("Red", piece.getPieceColor());
    }
}
