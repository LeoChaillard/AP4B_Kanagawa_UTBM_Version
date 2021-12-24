/************************************************************************
 * AP4B Project - Fall semester 2021 - Kanagawa, UTBM-like version
 * Authors : Jules RAMOS - jules.ramos@utbm.fr, Malak FADILI - malak.fadili@utbm.fr, Alan GAUTHIER - alan.gauthier@utbm.fr and Léo CHAILLARD - leo.chaillard@utbm.fr
 * Creation date : December, 2021
 ************************************************************************/

package view;

import java.awt.Graphics;
import java.util.*;

 public abstract class Shape{
   //Attributes
   private float width;
   private float height;
   private float scaleX = 1f;
   private float scaleY = 1f;
   private Direction dir;

   //Methods
   abstract void fill(Graphics g);

   /***************************************************/

   public void setDirection(Direction d){this.dir = d;}

   /***************************************************/

   public Direction getDirection(){return this.dir;}
   
   /***************************************************/

   public void setSize(float w,float h)
   {
     this.width = w;
     this.height = h;
   }

   /***************************************************/

   public void setSide(float s){this.setSize(s,s);}

   /***************************************************/

   public int getWidth(){return Math.round(this.width*this.scaleX);}

   /***************************************************/

   public int getHeight(){return Math.round(this.height*this.scaleY);}

   /***************************************************/

   public int getX() {return Math.round(this.dir.getX()); }

   /***************************************************/

   public int getY() {return Math.round(this.dir.getY()); }

   /***************************************************/

  public void setScale(float sX,float sY){this.scaleX = sX;this.scaleY = sY;}

 }
