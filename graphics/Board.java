/************************************************************************
 * AP4B Project - Fall semester 2021 - Kanagawa, UTBM-like version
 * Authors : Jules RAMOS - jules.ramos@utbm.fr, Malak FADILI - malak.fadili@utbm.fr, Alan GAUTHIER - alan.gauthier@utbm.fr Léo CHAILLARD - leo.chaillard@utbm.fr
 * Creation date : December, 2021
 ************************************************************************/

package graphics;

import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.Polygon;

import javax.swing.JPanel;

import java.util.*;


public class Board extends JPanel{
  //Attributes
  private static final float X_ELEMENTS = 4;
  private static final float Y_ELEMENTS = 3;
  private static final float ELEMENT_SIZE = 0.95f;

  //Constructor
  public Board() {}

  //Methods
  @Override
  public void paint(Graphics g)
  {
    super.paint(g);
    	this.setBackground(Color.WHITE);

    	//Getting size of the window
    	float width = getWidth();
    	float height = getHeight();

    	//Direction
    	Direction d = new Direction( ELEMENT_SIZE/2, ELEMENT_SIZE/2 + 2*ELEMENT_SIZE); //Origin position at the middle of the bottom left square
    	d.setScale(width/X_ELEMENTS,height/Y_ELEMENTS);

    	//Rectangles
    	Rectangle rec = new Rectangle();
    	rec.setDirection(d);
    	rec.setScale(width/X_ELEMENTS,height/Y_ELEMENTS);
    	rec.setSide(ELEMENT_SIZE);

    	int hiddenCard = 0;

    	for(int k=0;k<X_ELEMENTS;++k)
    	{
    	  	for(int j=0;j<Y_ELEMENTS;++j)
    		{
    			if(j==hiddenCard%3)
          {
    				g.setColor(Color.RED);
    				rec.fill(g);
          }
    			else
          {
    				g.setColor(Color.YELLOW);
    				rec.fill(g);
          }
    			d.up(1);
    		}
    		++hiddenCard;
    		d.resetMove();
    		d.right(k+1); //Going to the right next column
    	}


    Toolkit.getDefaultToolkit().sync();
  }

}
