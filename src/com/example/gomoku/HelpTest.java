package com.example.gomoku;

import android.content.Intent;
import android.widget.ImageButton;
/**
 * // -------------------------------------------------------------------------
/**
 *  Test cases for the Help class, which displays the instructions for the game
 *
 *  @author Gina
 *  @author Amit
 *  @author Jay
 *  @version Nov 29, 2014
 */
public class HelpTest extends student.AndroidTestCase<Help>
{
    private ImageButton back;

    /**
     * constructor for class
     */
    public HelpTest()
    {
        super(Help.class);
    }

    /**
     * tests the back button
     */
    public void testBack()
    {
        this.prepareForUpcomingActivity(Intent.ACTION_VIEW);
        click(back);
    }

}


