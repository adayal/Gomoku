package com.example.gomoku;

/**
 * Location class
 *
 * @author Amit
 * @author Gina
 * @author jay
 * @version Nov 8, 2014
 */
public class Location
{
    private int x;
    private int y;


    /**
     * constructor for Location class, takes in x and y coordinate
     *
     * @param x
     *            x-coordinate
     * @param y
     *            y-coordinate
     */
    public Location(int x, int y)
    {
        this.x = x;
        this.y = y;
    }


    /**
     * get x-coordinate
     *
     * @return x-coordinate
     */
    public int x()
    {
        return x;
    }


    /**
     * get y-coordinate
     *
     * @return y-coordinate
     */
    public int y()
    {
        return y;
    }


    /**
     * checks if two x,y coordinates are equal to each other
     *
     * @param object
     *            location object
     * @return true if they are equal, false otherwise
     */
    @Override
    public boolean equals(Object object)
    {
        if (object instanceof Location)
        {
            Location loc = (Location)object;
            return (loc.x() == x && loc.y() == y);
        }
        return false;
    }


    /**
     * prints an (x,y) formatted string
     *
     * @return returns a string
     */
    @Override
    public String toString()
    {
        return "(" + x + ", " + y + ")";
    }
}
