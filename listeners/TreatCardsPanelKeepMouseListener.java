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
 * whenever the player clicks on the keep card in hand JLabel.
 * Player will be notified if this action isn't possible.
 */
public class TreatCardsPanelKeepMouseListener implements MouseListener{
  //Attributes
  private Game game;

  //Constructor
  public TreatCardsPanelKeepMouseListener(Game game)
  {
    this.game = game;
  }

  //Methods
  @Override
  public void mouseClicked(MouseEvent evt)
  {
    System.out.println("click on keepcard");
    if(this.game.getPlayers().get(this.game.getPlayerIndex()).keepCardInHand())//if card has been added to hand
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
      msg.showMessageDialog( this.game.getWindow(), "You don't have any bonus to allow you to keep a card in hand!", "Can't do that", JOptionPane.WARNING_MESSAGE);
    }
  }

  /***************************************************/
  
  @Override
  public void mouseExited(MouseEvent evt) {}
  public void mouseEntered(MouseEvent evt){}
  public void mousePressed(MouseEvent evt){}
  public void mouseReleased(MouseEvent evt){}

}
