/*
 * Theophanis Fox, June 2018
 * 
 * Strategy.java
 * TicTacToe strategy
 */
import java.util.*;

public abstract class Strategy
{
  protected final Location CENTER = new Location(1,1);
  protected final Location[] CORNERS = {new Location(0,0), new Location(0,2),
                                      new Location(2,0), new Location(2,2)};
  protected final Location[] EDGES = {new Location(0,1), new Location(1,0),
                                    new Location(1,2), new Location(2,1)};

  // finds the best move, if any, determined by the strategy
  public abstract Location findBestMove(TicTacToeGame game);
  
  // returns a random available move in the game
  public Location findRandomMove(TicTacToeGame game)
  {
    int rows = game.numRows(), cols = game.numCols();
    Location[] list = new Location[game.countEmpty()];

    int count = 0;

    for (int r = 0; r < rows; r++)
      for (int c = 0; c < cols; c++)
        if (game.isEmpty(r, c))
          list[count++] = new Location(r, c);
    
    int n = (int)(list.length * Math.random());
    return list[n];
  }
  
  // returns a random available move from the given array of locations
  public Location findRandomMove(TicTacToeGame game, Location[] locs)
  {
    ArrayList<Location> list = new ArrayList<>();
    
    for(Location loc : locs)
        if(game.isEmpty(loc))
            list.add(loc);
        
    int n = (int)(list.size() * Math.random());
    return list.get(n);
  }
  
  // returns a random available move from the given arraylist of locations
  public Location findRandomMove(TicTacToeGame game, ArrayList<Location> locs)
  {
    ArrayList<Location> list = new ArrayList<>();
    
    for(Location loc : locs)
        if(game.isEmpty(loc))
            list.add(loc);
        
    int n = (int)(list.size() * Math.random());
    return list.get(n);
  }
}
