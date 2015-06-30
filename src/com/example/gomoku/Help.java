package com.example.gomoku;

import android.text.method.ScrollingMovementMethod;
import android.widget.TextView;
import sofia.app.Screen;
/**
 * // -------------------------------------------------------------------------
/**
 *  Help class for the Gomoku instructions
 *
 *  @author Gina
 *  @author Amit
 *  @author Jay
 *  @version Nov 29, 2014
 */
public class Help
    extends Screen
{
    /**
     * initializes content view
     */
    public void initialize()
    {
        setContentView(R.layout.activity_help);
        TextView textview= (TextView) findViewById(R.id.editText2);
        textview.setMovementMethod(new ScrollingMovementMethod());
    }

    /**
     * pops the current activity off the stack, similating a back button
     */
    public void backClicked() {
        finish();
    }
}



