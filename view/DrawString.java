/************************************************************************
 * AP4B Project - Fall semester 2021 - Kanagawa, UTBM-like version
 * Authors : Jules RAMOS - jules.ramos@utbm.fr, Malak FADILI - malak.fadili@utbm.fr, Alan GAUTHIER - alan.gauthier@utbm.fr and Léo CHAILLARD - leo.chaillard@utbm.fr
 * Creation date : December, 2021
 ************************************************************************/

package view;

import java.awt.Graphics;

 public class DrawString extends Shape{

   //Methods
   @Override
   public void fill(Graphics g)
   {

   }

   public void draw(Graphics g, String myString)
   {
     int width = getWidth();
     int height = getHeight();

     int x = getX();
     int y = getY();

     g.drawString(myString,x,y);
   }
 }
