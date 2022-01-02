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
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Point;

import javax.swing.JPanel;


/**
 * Class defining the board and
 * drawing its graphical representation.
 */
public class Board extends JPanel{
  //Attributes
  private static final long serialVersionUID = 1L;
  private static final int X_ELEMENTS = 4;
  private static final int Y_ELEMENTS = 3;
  private static final float ELEMENT_SIZE = 0.95f;
  private static final float CARD_SIZE = 0.80f;
  private static final float CARD_SIZE_TO_FULL_SIZE = 1.0f/CARD_SIZE;
  private static final Card [][] slots = new Card[X_ELEMENTS][Y_ELEMENTS]; //Origin (0,0) at the bottom left corner
  private static final boolean [][] hiddenCards = {{true,false,false}, {false,true,false}, {false,false,true}, {true,false,false}}; //Origin (0,0) at the bottom left corner
  private static final float [] columnsXCoordinate = new float[X_ELEMENTS]; //At the middle
  private static final float [] columnsYCoordinate = new float[X_ELEMENTS]; //At the middle
  private static float columnWidth;
  private static float columnHeight;

  private boolean [] highlightColumns;
  private int addedRows;

  //Constructor
  public Board()
  {
    this.setOpaque(false);
    addedRows = 0;
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

  public boolean columnContains(int col, Point pos)
  {
    return (this.columnsXCoordinate[col] - this.columnWidth/2 <= pos.getX()) && (this.columnsXCoordinate[col] + this.columnWidth/2 >= pos.getX()) && (this.columnsYCoordinate[col] - this.columnHeight <= pos.getY()) && (this.columnsYCoordinate[col] + this.columnHeight >= pos.getY());
  }

  /***************************************************/

  public void resetHighlight()
  {
      for(int i=0;i<X_ELEMENTS;++i) this.highlightColumns[i] = false;
  }

  /***************************************************/

  public boolean isColumnEmpty(int col)
  {
    return slots[col][0] == null && slots[col][1] == null && slots[col][2] == null;
  }

  /***************************************************/

  public boolean areAllColumnsEmpty()
  {
    for(int i=0;i<Game.numberOfPlayers;++i)
    {
      if(!isColumnEmpty(i)) return false;
    }
    return true;
  }

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

  public int getRemainingColumns()
  {
    int remaining = 0;
    for(int i=0;i<Game.numberOfPlayers;++i) if(!isColumnEmpty(i)) ++remaining;
    return remaining;
  }

  /***************************************************/

  public void addRow(int numberOfPlayers, int pickedCards, int totalCards, boolean newTurn)
  {
    if(pickedCards < totalCards)
    {
      ++addedRows;
      try
      {
        for(int i=0;i<numberOfPlayers;++i)
        {
          if(!isColumnEmpty(i) || newTurn ) //if column has been picked up or new big turn
          {
            slots[i][Y_ELEMENTS-addedRows] = Game.cardFromDeck();
          }
        }
      } catch(Exception e) {}
    }
    newTurn = false;
  }

  /***************************************************/

  @Override
  public void paint(Graphics g)
  {
    super.paint(g);
    this.setBackground(Colors.getBackgroundColors()[0]);

    //Getting size of the window
    float width = getWidth();
    float height = getHeight();

  	//Direction
    Direction d = new Direction(ELEMENT_SIZE/2, ELEMENT_SIZE/2 + 2*ELEMENT_SIZE + 0.05f); //Origin position at the middle of the bottom left square, adding 0.05 because of the window top bar
    d.setScale(width/X_ELEMENTS,height/Y_ELEMENTS);

    //Cards
    Direction cardDir = new Direction(0.10f + 0.5f, 0.5f + 0.15f + (5.0f + 2.5f) * 2.0f); //Origin position for drawing card elements
    DrawCard card = new DrawCard(CARD_SIZE * (width/X_ELEMENTS), CARD_SIZE * (height/Y_ELEMENTS)); //Giving card width and height as parameter

    //Columns
    Rectangle col = new Rectangle();
    Direction colDir = new Direction(ELEMENT_SIZE/2, ELEMENT_SIZE/2 + 0.025f); //Origin position at the middle of the first left column
    colDir.setScale(width/X_ELEMENTS, height);
    col.setDirection(colDir);
    col.setScale(width/X_ELEMENTS, height);
    col.setSide(1.0f);
    g.setColor(Colors.getBoardColors()[2]);
    this.columnWidth = col.getWidth();
    this.columnHeight = col.getHeight();

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

    //Drawing Columns
    for(int i =0;i<X_ELEMENTS;++i)
    {
      if(this.highlightColumns[i]) g.setColor(Colors.getBoardColors()[3]);
      else g.setColor(Colors.getBoardColors()[2]);
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
    		if(this.isHidden(k, j))
        {
    			g.setColor(Colors.getBoardColors()[1]);
    			rec.fill(g);
          Graphics2D g2 = (Graphics2D) g;
          g2.setStroke(new BasicStroke(2));

          //Hidden card
          if(slots[k][j] != null)
          {
            card.setDrawBack(true);
            card.setScale(width/X_ELEMENTS,height/Y_ELEMENTS);
            card.setDirection(d);
            card.setToDraw(slots[k][j]);
            card.setInInventory(false);
            card.fill(g);
          }
        }
    		else
        {
    			g.setColor(Colors.getBoardColors()[0]);

          //Drawing thicker borders
          Graphics2D g2 = (Graphics2D) g;
          g2.setStroke(new BasicStroke(2));
    			rec.draw(g2);

          //Visible card
          if(slots[k][j] != null)
          {
            card.setDrawBack(false);
            card.setDirection(cardDir);
            card.setToDraw(slots[k][j]);
            card.fill(g);
          }
        }
    		d.up(1);
        cardDir.up(5.0f + 2.5f);
    	}
      d.resetMove();
    	d.right(k+1); //Going to the right next column
      cardDir.resetMove();
      cardDir.right(CARD_SIZE_TO_FULL_SIZE * (1+k)); //Going to the right next column
    }
    Toolkit.getDefaultToolkit().sync();
  }

  /***************************************************/

  public float [] getColumnsXCoordinate(){return this.columnsXCoordinate;}
  public float [] getColumnsYCoordinate(){return this.columnsYCoordinate;}
  public float getColumnWidth(){return this.columnWidth;}
  public float getColumnHeight(){return this.columnHeight;}
  public int getAddedRows(){return addedRows;}
  public void setHighlight(int col, boolean h){this.highlightColumns[col] = h;}
  public boolean isHighlighted(int col){return this.highlightColumns[col];}
  public static Card [][] getSlots(){return slots;}
}
