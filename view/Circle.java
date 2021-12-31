/************************************************************************
 * AP4B Project - Fall semester 2021 - Kanagawa, UTBM-like version
 * Authors : Jules RAMOS - jules.ramos@utbm.fr, Malak FADILI - malak.fadili@utbm.fr, Alan GAUTHIER - alan.gauthier@utbm.fr and Léo CHAILLARD - leo.chaillard@utbm.fr
 * Creation date : December, 2021
 ************************************************************************/

package view;
import java.awt.Graphics;

/**
 * Class derivated from Shape.
 * Can fill or draw a circle following Shape's Direction.
 */
 public class Circle extends Shape{
   //Methods
   @Override
   public void fill(Graphics g)
   {
     int width = getWidth();
     int height = getHeight();

     int x = getX();
     int y = getY();

     g.fillOval(x-width/2,y-height/2,width,height);
   }

   /***************************************************/

   public void draw(Graphics g)
   {
     int width = getWidth();
     int height = getHeight();

     int x = getX();
     int y = getY();

     g.drawOval(x-width/2,y-height/2,width,height);
   }

 }
