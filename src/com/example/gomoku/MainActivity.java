package com.example.gomoku;
import android.content.DialogInterface;
import android.content.DialogInterface;
import android.app.AlertDialog;
import android.content.Intent;
import android.widget.TextView;
import java.util.Stack;
import sofia.app.ShapeScreen;
import sofia.graphics.Color;
import sofia.graphics.RectangleShape;
import sofia.graphics.ShapeView;
import sofia.util.Random;

// -------------------------------------------------------------------------
/**
 * This is the screen class for the game Gomoku
 *
 * @author Amit
 * @author Gina
 * @author Jay
 * @version Nov 24, 2014
 */
public class MainActivity extends ShapeScreen
{
    private RectangleShape[][] shapes;
    private Gomoku             game;
    private Gomoku hello;
    private float              size;
    private int                turn;
    private TextView           message;
    private ShapeView shapeView;
    private Stack<Object> stack;
    private boolean hasGameEnded;
    private boolean redPowerUp;
    private boolean bluePowerUp;
    private int powerUpCounter;
    private int randomCounter;
    private boolean extraTurn;
    private boolean greyPowerUp;
    private Stack<Color> greyStack;



    /**
     * initializes the screen with the grid to play the game on
     */
    @Override
    public void initialize()
    {
        greyPowerUp = false;
        extraTurn = false;
        setContentView(R.layout.activity_main);
        game = new Gomoku();
        hello = new Gomoku();
        System.out.println(game.equals(hello));
        hasGameEnded = false;
        stack = new Stack<Object>();
        greyStack = new Stack<Color>();
        int pixelSize = (int)Math.min(getWidth(), getHeight());
        size = pixelSize / 15;
        shapes = new RectangleShape[15][15];
        turn = 0; // %2 to figure out which piece is going to be played next
        message.setText("Blue's turn to play");
        for (int i = 0; i < 15; i++)
        {
            for (int j = 0; j < 15; j++)
            {
                float left = size * i;
                float top = size * j;
                float right = left + size;
                float bottom = top + size;
                RectangleShape s = new RectangleShape(left, top, right, bottom);
                s.setFillColor(Color.white);
                s.setColor(Color.black);
                shapes[i][j] = s;
                shapeView.add(s);
            }
        }
        redPowerUp = false;
        bluePowerUp = false;
        randomCounter = 0;
        powerUpCounter = 0;
    }



