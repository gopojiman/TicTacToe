/*
 * Theophanis Fox, June 2018
 * 
 * HumanPlayer.java
 * Implements a human player in a board game
 */

import java.awt.event.*;

public class HumanPlayer
    implements Player, MouseListener
{
  private TicTacToe program;
  private TicTacToeGame game;
  private BoardPanel board;

  private boolean myTurn, twoPlayer;
  private char symbol;
  
  private final String[] prompts = {
      " Your turn",
      " Your move buddy",
      " You're up"
  };
  private String[] twoPlayerPrompts;
  private static final String[] nextPrompts = {
      " Why are you taking so long",
      " Hurry it up",
      " I don't have all day",
      " Do something already",
      " Pick up the pace, would ya",
      " Don't you have something better to do",
      " I have plans later, don't make me late",
      " Click something, anything",
      " You bore me",
      " You are the slowest being on the planet",
      " ZZZZZZZZZzzzzzzzz....",
      " Why u so slooooow",
      " Can't you just decide on something already?",
      " Your indecision is getting ridiculous",
      " Your lack of speed is starting to bother me",
      " .....",
      " You're hopeless, hurry up and do something",
      " You're really testing my patience",
      " Time is running out",
      " It's not that hard, just click somewhere",
      " Did you die at the keyboard?"
  };
  private final String[] firstPrompts = {
      " You go first",
      " You move first",
      " You're up first"
  };
  private final String[] twoPlayerFirstPrompts = {
      " X goes first",
      " X moves first",
      " X is up first"
  };
  private final String[] winMessages = {
      " You won only because you're too wimpy to try God mode",
      " Congratulations, you've accomplished absolutely nothing",
      " That game was unrepresentative of my true abilities"
  };
  private final String[] drawMessages = {
      " Looks like you failed to win",
      " Maybe you'll win someday",
      " I prevented your victory yet again"
  };
  private String[] twoPlayerWinMessages;
  private final String[] twoPlayerDrawMessages = {
      " Looks like neither of you were good enough to win",
      " Well that didn't get us anywhere",
      " Ya done drew yourselves"
  };

  public HumanPlayer(TicTacToe program, TicTacToeGame game, BoardPanel board, char symbol, boolean twoPlayer)
  {
    this.program = program;
    this.game = game;
    this.board = board;
    this.symbol = symbol;
    this.twoPlayer = twoPlayer;
    board.addMouseListener(this);
    
    twoPlayerPrompts = new String[]{
        " " + symbol + "'s turn",
        " " + symbol + "'s move",
        " " + symbol + " is up"
    };
    twoPlayerWinMessages = new String[]{
        " " + symbol + " destroyed the competition",
        " " + symbol + " eviscerated their opponent",
        " Looks like " + symbol + " crushed it"
    };
  }
  
  public char getSymbol()
  {
    return symbol;
  }

  /*
   * Returns a prompt to be displayed before
   * the next move of this player
   */
  public String getPrompt()
  {
    if (twoPlayer)
        return twoPlayerPrompts[(int)(Math.random() * twoPlayerPrompts.length)];
    return prompts[(int)(Math.random() * prompts.length)];
  }
  
  // gets the next prompt to be displayed
  public static String getNextPrompt()
  {
    return nextPrompts[(int)(Math.random() * nextPrompts.length)];
  }
  
  // returns the prompt to be displayed at the start of the game
  public String getFirstPrompt()
  {
      if (twoPlayer)
          return twoPlayerFirstPrompts[(int)(Math.random() * twoPlayerFirstPrompts.length)];
      return firstPrompts[(int)(Math.random() * firstPrompts.length)];
  }

  /*
   * Returns a message to be displayed when
   * this player has won
   */
  public String getWinMessage()
  {
    if (game.countEmpty() == 0 && !game.threeInARow())
    {
        if (twoPlayer)
            return twoPlayerDrawMessages[(int)(Math.random() * twoPlayerDrawMessages.length)];
        return drawMessages[(int)(Math.random() * drawMessages.length)];
    }
    if (twoPlayer)
        return twoPlayerWinMessages[(int)(Math.random() * twoPlayerWinMessages.length)];
    return winMessages[(int)(Math.random() * winMessages.length)];
  }

  /*
   * Initiates this player's next move
   */
  public void makeMove()
  {
      myTurn = true;
  }
  
  /*
   * Prevents this player from moving
   */
  public void disable()
  {
      myTurn = false;
  }

  /*
   * Called automatically when the mouse button is released
   */
  public void mouseReleased(MouseEvent e)
  {
    if (!myTurn)
      return;

    // board "knows" how to translate raw pixel coordinates x, y
    //   into row, col on the board:

    Location pos = board.getPos(e.getX(), e.getY());
    int row = pos.getRow();
    int col = pos.getCol();

    if (game.isEmpty(row, col))
    {
      game.makeMove(row, col, this);
      program.hasMoved();
      myTurn = false;
    }
  }

  // Not used but required by the MouseListener interface spec:
  public void mouseClicked(MouseEvent e) {}
  public void mousePressed(MouseEvent e) {}
  public void mouseEntered(MouseEvent e) {}
  public void mouseExited(MouseEvent e) {}
}
