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
 * whenever the player clicks on the work on project JLabel.
 * Player will be notified if this action isn't possible.
 */
public class TreatCardsPanelProjectMouseListener implements MouseListener{
  //Attributes
  private Game game;

  //Constructor
  public TreatCardsPanelProjectMouseListener(Game game)
  {
    this.game = game;
  }

  //Methods
  @Override
  public void mouseClicked(MouseEvent evt)
  {
    System.out.println("click on project");
    if(this.game.getPlayers().get(this.game.getPlayerIndex()).workOnProject()) //if player has been able to work on his project
    {
      this.game.getWindow().repaint();
      this.game.getWindow().getInventory().getInventoryCardsTab().repaint();
      this.game.getWindow().getTreatCardsPane().setVisible(false);

      //When temporary hand is empty, going to next turn
      if(this.game.getPlayers().get(this.game.getPlayerIndex()).getTemporaryHand().isEmpty())
      {
        this.game.setPickingUpColumn(false);
        this.game.nextTurn();
      }
    }
    else
    {
      JOptionPane msg = new JOptionPane();
      msg.showMessageDialog( this.game.getWindow(), "You either are not skilled enough or you don't have available hours to draw this project's part!", "Can't do that", JOptionPane.WARNING_MESSAGE);
    }
  }

  /***************************************************/

  @Override
  public void mouseExited(MouseEvent evt) {}
  public void mouseEntered(MouseEvent evt){}
  public void mousePressed(MouseEvent evt){}
  public void mouseReleased(MouseEvent evt){}

}
