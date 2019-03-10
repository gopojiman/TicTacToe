/*
 * Theophanis Fox, June 2018
 * 
 * PlebianStrategy.java
 * Implements Plebian TicTacToe strategy
 */

public class PlebianStrategy extends Strategy
{
  // Returns the best move for completing or blocking 3 in a row
  public Location findBestMove(TicTacToeGame game)
  {
    Location pos = game.winOpportunity('O');
    if (pos != null)
    	return pos;
    
    pos = game.winOpportunity('X');
    if (pos != null)
    	return pos;
    	
    return null;
  }
}
