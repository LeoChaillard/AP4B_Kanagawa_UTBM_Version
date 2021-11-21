import java.awt.Graphics;

 public class Rectangle extends Shape{

   //Methods
   @Override
   public void fill(Graphics g)
   {
     int width = getWidth();
     int height = getHeight();

     int x = getX();
     int y = getY();

     g.fillRect(x-width/2,y-height/2,width,height);
   }
 }
