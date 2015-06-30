package com.example.gomoku;

import android.content.Intent;
import android.widget.ImageButton;


//-------------------------------------------------------------------------
/**
* This is the screen test class for the game Gomoku
*
* @author Amit
* @author Gina
* @author Jay
* @version Nov 25, 2014
*/
public class SplashScreenTest extends student.AndroidTestCase<SplashScreen>
{
    /**
     * Does nothing
     */
    public SplashScreenTest()
    {
        super(SplashScreen.class);
        // TODO Auto-generated constructor stub
    }
    /**
     * called at the beginning of each test
     */
    public void setUp()
    {
        /**
         * does nothing
         */
    }
    private ImageButton play;
    private ImageButton help;
    /**
     * test what happens when the play button is clicked
     */
    public void testPlayClicked()
    {
        prepareForUpcomingActivity(Intent.ACTION_VIEW);
        click(play);
    }
    /**
     * test what happens when the about button is clicked
     */
    public void testHelpClicked()
    {
        prepareForUpcomingActivity(Intent.ACTION_VIEW);
        click(help);
    }
}