    /**
     * process a touch on the screen
     *
     * @param x
     *            -coordinate
     * @param y
     *            -coordinate
     */
    public void processTouch(int x, int y)
    {
        selectPowerUp();
        Location loc = new Location(x, y);
        if (turn % 2 == 0 && !hasGameEnded)
        {
            /*
             * If blue has a powerUp, this should be true
             */
            // Blue's turn to play
            Piece piece = new Piece("Blue");
            if (game.getLocation(loc) == null)
            {
                game.setLocation(loc, piece);
                shapes[loc.x()][loc.y()].setFillColor(Color.blue);
                stack.add(loc);
                message.setText("Red's turn to play");
                if(checkStalemate())
                {
                    hasGameEnded = true;
                    message.setText("The game ends in a draw");
                }
                turn++;
                if (bluePowerUp)
                {
                    int pickPowerUp = Random.generator().nextInt();
                    //int pickPowerUp = 1;
                    if (pickPowerUp % 2 == 0)
                    {
                        message.setText("Blue, you got an extra turn");
                        new AlertDialog.Builder(this).setTitle("Power Up").setMessage("Blue Recieved an Extra Turn").setNeutralButton("Ok", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int which)
                            {
                                // TODO Auto-generated method stub

                            }
                        }).show();

                        // Extra turn powerUp
                        extraTurn = true;
                    }
                    else
                    {
                        message.setText("Blue, " +
                            "you get to remove one piece from the board");

                        // Grey out piece powerUp
                        // player must place 'grey' piece on the board during their
                        // turn while also placing their normal piece.
                        greyPowerUp = true;
                    }
                    bluePowerUp = false;
                }
                if (greyPowerUp)
                {
                    message.setText("Blue, please place your grey piece");
                    new AlertDialog.Builder(this).setTitle("Power Up").setMessage("Blue please place your grey piece").setNeutralButton("Ok", new DialogInterface.OnClickListener() {

                        public void onClick(DialogInterface dialog, int which)
                        {
                            // TODO Auto-generated method stub

                        }
                    }).show();
                }
                if (extraTurn)
                {
                    turn++;
                    extraTurn = false;
                }
            }
        }
        else if (turn % 2 == 1 && !hasGameEnded)
        {
            // Red's turn to play
            Piece piece = new Piece("Red");
            if (game.getLocation(loc) == null)
            {
                game.setLocation(loc, piece);
                stack.add(loc);
                shapes[loc.x()][loc.y()].setFillColor(Color.red);
                message.setText("Blue's turn to play");
                if(checkStalemate())
                {
                    hasGameEnded = true;
                    message.setText("The game ends in a draw");
                }
                turn++;
                if (redPowerUp)
                {
                    int pickPowerUp = Random.generator().nextInt();
                    if (pickPowerUp % 2 == 0)
                    {
                        message.setText("Red, you got an extra turn");
                        // Extra turn powerUp
                        new AlertDialog.Builder(this).setTitle("Power Up").setMessage("Red Recieved an Extra Turn").setNeutralButton("Ok", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int which)
                            {
                                // TODO Auto-generated method stub

                            }
                        }).show();
                        extraTurn = true;
                    }
                    else
                    {
                        message.setText("Red, " +
                            "you got to remove one piece from the board");
                        new AlertDialog.Builder(this).setTitle("Power Up").setMessage("Red Recieved a Grey Piece").setNeutralButton("Ok", new DialogInterface.OnClickListener() {

                            public void onClick(DialogInterface dialog, int which)
                            {
                                // TODO Auto-generated method stub

                            }
                        }).show();
                        greyPowerUp = true;
                        // Grey out piece powerUp
                    }
                    redPowerUp = false;
                }
                if (greyPowerUp)
                {
                    message.setText("Red, please place your grey piece");
                }
                if (extraTurn)
                {
                    turn++;
                    extraTurn = false;
                }
            }
        }
        checkBlueWin();
        checkRedWin();
    }

    /**
     * called when the game ends
     * @param piece to check for
     * @param loc location to search from
     */
    public void endGame(Piece piece, Location loc)
    {
        Stack<Location> winningStack = game.getWinningStack(loc, piece);
        //highlight the locations in stack to show the winning combination
        while (!winningStack.isEmpty())
        {
            /*
             * If there is winning method, the player basically wins in 2
             * different directions. For example, red/blue can win if the pieces
             * lines up vertically, which also means they win top to bottom and
             * bottom to top
             */
            Location steps = winningStack.pop();
            shapes[steps.x()][steps.y()].setFillColor(Color.gold);
        }
    }
    /**
     * checks if the board if there is a win for red. If there is, end the game
     * and set the approrpiate win message
     */
    private void checkRedWin()
    {
        Piece piece = new Piece("Red");
        for (int i = 0; i < 15; i++)
        {
            for (int j = 0; j < 15; j++)
            {
                if (game.checkWin(new Location(i, j), piece))
                {
                    hasGameEnded = true;
                    message.setText("Red Won!");
                    // red won
                    endGame(piece, new Location(i, j));
                    break;
                }
            }
        }
    }

    /**
     * checks if the board if there is a win for blue. If there is, end the game
     * and set the approrpiate win message
     */
    private void checkBlueWin()
    {
        Piece piece = new Piece("Blue");
        for (int i = 0; i < 15; i++)
        {
            for (int j = 0; j < 15; j++)
            {
                if (game.checkWin(new Location(i, j), piece))
                {
                    hasGameEnded = true;
                    message.setText("Blue Won!");
                    // red won
                    endGame(piece, new Location(i, j));
                    break;
                }
            }
        }
    }

    /**
     * Called when user touches the screen at a specific point
     *
     * @param x
     *            -coordinate
     * @param y
     *            -coordinate
     */
    public void onTouchDown(float x, float y)
    {
        int locX = (int)(x / size);
        int locY = (int)(y / size);
        Location loc = new Location(locX, locY);
        if (checkLocation(loc))
        {
            if (greyPowerUp && ((turn - 1) % 2 == 0))
            {
                //turn - 1 %2 = 0 --> blue got the power up
                greyPowerUp(loc, "Red");
            }
            else if (greyPowerUp && ((turn - 1) % 2 == 1))
            {
                //turn - 1 %2 = 1 --> red got the power up
                greyPowerUp(loc, "Blue");
            }
            else
            {
                processTouch(loc.x(), loc.y());
            }
        }
    }


    /**
     * get a cell at a current location
     *
     * @param x
     *            -coordinate
     * @param y
     *            -coordinate
     * @return rectangle shape
     */
    public RectangleShape getShape(int x, int y)
    {
        return shapes[x][y];
    }


    /**
     * check if clicked location is within bounds
     *
     * @return true if it is
     * @param loc
     *            location of where user clicked
     */
    public boolean checkLocation(Location loc)
    {
        return (loc.x() < 15 && loc.x() >= 0 && loc.y() < 15 && loc.y() >= 0);
    }

    /**
     * splash menu appears when this button is clicked
     */
    public void splashClicked()
    {
        Intent intent = new Intent(MainActivity.this, SplashScreen.class);
        startActivityForResult(intent, 0);
    }

    /**
     * action when reset is clicked
     */
    public void resetClicked()
    {
        initialize();
    }

    /**
     * take back a move by removing 2 elements from stack
     * The top element will be the location of where the piece was placed
     */
    public void undoClicked()
    {
        if (!stack.empty() && !hasGameEnded)
        {
            Location loc = (Location)stack.pop();
            Piece temp = game.getLocation(loc);
            if (!stack.empty() && game.getLocation((Location)stack.peek()).getPieceColor()
                .equals("Grey"))
            {
                /*
                 * Check if the stack has a grey piece
                 */

                Location loc2 = (Location)stack.pop();
                shapes[loc.x()][loc.y()].setFillColor(Color.white);
                game.setLocation(loc, null);
                shapes[loc2.x()][loc2.y()].setFillColor(greyStack.pop());
                game.setLocation(loc2, null);
                turn--;
                if (turn % 2 == 0)
                {
                    message.setText("Blue's turn to play");
                }
                else
                {
                    message.setText("Red's turn to play");
                }
            }
            else if (!stack.empty() && temp.getPieceColor()
                .equals(game.getLocation((Location)stack.peek())
                    .getPieceColor()))
            {
                //the piece taken off and the top of the stack have the same
                // color, therefore an extra turn was provided
                Location loc2 = (Location)stack.pop();
                shapes[loc.x()][loc.y()].setFillColor(Color.white);
                game.setLocation(loc, null);
                shapes[loc2.x()][loc2.y()].setFillColor(Color.white);
                game.setLocation(loc2, null);
                turn--;
                if (turn % 2 == 0)
                {
                    message.setText("Blue's turn to play");
                }
                else
                {
                    message.setText("Red's turn to play");
                }
            }
            else
            {
                shapes[loc.x()][loc.y()].setFillColor(Color.white);
                game.setLocation(loc, null);
                turn--;
                if (turn % 2 == 0)
                {
                    message.setText("Blue's turn to play");
                }
                else
                {
                    message.setText("Red's turn to play");
                }
            }
        }
    }

    /**
     * get the textview object
     * @return textview object
     */
    public TextView getTextView()
    {
        return message;
    }

    /**
     * get the game from the screen
     * @return game
     */
    public Gomoku getGomoku()
    {
        return game;
    }

    /**
     * get the size of screen
     * @return size of screen
     */
    public float getSize()
    {
        return size;
    }

    /**
     * return the shapeView object
     * @return shapeview object
     */
    public ShapeView getShapeView()
    {
        return shapeView;
    }

    /**
     * return the stack with all the recorded moves
     * @return stack of moves
     */
    public Stack<Object> getStack()
    {
        return stack;
    }

    /**
     * return if the game has ended
     * @return if the game has ended or not
     */
    public boolean getHasGameEnded()
    {
        return hasGameEnded;
    }

    /**
     * get current turn
     * @return the turn
     */
    public int getTurn()
    {
        return turn;
    }
    /**
     * adds an extra turn to the game (making it the user's turn again)
     */
    public void powerUpExtraTurn()
    {
        turn++;
    }

    /**
     * Gives a powerup to a random player after 20 turns
     * After the 20th turn, a new number from 10 to 15 is selected
     * Once the random counter reaches the powerUp counter, another powerup is
     * awarded and the powerUpCounter is reset again
     */
    public void selectPowerUp()
    {
        if (turn >= 20 && randomCounter == powerUpCounter)
        {
            powerUpCounter = Random.generator().nextInt(10, 15);
            randomCounter = 0;
            Boolean x = Random.generator().nextBoolean();
            if (x)
            {
                bluePowerUp = true;
            }
            else
            {
                redPowerUp = true;
            }
        }
        else if (turn >= 20)
        {
            randomCounter++;
        }
    }
    /**
     * If all the pieces in the board are filled up and nobody has won, then
     * the game has reached a stalemate
     * @return true if the whole board contains a piece
     */
    public boolean checkStalemate()
    {
        Stack<Piece> pieces = new Stack<Piece>();
        for (int i = 0; i < 15; i++)
        {
            for (int j = 0; j < 15; j++)
            {
                pieces.add(game.getLocation(new Location(i, j)));
            }
        }
        if (pieces.contains(null))
        {
            // null = empty space, so if there is an empty space, then all the
            // pieces aren't filled in yet
            return false;
        }
        else
        {
            return true;
        }
    }
    /**
     * Places grey piece on any spot on the board. The grey piece signifies
     * 'no mans land'. The spot renders any spot useless for any player's
     * winning row.
     * @param loc location to place the grey piece
     * @param x next player. If red places the grey piece, then 'Blue' should
     * be passed in
     */
    public void greyPowerUp(Location loc, String x)
    {
        Piece piece = new Piece("Grey");
        greyStack.add(shapes[loc.x()][loc.y()].getFillColor());

        //the grey stack stores what the grey piece sits on top of. For example
        // if the grey piece is placed on a 'red' piece, red will be placed on
        // the stack. When a grey piece is removed, it will revert back to what
        // the piece was originially. This will be used in the undo method

        game.setLocation(loc, piece);
        shapes[loc.x()][loc.y()].setFillColor(Color.gray);
        stack.add(loc);
        message.setText(x + " player, it is your turn");
        greyPowerUp = false;
    }

    /**
     * get GreyPowerUp boolean
     * @return true if powerup is on
     */
    public boolean getGreyPowerUp()
    {
        return greyPowerUp;
    }

    /**
     * set greyPowerUp
     * @param bool boolean value to switch greyPowerUp To
     */
    public void setGreyPowerUp(boolean bool)
    {
        greyPowerUp = bool;
    }

    /**
     * get Red Power Up status
     * @return true if its on, false if not
     */
    public boolean getRedPowerUp()
    {
        return redPowerUp;
    }

    /**
     * set Red Power Up
     * @param bool boolean value to switch redPowerUp to
     */
    public void setRedPowerUp(boolean bool)
    {
        redPowerUp = bool;
    }

    /**
     * get blue Power Up status
     * @return true if its on, false if not
     */
    public boolean getBluePowerUp()
    {
        return bluePowerUp;
    }

    /**
     * set blue Power Up
     * @param bool boolean value to switch redPowerUp to
     */
    public void setBluePowerUp(boolean bool)
    {
        bluePowerUp = bool;
    }
    /**
     * set the turn
     * @param turn you want to set the turn field to
     */
    public void setTurn(int turn)
    {
        this.turn = turn;
    }

    /**
     * get the extra turn powerUp
     * @return true if the powerup is activated, false otherwise
     */
    public boolean getExtraTurn()
    {
        return extraTurn;
    }

    /**
     * set the extra turn powerUp
     * @param bool boolean to set the extraTurn to
     */
    public void setExtraTurn(boolean bool)
    {
        extraTurn = bool;
    }
}
