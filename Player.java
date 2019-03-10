/*
 * Taken (mostly) from APCS, June 2018
 *
 * Player.java
 * A player in a board game
 */

public interface Player
{
    char getSymbol();
  /*
   * Returns a prompt to be displayed before
   * the next move of this player
   */
  String getPrompt();
  String getFirstPrompt();

  /*
   * Returns a message to be displayed when
   * this player has won
   */
  String getWinMessage();

  /*
   * Initiates this player's next move
   */
  void makeMove();
}

