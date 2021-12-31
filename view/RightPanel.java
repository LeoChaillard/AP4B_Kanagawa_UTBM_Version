/************************************************************************
 * AP4B Project - Fall semester 2021 - Kanagawa, UTBM-like version
 * Authors : Jules RAMOS - jules.ramos@utbm.fr, Malak FADILI - malak.fadili@utbm.fr, Alan GAUTHIER - alan.gauthier@utbm.fr and Léo CHAILLARD - leo.chaillard@utbm.fr
 * Creation date : December, 2021
 ************************************************************************/

package view;
import model.*;
import manager.*;
import java.util.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.Point;

import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.ImageIcon;
import javax.swing.BorderFactory;

/**
 * Right panel of our JFrame, containing
 * all the games' utilities / informations.
 */
public class RightPanel extends JPanel{
  //Attributes
  private static final float CARD_SIZE = 0.8f;
  private static final float X_ELEMENTS = 4;
  private static final float Y_ELEMENTS = 3;

  private float temporaryCardXCoordinate; //At the middle
  private float temporaryCardYCoordinate; //At the middle
  private float temporaryCardWidth;
  private float temporaryCardHeight;

  private JLabel gameInfos;
  private JLabel passTurn;
  private JLabel pickUpColumn;
  private JButton arrow;

  //Constructor
  public RightPanel(int size)
  {
    //Set up of the panel
    this.setBackground(Colors.getBackgroundColors()[1]);
    this.setLayout(new GridBagLayout());
    this.setMinimumSize(new Dimension(size,0));
    this.setPreferredSize(new Dimension(size,0));

    //JLabel for the current player
    this.gameInfos = new JLabel("<html><br>Player</html>");
    Font font1 = new Font("Arial", Font.BOLD,20);
    this.gameInfos.setFont(font1);

    //JLabels for the available actions
    Font font2 = new Font("Arial", Font.BOLD,25);
    this.passTurn = new JLabel("PASS TURN");
    this.passTurn.setFont(font2);

    Font font3 = new Font("Arial", Font.BOLD,20);
    this.pickUpColumn = new JLabel("PICK UP COLUMN");
    this.pickUpColumn.setFont(font3);

    //JLabel arrow for swapping temporary hand cards
    ImageIcon gear = new ImageIcon("assets/arrow.png");
    this.arrow = new JButton(gear);
    this.arrow.setBorder(BorderFactory.createEmptyBorder());
    this.arrow.setBorderPainted(false);
    this.arrow.setOpaque(false);
    this.arrow.setContentAreaFilled(false);

    //GridBagConstraints and adding components
    GridBagConstraints c = new GridBagConstraints();
    c.fill = GridBagConstraints.HORIZONTAL;
    c.anchor = GridBagConstraints.PAGE_START;
    c.gridx = 0;
    c.gridy = 0;
    c.weighty = 0.5;
    c.insets = new Insets(0,0,0,0);
    this.add(this.gameInfos,c);

    c.fill = GridBagConstraints.HORIZONTAL;
    c.anchor = GridBagConstraints.PAGE_END;
    c.gridx = 0;
    c.gridy = 1;
    c.weighty = 0.5;
    c.insets = new Insets(40,0,0,0);
    this.add(this.arrow,c);

    c.fill = GridBagConstraints.HORIZONTAL;
    c.anchor = GridBagConstraints.PAGE_END;
    c.gridx = 0;
    c.gridy = 2;
    c.weighty = 0.5;
    c.insets = new Insets(0,0,5,0);
    this.add(this.passTurn,c);


    c.fill = GridBagConstraints.HORIZONTAL;
    c.anchor = GridBagConstraints.PAGE_START;
    c.gridx = 0;
    c.gridy = 3;
    c.weighty = 0.5;
    c.insets = new Insets(0,0,0,0);
    this.add(this.pickUpColumn,c);
  }

  //Methods
  public boolean temporaryHandContains(Point pos)
  {
    return (this.temporaryCardXCoordinate - this.temporaryCardWidth/2 <= pos.getX()) && (this.temporaryCardXCoordinate + this.temporaryCardWidth/2 >= pos.getX()) && (this.temporaryCardYCoordinate - this.temporaryCardHeight <= pos.getY()) && (this.temporaryCardYCoordinate + this.temporaryCardHeight >= pos.getY());
  }

  public JLabel getPassLabel(){return this.passTurn;}

  /***************************************************/

  public JLabel getPickColumnLabel(){return this.pickUpColumn;}

  /***************************************************/

  public JButton getArrowButton(){return this.arrow;}

  /***************************************************/

  public void updateInfos(String name)
  {
    //Currently Playing
    String hex = "#"+Integer.toHexString(Color.BLACK.getRGB()).substring(2);
    this.gameInfos.setText("<html><br><font color='" + hex + "'>" + name + "</font></html>");
  }

  /***************************************************/

  public void paint(Graphics g)
  {
    super.paint(g);

    //Getting size of the panel
    float width = getWidth();
    float height = getHeight();

    //Direction
    Direction d = new Direction(CARD_SIZE/2 + 0.09f, CARD_SIZE/2 + 0.1f);
    d.setScale(width, height/Y_ELEMENTS);

    Direction cardDir = new Direction( 0.10f + 0.5f, 0.5f + 0.15f);

    //Temporary hand zone
    Rectangle temporary = new Rectangle();
    temporary.setDirection(d);
    temporary.setScale(width, height/3);
    temporary.setSide(0.9f);

    //Thicker borders
    Graphics2D g2 = (Graphics2D) g;
    g2.setStroke(new BasicStroke(3));

    //Card
    DrawCard card = new DrawCard(CARD_SIZE * width, CARD_SIZE * height/3);
    card.setDirection(cardDir);
    card.setScale(width, height/3);
    card.setSide(CARD_SIZE);
    card.setDrawBack(false);
    card.setInInventory(false);
    this.temporaryCardWidth = temporary.getWidth();
    this.temporaryCardHeight = temporary.getHeight();

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
    this.temporaryCardXCoordinate = temporary.getX();
    this.temporaryCardYCoordinate = temporary.getY();
    cardDir.down(4.25f);
    for(Player p : Game.players)
    {
      for(Card c : p.getTemporaryHand())
      {
        if(c != null)
        {
          card.setToDraw(c);
          card.fill(g);
        }
      }
    }
  }
}
