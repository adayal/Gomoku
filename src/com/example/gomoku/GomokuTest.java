package com.example.gomoku;

import java.util.Stack;
/**
 *  Tests the gomoku class
 *
 * @author Amit
 * @author Gina
 * @author jay
 * @version Nov 8, 2014
 */
public class GomokuTest extends student.TestCase
{
    //Fields
    private Gomoku gomoku;
    private Location loc;
    private Piece piece;

    /**
     * called on every test
     */
    public void setUp() {
        gomoku = new Gomoku();
        loc = new Location(5, 5);
        piece = new Piece("Blue");
    }

    /**
     * tests the size method
     */
    public void testGomoku()
    {
        assertEquals(15, gomoku.getSize());
    }

    /**
     * tests the get location method
     */
    public void testGetLocation() {
        //no piece
        loc = new Location(5, 5);
        assertEquals(null, gomoku.getLocation(loc));
        //there is a piece
        gomoku.setLocation(loc, piece);
        assertEquals(piece, gomoku.getLocation(loc));
    }

    /**
     * tests the check location method
     */
    public void testCheckLocation()
    {
        //in bounds
        gomoku.setLocation(loc, piece);
        assertTrue(gomoku.checkLocation(loc));

        //out of bounds
        loc = new Location (16, 16);
        assertFalse(gomoku.checkLocation(loc));
    }

    /**
     * tests the check bottom method
     */
    public void testCheckBottom()
    {
        Location loc2 = new Location(5, 6);
        Piece piece2 = new Piece("Blue");
        Location loc3 = new Location(5, 7);
        Piece piece3 = new Piece("Blue");
        Location loc4 = new Location(5, 8);
        Piece piece4 = new Piece("Blue");
        Location loc5 = new Location(5, 9);
        Piece piece5 = new Piece("Blue");


        gomoku.setLocation(loc, piece);
        gomoku.setLocation(loc2, piece2);
        gomoku.setLocation(loc3, piece3);
        gomoku.setLocation(loc4, piece4);
        gomoku.setLocation(loc5, piece5);

        Stack<Location> stack = new Stack<Location>();
        stack.push(loc);
        stack.push(loc2);
        stack.push(loc3);
        stack.push(loc4);
        stack.push(loc5);

        assertEquals(stack, gomoku.checkBottom(loc, piece));


    }

    /**
     * tests the check top method
     */
    public void testCheckTop()
    {
        Location loc2 = new Location(5, 4);
        Location loc3 = new Location(5, 3);
        Location loc4 = new Location(5, 2);
        Location loc5 = new Location(5, 1);


        gomoku.setLocation(loc, piece);
        gomoku.setLocation(loc2, piece);
        gomoku.setLocation(loc3, piece);
        gomoku.setLocation(loc4, piece);
        assertFalse(gomoku.checkWin(loc, piece));
        gomoku.setLocation(loc5, piece);

        /*
        Stack<Location> stack = new Stack<Location>();
        stack.push(loc);
        stack.push(loc2);
        stack.push(loc3);
        stack.push(loc4);
        stack.push(loc5);

        assertEquals(stack, gomoku.checkTop(loc, piece));
        assertTrue(gomoku.checkWin(loc, piece));
        */
    }

    /**
     * tests the check right method
     */
    public void testCheckRight()
    {
        Location loc2 = new Location(6, 5);
        Location loc3 = new Location(7, 5);
        Location loc4 = new Location(8, 5);
        Location loc5 = new Location(9, 5);


        gomoku.setLocation(loc, piece);
        gomoku.setLocation(loc2, piece);
        gomoku.setLocation(loc3, piece);
        gomoku.setLocation(loc4, piece);
        assertFalse(gomoku.checkWin(loc, piece));
        gomoku.setLocation(loc5, piece);

        Stack<Location> stack = new Stack<Location>();
        stack.push(loc);
        stack.push(loc2);
        stack.push(loc3);
        stack.push(loc4);
        stack.push(loc5);

        assertEquals(stack, gomoku.checkRight(loc, piece));
        assertTrue(gomoku.checkWin(loc, piece));
    }

    /**
     * tests the check left method
     */
    public void testCheckLeft()
    {
        Location loc2 = new Location(4, 5);
        Piece piece2 = new Piece("Blue");
        Location loc3 = new Location(3, 5);
        Piece piece3 = new Piece("Blue");
        Location loc4 = new Location(2, 5);
        Piece piece4 = new Piece("Blue");
        Location loc5 = new Location(1, 5);
        Piece piece5 = new Piece("Blue");


        gomoku.setLocation(loc, piece);
        gomoku.setLocation(loc2, piece2);
        gomoku.setLocation(loc3, piece3);
        gomoku.setLocation(loc4, piece4);
        gomoku.setLocation(loc5, piece5);

        Stack<Location> stack = new Stack<Location>();
        stack.push(loc);
        stack.push(loc2);
        stack.push(loc3);
        stack.push(loc4);

        assertFalse(gomoku.checkWin(loc, piece));
        /*
        stack.push(loc5);

        assertEquals(stack, gomoku.checkLeft(loc, piece));
        assertTrue(gomoku.checkWin(loc, piece));
        */
    }

