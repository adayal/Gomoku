package com.example.gomoku;

import java.util.Stack;
/**
 *  This represents the gomoku board
 *
 * @author Amit
 * @author Gina
 * @author Jay
 * @version Nov 25, 2014
 */
public class Gomoku
{
    private Piece[][] grid;
    private int       size;


    /**
     * size is constant
     */
    public Gomoku()
    {
        size = 15;
        grid = new Piece[size][size];
    }


    /**
     * returns the size of the board
     *
     * @return size of board
     */
    public int getSize()
    {
        return size;
    }


    /**
     * sets the location of a specific coordinate on the grid to a piece
     *
     * @param loc
     *            location of where the piece will be placed
     * @param piece
     *            that will be placed in the grid
     */
    public void setLocation(Location loc, Piece piece)
    {
        grid[loc.x()][loc.y()] = piece;
    }

    /**
     * Returns a piece at a certain location If the location does not contain a
     * piece, null is returned
     * @return piece at the location
     * @param loc location of where to get piece from
     */
    public Piece getLocation(Location loc)
    {
        if (checkLocation(loc))
        {
            return grid[loc.x()][loc.y()];
        }
        else
        {
            return null;
        }
    }


    /**
     * checks if the location is in bounds
     *
     * @return true if it is in bounds
     * @param loc
     *            location to check for
     */
    public boolean checkLocation(Location loc)
    {
        return (loc.x() < size && loc.x() >= 0 && loc.y() < size && loc.y() >= 0);
    }


    /**
     * Check if the 4 locations below the current piece are consecutive If they
     * are, then there is a winning stack in this orientation Compare the piece
     * strings to see what type of piece they are Check if the location is
     * within bounds
     *
     * @param loc
     *            location of the starting piece
     * @param piece
     *            type to check for
     * @return stack of the piece below this piece that are of the same type
     */
    public Stack<Location> checkBottom(Location loc, Piece piece)
    {
        Stack<Location> stack = new Stack<Location>();
        for (int i = 0; i < 5; i++)
        {
            Location location = new Location(loc.x(), loc.y() + i);
            if (checkLocation(location)
                && getLocation(location) != null)
            {
                if (getLocation(location).getPieceColor().equals(
                    piece.getPieceColor()))
                {
                    stack.add(location);
                }
                else
                {
                    break;
                }
            }
            else
            {
                break;
                // If it is out of bounce, then there is no point in checking
                // the rest of the locations south of this location
            }
        }
        return stack;
    }
    /**
     * Check if the 4 locations below the current piece are consecutive If they
     * are, then there is a winning stack in this orientation Compare the piece
     * strings to see what type of piece they are Check if the location is
     * within bounds
     *
     * @param loc
     *            location of the starting piece
     * @param piece
     *            type to check for
     * @return stack of the piece below this piece that are of the same type
     */
    public Stack<Location> checkRight(Location loc, Piece piece)
    {
        Stack<Location> stack = new Stack<Location>();
        for (int i = 0; i < 5; i++)
        {
            Location location = new Location(loc.x() + i, loc.y());
            if (checkLocation(location)
                && getLocation(location) != null)
            {
                if (getLocation(location).getPieceColor().equals(
                    piece.getPieceColor()))
                {
                    stack.add(location);
                }
                else
                {
                    break;
                }
            }
            else
            {
                break;
                // If it is out of bounce, then there is no point in checking
                // the rest of the locations south of this location
            }
        }
        return stack;
    }

    /**
     * Check if the 4 locations below the current piece are consecutive If they
     * are, then there is a winning stack in this orientation Compare the piece
     * strings to see what type of piece they are Check if the location is
     * within bounds
     *
     * @param loc
     *            location of the starting piece
     * @param piece
     *            type to check for
     * @return stack of the piece below this piece that are of the same type
     */
    public Stack<Location> checkTopRightDiagonal(Location loc, Piece piece)
    {
        Stack<Location> stack = new Stack<Location>();
        for (int i = 0; i < 5; i++)
        {
            Location location = new Location(loc.x() + i, loc.y() - i);
            if (checkLocation(location)
                && getLocation(location) != null)
            {
                if (getLocation(location).getPieceColor().equals(
                    piece.getPieceColor()))
                {
                    stack.add(location);
                }
                else
                {
                    break;
                }
            }
            else
            {
                break;
                // If it is out of bounce, then there is no point in checking
                // the rest of the locations south of this location
            }
        }
        return stack;
    }


    /**
     * Check if the 4 locations below the current piece are consecutive If they
     * are, then there is a winning stack in this orientation Compare the piece
     * strings to see what type of piece they are Check if the location is
     * within bounds
     *
     * @param loc
     *            location of the starting piece
     * @param piece
     *            type to check for
     * @return stack of the piece below this piece that are of the same type
     */
    public Stack<Location> checkTopLeftDiagonal(Location loc, Piece piece)
    {
        Stack<Location> stack = new Stack<Location>();
        for (int i = 0; i < 5; i++)
        {
            Location location = new Location(loc.x() - i, loc.y() - i);
            if (checkLocation(location)
                && getLocation(location) != null)
            {
                if (getLocation(location).getPieceColor().equals(
                    piece.getPieceColor()))
                {
                    stack.add(location);
                }
                else
                {
                    break;
                }
            }
            else
            {
                break;
                // If it is out of bounce, then there is no point in checking
                // the rest of the locations south of this location
            }
        }
        return stack;
    }

    /**
     * checks if the there is a possible stack of 4 or more pieces of the same
     * type in a row. If there is, then one side has won the game;
     *
     * @return true if there is a winning stack
     * @param loc
     *            location to start checking
     * @param piece
     *            to check against other pieces in the vicinity against
     */
    public boolean checkWin(Location loc, Piece piece)
    {
        return (checkBottom(loc, piece).size() > 4
            || checkRight(loc, piece).size() > 4
            || checkTopRightDiagonal(loc, piece).size() > 4
            || checkTopLeftDiagonal(loc, piece).size() > 4);
    }

    /**
     * return winning stack
     * @return stack
     * @param loc location to get stack from
     * @param piece color of winning stack
     */
    public Stack<Location> getWinningStack(Location loc, Piece piece)
    {
        if (checkBottom(loc, piece).size() > 4)
        {
            return checkBottom(loc, piece);
        }
        else if (checkRight(loc, piece).size() > 4)
        {
            return checkRight(loc, piece);
        }
        else if (checkTopRightDiagonal(loc, piece).size() > 4)
        {
            return checkTopRightDiagonal(loc, piece);
        }
        else if (checkTopLeftDiagonal(loc, piece).size() > 4)
        {
            return checkTopLeftDiagonal(loc, piece);
        }
        else
        {
            return null;
        }
    }
}
