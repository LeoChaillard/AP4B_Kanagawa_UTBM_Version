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
 * whenever the player clicks on a the pass JLabel.
 * Player will be notified if this action isn't possible.
 */
public class RightPanelPassMouseListener implements MouseListener{
  //Attributes
  private Game game;

  //Constructor
  public RightPanelPassMouseListener(Game game)
  {
    this.game = game;
  }

  //Methods
  @Override
  public void mouseClicked(MouseEvent evt)
  {
    if(!this.game.isEndOfGame())
    {
      if(!this.game.getPlayers().get(this.game.getPlayerIndex()).getTemporaryHand().isEmpty())//if cards in temporary hand, can't pass
      {
        JOptionPane msg = new JOptionPane();
        msg.showMessageDialog( this.game.getWindow(), "You still have cards in your temporary hand!", "Can't do that", JOptionPane.WARNING_MESSAGE);
      }
      else if(this.game.getWindow().getBoard().getRemainingColumns() == 1) //if last player and one column remaining, then can't pass
      {
        JOptionPane msg = new JOptionPane();
        msg.showMessageDialog( this.game.getWindow(), "You're the last player!", "Can't do that", JOptionPane.WARNING_MESSAGE);
      }
      else if(this.game.getWindow().getBoard().getAddedRows() == 3) //if all rows are already filled up, then can't pass
      {
        JOptionPane msg = new JOptionPane();
        msg.showMessageDialog( this.game.getWindow(), "All rows are already filled up!", "Can't do that", JOptionPane.WARNING_MESSAGE);
      }
      else if(!this.game.isPickingUpColumn() && this.game.getPlayers().get(this.game.getPlayerIndex()).getTemporaryHand().isEmpty())//can pass
      {
        this.game.passTurn();
        this.game.nextTurn();
      }
      else
      {
        JOptionPane msg = new JOptionPane();
        msg.showMessageDialog(this.game.getWindow(), "Can't pass while picking up a column!", "Can't do that", JOptionPane.WARNING_MESSAGE);
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
