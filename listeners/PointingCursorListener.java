/************************************************************************
 * AP4B Project - Fall semester 2021 - Kanagawa, UTBM-like version
 * Authors : Jules RAMOS - jules.ramos@utbm.fr, Malak FADILI - malak.fadili@utbm.fr, Alan GAUTHIER - alan.gauthier@utbm.fr and Léo CHAILLARD - leo.chaillard@utbm.fr
 * Creation date : December, 2021
 ************************************************************************/

package listeners;

import java.awt.Cursor;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

import javax.swing.JFrame;

/**
 * Class defining a mouse listener for
 * whenever the player's mouse goes over
 * a clickable component.
 */
public class PointingCursorListener implements MouseListener{
  //Attributes
  private JFrame window;

  //Constructor
  public PointingCursorListener(JFrame window)
  {
    this.window = window;
  }

  //Methods
  @Override
  public void mouseEntered(MouseEvent evt)
  {
    this.window.setCursor(new Cursor(Cursor.HAND_CURSOR));
    this.window.repaint();
  }

  /***************************************************/

  @Override
  public void mouseExited(MouseEvent evt)
  {
    this.window.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    this.window.repaint();
  }

  /***************************************************/

  @Override
  public void mouseClicked(MouseEvent evt) {}
  public void mousePressed(MouseEvent evt){}
  public void mouseReleased(MouseEvent evt){}
}
