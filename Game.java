/************************************************************************
 * AP4B Project - Fall semester 2021 - Kanagawa, UTBM-like version
 * Authors : Jules RAMOS - jules.ramos@utbm.fr, Malak FADILI - malak.fadili@utbm.fr, Alan GAUTHIER - alan.gauthier@utbm.fr and Léo CHAILLARD - leo.chaillard@utbm.fr
 * Creation date : December, 2021
 ************************************************************************/

import model.*;
import view.*;
import java.util.*;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JFrame;

import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;
import java.awt.Color;


public class Game implements ActionListener, MouseListener{
  //Attributes
  private static final int MAX_PLAYERS = 4;

  private static List<Player> players = new ArrayList<Player>(MAX_PLAYERS);
  private Window window;
  private Menu menu;
  private int [] availableMentions;
  private int numberOfPlayers;
  private int playerIndex;

  private enum Actions
  {
    MENU,
    NEWGAME,
    RESUME
  }

  private enum Choices
  {
    PASS,
    PICKUP_COLUMN,
    KEEP_CARD
  }

  //Constructor
  public Game()
  {
    this.window = new Window();
    this.menu = new Menu();
  }

  //Methods
  @Override
  public void mouseClicked(MouseEvent evt)
  {
    int x = evt.getX();
    int y = evt.getY();
    Player player = this.players.get(playerIndex);

    if(true) /*Condition will depend on which column the player clicked*/
    {
      Card [][] boardSlots = new Card[4][3];
      boardSlots = window.getBoard().getSlots();

      /*To be implemented*/

      playerIndex = (playerIndex+1)%4;
    }


  }

  /***************************************************/

  @Override
  public void mousePressed(MouseEvent evt){}
  public void mouseEntered(MouseEvent evt){}
  public void mouseExited(MouseEvent evt){}
  public void mouseReleased(MouseEvent evt){}

  /***************************************************/

  @Override
  public void actionPerformed(ActionEvent evt)
  {
    //Menu Actions
    if (evt.getActionCommand() == Actions.NEWGAME.name())
    {
        this.window.setVisible(true);
        this.menu.setVisible(false);
        resetGame();
    }

    if (evt.getActionCommand() == Actions.RESUME.name())
    {
        this.window.setVisible(true);
        this.menu.setVisible(false);
    }

    if (evt.getActionCommand() == Actions.MENU.name())
    {
        this.window.setVisible(false);
        this.menu.setVisible(true);
    }

    //Players Choices
    if (evt.getActionCommand() == Choices.PASS.name())
    {

    }

    if (evt.getActionCommand() == Choices.PICKUP_COLUMN.name())
    {

    }

    if (evt.getActionCommand() == Choices.KEEP_CARD.name())
    {

    }
  }

  /***************************************************/

  public void start()
  {
    initialize();
  }

  /***************************************************/

  public void initialize()
  {
    //Initializing windows
    this.window.initWindow();
    this.menu.initMenu();

    //Adding action listeners for menu interactions
    this.menu.getNewGame().addActionListener(this);
    this.menu.getResume().addActionListener(this);
    this.window.getMenuButton().addActionListener(this);

    //Setting action commands for menu interactions
    this.menu.getNewGame().setActionCommand(Actions.NEWGAME.name());
    this.menu.getResume().setActionCommand(Actions.RESUME.name());
    this.window.getMenuButton().setActionCommand(Actions.MENU.name());

    //Adding action listeners for player choices


    //Setting action command for player choices


    this.menu.getResume().setEnabled(false);
    this.menu.draw();
  }

  /***************************************************/

  public void resetGame()
  {
    this.menu.getResume().setEnabled(true);
  }

}
