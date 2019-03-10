/*
 * Theophanis Fox, June 2018
 * 
 * IntellectualStrategy.java
 * Implements Intellectual TicTacToe strategy
 */

public class IntellectualStrategy extends Strategy
{
  // Returns the best move for completing or blocking 3 in a row and the best first turn moves
  public Location findBestMove(TicTacToeGame game)
  {
    Location pos = game.winOpportunity('O');
    if (pos != null)
    	return pos;
    
    pos = game.winOpportunity('X');
    if (pos != null)
    	return pos;
    	
    int turn = 5 - game.countEmpty() / 2;
    if (turn == 1)
    {
        if (!game.isEmpty(CENTER))
            return findRandomMove(game, CORNERS);
        
        for (Location corner : CORNERS)
            if (!game.isEmpty(corner))
                return CENTER;
        
        return CENTER;
    }
    
    return null;
  }
}
