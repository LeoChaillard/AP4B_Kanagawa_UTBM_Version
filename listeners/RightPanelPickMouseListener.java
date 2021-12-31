/************************************************************************
 * AP4B Project - Fall semester 2021 - Kanagawa, UTBM-like version
 * Authors : Jules RAMOS - jules.ramos@utbm.fr, Malak FADILI - malak.fadili@utbm.fr, Alan GAUTHIER - alan.gauthier@utbm.fr and Léo CHAILLARD - leo.chaillard@utbm.fr
 * Creation date : December, 2021
 ************************************************************************/

package listeners;
import manager.*;
import view.*;

import java.awt.Cursor;
import java.awt.event.MouseListener;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;

/**
 * Class defining a mouse listener for
 * whenever the player clicks on a the pick up column JLabel.
 * Player will be notified if this action isn't possible.
 */
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
  public void mouseClicked(MouseEvent evt)
  {
    if(!this.game.isEndOfGame())
    {
      if(!this.game.isPickingUpColumn()) //if not already picking up a column
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

    /***************************************************/

    @Override
    public void mouseExited(MouseEvent evt) {}
    public void mouseEntered(MouseEvent evt){}
    public void mousePressed(MouseEvent evt){}
    public void mouseReleased(MouseEvent evt){}

}
