package com.example.gomoku;
import android.content.Intent;
import sofia.app.Screen;

/**
 *  Initializes a splash screen that has a menu of options
 *
 * @author Amit
 * @author Gina
 * @author Jay
 * @version Nov 25, 2014
 */
public class SplashScreen extends Screen
{
    /**
     * initializes the layout
     */
    public void initialize() {
        setContentView(R.layout.activity_splash);
    }

    /**
     * activates the main activity (game) when this button is clicked
     */
    public void playClicked() {
        if ((getCallingActivity() != null) &&
            getCallingActivity().getClassName().
            equals("com.example.gomoku.MainActivity"))
        {
            /* finish basically finishes the current activity on the activity stack
             * since the activity is popped off the stack, the previous activity is
             * called up to the screen. This is essentially a 'back' button
             */
            finish();
        }
        else
        {
            /*
             * If the screen was called from the android screen, that means the
             * game has not been started yet, so it needs to be started first
             */
            Intent intent = new Intent(SplashScreen.this, MainActivity.class);
            startActivity(intent);
        }
    }
    /**
     * opens the help screen
     */
    public void helpClicked()
    {
        Intent intent = new Intent(SplashScreen.this, Help.class);
        startActivityForResult(intent, 0);
    }
}
