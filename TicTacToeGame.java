/*
 * Theophanis Fox, June 2018
 * 
 * TicTacToeGame.java
 * Implements a TicTacToe game
 */

public class TicTacToeGame extends CharMatrix
{
  private BoardPanel board;

  public TicTacToeGame(BoardPanel board)
  {
    super(board.numRows(), board.numCols());
    this.board = board;
    board.update(this);
  }

  /*
   * Returns true if row, col isn't out of bounds and the character
   * at row, col is a space, false otherwise.
   */
  public boolean isEmpty(int row, int col)
  {
    return isValid(row, col) && super.isEmpty(row, col);
  }
  
  public boolean isEmpty(Location loc)
  {
    return isEmpty(loc.getRow(), loc.getCol());
  }
  
  // counts the number of empty spaces on the board
  public int countEmpty(Location[] list)
  {
      int count = 0;
      for (Location loc : list)
          if (isEmpty(loc))
              count++;
      return count;
  }

  /*
   * Returns true if the game is finished
   */
  public boolean isWon()
  {
    return countEmpty() == 0 || threeInARow();
  }

  /*
   * Adjusts and repaints the board after the move at row, col
   */
  public void makeMove(int row, int col, Player player)
  {
    setCharAt(row, col, player.getSymbol());
    board.setDisplayCount(0);
    board.update(this);
  }
  
  // clears the board
  public void reset()
  {
  	clearRect();
  	board.update(this);
  }
  
  /* 
   * if there is an opportunity for there to be 3 of the given character in a row,
   * returns the location the character needs to be placed for this to occur,
   * returns null otherwise
   */
  public Location winOpportunity(char c)
  {
  	for (int i = 0; i < numRows(); i++)
  		for (int j = 0; j < numCols(); j++)
  			if (charAt(i, j) == c)
  				for (int a = i-1; a <= i+1; a++)
  					for (int b = j-1; b <= j+1; b++)
  						if (isValid(a, b) && charAt(a, b) == c && !(a == i && b == j))
  						{
  							int x = 2 * i - a;
                        	int y = 2 * j - b;
                        	if (isValid(x, y) && isEmpty(x, y))
                        		return new Location(x, y);
  						}
  						else if (isValid(a, b) && isEmpty(a, b))
  						{
  							int x = 2 * a - i;
                        	int y = 2 * b - j;
                        	if (isValid(x, y) && charAt(x, y) == c)
                        		return new Location(a, b);
  						}
  	return null;
  }
}
