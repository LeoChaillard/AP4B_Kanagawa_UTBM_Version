/************************************************************************
 * AP4B Project - Fall semester 2021 - Kanagawa, UTBM-like version
 * Authors : Jules RAMOS - jules.ramos@utbm.fr, Malak FADILI - malak.fadili@utbm.fr, Alan GAUTHIER - alan.gauthier@utbm.fr and Léo CHAILLARD - leo.chaillard@utbm.fr
 * Creation date : December, 2021
 ************************************************************************/

package view;
import model.*;
import manager.*;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Point;

import javax.swing.JPanel;
import javax.swing.ImageIcon;

import java.util.*;

/**
 * Class Defining the inventory cards tab.
 * It draws the player project and hand cards.
 */
public class InventoryCardsTab extends JPanel{
  //Attributes
  private static final int X_ELEMENTS = 8;
  private static final int Y_ELEMENTS = 5;
  private static final float ELEMENT_SIZE = 0.95f;
  private static final float CARD_SIZE = 0.80f;
  private static final float CARD_SIZE_TO_REAL_SIZE = 1.0f/CARD_SIZE;
  private static final int LINE_ELEMENTS = X_ELEMENTS - 2;

  private ImageIcon icon;
  private int cardWidth;
  private int cardHeight;
  private int [][] cardCoords;

  //Constructor
  public InventoryCardsTab()
  {
    this.icon = new ImageIcon("assets/icon.jpg");
    this.cardWidth = 0;
    this.cardHeight = 0;
    this.cardCoords = new int[12][2];
  }

  //Methods
  public boolean cardContains(int cardIndex, Point p)
  {
    return (cardCoords[cardIndex][0] - cardWidth/2 <= p.getX())  && (cardCoords[cardIndex][0] + cardWidth/2 >= p.getX())  && (cardCoords[cardIndex][1] - cardHeight/2 <= p.getY()) && (cardCoords[cardIndex][1] + cardHeight/2 >= p.getY());
  }

  /***************************************************/
  @Override
  public void paintComponent(Graphics g)
  {
    super.paintComponent(g);
    int h= this.getHeight();
    int w = this.getWidth();
    g.drawImage(icon.getImage(),(this.getWidth()-w)/2,(this.getHeight()-h)/2,w,h,this);
  }

  /***************************************************/

  @Override
  public void paint(Graphics g)
  {
    super.paint(g);
    //Getting size of the window
    float width = getWidth();
    float height = getHeight();

    //Direction
    Direction d = new Direction( ELEMENT_SIZE/2, ELEMENT_SIZE/2 + 0.25f);
    d.setScale(width/X_ELEMENTS,height/Y_ELEMENTS);

    //Cards
    Direction cardDir = new Direction( 0.10f + 0.5f, 0.5f + 2.5f);
    DrawCard card = new DrawCard(CARD_SIZE * (width/X_ELEMENTS), CARD_SIZE * (height/Y_ELEMENTS));

    //Rectangles
    Rectangle rec = new Rectangle();
    rec.setDirection(d);
    rec.setScale(width/X_ELEMENTS,height/Y_ELEMENTS);
    rec.setSide(ELEMENT_SIZE);

    //Strings
    DrawString string = new DrawString();
    string.setDirection(d);
    string.setScale(width/X_ELEMENTS, height/Y_ELEMENTS);
    string.setSide(1.0f);

    //Drawing project slots
    Graphics2D g2 = (Graphics2D) g;
    g2.setStroke(new BasicStroke(2));

    for(int j=0;j<3;++j)
    {
      d.right(1);
      for(int i=0;i<LINE_ELEMENTS;++i)
      {
        g2.setColor(new Color(0x484339));
        rec.draw(g2);
        d.right(1);
      }
      d.resetMove();
      d.down(1+j);
    }

    //Drawing project cards
    d.resetMove();
    d.right(1);
    cardDir.resetMove();
    cardDir.right(CARD_SIZE_TO_REAL_SIZE);
    int projectCards = 0;
    for( Card c : Game.players.get(Game.playerIndex).getProject())
    {
      if(projectCards == 6)
      {
        cardDir.resetMove();
        cardDir.down(5.0f + 2.5f);
        cardDir.right(CARD_SIZE_TO_REAL_SIZE);
      }

      if(projectCards == 12)
      {
        cardDir.resetMove();
        cardDir.down((5.0f + 2.5f)*2.0f);
        cardDir.right(CARD_SIZE_TO_REAL_SIZE*2.0f);
      }

      if(c != null)
      {
        ++projectCards;
        g.setColor(Color.lightGray);

        card.setDrawBack(false);
        card.setDirection(cardDir);
        card.setToDraw(c);
        card.setInInventory(true);
        card.fill(g);

        cardDir.down(2.5f);
        this.cardCoords[projectCards-1][0] = card.getX();
        this.cardCoords[projectCards-1][1] = card.getY();
        this.cardWidth = card.getWidth();
        this.cardHeight = card.getHeight();
        cardDir.up(2.5f);

        cardDir.right(CARD_SIZE_TO_REAL_SIZE);
      }
    }

    //Drawing hand slots
    d.resetMove();
    d.down(3.50f);
    d.right(1);

    for(int i=0;i<LINE_ELEMENTS;++i)
    {
      g2.setColor(new Color(0x605A4D));
      rec.draw(g2);
      d.right(1);
    }

    //Drawing hand cards
    cardDir.resetMove();
    cardDir.right(CARD_SIZE_TO_REAL_SIZE);
    cardDir.down(18.75f);
    for(Card c : Game.players.get(Game.playerIndex).getHand())
    {
      if(c != null)
      {
       g.setColor(Color.lightGray);
       card.setDrawBack(false);
       card.setDirection(cardDir);
       card.setToDraw(c);
       card.fill(g);

       cardDir.right(CARD_SIZE_TO_REAL_SIZE);
      }
    }

    //Drawing Strings
    d.resetMove();
    d.up(0.5f);
    d.right(0.5f);

    g.setFont(new Font("Baskerville Old Face", Font.BOLD, 30));
    g.setColor(new Color(0x484339));
    string.draw(g,"Project");

    g.setColor(new Color(0x605A4D));
    d.down(3.50f);
    string.draw(g,"Hand");
  }
}
