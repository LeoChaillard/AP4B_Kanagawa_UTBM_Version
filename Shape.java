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
