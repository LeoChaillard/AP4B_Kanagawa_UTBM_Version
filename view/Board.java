/************************************************************************
 * AP4B Project - Fall semester 2021 - Kanagawa, UTBM-like version
 * Authors : Jules RAMOS - jules.ramos@utbm.fr, Malak FADILI - malak.fadili@utbm.fr, Alan GAUTHIER - alan.gauthier@utbm.fr and Léo CHAILLARD - leo.chaillard@utbm.fr
 * Creation date : December, 2021
 ************************************************************************/

package view;
import model.*;
import manager.*;
import java.util.*;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.BasicStroke;
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
  public static final float [] columnsXCoordinate = new float[X_ELEMENTS];
  public static final float [] columnsYCoordinate = new float[X_ELEMENTS];
  public static float columnWidth;
  public static float columnHeight;

  private boolean [] highlightColumns;
  private int addedRows;

  //Constructor
  public Board()
  {
    this.addedRows = 0;
    this.highlightColumns = new boolean[X_ELEMENTS];
    resetHighlight();
  }

  //Methods
  public void removeAll()
  {
    for(int i=1;i<=4;++i) removeColumn(i);
    this.addedRows = 0;
    resetHighlight();
  }

  /***************************************************/

  public void resetHighlight()
  {
      for(int i=0;i<X_ELEMENTS;++i) this.highlightColumns[i] = false;
  }

  /***************************************************/

  public void setHighlight(int col, boolean h){this.highlightColumns[col] = h;}

  /***************************************************/

  public boolean isHighlighted(int col){return this.highlightColumns[col];}

  /***************************************************/

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

  public void addRow(int players)
  {
    ++this.addedRows;

    try
    {
      for(int i=0;i<players;++i) slots[i][Y_ELEMENTS-this.addedRows] = Game.cardFromDeck();
    } catch(Exception e) {}

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
    Direction d = new Direction( ELEMENT_SIZE/2, ELEMENT_SIZE/2 + 2*ELEMENT_SIZE + 0.12f); //Origin position at the middle of the bottom left square, adding 0.1 because of the window top bar
    d.setScale(width/X_ELEMENTS,height/Y_ELEMENTS);

    //Columns
    Rectangle col = new Rectangle();
    Direction colDir = new Direction(ELEMENT_SIZE/2, ELEMENT_SIZE/2 + 0.025f);
    colDir.setScale(width/X_ELEMENTS, height);
    col.setDirection(colDir);
    col.setScale(width/X_ELEMENTS, height);
    col.setSide(1.0f);
    g.setColor(new Color(253,241,184,100));
    this.columnWidth = col.getWidth();
    this.columnHeight = col.getHeight();

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

    //Drawing Columns
    for(int i =0;i<X_ELEMENTS;++i)
    {
      if(this.highlightColumns[i])
      {
        /*Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLACK);
        g2.setStroke(new BasicStroke(10));
        col.draw(g2);*/

        //g.setColor(new Color(253,241,184,100));
        g.setColor(new Color(153,153,153,50));
      }
      else g.setColor(new Color(0xfdf1b8));

      col.fill(g);
      this.columnsXCoordinate[i] = col.getX();
      this.columnsYCoordinate[i] = col.getY();
      colDir.right(1);
    }




    //Drawing board and cards
    for(int k=0;k<X_ELEMENTS;++k)
    {
      for(int j=0;j<Y_ELEMENTS;++j)
    	{
    		if(hiddenCards[k][j])
        {
    			g.setColor(new Color(0xc4342d));
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
    			g.setColor(new Color(0xc4342d));

          //Drawing thicker borders
          Graphics2D g2 = (Graphics2D) g;
          g2.setStroke(new BasicStroke(2));
    			rec.draw(g2);

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
