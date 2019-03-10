/*
 * Theophanis Fox, June 2018
 * 
 * CharMatrix.java
 * Implements a 2-D array of characters
 */

public class CharMatrix
{
  // Fields:
  private char[][] matrix;

  /*
   * Constructor: creates a grid with dimensions rows, cols,
   * and fills it with spaces
   */
  public CharMatrix(int rows, int cols)
  {
  	matrix = new char[rows][cols];
    for (int i = 0; i < rows; i++)
    	for (int j = 0; j < cols; j++)
    		matrix[i][j] = ' ';
  }

  /*
   * Returns the number of rows in grid
   */
  public int numRows()
  {
    return matrix.length;
  }

  /*
   * Returns the number of columns in grid
   */
  public int numCols()
  {
    return matrix[0].length;
  }

  /*
   * Returns the character at row, col location
   */
  public char charAt(int row, int col)
  {
    return matrix[row][col];
  }

  /*
   * Sets the character at row, col location to ch
   */
  public void setCharAt(int row, int col, char ch)
  {
    matrix[row][col] = ch;
  }

  /*
   * Returns true if the character at row, col is a SPACE,
   * false otherwise
   */
  public boolean isEmpty(int row, int col)
  {
    return matrix[row][col] == ' ';
  }
  
  // returns true if the position at row, col is in the matrix
  public boolean isValid(int row, int col)
  {
    return row >= 0 && row < matrix.length && col >= 0 && col < matrix[row].length;
  }
  
  // fills the matrix with spaces
  public void clearRect()
  {
    for (int i = 0; i < matrix.length; i++)
    	for (int j = 0; j < matrix[i].length; j++)
    		matrix[i][j] = ' ';
  }
  
  // returns the number of spaces in the matrix
  public int countEmpty()
  {
  	int count = 0;
  	for (int i = 0; i < matrix.length; i++)
  		for (int j = 0; j < matrix[i].length; j++)
  			if (matrix[i][j] == ' ')
  				count++;
  	return count;
  }
  
  // returns true if there are 3 of any non space character in a row in the matrix
  public boolean threeInARow()
  {
    for (int i = 0; i < matrix.length; i++)
  		for (int j = 0; j < matrix[i].length; j++)
        	if (!isEmpty(i, j))
  			{
            	char current = charAt(i, j);
            	for (int a = i-1; a <= i+1; a++)
  					for (int b = j-1; b <= j+1; b++)
                 		if (isValid(a, b) && charAt(a, b) == current && !(a == i && b == j))
                 		{
                    		int x = 2 * i - a;
                        	int y = 2 * j - b;
                        	if (isValid(x, y) && charAt(x, y) == current)
                        		return true;
                   		}
  			}
    return false;
  }
}
