package com.example.gomoku;
/**
 *  Holds a piece with a specific color
 *
 * @author Amit
 * @author Gina
 * @author jay
 *  @version Nov 8, 2014
 */
public class Piece
{
    private String type;

    /**
     * Creates a piece with a specific color
     * @param color of the piece
     */
    public Piece(String color)
    {
        type = color;
    }

    /**
     * Get the color a specific piece
     * @return the string representation of the color
     */
    public String getPieceColor()
    {
        return type;
    }
}
