/*
 * Theophanis Fox, June 2018
 * 
 * TicTacToe.java
 * Implements a TicTacToe game program with multiple
 * gamemodes
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class TicTacToe extends JFrame implements ActionListener
{
  private TicTacToeGame game;
  private JTextField display;
  private JButton infant, plebian, intellectual, god, twoPlayer, reset;
  private Player players[];
  private HumanPlayer human, playerOne, playerTwo;
  private ComputerPlayer computer;
  private int currentPlayer;
  private BoardPanel board;
  private Timer clock;

  public TicTacToe(int size)
  {
    Container c = getContentPane();

    display = new JTextField(20);
    display.setBackground(new Color(240, 240, 240));
    display.setEditable(false);
    c.add(display, BorderLayout.NORTH);
    
    JPanel buttons = new JPanel(new GridLayout(2, 4));
        infant = new JButton("Infant");
        infant.addActionListener(this);
        buttons.add(infant);
        
        plebian = new JButton("Plebian");
        plebian.addActionListener(this);
        buttons.add(plebian);
        
        intellectual = new JButton("Intellectual");
        intellectual.addActionListener(this);
        buttons.add(intellectual);
        
        god = new JButton("God");
        god.addActionListener(this);
        buttons.add(god);
            
        twoPlayer = new JButton("2 Player");
        twoPlayer.addActionListener(this);
        buttons.add(Box.createVerticalGlue());
        buttons.add(twoPlayer);
        
        reset = new JButton("Reset");
        reset.addActionListener(this);
        buttons.add(reset);
        buttons.add(Box.createVerticalGlue());
    c.add(buttons, BorderLayout.SOUTH);

    board = new BoardPanel(size);
    c.add(board, BorderLayout.CENTER);

    game = new TicTacToeGame(board);

    players = new Player[2];
    computer = new ComputerPlayer(this, game, board);
    human = new HumanPlayer(this, game, board, 'X', false);
    playerOne = new HumanPlayer(this, game, board, 'X', true);
    playerTwo = new HumanPlayer(this, game, board, 'O', true);
    
    String[] prompts = {
        " Choose a game mode ya big weenie",
        " Play against a friend or choose a difficulty to fight me",
        " Press one of the buttons to do something"
    };
    display.setText(prompts[(int)(Math.random() * prompts.length)]);
    
    clock = new Timer(10000, this);
    clock.start();
    
    setTitle("TicTacToe");
    setBounds(500, 200, 3 * size, 3 * size + 107);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setResizable(false);
    setVisible(true);
  }
  
  // handles the computer's messages and button actions
  public void actionPerformed(ActionEvent e)
  {
    if (e.getSource() == reset && players[0] == null)
        return;
    if (e.getSource() == clock)
        display.setText(HumanPlayer.getNextPrompt());
    else
    {
        if (e.getSource() == twoPlayer)
        {
            players[0] = playerOne;
            players[1] = playerTwo;
            human.disable();
        }
        else
        {
            if (e.getSource() == infant)
                computer.setStrategy(new InfantStrategy());
            else if (e.getSource() == plebian)
                computer.setStrategy(new PlebianStrategy());
            else if (e.getSource() == intellectual)
                computer.setStrategy(new IntellectualStrategy());
            else if (e.getSource() == god)
                computer.setStrategy(new GodStrategy());
            
            if (e.getSource() != reset)
            {
                players[0] = human;
                players[1] = computer;
                playerOne.disable();
                playerTwo.disable();
            }
        }
        game.reset();
        clock.restart();
        currentPlayer = 0;
    	Player p = players[currentPlayer];
        display.setText(p.getFirstPrompt());
     	p.makeMove();
    }
  }

  // Called by the player when its move is completed.
  public void hasMoved()
  {
    Player p = players[currentPlayer];
    
    if (game.isWon())
    {
        clock.stop();
      display.setText(p.getWinMessage());
    }
    else
    {
        clock.restart();
        currentPlayer = (currentPlayer + 1) % 2;
    	p = players[currentPlayer];
        display.setText(p.getPrompt());
     	p.makeMove();
    }
  }

  public static void main(String[] args)
  {
    TicTacToe t = new TicTacToe(130);
  }
}

