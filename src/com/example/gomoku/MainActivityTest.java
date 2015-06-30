package com.example.gomoku;
import android.widget.Button;
import android.widget.TextView;
import sofia.graphics.Color;
import sofia.graphics.ShapeView;
import sofia.util.Random;

/**
 *  This class tests the MainActivity (screen) class for our game, Gomoku
 *
 *  @author Amit
 *  @author Gina
 *  @author Jay
 *  @version Oct 30, 2014
 */

public class MainActivityTest extends student.AndroidTestCase<MainActivity>
{
    private ShapeView shapeView;
    private TextView message;
    private Button reset;
    private Button undo;
    private int cellSize;
    private Gomoku game;

    /**
     * constructor is empty because stuff is initialized at setup
     */
    public MainActivityTest()
    {
        super(MainActivity.class);
        /**
         * nothing to initialize here
         */
    }
    /**
     * called at the beginning of each test
     */
    public void setUp()
    {
        float viewSize =
            Math.min(shapeView.getWidth(), shapeView.getHeight());
        message = getScreen().getTextView();
        cellSize = (int)viewSize / 15;
        game = getScreen().getGomoku();

    }

    /**
     * tests the process touch method when different cells are clicked
     */
    public void testProcessTouch()
    {
        clickCell(1, 1);
        assertEquals(Color.blue, getScreen().getShape(1, 1).getFillColor());
        assertFalse(getScreen().getHasGameEnded());
        Location loc = new Location(3, 3);
        assertTrue(getScreen().checkLocation(loc));
        loc = new Location(-1, 1);
        assertFalse(getScreen().checkLocation(loc));
        loc = new Location(16, 1);
        assertFalse(getScreen().checkLocation(loc));
        loc = new Location(1, -1);
        assertFalse(getScreen().checkLocation(loc));
        loc = new Location(1, 16);
        assertFalse(getScreen().checkLocation(loc));
        click(reset);
        assertEquals(0, getScreen().getTurn());
        assertFalse(getScreen().getHasGameEnded());
        assertEquals("Red's turn to play", message.getText());
        clickCell(1, 1);
        assertEquals("Red's turn to play", message.getText());
        clickCell(1, 1);
        assertEquals("Red's turn to play", message.getText());
        clickCell(1, 2);
        assertEquals("Red's turn to play", message.getText());
    }

    /**
     * test to see if blue wins
     */
    public void testWinBlue()
    {
        clickCell(0, 0);
        clickCell(2, 2);
        clickCell(1, 0);
        clickCell(3, 3);
        clickCell(2, 0);
        clickCell(4, 4);
        clickCell(3, 0);
        clickCell(5, 5);
        clickCell(4, 0);
        assertEquals("Blue Won!", message.getText());
        assertTrue(getScreen().getHasGameEnded());
    }
    /**
     * test to see if red wins
     */
    public void testWinRed()
    {
        clickCell(9, 8);
        clickCell(0, 0);
        clickCell(2, 2);
        clickCell(1, 0);
        clickCell(3, 3);
        clickCell(2, 0);
        clickCell(4, 4);
        clickCell(3, 0);
        clickCell(5, 5);
        clickCell(4, 0);
        assertEquals("Red Won!", message.getText());
        assertTrue(getScreen().getHasGameEnded());
    }

    /**
     * test the undo click method with a non-empty stack
     */
    public void testUndoClicked()
    {
        clickCell(0, 0);
        assertEquals(1, getScreen().getStack().size());
        click(undo);
        assertEquals(Color.white, getScreen().getShape(0, 0).getFillColor());
        Gomoku game = getScreen().getGomoku();
        assertEquals(null, game.getLocation(new Location(0, 0)));
        assertEquals(0, getScreen().getTurn());
    }

    /**
     * test the size of the mainActivity class
     */
    public void testGetSize()
    {
        assertEquals(32.0, getScreen().getSize(), 0.1);
    }


    /**
     * Simulates clicking the middle of the specified cell in the maze. This is
     * equivalent to calling: touchDownCell(x, y); touchUp();
     */
    private void clickCell(int x, int y)
    {
        touchDownCell(x, y);
        touchUp();
    }

    /**
     * Simulates touching down on the middle of the specified cell in the maze.
     */
    private void touchDownCell(int x, int y)
    {
        touchDown(shapeView, (x + 0.5f) * cellSize, (y + 0.5f) * cellSize);
    }

    /**
     * test the stalemate method
     */
    public void testStalemate()
    {
        Piece piece = new Piece("Gray");
        Gomoku game = getScreen().getGomoku();
        for (int i = 0; i < 15; i++)
        {
            for (int j = 0; j < 15; j++)
            {
                game.setLocation(new Location(i, j), piece);
            }
        }
        assertTrue(getScreen().checkStalemate());
    }

    /**
     * test getter and setter methods
     */
    public void testSetAndGet()
    {
        getScreen().setBluePowerUp(true);
        assertTrue(getScreen().getBluePowerUp());
        getScreen().setRedPowerUp(true);
        assertTrue(getScreen().getRedPowerUp());
        getScreen().setGreyPowerUp(true);
        assertTrue(getScreen().getGreyPowerUp());
        getScreen().setExtraTurn(true);
        assertTrue(getScreen().getExtraTurn());
    }

    /**
     * test the grey power up method
     */
    public void testGreyPowerUpMethod()
    {
        getScreen().setGreyPowerUp(true);
        getScreen().setTurn(3);
        assertEquals(3, getScreen().getTurn());
        clickCell(1, 1);
        assertEquals(Color.gray, getScreen().getShape(1, 1).getFillColor());
        assertEquals("Red player, it is your turn", message.getText());
        assertEquals("Grey",
            game.getLocation(new Location(1, 1)).getPieceColor());
        assertFalse(getScreen().getGreyPowerUp());
        getScreen().setGreyPowerUp(true);
        getScreen().setTurn(4);
        clickCell(2, 1);
        assertEquals(Color.gray, getScreen().getShape(2, 1).getFillColor());
        assertEquals("Blue player, it is your turn", message.getText());
        assertEquals("Grey",
            game.getLocation(new Location(2, 1)).getPieceColor());
        assertFalse(getScreen().getGreyPowerUp());
    }

    /**
     * test the random powerUp method for blue's turn
     */
    public void testRandomPowerUp()
    {
        getScreen().setBluePowerUp(true);
        getScreen().setTurn(4); //blue's turn
        Random.setNextInts(2);
        assertEquals("Blue's turn to play", message.getText());
        clickCell(1, 1);
        assertEquals("Blue, you got an extra turn", message.getText());
        clickCell(1, 2);
        assertEquals(Color.blue, getScreen().getShape(1, 2).getFillColor());
        assertEquals("Red's turn to play", message.getText());
        assertFalse(getScreen().getBluePowerUp());
        getScreen().setBluePowerUp(true);
        getScreen().setTurn(4);
        Random.setNextInts(1);
        clickCell(3, 1);
        assertEquals("Blue, please place your grey piece",
            message.getText());
        assertTrue(getScreen().getGreyPowerUp());
        clickCell(4, 1);
        assertEquals(Color.gray, getScreen().getShape(4, 1).getFillColor());
    }
}
