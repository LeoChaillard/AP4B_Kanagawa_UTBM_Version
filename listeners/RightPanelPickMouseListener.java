package listeners;
import manager.*;
import view.*;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import java.awt.Cursor;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

public class RightPanelPickMouseListener implements MouseListener{
  //Attributes
  private Game game;

  //Constructor
  public RightPanelPickMouseListener(Game game)
  {
    this.game = game;
  }

  //Methods
  @Override
  public void mouseExited(MouseEvent evt) {}
  public void mouseEntered(MouseEvent evt){}
  public void mousePressed(MouseEvent evt){}
  public void mouseReleased(MouseEvent evt){}

    @Override
    public void mouseClicked(MouseEvent evt)
    {
      if(!this.game.isPickingUpColumn())
      {
        JOptionPane msg = new JOptionPane();
        msg.showMessageDialog( this.game.getWindow(), "Please click on one available column that you wish", "Pick one column!", JOptionPane.INFORMATION_MESSAGE);
        this.game.setPickingUpColumn(true);
      }
      else
      {
        JOptionPane msg = new JOptionPane();
        msg.showMessageDialog( this.game.getWindow(), "Can't pick another column during same turn!", "Can't do that", JOptionPane.WARNING_MESSAGE);
      }
    }

}
