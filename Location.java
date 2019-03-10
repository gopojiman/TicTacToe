/*
 * Taken from APCS, June 2018
 * 
 * Location.java
 * Implements a location with two coordinates
 */

public class Location
{
  private int row, col;

  public Location(int r, int c)
  {
    row = r;
    col = c;
  }

  public int getRow()
  {
    return row;
  }

  public int getCol()
  {
    return col;
  }
}
