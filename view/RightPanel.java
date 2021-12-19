/************************************************************************
 * AP4B Project - Fall semester 2021 - Kanagawa, UTBM-like version
 * Authors : Jules RAMOS - jules.ramos@utbm.fr, Malak FADILI - malak.fadili@utbm.fr, Alan GAUTHIER - alan.gauthier@utbm.fr and Léo CHAILLARD - leo.chaillard@utbm.fr
 * Creation date : December, 2021
 ************************************************************************/

package view;
import model.*;
import manager.*;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;

import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.RenderingHints;

import java.util.*;

/**
 * Right panel of our JFrame, containing
 * all the games' utilities / informations.
 */
public class RightPanel extends JPanel{
  //Attributes
  private static final float ELEMENT_SIZE = 0.8f;
  private static final float Y_ELEMENTS = 3;
  public static float temporaryCardXCoordinate; //At the middle
  public static float temporaryCardYCoordinate; //At the middle
  public static float temporaryCardWidth;
  public static float temporaryCardHeight;

  private JLabel gameInfos;
  private JLabel passTurn;
  private JLabel pickUpColumn;

  //Constructor
  public RightPanel(int size)
  {
    //Set up of the panel
    this.setBackground(new Color(0xF2E1C1));
    this.setLayout(new GridLayout(3,1));
    this.setMinimumSize(new Dimension(size,0));
    this.setPreferredSize(new Dimension(size,0));

    //JLabel for the current player
    this.gameInfos = new JLabel();
    Font font1 = new Font("Arial", Font.BOLD,20);
    this.gameInfos.setFont(font1);
    this.gameInfos.setForeground(Color.BLACK);
    this.gameInfos.setHorizontalAlignment(SwingConstants.CENTER);
    this.gameInfos.setVerticalAlignment(SwingConstants.TOP);

    //JLabels for the available actions
    Font font2 = new Font("Arial", Font.BOLD,25);
    this.passTurn = new JLabel("PASS TURN");
    this.passTurn.setFont(font2);
    this.passTurn.setForeground(Color.BLACK);
    this.passTurn.setHorizontalAlignment(SwingConstants.CENTER);
    this.passTurn.setVerticalAlignment(SwingConstants.BOTTOM);

    Font font3 = new Font("Arial", Font.BOLD,20);
    this.pickUpColumn = new JLabel("PICK UP COLUMN");
    this.pickUpColumn.setFont(font3);
    this.pickUpColumn.setForeground(Color.BLACK);
    this.pickUpColumn.setHorizontalAlignment(SwingConstants.CENTER);
    this.pickUpColumn.setVerticalAlignment(SwingConstants.TOP);


    //Adding the components to the panel
    this.add(this.gameInfos);
    this.add(this.passTurn);
    this.add(this.pickUpColumn);
  }

  //Methods
  public JLabel getPassLabel(){return this.passTurn;}

  /***************************************************/

  public JLabel getPickColumnLabel(){return this.pickUpColumn;}

  /***************************************************/

  public void updateInfos(String name)
  {
    //Currently Playing
    String hex = "#"+Integer.toHexString(Color.BLACK.getRGB()).substring(2);
    this.gameInfos.setText("<html><br/><br/><font color='" + hex + "'>" + name + "</font></html>");
  }

  /***************************************************/

  public void paint(Graphics g)
  {
    super.paint(g);

    //Getting size of the panel
    float width = getWidth();
    float height = getHeight();


    //Direction
    Direction d = new Direction(ELEMENT_SIZE/2 + 0.09f, ELEMENT_SIZE/2 + 0.1f);
    d.setScale(width, height/Y_ELEMENTS);

    //Temporary hand zone
    Rectangle temporary = new Rectangle();
    temporary.setDirection(d);
    temporary.setScale(width, height/3);
    temporary.setSide(0.9f);

    //Thicker borders
    Graphics2D g2 = (Graphics2D) g;
    g2.setStroke(new BasicStroke(3));

    //Card
    DrawCard card = new DrawCard();
    card.setDirection(d);
    card.setScale(width, height/3);
    card.setSide(ELEMENT_SIZE);
    this.temporaryCardWidth = card.getWidth();
    this.temporaryCardHeight = card.getHeight();

    //Strings
    DrawString string = new DrawString();
    string.setDirection(d);
    string.setScale(width, height/3);
    string.setSide(1.0f);

    //Drawing elements
    g.setColor(new Color(0x484339));
    d.left(0.4f);
    g.setFont(new Font("Baskerville Old Face", Font.BOLD, 20));
    string.draw(g,"Temporary Hand");

    d.resetMove();
    d.down(0.5f);

    //Temporary Cards
    temporary.draw(g2);
    g.setColor(Color.lightGray);
    this.temporaryCardXCoordinate = card.getX();
    this.temporaryCardYCoordinate = card.getY();
    for(Player p : Game.players)
    {
      for(Card c : p.getTemporaryHand())
      {
        if(c != null) card.fill(g);
      }
    }

  }
}