    /**
     * tests the check top right diagonal method
     */
    public void testCheckTopRightDiagonal()
    {
        Location loc2 = new Location(6, 4);
        Piece piece2 = new Piece("Blue");
        Location loc3 = new Location(7, 3);
        Piece piece3 = new Piece("Blue");
        Location loc4 = new Location(8, 2);
        Piece piece4 = new Piece("Blue");
        Location loc5 = new Location(9, 1);
        Piece piece5 = new Piece("Blue");


        gomoku.setLocation(loc, piece);
        gomoku.setLocation(loc2, piece2);
        gomoku.setLocation(loc3, piece3);
        gomoku.setLocation(loc4, piece4);
        gomoku.setLocation(loc5, piece5);

        Stack<Location> stack = new Stack<Location>();
        stack.push(loc);
        stack.push(loc2);
        stack.push(loc3);
        stack.push(loc4);

        //assertFalse(gomoku.checkWin(loc, piece));

        stack.push(loc5);

        assertEquals(stack, gomoku.checkTopRightDiagonal(loc, piece));
        assertTrue(gomoku.checkWin(loc, piece));
    }

    /**
     * tests the check top left diagonal method
     */
    public void testCheckTopLeftDiagonal()
    {
        Location loc2 = new Location(4, 4);
        Piece piece2 = new Piece("Blue");
        Location loc3 = new Location(3, 3);
        Piece piece3 = new Piece("Blue");
        Location loc4 = new Location(2, 2);
        Piece piece4 = new Piece("Blue");
        Location loc5 = new Location(1, 1);
        Piece piece5 = new Piece("Blue");


        gomoku.setLocation(loc, piece);
        gomoku.setLocation(loc2, piece2);
        gomoku.setLocation(loc3, piece3);
        gomoku.setLocation(loc4, piece4);
        gomoku.setLocation(loc5, piece5);

        Stack<Location> stack = new Stack<Location>();
        stack.push(loc);
        stack.push(loc2);
        stack.push(loc3);
        stack.push(loc4);

        //assertFalse(gomoku.checkWin(loc, piece));

        stack.push(loc5);

        assertEquals(stack, gomoku.checkTopLeftDiagonal(loc, piece));
        assertTrue(gomoku.checkWin(loc, piece));
    }

    /**
     * tests the check bottom right diagonal method
     */
    public void testCheckBottomRightDiagonal()
    {
        Location loc2 = new Location(6, 6);
        Piece piece2 = new Piece("Blue");
        Location loc3 = new Location(7, 7);
        Piece piece3 = new Piece("Blue");
        Location loc4 = new Location(8, 8);
        Piece piece4 = new Piece("Blue");
        Location loc5 = new Location(9, 9);
        Piece piece5 = new Piece("Blue");


        gomoku.setLocation(loc, piece);
        gomoku.setLocation(loc2, piece2);
        gomoku.setLocation(loc3, piece3);
        gomoku.setLocation(loc4, piece4);
        gomoku.setLocation(loc5, piece5);

        Stack<Location> stack = new Stack<Location>();
        stack.push(loc);
        stack.push(loc2);
        stack.push(loc3);
        stack.push(loc4);

        assertFalse(gomoku.checkWin(loc, piece));
        /*
        stack.push(loc5);

        assertEquals(stack, gomoku.checkBottomRightDiagonal(loc, piece));
        assertTrue(gomoku.checkWin(loc, piece));
        */
    }

    /**
     * tests the check bottom left diagonal method
     */
    public void testCheckBottomLeftDiagonal()
    {
        Location loc2 = new Location(4, 6);
        Piece piece2 = new Piece("Blue");
        Location loc3 = new Location(3, 7);
        Piece piece3 = new Piece("Blue");
        Location loc4 = new Location(2, 8);
        Piece piece4 = new Piece("Blue");
        Location loc5 = new Location(1, 9);
        Piece piece5 = new Piece("Blue");


        gomoku.setLocation(loc, piece);
        gomoku.setLocation(loc2, piece2);
        gomoku.setLocation(loc3, piece3);
        gomoku.setLocation(loc4, piece4);
        gomoku.setLocation(loc5, piece5);

        Stack<Location> stack = new Stack<Location>();
        stack.push(loc);
        stack.push(loc2);
        stack.push(loc3);
        stack.push(loc4);

        assertFalse(gomoku.checkWin(loc, piece));
        /*
        stack.push(loc5);

        assertEquals(stack, gomoku.checkBottomLeftDiagonal(loc, piece));
        assertTrue(gomoku.checkWin(loc, piece));
        */
    }
}
