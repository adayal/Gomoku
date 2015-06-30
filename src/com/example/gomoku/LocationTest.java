package com.example.gomoku;

/**
 * Test Class for the Location Class
 *
 * @author Amit
 * @author Gina
 * @author Jay
 * @version November 8, 2014
 */

public class LocationTest extends student.TestCase
{
    private Location loc;

    /**
     * constructor for class
     */
    public LocationTest()
    {
        /**
         * No information for class
         */
    }

    /**
     * Called every time test case is called
     */
    public void setUp()
    {
        loc = new Location(1, 1);
    }
    /**
     * test x coordinate method
     */
    public void testX()
    {
        assertEquals(1, loc.x());
    }

    /**
     * test y coordinate method
     */
    public void testY()
    {
        assertEquals(1, loc.y());
    }

    /**
     * test equals method
     */
    public void testEquals()
    {
        Location loc1 = new Location(1, 1);
        Location loc2 = new Location(1, 2);
        Location loc3 = null;

        assertTrue(loc.equals(loc1));
        assertFalse(loc.equals(loc2));
        assertFalse(loc.equals(loc3));
    }

    /**
     * test toString method
     */
    public void testToString()
    {
        assertEquals("(1, 1)", loc.toString());
    }
}
