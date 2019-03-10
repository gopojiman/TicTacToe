/*
 * Theophanis Fox, June 2018
 * 
 * BoardPanel.java
 * Implements a board for the TicTacToe program
 */

import java.awt.*;
import java.awt.Dimension;
import javax.swing.JPanel;

public class BoardPanel extends JPanel
{
  private final int ROWS = 3, COLS = 3;   // board dimensions
  private final int CELLSIZE;
  
  private int tentativeRow, tentativeCol, displayCount;
  private TicTacToeGame game;
  
  // Constructor
  public BoardPanel(int size)
  {
    setPreferredSize(new Dimension(COLS * size, ROWS * size));
    setBackground(Color.LIGHT_GRAY);
    CELLSIZE = size;
  }

  // Returns the number of rows in the board
  public int numRows()
  {
    return ROWS;
  }

  // Returns the number of columns in the board
  public int numCols()
  {
    return COLS;
  }
  
  // Returns the location that corresponds to the x,y-coordinates
  // of the mouse click on the board
  public Location getPos(int x, int y)
  {
    return new Location(y / CELLSIZE, x / CELLSIZE);
  }

  // Sets location for the expected move at row, col
  // (to provide visual feedback, e.g. flashing the changed squares)
  public void setMove(int row, int col)
  {
    tentativeRow = row;
    tentativeCol = col;
  }

  // Sets count for visual feedback, e.g. flashing the changed squares
  public void setDisplayCount(int count)
  {
    displayCount = count;
  }

  // Repaints the board after the move at row, col
  public void update(TicTacToeGame game)
  {
    this.game = game;
    repaint();
  }

  // Displays the board after a repaint request
  // (redefines the method of the base class)
  public void paintComponent(Graphics g)
  {
    super.paintComponent(g);

    if (game == null)
      return;

    for (int r = 0; r < ROWS; r++)
    {
      for (int c = 0; c < COLS; c++)
      {
        Color color;

        if (displayCount % 2 != 0 && r == tentativeRow && c == tentativeCol)
          color = Color.WHITE;
        else
          color = Color.LIGHT_GRAY;
        g.setColor(color);
        int x = c * CELLSIZE;
        int y = r * CELLSIZE;
        g.fillRect(x, y, CELLSIZE, CELLSIZE);
        g.setColor(Color.BLACK);
        g.drawRect(x, y, CELLSIZE, CELLSIZE);
        g.drawRect(x+1, y+1, CELLSIZE-2, CELLSIZE-2);
        if (!game.isEmpty(r, c))
        {
          g.setColor(Color.BLACK);
          g.setFont(new Font("Comic Sans MS", Font.BOLD, CELLSIZE));
          char[] symbol = {game.charAt(r, c)};
          g.drawChars(symbol, 0, 1, x + CELLSIZE / 8, y + CELLSIZE * 7/8);
        }
      }
    }

    if (displayCount > 0)
      displayCount--;
    
  }
}
