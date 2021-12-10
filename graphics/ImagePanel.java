/************************************************************************
 * AP4B Project - Fall semester 2021 - Kanagawa, UTBM-like version
 * Authors : Jules RAMOS - jules.ramos@utbm.fr, Malak FADILI - malak.fadili@utbm.fr, Alan GAUTHIER - alan.gauthier@utbm.fr Léo CHAILLARD - leo.chaillard@utbm.fr
 * Creation date : December, 2021
 ************************************************************************/

package graphics;

import javax.swing.JComponent;
import java.awt.Image;
import java.awt.Graphics;

/**
 * This class enables us to paint on a JPcomponent.
 */
public class ImagePanel extends JComponent{
  //Attributes
  private Image image;

  //Constructor
  public ImagePanel(Image image)
  {
    this.image = image;
  }

  //Methods
  @Override
  public void paintComponent(Graphics g)
  {
    super.paintComponent(g);
    int h= this.getHeight();
    int w = this.getWidth();
    g.drawImage(image,(this.getWidth()-w)/2,(this.getHeight()-h)/2,w,h,this);
  }
}
