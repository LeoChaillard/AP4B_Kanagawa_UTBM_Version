/************************************************************************
 * AP4B Project - Fall semester 2021 - Kanagawa, UTBM-like version
 * Authors : Jules RAMOS - jules.ramos@utbm.fr, Malak FADILI - malak.fadili@utbm.fr, Alan GAUTHIER - alan.gauthier@utbm.fr and Léo CHAILLARD - leo.chaillard@utbm.fr
 * Creation date : December, 2021
 ************************************************************************/

package graphics;
import play.*;
import java.util.*;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Polygon;

import javax.swing.JPanel;




public class Board extends JPanel{
  //Attributes
  private static final long serialVersionUID = 1L;
  private static final int X_ELEMENTS = 4;
  private static final int Y_ELEMENTS = 3;
  private static final float ELEMENT_SIZE = 0.95f;
  private static final float CARD_SIZE = 0.80f;
  private static final Card [][] slots = new Card[X_ELEMENTS][Y_ELEMENTS]; //Origin (0,0) at the bottom left corner
  private static final boolean [][] hiddenCards = {{true,false,false}, {false,true,false}, {false,false,true}, {true,false,false}}; //Origin (0,0) at the bottom left corner

  //Constructor
  public Board()
  {
    //For testing purposes
    addRow(1);
    addRow(2);
    removeColumn(2);
  }

  //Methods
  public static Card [][] getSlots(){return slots;}

  /***************************************************/

  public boolean isHidden(int index1, int index2)
  {
    return hiddenCards[index1][index2];
  }

  /***************************************************/

  public void removeColumn(int column)
  {
    for(int i=0;i<Y_ELEMENTS;++i) slots[column-1][i] = null;
  }

  /***************************************************/

  public void addRow(int row)
  {
    for(int i=0;i<X_ELEMENTS;++i) slots[i][Y_ELEMENTS-row] = new Card();
  }

  /***************************************************/

  @Override
  public void paint(Graphics g)
  {
    super.paint(g);
    this.setBackground(Color.WHITE);

    //Getting size of the window
    float width = getWidth();
    float height = getHeight();

  	//Direction
    Direction d = new Direction( ELEMENT_SIZE/2, ELEMENT_SIZE/2 + 2*ELEMENT_SIZE + 0.1f); //Origin position at the middle of the bottom left square, adding 0.1 because of the window top bar
    d.setScale(width/X_ELEMENTS,height/Y_ELEMENTS);

    //Rectangles
    Rectangle rec = new Rectangle();
    rec.setDirection(d);
    rec.setScale(width/X_ELEMENTS,height/Y_ELEMENTS);
    rec.setSide(ELEMENT_SIZE);

    //Cards
    DrawCard card = new DrawCard();
    card.setDirection(d);
    card.setScale(width/X_ELEMENTS,height/Y_ELEMENTS);
    card.setSide(CARD_SIZE);

    //Drawing board and cards
    for(int k=0;k<X_ELEMENTS;++k)
    {
      for(int j=0;j<Y_ELEMENTS;++j)
    	{
    		if(hiddenCards[k][j])
        {
    			g.setColor(Color.RED);
    			rec.fill(g);

          //Hidden card
          if(slots[k][j] != null)
          {
            g.setColor(Color.darkGray);
            card.fill(g);
          }
        }
    		else
        {
    			g.setColor(Color.YELLOW);
    			rec.fill(g);

          //Visible card
          if(slots[k][j] != null)
          {
            g.setColor(Color.lightGray);
            card.fill(g);
          }
        }
    		d.up(1);
    	}
      d.resetMove();
    	d.right(k+1); //Going to the right next column
    }

    Toolkit.getDefaultToolkit().sync();
  }

}
