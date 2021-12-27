/************************************************************************
 * AP4B Project - Fall semester 2021 - Kanagawa, UTBM-like version
 * Authors : Jules RAMOS - jules.ramos@utbm.fr, Malak FADILI - malak.fadili@utbm.fr, Alan GAUTHIER - alan.gauthier@utbm.fr and Léo CHAILLARD - leo.chaillard@utbm.fr
 * Creation date : December, 2021
 ************************************************************************/

package view;
import javax.swing.ImageIcon;


import java.awt.Graphics;

 public class DrawImage extends Shape{


   public DrawImage()
   {
     
   }
   //Methods
   @Override
   public void fill(Graphics g)
   {

   }

   public void draw(Graphics g, ImageIcon icon)
   {
     int width = getWidth();
     int height = getHeight();

     int x = getX();
     int y = getY();

     g.drawImage(icon.getImage(),x-width/2, y-height/2,width,height,null);
   }

 }
