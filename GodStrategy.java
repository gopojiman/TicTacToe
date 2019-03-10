/*
 * Theophanis Fox, June 2018
 * 
 * GodStrategy.java
 * Implements God TicTacToe strategy
 */
import java.util.*;

public class GodStrategy extends Strategy
{
  private String firstMove;
  
  // Returns the best move for the current board
  public Location findBestMove(TicTacToeGame game)
  {
    Location pos = game.winOpportunity('O');
    if (pos != null)
    	return pos;
    
    pos = game.winOpportunity('X');
    if (pos != null)
    	return pos;
    	
    int turn = 5 - game.countEmpty() / 2;
    
    // returns the best first turn moves
    if (turn == 1)
    {
        if (!game.isEmpty(CENTER))
        {
            firstMove = "center";
            return findRandomMove(game, CORNERS);
        }
        
        for (Location corner : CORNERS)
            if (!game.isEmpty(corner))
            {
                firstMove = "corner";
    		return CENTER;
            }
        
        firstMove = "edge";
        return CENTER;
    }
    
    // returns the best second turn moves
    if (turn == 2)
    {
        if (firstMove.equals("center"))
            return findRandomMove(game, CORNERS);
            
        if (firstMove.equals("corner"))
            return findRandomMove(game, EDGES);
        
        // attempts to force a win if the human started at an edge
        ArrayList<Location> list = new ArrayList<>();
        for (Location edge : EDGES)
            if (!game.isEmpty(edge))
                list.add(edge);
        if (list.size() == 2 && (list.get(0).getRow() == list.get(1).getRow() || list.get(0).getCol() == list.get(1).getCol()))
            return findRandomMove(game, CORNERS);
            
        // prevents the human from winning if the human started at an edge
        ArrayList<Location> locs = new ArrayList<>();
        for (Location corner : CORNERS)
        	for (Location edge : EDGES)
        		if (!game.isEmpty(edge) && !locs.contains(corner) &&
                           ((Math.abs(corner.getRow() - edge.getRow()) == 1 && corner.getCol() == edge.getCol()) || 
                            (Math.abs(corner.getCol() - edge.getCol()) == 1 && corner.getRow() == edge.getRow())))
        			locs.add(corner);
        return findRandomMove(game, locs);
    }
    
    return null;
  }
}
