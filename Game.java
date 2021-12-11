/************************************************************************
 * AP4B Project - Fall semester 2021 - Kanagawa, UTBM-like version
 * Authors : Jules RAMOS - jules.ramos@utbm.fr, Malak FADILI - malak.fadili@utbm.fr, Alan GAUTHIER - alan.gauthier@utbm.fr and Léo CHAILLARD - leo.chaillard@utbm.fr
 * Creation date : December, 2021
 ************************************************************************/

import play.*;
import graphics.*;
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
  private Window window;
  private Menu menu;
  private static List<Player> players = new ArrayList<Player>(4);

  private enum Actions
  {
    MENU,
    NEWGAME,
    RESUME
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

    //Adding action listeners
    this.menu.getNewGame().addActionListener(this);
    this.menu.getResume().addActionListener(this);
    this.window.getMenuButton().addActionListener(this);

    //Setting action commands
    this.menu.getNewGame().setActionCommand(Actions.NEWGAME.name());
    this.menu.getResume().setActionCommand(Actions.RESUME.name());
    this.window.getMenuButton().setActionCommand(Actions.MENU.name());

    this.menu.getResume().setEnabled(false);

    this.menu.draw();
  }

  /***************************************************/

  public void resetGame()
  {
    this.menu.getResume().setEnabled(true);
  }

}
