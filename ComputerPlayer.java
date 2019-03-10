/*
 * Theophanis Fox, June 2018
 * 
 * ComputerPlayer.java
 * Implements a computer player in a board game
 */

import java.awt.event.*;
import javax.swing.Timer;

public class ComputerPlayer
    implements Player, ActionListener
{
  private TicTacToe program;
  private TicTacToeGame game;
  private BoardPanel board;

  private Timer clock;
  private int clockCount;
  private int moveRow, moveCol;

  private Strategy strategy;
  private EasySound sound;
  
  private final char symbol = 'O';
  
  private final String[] prompts = {
      " Hmm... Let me think...",
      " Let's see...",
      " Where to move next..."
  };
  private final String[] winMessages = {
      " Ha! I wasn't trying at all but I still won",
      " And this isn't even my final form",
      " Looks like my flawless strategy annihilated you"
  };

  public ComputerPlayer(TicTacToe program, TicTacToeGame game, BoardPanel board)
  {
    this.program = program;
    this.game = game;
    this.board = board;
    clock = new Timer(150, this);
    sound = new EasySound("fattyUMM.wav");
  }
  
  public char getSymbol()
  {
    return symbol;
  }

  /*
   * Sets this player's strategy
   */
  public void setStrategy(Strategy strategy)
  {
    this.strategy = strategy;
  }

  /*
   * Returns a prompt to be displayed before
   * the next move of this player
   */
  public String getPrompt()
  {
    return prompts[(int)(Math.random() * prompts.length)];
  }
  
  public String getNextPrompt()
  {
    return getPrompt();
  }
  
  public String getFirstPrompt()
  {
    return getPrompt();
  }    

  /*
   * Returns a message to be displayed when
   * this player has won
   */
  public String getWinMessage()
  {
    return winMessages[(int)(Math.random() * winMessages.length)];
  }

  /*
   * Initiates this player's next move
   */
  public void makeMove()
  {
    Location pos = strategy.findBestMove(game);
    if (pos == null)
      pos = strategy.findRandomMove(game);

    moveRow = pos.getRow();
    moveCol = pos.getCol();
    clockCount = 5;
    board.setMove(moveRow, moveCol);
    board.setDisplayCount(clockCount);
    clock.start();
  }

  /*
   * Called automatically when the timer fires a pulse
   * (to provide visual feedback for the anticipated move, e.g.
   * flashing the changed squares a few times)
   */
  public void actionPerformed(ActionEvent e)
  {
    if (clockCount > 0)
    {
      if (clockCount == 5)
      {
          Thread t = new Thread()
          {
            public void run()
            {
               sound.play();
            }
          };
          t.start();
      }
      board.update(game);
      clockCount--;
    }
    else
    {
      clock.stop();
      game.makeMove(moveRow, moveCol, this);
      program.hasMoved();
    }
  }
}

