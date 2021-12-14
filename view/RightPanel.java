/************************************************************************
 * AP4B Project - Fall semester 2021 - Kanagawa, UTBM-like version
 * Authors : Jules RAMOS - jules.ramos@utbm.fr, Malak FADILI - malak.fadili@utbm.fr, Alan GAUTHIER - alan.gauthier@utbm.fr and Léo CHAILLARD - leo.chaillard@utbm.fr
 * Creation date : December, 2021
 ************************************************************************/

package view;
import model.*;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.GridLayout;

import java.util.*;

/**
 * Right panel of our JFrame, containing
 * all the games' utilities / informations.
 */
public class RightPanel extends JPanel{
  //Attributes
  private JButton menuButton;
  private JLabel gameInfos;

  //Constructor
  public RightPanel(int size)
  {
    //Set up of the panel
    this.setBackground(Color.WHITE);
    this.setLayout(new GridLayout(4,1));
    this.setMinimumSize(new Dimension(size,0));
    this.setPreferredSize(new Dimension(size,0));

    //Buttons
    this.menuButton = new JButton("Menu");
    this.menuButton.setBackground(Color.WHITE);

    //First JLabel for updating the current player infos
    this.gameInfos = new JLabel();
    Font font1 = new Font("Arial", Font.BOLD,16);
    this.gameInfos.setFont(font1);
    this.gameInfos.setForeground(Color.BLACK);
    this.gameInfos.setHorizontalAlignment(SwingConstants.LEFT);
    this.gameInfos.setVerticalAlignment(SwingConstants.TOP);

    //Adding the components to the panel
    this.add(this.menuButton);
    this.add(this.gameInfos);
  }

  //Methods
  public JButton getMenuButton(){return this.menuButton;}

  /***************************************************/

  public void updateInfos(List<Player> players,String name)
  {
    //Currently Playing
    String hex = "#"+Integer.toHexString(Color.BLACK.getRGB()).substring(2);
    this.gameInfos.setText("<html><br/>&emsp;Game's informations<br/><br/>Playing : <font color='" + hex + "'>" + name + "</font></html>");


    //Global players informations

  }
}
