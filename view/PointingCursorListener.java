package view;

import javax.swing.JFrame;

import java.awt.Cursor;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

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
  public void mouseClicked(MouseEvent evt) {}
  public void mousePressed(MouseEvent evt){}
  public void mouseReleased(MouseEvent evt){}

  @Override
  public void mouseEntered(MouseEvent evt)
  {
    this.window.setCursor(new Cursor(Cursor.HAND_CURSOR));
    this.window.repaint();
  }

  /***************************************************/
  
  public void mouseExited(MouseEvent evt)
  {
    this.window.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
    this.window.repaint();
  }
}
